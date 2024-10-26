package org.hiedacamellia.watersource.common.block;

import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public class ModPressurePlateBlock extends PressurePlateBlock {
    public ModPressurePlateBlock(Sensitivity sensitivityIn, Properties propertiesIn) {
        super(sensitivityIn, propertiesIn, BlockSetType.OAK);
    }
}
