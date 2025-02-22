package org.hiedacamellia.watersource.common.block;


import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import org.hiedacamellia.watersource.WaterSource;
import org.hiedacamellia.watersource.data.ModBlockTags;
import org.hiedacamellia.watersource.registry.BlockRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.registries.ForgeRegistries;


import java.util.Random;

import static net.minecraft.world.level.block.state.properties.BlockStateProperties.HORIZONTAL_FACING;


public class PalmTreeHeadBlock extends Block implements IPlantable, BonemealableBlock {
    public PalmTreeHeadBlock(Properties properties) {
        super(properties);
    }


    public boolean canGrow(ServerLevel level, BlockPos pos, BlockState state, boolean isClient) {
        if (ForgeRegistries.BLOCKS.tags().getTag(ModBlockTags.PALM_TREE_LOGS).contains(level.getBlockState(pos.below()).getBlock())) {
            return true;
        }
        return false;
    }



    public void grow(ServerLevel level, RandomSource rand, BlockPos pos, BlockState state) {
        int a = rand.nextInt(4);
        Direction direction = Direction.from2DDataValue(a);
        if (level.getBlockState(pos.offset(direction.getNormal())).isAir()) {
            level.setBlock(pos.offset(direction.getNormal()), BlockRegistry.BLOCK_NATURAL_COCONUT.get().defaultBlockState().setValue(NaturalCoconutBlock.AGE, 0).setValue(HORIZONTAL_FACING, direction.getOpposite()), 3);
        }
    }


    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource rand) {
        if (canGrow(level, pos, state, false)) {
            if (!level.isAreaLoaded(pos, 1))
                return; // Forge: prevent loading unloaded chunks when checking neighbor's light
            if (net.minecraftforge.common.ForgeHooks.onCropsGrowPre(level, pos, state, rand.nextInt(26) == 0)) {
                grow(level, rand, pos, state);
                net.minecraftforge.common.ForgeHooks.onCropsGrowPost(level, pos, state);
            }
        }
    }


    @Override
    public BlockState getPlant(BlockGetter world, BlockPos pos) {
        return null;
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader levelReader, BlockPos blockPos, BlockState blockState, boolean b) {
        return true;
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel serverLevel, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        grow(serverLevel, randomSource, blockPos, blockState);
    }
}
