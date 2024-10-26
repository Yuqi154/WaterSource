package org.hiedacamellia.watersource.common.block.entity;

import net.minecraftforge.common.capabilities.ForgeCapabilities;
import org.hiedacamellia.watersource.registry.BlockEntityRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class StrainerBlockEntity extends ModNormalBlockEntity {
    LazyOptional<ItemStackHandler> strainerHandler = LazyOptional.of(this::createStrainerItemStackHandler);

    public StrainerBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.STRAINER_TILE.get(), pos, state);

    }

    public void setStrainer(ItemStack strainer) {
        strainerHandler.ifPresent(data -> {
            data.setStackInSlot(0, strainer);
        });
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        this.strainerHandler.ifPresent(stackHandler -> {
            CompoundTag compound = ((INBTSerializable<CompoundTag>) stackHandler).serializeNBT();
            tag.put("strainer", compound);
        });
        super.saveAdditional(tag);
    }


    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        CompoundTag strainerTag = tag.getCompound("strainer");
        this.strainerHandler.ifPresent(stackHandler -> ((INBTSerializable<CompoundTag>) stackHandler).deserializeNBT(strainerTag));
    }


    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return strainerHandler.cast();
        }
        return super.getCapability(cap, side);
    }


    private ItemStackHandler createStrainerItemStackHandler() {
        return new ItemStackHandler(1) {
            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
                clientSync();
            }

            @Override
            public boolean isItemValid(int slot, ItemStack stack) {
                return true;
            }

            @Override
            protected int getStackLimit(int slot, @Nonnull ItemStack stack) {
                return 1;
            }
        };
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        strainerHandler.invalidate();
    }

    @Override
    public void reviveCaps() {
        super.reviveCaps();
        strainerHandler = LazyOptional.of(this::createStrainerItemStackHandler);
    }

}
