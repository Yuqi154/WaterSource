package org.hiedacamellia.watersource.common.item;

import org.hiedacamellia.watersource.registry.ItemRegistry;
import net.minecraft.world.item.ItemStack;

public class LeatherWaterBagItem extends DurableDrinkContainerItem{
    public LeatherWaterBagItem( Properties properties, int capacity) {
        super(properties, capacity);
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        return new ItemStack(ItemRegistry.LEATHER_WATER_BAG.get());
    }
}
