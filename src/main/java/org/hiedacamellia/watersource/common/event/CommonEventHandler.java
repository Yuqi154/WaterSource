package org.hiedacamellia.watersource.common.event;

import net.minecraft.util.RandomSource;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.level.BlockEvent;
import org.hiedacamellia.watersource.WaterSource;
import org.hiedacamellia.watersource.common.capability.PlayerLastPosCapability;
import org.hiedacamellia.watersource.common.capability.WaterLevelCapability;
import org.hiedacamellia.watersource.common.network.DrinkWaterMessage;
import org.hiedacamellia.watersource.common.network.PlayerWaterLevelMessage;
import org.hiedacamellia.watersource.common.network.SimpleNetworkHandler;
import org.hiedacamellia.watersource.helper.FluidHelper;
import org.hiedacamellia.watersource.helper.WaterLevelUtil;
import org.hiedacamellia.watersource.registry.*;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.Difficulty;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;


import java.util.Random;

import static org.hiedacamellia.watersource.registry.ConfigRegistry.RESET_WATER_LEVEL_IN_DEATH;

@Mod.EventBusSubscriber(modid = WaterSource.MODID)
public class CommonEventHandler {
    static int tick = 0;

    @SubscribeEvent
    public static void addCap(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player && !(event.getObject() instanceof FakePlayer)) {
            event.addCapability(new ResourceLocation(WaterSource.MODID, "player_thirst_level"), new WaterLevelCapability.Provider());
            event.addCapability(new ResourceLocation(WaterSource.MODID, "player_last_position"), new PlayerLastPosCapability.Provider());
        }
    }

    @SubscribeEvent
    public static void onLivingEntityUseItemEventFinish(LivingEntityUseItemEvent.Finish event) {
        LivingEntity entity = event.getEntity();
        ItemStack stack = event.getItem();
        if (entity instanceof ServerPlayer player) {
            WaterLevelUtil.drink(player, stack);
            //SimpleNetworkHandler.CHANNEL.send(PacketDistributor.PLAYER.with(() -> player), new DrinkWaterMessage());
        }
    }

    @SubscribeEvent
    public static void onJump(LivingEvent.LivingJumpEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof ServerPlayer player) {
            if (WaterLevelUtil.canPlayerAddWaterExhaustionLevel(player)) {
                entity.getCapability(CapabilityRegistry.PLAYER_WATER_LEVEL).ifPresent(data -> {
                    if (entity.isSprinting()) {
                        data.addExhaustion(player, 0.24f);
                    } else data.addExhaustion(player, 0.14f);
                });
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerEventClone(PlayerEvent.Clone event) {
        boolean flag;
        flag = !(event.getEntity() instanceof FakePlayer) && event.getEntity() instanceof ServerPlayer;
        if (RESET_WATER_LEVEL_IN_DEATH.get()) {
            flag = flag && !event.isWasDeath();
        }
        if (flag && event.getEntity().getCapability(CapabilityRegistry.PLAYER_WATER_LEVEL).isPresent()) {
            event.getEntity().getCapability(CapabilityRegistry.PLAYER_WATER_LEVEL).ifPresent(date -> {
                event.getOriginal().getCapability(CapabilityRegistry.PLAYER_WATER_LEVEL).ifPresent(t -> {
                    date.setWaterLevel(t.getWaterLevel());
                    date.setWaterExhaustionLevel(t.getWaterExhaustionLevel());
                    date.setWaterSaturationLevel(t.getWaterSaturationLevel());
                });
            });
            event.getEntity().getCapability(CapabilityRegistry.PLAYER_WATER_LEVEL).ifPresent(t -> SimpleNetworkHandler.CHANNEL.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) event.getEntity()), new PlayerWaterLevelMessage(t.getWaterLevel(), t.getWaterSaturationLevel(), t.getWaterExhaustionLevel())));
        }
    }

    @SubscribeEvent
    public static void onPlayerLoggedInEvent(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayer player && !(event.getEntity() instanceof FakePlayer)) {
            event.getEntity().getCapability(CapabilityRegistry.PLAYER_WATER_LEVEL).ifPresent(t -> SimpleNetworkHandler.CHANNEL.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) event.getEntity()), new PlayerWaterLevelMessage(t.getWaterLevel(), t.getWaterSaturationLevel(), t.getWaterExhaustionLevel())));
            CriteriaTriggerRegistry.GUIDE_BOOK_TRIGGER.trigger(player);
        }
    }

    @SubscribeEvent
    public static void EntityJoinWorldEvent(EntityJoinLevelEvent event) {
        if (event.getEntity() instanceof ServerPlayer && !(event.getEntity() instanceof FakePlayer)) {
            event.getEntity().getCapability(CapabilityRegistry.PLAYER_WATER_LEVEL).ifPresent(t -> SimpleNetworkHandler.CHANNEL.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) event.getEntity()), new PlayerWaterLevelMessage(t.getWaterLevel(), t.getWaterSaturationLevel(), t.getWaterExhaustionLevel())));
        }
    }


    @SubscribeEvent
    public static void onPlayerTickEvent(TickEvent.PlayerTickEvent event) {
        tick++;
        tick %= 8000;
        Player player = event.player;
        Level level = player.level();
        if (!(player instanceof ServerPlayer)) return;
        if (WaterLevelUtil.canPlayerAddWaterExhaustionLevel(player)) {
            if (tick % 2 == 0) {
                player.getCapability(CapabilityRegistry.PLAYER_LAST_POSITION).ifPresent(data -> {
                    boolean lastOnGround = data.isLastOnGround();
                    double lastX = data.getLastX();
                    double lastY = data.getLastY();
                    double lastZ = data.getLastZ();
                    if (lastOnGround && player.onGround() || player.isInWater()) {
                        double x = Math.sqrt(Math.pow(lastX - player.getPosition(0f).x, 2) + Math.pow(lastY - player.getPosition(0f).y, 2) + Math.pow(lastZ - player.getPosition(0f).z, 2));
                        if (x < 5) {
                            player.getCapability(CapabilityRegistry.PLAYER_WATER_LEVEL).ifPresent(dataW -> {
                                if (player.isSprinting()) {
                                    dataW.addExhaustion(player, (float) (x / 15));
                                } else dataW.addExhaustion(player, (float) (x / 30));
                            });
                        }
                    }

                    if (player.onGround() || player.isInWater()) {
                        data.setLastX(player.getPosition(0f).x);
                        data.setLastY(player.getPosition(0f).y);
                        data.setLastZ(player.getPosition(0f).z);
                        data.setLastOnGround(true);
                    } else data.setLastOnGround(false);
                });
            }

            if (tick % 10 == 0) {
                //WaterRestoring effect
                MobEffectInstance effectInstance1 = player.getEffect(MobEffectRegistry.WATER_RESTORING.get());
                if (effectInstance1 != null) {
                    player.getCapability(CapabilityRegistry.PLAYER_WATER_LEVEL).ifPresent(data -> {
                        data.restoreWater(player, 1);
                    });
                }

                Biome biome = level.getBiome(BlockPos.containing(player.getPosition(0f).x,player.getPosition(0f).y,player.getPosition(0f).z)).value();
                if (level.getLightEmission(BlockPos.containing(player.getPosition(0f).x,player.getPosition(0f).y,player.getPosition(0f).z)) == 15 && level.getDayTime() < 11000 && level.getDayTime() > 450 && !level.isRainingAt(BlockPos.containing(player.getPosition(0f).x,player.getPosition(0f).y,player.getPosition(0f).z))) {
                    if (biome.getBaseTemperature() > 0.3) {
                        player.getCapability(CapabilityRegistry.PLAYER_WATER_LEVEL).ifPresent(data -> {
                            data.addExhaustion(player, 0.0075f);
                        });
                    }
                    if (biome.getBaseTemperature() > 0.9) {
                        player.getCapability(CapabilityRegistry.PLAYER_WATER_LEVEL).ifPresent(data -> {
                            data.addExhaustion(player, 0.0055f);
                        });
                    }
                }
                //}
                //Thirty State
                MobEffectInstance effectInstance = player.getEffect(MobEffectRegistry.THIRST.get());
                if (effectInstance != null) {
                    player.getCapability(CapabilityRegistry.PLAYER_WATER_LEVEL).ifPresent(data -> {
                        data.addExhaustion(player, 0.07f + 0.05f * effectInstance.getAmplifier());
                    });
                }
            }
        }
        //Punishment/Reward - 5s
        if (tick % 250 == 0 && !(player instanceof FakePlayer)) {
            player.getCapability(CapabilityRegistry.PLAYER_WATER_LEVEL).ifPresent(data -> {
                if (!player.isCreative()) {
                    data.punishment(player);
                    data.award(player);
                }
            });
        }
        //Restore water level in Peaceful difficulty mode - 3s
        if (tick % 150 == 0 && !(player instanceof FakePlayer)) {
            if (level.getDifficulty() == Difficulty.PEACEFUL) {
                player.getCapability(CapabilityRegistry.PLAYER_WATER_LEVEL).ifPresent(data -> {
                    data.restoreWater(player, 2);
                });
            }
        }
        //Update water between server and client - 30s
        if (tick % 1500 == 0 && !(player instanceof FakePlayer) && !level.isClientSide()) {
            player.getCapability(CapabilityRegistry.PLAYER_WATER_LEVEL).ifPresent(data -> {
                SimpleNetworkHandler.CHANNEL.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) player), new PlayerWaterLevelMessage(data.getWaterLevel(), data.getWaterSaturationLevel(), data.getWaterExhaustionLevel()));
            });
        }
    }

    @SubscribeEvent
    public static void onBlockBreakEvent(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        if (WaterLevelUtil.canPlayerAddWaterExhaustionLevel(player) && !player.level().isClientSide()) {
            player.getCapability(CapabilityRegistry.PLAYER_WATER_LEVEL).ifPresent(data -> data.addExhaustion(player, 0.005f));
        }
    }

    @SubscribeEvent
    public static void onEntityInteractSpecific(PlayerInteractEvent.EntityInteractSpecific event) {
        ItemStack stack = event.getItemStack();
        Player player = event.getEntity();
        if (event.getTarget() instanceof Cow && FluidHelper.isFluidBottleQualified(stack, FluidRegistry.COCONUT_JUICE.get())) {
            player.playSound(SoundEvents.COW_MILK, 1.0F, 1.0F);
            player.interactOn(event.getTarget(), event.getHand());
            player.awardStat(Stats.ITEM_USED.get(stack.getItem()));//todo
            stack.shrink(1);
            player.getInventory().add(new ItemStack(ItemRegistry.COCONUT_MILK_BOTTLE.get()));
        }
    }

    @SubscribeEvent
    public static void onPlayerRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        BlockState state = event.getLevel().getBlockState(event.getPos());
        ItemStack heldItem = event.getItemStack();
        Player player = event.getEntity();
        //stripe palm tree log
        if (heldItem.getItem() instanceof AxeItem && state.getBlock() == BlockRegistry.PALM_TREE_LOG.get()) {
            event.getLevel().playSound(player, event.getPos(), SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0F, 1.0F);
            if (!event.getLevel().isClientSide()) {
                event.getLevel().setBlock(event.getPos(), BlockRegistry.STRIPPED_PALM_TREE_LOG.get().defaultBlockState().setValue(RotatedPillarBlock.AXIS, state.getValue(RotatedPillarBlock.AXIS)), 3);
                heldItem.hurt(1, RandomSource.create(), (ServerPlayer) player);
            }
        }
        //drink water block
        if (heldItem.isEmpty() && event.getLevel().getFluidState(event.getHitVec().getBlockPos().offset(event.getFace().getNormal())).getType() == Fluids.WATER && player.getPose() == Pose.CROUCHING) {
            drinkWaterBlock(player);
        }
    }

    @SubscribeEvent
    public static void onPlayerRightClickEmpty(PlayerInteractEvent.RightClickEmpty event) {
        Player player = event.getEntity();
        Level level = event.getLevel();
        //drink water block
        HitResult hitresult = getPlayerPOVHitResult(level, player, ClipContext.Fluid.SOURCE_ONLY);
        if (player.getPose() == Pose.CROUCHING && hitresult.getType() == HitResult.Type.BLOCK && level.getFluidState(BlockPos.containing(hitresult.getLocation().x,hitresult.getLocation().y,hitresult.getLocation().z)).getType() == Fluids.WATER) {
            level.playSound(player, BlockPos.containing(player.getPosition(0f).x,player.getPosition(0f).y,player.getPosition(0f).z), SoundEvents.GENERIC_DRINK, SoundSource.PLAYERS, 0.4f, 1.0f);
            SimpleNetworkHandler.CHANNEL.sendToServer(new DrinkWaterMessage());
        }
    }

    @SubscribeEvent
    public static void onLivingAttacked(LivingAttackEvent event) {
        MobEffectInstance effectInstance = event.getEntity().getEffect(MobEffectRegistry.ACCOMPANYING_SOUL.get());
        if (effectInstance != null) {
            if (event.getEntity().level().dimension() == Level.NETHER) {
                event.getEntity().heal(event.getAmount() * (0.25f + (float) effectInstance.getAmplifier() * 0.06f));
            } else
                event.getEntity().heal(event.getAmount() * (0.2f + (float) effectInstance.getAmplifier() * 0.05f));
        }
    }

    @SubscribeEvent
    public static void getVanillaFurnaceFuelValue(FurnaceFuelBurnTimeEvent event) {
        if (event.getItemStack().getItem() == BlockRegistry.ITEM_DIRTY_STRAINER.get()) {
            event.setBurnTime(600);
        }
    }

    public static void drinkWaterBlock(Player player) {
        Level world = player.level();
        player.getCapability(CapabilityRegistry.PLAYER_WATER_LEVEL).ifPresent(data -> {
            data.addWaterLevel(player, 1);
            world.playSound(player, BlockPos.containing(player.getPosition(0f).x,player.getPosition(0f).y,player.getPosition(0f).z), SoundEvents.GENERIC_DRINK, SoundSource.PLAYERS, 0.4f, 1.0f);
            //0.05 poising，0.8 thirsty；
            if (!world.isClientSide()) {
                Random random = new Random();
                double d1 = random.nextDouble();
                double d2 = random.nextDouble();
                if (d1 <= 0.05D) player.addEffect(new MobEffectInstance(MobEffects.POISON, 300, 0));
                if (d2 <= 0.8D) player.addEffect(new MobEffectInstance(MobEffectRegistry.THIRST.get(), 900, 0));
            }
        });
    }

    //from Item.class
    protected static BlockHitResult getPlayerPOVHitResult(Level pLevel, Player pPlayer, ClipContext.Fluid pFluidMode) {
        float f = pPlayer.getXRot();
        float f1 = pPlayer.getYRot();
        Vec3 vec3 = pPlayer.getEyePosition();
        float f2 = Mth.cos(-f1 * 0.017453292F - 3.1415927F);
        float f3 = Mth.sin(-f1 * 0.017453292F - 3.1415927F);
        float f4 = -Mth.cos(-f * 0.017453292F);
        float f5 = Mth.sin(-f * 0.017453292F);
        float f6 = f3 * f4;
        float f7 = f2 * f4;
        double d0 = pPlayer.getBlockReach();
        Vec3 vec31 = vec3.add((double)f6 * d0, (double)f5 * d0, (double)f7 * d0);
        return pLevel.clip(new ClipContext(vec3, vec31, net.minecraft.world.level.ClipContext.Block.OUTLINE, pFluidMode, pPlayer));
    }
}
