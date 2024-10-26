package org.hiedacamellia.watersource.common.item;

import org.hiedacamellia.watersource.registry.CreativeModeTabRegistry;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.BlockItem;


public class ModNormalBlockItem extends BlockItem {
    public ModNormalBlockItem(Block blockIn) {
        super(blockIn, new Properties());
    }

    public ModNormalBlockItem(Block blockIn, String name) {
        super(blockIn, new Properties());
    }

    public ModNormalBlockItem(Block blockIn, Properties properties) {
        super(blockIn, properties);

    }
    public ModNormalBlockItem(Block blockIn, Properties properties, String name) {
        super(blockIn, properties);

    }
}
