package org.hiedacamellia.watersource.common.block;

import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.state.properties.BlockSetType;

import java.util.Properties;

public class ModButtonBlock extends ButtonBlock {
    public ModButtonBlock(Properties properties) {
        super(properties, BlockSetType.OAK, 20, false);
    }
}
