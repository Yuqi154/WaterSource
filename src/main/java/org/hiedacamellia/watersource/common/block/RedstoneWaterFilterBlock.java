package org.hiedacamellia.watersource.common.block;

import net.minecraft.world.level.SignalGetter;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import org.hiedacamellia.watersource.registry.BlockRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class RedstoneWaterFilterBlock extends WaterFilterBlock {
    public RedstoneWaterFilterBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean shouldCheckWeakPower(BlockState state, SignalGetter level, BlockPos pos, Direction side) {
        return true;
    }

    @Override
    public boolean hasAnalogOutputSignal(BlockState blockState) {
        return blockState.getValue(IS_UP);
    }

    @Override
    public int getAnalogOutputSignal(BlockState blockState, Level level, BlockPos blockPos) {
        //弱充能逻辑
        //* 有可用滤网时，+5点弱充能
        //* 当有液体时，+2点弱充能
        //* 当液体满时，额外+4点弱充能

        AtomicInteger power = new AtomicInteger();
        if (blockState.getValue(IS_UP)) {
            BlockEntity tile = level.getBlockEntity(blockPos);
            if (tile.getCapability(ForgeCapabilities.ITEM_HANDLER).map(data -> !data.getStackInSlot(0).isEmpty() && data.getStackInSlot(0).getItem() != BlockRegistry.ITEM_DIRTY_STRAINER.get()).orElse(false)) {
                power.addAndGet(5);
            }
            tile.getCapability(ForgeCapabilities.FLUID_HANDLER).ifPresent(data -> {
                if (!data.getFluidInTank(0).isEmpty()) {
                    power.addAndGet(2);
                    if (data.getFluidInTank(0).getAmount() == data.getTankCapacity(0)) {
                        power.addAndGet(2);

                    }
                }
            });
        } else {
            BlockEntity UpTile = level.getBlockEntity(blockPos.above());
            BlockEntity DownTile = level.getBlockEntity(blockPos);
            if (UpTile.getCapability(ForgeCapabilities.ITEM_HANDLER).map(data -> !data.getStackInSlot(0).isEmpty() && data.getStackInSlot(0).getItem() != BlockRegistry.ITEM_DIRTY_STRAINER.get()).orElse(false)) {
                power.addAndGet(5);
            }
            DownTile.getCapability(ForgeCapabilities.FLUID_HANDLER).ifPresent(data -> {
                if (!data.getFluidInTank(0).isEmpty()) {
                    power.addAndGet(2);
                    if (data.getFluidInTank(0).getAmount() == data.getTankCapacity(0)) {
                        power.addAndGet(2);
                    }
                }
            });
        }
        return power.get();
    }

    @Override
    public void appendHoverText(ItemStack p_49816_, @Nullable BlockGetter getter, List<Component> components, TooltipFlag tooltipFlag) {
        if (!Minecraft.getInstance().options.keyShift.isDown()) {//todo
            components.add(Component.translatable("watersource.info.redstone_water_filter").setStyle(Style.EMPTY.applyFormat(ChatFormatting.GRAY)));
        } else
            components.add(Component.translatable("watersource.info.press.sneak").setStyle(Style.EMPTY.applyFormat(ChatFormatting.GRAY).applyFormat(ChatFormatting.BOLD)));
    }
}
