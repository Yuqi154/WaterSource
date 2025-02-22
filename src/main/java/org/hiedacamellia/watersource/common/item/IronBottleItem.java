package org.hiedacamellia.watersource.common.item;

import org.hiedacamellia.watersource.registry.ItemRegistry;
import net.minecraft.world.item.ItemStack;

public class IronBottleItem extends DurableDrinkContainerItem{
    public IronBottleItem(Properties properties, int capacity) {
        super(properties, capacity);
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        return new ItemStack(ItemRegistry.IRON_BOTTLE.get());
    }
}
