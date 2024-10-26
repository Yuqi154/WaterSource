package org.hiedacamellia.watersource.client;

import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import org.hiedacamellia.watersource.WaterSource;
import org.hiedacamellia.watersource.client.color.item.FluidBottleColor;
import org.hiedacamellia.watersource.client.color.item.WoodenCupColor;
import org.hiedacamellia.watersource.registry.ItemRegistry;
import net.minecraft.client.color.item.ItemColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = WaterSource.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ColorRegistry {
    public static final ItemColor CUP_ITEM = new WoodenCupColor();
    public static final ItemColor FLUID_BOTTLE_ITEM = new FluidBottleColor();

    @SubscribeEvent
    public static void registerColors(RegisterColorHandlersEvent.Item event) {
        event.getItemColors().register(CUP_ITEM, ItemRegistry.WOODEN_CUP_DRINK.get());
        event.getItemColors().register(FLUID_BOTTLE_ITEM, ItemRegistry.FLUID_BOTTLE.get());
    }
}
