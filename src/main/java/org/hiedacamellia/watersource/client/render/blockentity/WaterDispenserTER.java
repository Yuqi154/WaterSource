package org.hiedacamellia.watersource.client.render.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import org.hiedacamellia.watersource.common.block.WaterDispenserBlock;
import org.hiedacamellia.watersource.common.block.entity.WaterDispenserBlockEntity;
import org.hiedacamellia.watersource.helper.RenderHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.entity.player.Player;
public class WaterDispenserTER implements BlockEntityRenderer<WaterDispenserBlockEntity> {
    public WaterDispenserTER(BlockEntityRendererProvider.Context context){

    }
    @Override
    public void render(WaterDispenserBlockEntity blockEntity, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        blockEntity.getCapability(ForgeCapabilities.FLUID_HANDLER).ifPresent(fluidHandler -> {
            if (!fluidHandler.getFluidInTank(0).isEmpty()){
                String text = fluidHandler.getFluidInTank(0).getAmount() + "mB/" + fluidHandler.getTankCapacity(0) + "mB";
                RenderHelper.renderFluidAmount(blockEntity, player, text, matrixStackIn, partialTicks, 1.5f);
            }
        });
    }
}
