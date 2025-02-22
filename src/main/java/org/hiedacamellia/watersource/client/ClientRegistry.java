package org.hiedacamellia.watersource.client;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterClientTooltipComponentFactoriesEvent;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.hiedacamellia.watersource.WaterSource;
import org.hiedacamellia.watersource.client.tooltip.component.WaterTooltipRenderer;
import org.hiedacamellia.watersource.client.tooltip.component.WaterTooltipComponent;
import org.hiedacamellia.watersource.registry.BlockRegistry;
import org.hiedacamellia.watersource.registry.FluidRegistry;
import org.hiedacamellia.watersource.registry.ItemRegistry;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;

@Mod.EventBusSubscriber(modid = WaterSource.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientRegistry {
    public static void init() {
        registerRenderType();
//        registerTooltipComponent();
        ItemProperties.register(ItemRegistry.FLUID_BOTTLE.get(),
                new ResourceLocation(WaterSource.MODID, "fluid_bottle"),
                (itemStack, level, livingEntity, value) -> {
                    IFluidHandlerItem fluidHandlerItem = itemStack.getCapability(ForgeCapabilities.FLUID_HANDLER_ITEM).orElse(null);
                    FluidStack fluidStack = fluidHandlerItem.getFluidInTank(0);
                    if (fluidStack.getFluid() == FluidRegistry.PURIFIED_WATER.get()) {
                        return 1.0f;
                    }
                    return 0.0f;
                });
    }
    public static void registerRenderType() {
        registerCutoutType(BlockRegistry.WOODEN_WATER_FILTER.get());
        registerCutoutType(BlockRegistry.IRON_WATER_FILTER.get());
        registerCutoutType(BlockRegistry.PALM_TREE_HEAD.get());
        registerCutoutType(BlockRegistry.BLOCK_COCONUT.get());
        registerCutoutType(BlockRegistry.PALM_TREE_LEAF.get());
        registerCutoutType(BlockRegistry.BLOCK_PALM_TREE_SAPLING.get());
        registerCutoutType(BlockRegistry.PRIMITIVE_STRAINER.get());
        registerCutoutType(BlockRegistry.BLOCK_NATURAL_COCONUT.get());
        registerCutoutType(BlockRegistry.PALM_TREE_DOOR.get());
        registerCutoutType(BlockRegistry.PALM_TREE_TRAPDOOR.get());
        registerCutoutType(BlockRegistry.STONE_RAIN_COLLECTOR.get());
        registerCutoutType(BlockRegistry.STONE_POT.get());
        registerCutoutType(BlockRegistry.WATER_DISPENSER.get());

//        registerTranslucentMovingType(BlockRegistry.BLOCK_PURIFIED_WATER_FLUID.get());
//        registerTranslucentMovingType(BlockRegistry.BLOCK_COCONUT_JUICE_FLUID.get());
//        registerTranslucentMovingType(BlockRegistry.BLOCK_SOUL_WATER_FLUID.get());
    }

    @SubscribeEvent
    public static void registerClientTooltipComponent(RegisterClientTooltipComponentFactoriesEvent event){
        event.register(WaterTooltipComponent.class, WaterTooltipRenderer::new);
    }
//    public static void registerTooltipComponent() {
//        MinecraftForge.registerTooltipComponentFactory(WaterTooltipComponent.class, WaterTooltipRenderer::new);
//    }

    private static void registerCutoutType(Block block) {
        ItemBlockRenderTypes.setRenderLayer(block, RenderType.cutout());
    }

    private static void registerTranslucentMovingType(Block block) {
        ItemBlockRenderTypes.setRenderLayer(block, RenderType.translucentMovingBlock());
    }

    private static void registerTranslucentMovingType(Fluid fluid) {
        ItemBlockRenderTypes.setRenderLayer(fluid, RenderType.translucent());
    }

    private static void registerCutoutMippedType(Block block) {
        ItemBlockRenderTypes.setRenderLayer(block, RenderType.cutoutMipped());
    }

}
