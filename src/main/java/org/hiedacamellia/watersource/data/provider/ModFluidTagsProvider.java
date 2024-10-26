package org.hiedacamellia.watersource.data.provider;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.FluidTagsProvider;
import org.hiedacamellia.watersource.WaterSource;
import org.hiedacamellia.watersource.data.ModBlockTags;
import org.hiedacamellia.watersource.data.ModFluidTags;
import org.hiedacamellia.watersource.registry.FluidRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModFluidTagsProvider extends FluidTagsProvider {

    public ModFluidTagsProvider(PackOutput generator, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(generator, lookupProvider, WaterSource.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(ModFluidTags.DRINK).add(Fluids.WATER, FluidRegistry.COCONUT_JUICE.get(), FluidRegistry.PURIFIED_WATER.get(), FluidRegistry.SOUL_WATER.get());
    }

    @Override
    public String getName() {
        return "watersource_fluid_tags_provider";
    }
}
