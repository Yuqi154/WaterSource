package org.hiedacamellia.watersource.data;

import org.hiedacamellia.watersource.WaterSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.material.Fluid;

public class ModFluidTags {
    public static final TagKey<Fluid> DRINK = FluidTags.create(new ResourceLocation(WaterSource.MODID,"drink"));

}
