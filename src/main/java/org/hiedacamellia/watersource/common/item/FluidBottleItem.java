package org.hiedacamellia.watersource.common.item;

import net.minecraftforge.common.capabilities.ForgeCapabilities;
import org.hiedacamellia.watersource.helper.FluidHelper;
import org.hiedacamellia.watersource.registry.FluidRegistry;
import org.hiedacamellia.watersource.registry.ItemRegistry;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;

import java.util.ArrayList;
import java.util.List;

public class FluidBottleItem extends DrinkContainerItem{
    public FluidBottleItem(Properties properties) {
        super(properties, 250);
    }
//    @Override
//    public void fillItemCategory(CreativeModeTab tab, NonNullList<ItemStack> items) {
//        List<Fluid> fluids = new ArrayList<>();
//        fluids.add(FluidRegistry.PURIFIED_WATER.get());
//        fluids.add(FluidRegistry.SOUL_WATER.get());
//        fluids.add(FluidRegistry.COCONUT_JUICE.get());
//        if (this.allowdedIn(tab) && this == ItemRegistry.FLUID_BOTTLE.get()) {
//            for (Fluid fluid : fluids) {
//                ItemStack itemStack = new ItemStack(ItemRegistry.FLUID_BOTTLE.get());
//                items.add(FluidHelper.fillContainer(itemStack, fluid));
//            }
//        }
//    }

    @Override
    public Component getName(ItemStack stack) {
        IFluidHandlerItem fluidHandlerItem =  stack.getCapability(ForgeCapabilities.FLUID_HANDLER_ITEM).orElse(null);
        FluidStack fluidStack = fluidHandlerItem.getFluidInTank(0);
        if (fluidStack.isEmpty()) return super.getName(stack);

        Component component = fluidStack.getDisplayName();
        return component.copy().append(Component.translatable("item.watersource.fluid_bottle"));
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        return new ItemStack(Items.GLASS_BOTTLE);
    }

}
