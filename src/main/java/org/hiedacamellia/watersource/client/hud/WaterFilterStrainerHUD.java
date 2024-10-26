package org.hiedacamellia.watersource.client.hud;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import org.hiedacamellia.watersource.WaterSource;
import org.hiedacamellia.watersource.common.block.entity.WaterFilterDownBlockEntity;
import org.hiedacamellia.watersource.common.block.entity.WaterFilterUpBlockEntity;
import org.hiedacamellia.watersource.registry.BlockRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = WaterSource.MODID)
public class WaterFilterStrainerHUD extends GuiGraphics {
    int animeTime = 0;
    protected Minecraft mc;

    public WaterFilterStrainerHUD(Minecraft p_283406_, MultiBufferSource.BufferSource p_282238_) {
        super(p_283406_, p_282238_);
        this.mc = p_283406_;
    }


    public void render(PoseStack pose, BlockPos pos) {
        BlockEntity tile = mc.level.getBlockEntity(pos);
        ProfilerFiller profiler = mc.getProfiler();
        if (tile == null) {
            animeTime -= animeTime > 0 ? 1 : 0;
        }
        else if (tile instanceof WaterFilterUpBlockEntity) {
            profiler.push("waterFilterStrainer");
            renderWaterFilterStrainer(pose, tile);
            profiler.pop();
        }
        else if (tile instanceof WaterFilterDownBlockEntity) {
            profiler.push("waterFilterStrainer");
            renderWaterFilterStrainer(pose, mc.level.getBlockEntity(pos.above()));
            profiler.pop();
        }
    }

    private void renderWaterFilterStrainer(PoseStack pose, BlockEntity tile) {
        if (tile == null) return;
        ItemStack stack = tile.getCapability(ForgeCapabilities.ITEM_HANDLER).map(data -> data.getStackInSlot(0)).orElse(ItemStack.EMPTY);
        if (stack.isEmpty()) return;

        Minecraft mc = Minecraft.getInstance();
        Font font = mc.font;

        float scale = 1.3f;
        int x = 10;
        int y = mc.getWindow().getGuiScaledHeight() / 2;

        //render item
        pose.pushPose();
        RenderSystem.enableBlend();
        this.renderItem(stack, x, y);


        //render text

        String text = I18n.get("watersource.misc.damage") + (stack.getMaxDamage() - stack.getDamageValue()) + "/" + stack.getMaxDamage();
        if (stack.getItem() == BlockRegistry.ITEM_DIRTY_STRAINER.get()) text = I18n.get("watersource.misc.dirty_strainer");
        this.drawString(font, text, x + 17, y + 5, 0xFFFFFF);
        pose.popPose();
    }
}
