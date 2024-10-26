package org.hiedacamellia.watersource.helper;

import org.hiedacamellia.watersource.common.network.DrinkWaterMessage;
import org.hiedacamellia.watersource.common.network.SimpleNetworkHandler;
import org.hiedacamellia.watersource.common.recipe.IThirstRecipe;
import org.hiedacamellia.watersource.common.recipe.ModRecipeManager;
import org.hiedacamellia.watersource.common.recipe.WaterLevelAndEffectRecipe;
import org.hiedacamellia.watersource.registry.CapabilityRegistry;
import org.hiedacamellia.watersource.registry.MobEffectRegistry;
import org.hiedacamellia.watersource.registry.EnchantmentRegistry;
import org.hiedacamellia.watersource.registry.ItemRegistry;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.IFluidHandler;

import java.util.Random;

import static net.minecraft.world.Difficulty.PEACEFUL;


public class WaterLevelUtil {
    public static void drink(Player player, ItemStack stack) {
        Level level = player.level();
        Random rand = new Random();

        WaterLevelAndEffectRecipe wRecipe = ModRecipeManager.getWERecipeFromItem(level, stack);
        IThirstRecipe tRecipe = ModRecipeManager.getThirstRecipeFromItem(level, stack);
        if (wRecipe != null) {
            player.getCapability(CapabilityRegistry.PLAYER_WATER_LEVEL).ifPresent(data -> {
                if (player.getRemainingFireTicks() > 0 && wRecipe.getWaterLevel() >= 4) {//extinguish player
                    if (!player.level().isClientSide()) {
                        data.addWaterLevel(player, wRecipe.getWaterLevel() - 4);
                        if (tRecipe == null) {
                            data.addWaterSaturationLevel(player, Math.max(wRecipe.getWaterSaturationLevel() - 4, 0));
                        }
                    }
                    player.playSound(SoundEvents.FIRE_EXTINGUISH, 1.0F, 1.0F);
                    player.clearFire();
                } else {//add water level
                    data.addWaterLevel(player, wRecipe.getWaterLevel());
                    if (tRecipe == null) {
                        data.addWaterSaturationLevel(player, wRecipe.getWaterSaturationLevel());
                    }
                }
            });
            for (MobEffectInstance mobEffectInstance : wRecipe.getMobEffectInstances()) {
                player.addEffect(mobEffectInstance);
            }
        }
        if (tRecipe != null) {
            if (rand.nextDouble() < tRecipe.getProbability()) {
                player.addEffect(new MobEffectInstance(MobEffectRegistry.THIRST.get(), tRecipe.getDuration(), tRecipe.getAmplifier()));
            }
        }
    }

    public static void drink(Player player, Fluid fluid) {
        ItemStack stack = new ItemStack(ItemRegistry.FLUID_BOTTLE.get());
        IFluidHandler fluidHandler = FluidUtil.getFluidHandler(stack).orElse(null);
        fluidHandler.fill(new FluidStack(fluid, 250), IFluidHandler.FluidAction.EXECUTE);
        WaterLevelUtil.drink(player, stack);
    }

    public static boolean canPlayerAddWaterExhaustionLevel(Player player) {
        return !(player instanceof FakePlayer) && !player.isCreative() && !player.isSpectator() && player.getCapability(CapabilityRegistry.PLAYER_WATER_LEVEL) != null && player.level().getDifficulty() != PEACEFUL;
    }

    public static float getMoisturizingRate(Player player) {
        int moisturizingLevel = 0;
        for (ItemStack stack : player.getArmorSlots()) {
            moisturizingLevel += EnchantmentHelper.getItemEnchantmentLevel(EnchantmentRegistry.MOISTURIZING.get(), stack);
        }
        float moisturizingRate = 1.0f;
        if (moisturizingLevel == 1) moisturizingRate = 0.7f;
        if (moisturizingLevel >= 2) moisturizingRate = 0.5f;
        return moisturizingRate;
    }
}
