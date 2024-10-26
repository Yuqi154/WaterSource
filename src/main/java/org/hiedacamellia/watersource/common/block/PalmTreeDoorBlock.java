package org.hiedacamellia.watersource.common.block;

import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public class PalmTreeDoorBlock extends DoorBlock {
    public PalmTreeDoorBlock(Properties builder) {
        super(builder, BlockSetType.OAK);
    }
}
