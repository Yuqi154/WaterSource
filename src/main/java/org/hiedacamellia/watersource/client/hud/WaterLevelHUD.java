package org.hiedacamellia.watersource.client.hud;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.MultiBufferSource;
import org.hiedacamellia.watersource.WaterSource;
import org.hiedacamellia.watersource.common.capability.WaterLevelCapability;
import org.hiedacamellia.watersource.registry.ConfigRegistry;
import org.hiedacamellia.watersource.registry.MobEffectRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = WaterSource.MODID)
public class WaterLevelHUD extends GuiGraphics {
    protected static int tick = 0;
    public final static ResourceLocation OVERLAY_BAR = new ResourceLocation(WaterSource.MODID, "textures/gui/hud/icons.png");
    protected final static int WIDTH = 9;
    protected final static int HEIGHT = 9;

    protected Minecraft mc;

    public WaterLevelHUD(Minecraft p_283406_, MultiBufferSource.BufferSource p_282238_) {
        super(p_283406_, p_282238_);
        this.mc = p_283406_;
    }


    public void render(PoseStack poseStack,float partialTicks, int screenWidth, int screenHeight, WaterLevelCapability capData, double toughness) {
        poseStack.pushPose();
        RenderSystem.enableBlend();
        RenderSystem.setShaderTexture(0, OVERLAY_BAR);
        MobEffectInstance effectInstance = mc.player.getEffect(MobEffectRegistry.THIRST.get());
        int waterLevel = capData.getWaterLevel();
        int waterSaturationLevel = capData.getWaterSaturationLevel();
        float waterExhaustionLevel = capData.getWaterExhaustionLevel();
        if (hasAirBar(mc.player)){
            poseStack.translate(0,-9,0);
        }
        int OffsetX = screenWidth / 2 + 91;
        int OffsetY = screenHeight - 50;
        if (ModList.get().isLoaded("toughnessbar") && toughness != 0) {
            OffsetY -= 10;
        }
        if (ModList.get().isLoaded("elenaidodge2")) {
            OffsetY -= 10;
        }
        int OffsetY1 = OffsetY;
        int texU = 0;
        int texV = 0;
        int texU1 = texU;
        int texU2 = texU;
        if (effectInstance != null) {
            texU1 += 18;
            texU2 += 9;
        }
        for (int k6 = 0; k6 < 10; ++k6) {
            if (waterSaturationLevel <= 0.0F && tick % (waterLevel * 3 + 1) == 0) {
                OffsetY1 = OffsetY + (tick / 2 + k6 + waterLevel) % 3 - 1;
            }
            int OffsetX1 = OffsetX - k6 * 8 - 9;
            this.blit(OVERLAY_BAR, OffsetX1, OffsetY1, 36 + texU2, texV, WIDTH, HEIGHT);

            if (k6 * 2 + 1 < waterLevel) {
                this.blit(OVERLAY_BAR, OffsetX1, OffsetY1, texU1, texV, WIDTH, HEIGHT);
            }
            if (k6 * 2 + 1 == waterLevel) {
                this.blit(OVERLAY_BAR, OffsetX1, OffsetY1, texU1 + 9, texV, WIDTH, HEIGHT);
            }

            //Water Saturation Level
            if (ConfigRegistry.OPEN_WATER_SATURATION_LEVEL.get()) {
                if (k6 * 2 + 1 < waterSaturationLevel) {
                    this.blit(OVERLAY_BAR, OffsetX1, OffsetY1 - 1, texU1, texV + 9, 9, 9);
                    this.blit(OVERLAY_BAR, OffsetX1, OffsetY1 + 1, texU1 + 9, texV + 9, 9, 9);
                }
                if (k6 * 2 + 1 == waterSaturationLevel) {
                    this.blit(OVERLAY_BAR, OffsetX1, OffsetY1, texU1, texV + 9, 9, 9);
                }
            }
        }
        //test
        if (ConfigRegistry.IS_DEBUG_MODE.get()) {
            this.drawString(mc.font, String.valueOf(waterExhaustionLevel), OffsetX, OffsetY - 10, 16777215);
        }
        //mc.getTextureManager().bindForSetup(HUDHandler.DEFAULT);
        poseStack.popPose();
    }

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        tick++;
        tick %= 1200;
    }
    public static boolean hasAirBar(Player player){
        int l5 = player.getMaxAirSupply();
        int i6 = Math.min(player.getAirSupply(), l5);
        return player.isEyeInFluid(FluidTags.WATER) || i6 < l5;
    }
}
