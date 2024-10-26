package org.hiedacamellia.watersource.common.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.joml.Quaternionf;

public class SimpleAnimationBlockEntity extends ModNormalBlockEntity{
    float animTime = 0f;
    float scale = 0;
    Quaternionf quaternion = new Quaternionf(0,0,0,0);

    public float getAnimTime() {
        return animTime;
    }

    public float getScale() {
        return scale;
    }

    public Quaternionf getQuaternion() {
        return quaternion;
    }

    public void setQuaternion(Quaternionf quaternion) {
        this.quaternion = quaternion;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public void setAnimTime(float animTime) {
        this.animTime = animTime;
    }
    public SimpleAnimationBlockEntity(BlockEntityType<?> blockEntityType, BlockPos pos, BlockState state) {
        super(blockEntityType, pos, state);
    }
}
