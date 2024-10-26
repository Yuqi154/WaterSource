package org.hiedacamellia.watersource.client.color.item;

import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import org.hiedacamellia.watersource.registry.FluidRegistry;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;

public class FluidBottleColor implements ItemColor {
    @Override
    public int getColor(ItemStack itemStack, int tintIndex) {
        IFluidHandlerItem  fluidHandlerItem = FluidUtil.getFluidHandler(itemStack).orElse(null);
        if (tintIndex == 1) {
            int color = 0;
            //int color = FluidUtil.getFluidHandler(itemStack).map(h -> h.getFluidInTank(0).getFluid().getFluidType().getAttributes().getColor()).orElse(-1);
            if (color == 0) {
                return -1;
            }
            if (fluidHandlerItem.getFluidInTank(0).getFluid() == FluidRegistry.PURIFIED_WATER.get()) return -1;
            return color;
        }
        else return -1;
    }
}
