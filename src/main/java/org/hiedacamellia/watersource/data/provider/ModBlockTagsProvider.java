package org.hiedacamellia.watersource.data.provider;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.BlockTagsProvider;
import org.hiedacamellia.watersource.WaterSource;
import org.hiedacamellia.watersource.data.ModBlockTags;
import org.hiedacamellia.watersource.data.ModItemTags;
import org.hiedacamellia.watersource.registry.BlockRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(PackOutput generator, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(generator, lookupProvider, WaterSource.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(ModBlockTags.COCONUT_SOIL).add(Blocks.SAND, Blocks.RED_SAND);
        this.tag(ModBlockTags.EVERLASTING_STRAINERS).add(BlockRegistry.EVERLASTING_SOUL_STRAINER.get(), BlockRegistry.EVERLASTING_STRAINER.get());
        this.tag(ModBlockTags.PALM_TREE_LOGS).add(BlockRegistry.PALM_TREE_LOG.get());
        this.tag(ModBlockTags.PURIFICATION_STRAINERS).add(BlockRegistry.PRIMITIVE_STRAINER.get(), BlockRegistry.PAPER_STRAINER.get(), BlockRegistry.EVERLASTING_STRAINER.get());
        this.tag(ModBlockTags.SOUL_STRAINERS).add(BlockRegistry.SOUL_STRAINER.get(), BlockRegistry.PAPER_SOUL_STRAINER.get(), BlockRegistry.EVERLASTING_SOUL_STRAINER.get());
        this.tag(ModBlockTags.STRAINERS).addTags(ModBlockTags.SOUL_STRAINERS, ModBlockTags.PURIFICATION_STRAINERS);
        this.tag(ModBlockTags.WATER_FILTERS).add(BlockRegistry.WOODEN_WATER_FILTER.get(), BlockRegistry.IRON_WATER_FILTER.get());
        this.tag(ModBlockTags.RAIN_COLLECTORS).add(BlockRegistry.STONE_RAIN_COLLECTOR.get());
        this.tag(ModBlockTags.PALM_BLOCKS).add(BlockRegistry.WOODEN_WATER_FILTER.get(), BlockRegistry.BLOCK_COCONUT.get(), BlockRegistry.PALM_TREE_LOG.get(), BlockRegistry.PALM_TREE_BUTTON.get(), BlockRegistry.PALM_TREE_DOOR.get(), BlockRegistry.PALM_TREE_FENCE.get(), BlockRegistry.PALM_TREE_HEAD.get(), BlockRegistry.PALM_TREE_FENCE_GATE.get(), BlockRegistry.PALM_TREE_PLANKS.get(), BlockRegistry.PALM_TREE_PRESSURE_PLATE.get(), BlockRegistry.PALM_TREE_STAIRS.get(), BlockRegistry.PALM_TREE_TRAPDOOR.get(), BlockRegistry.PALM_TREE_SLAB.get(), BlockRegistry.STRIPPED_PALM_TREE_LOG.get());


        this.tag(BlockTags.MINEABLE_WITH_AXE).addTag(ModBlockTags.PALM_BLOCKS);
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(BlockRegistry.STONE_RAIN_COLLECTOR.get(), BlockRegistry.IRON_WATER_FILTER.get());
        this.tag(BlockTags.LEAVES).add(BlockRegistry.PALM_TREE_LEAF.get());
        this.tag(BlockTags.PLANKS).add(BlockRegistry.PALM_TREE_PLANKS.get());
        this.tag(BlockTags.LOGS_THAT_BURN).add(BlockRegistry.PALM_TREE_LOG.get());
        this.tag(BlockTags.WOODEN_STAIRS).add(BlockRegistry.PALM_TREE_STAIRS.get());
        this.tag(BlockTags.WOODEN_TRAPDOORS).add(BlockRegistry.PALM_TREE_TRAPDOOR.get());
        this.tag(BlockTags.WOODEN_PRESSURE_PLATES).add(BlockRegistry.PALM_TREE_PRESSURE_PLATE.get());
        this.tag(BlockTags.WOODEN_DOORS).add(BlockRegistry.PALM_TREE_DOOR.get());
        this.tag(BlockTags.WOODEN_FENCES).add(BlockRegistry.PALM_TREE_FENCE.get());
        this.tag(BlockTags.FENCE_GATES).add(BlockRegistry.PALM_TREE_FENCE_GATE.get());
        this.tag(BlockTags.WOODEN_SLABS).add(BlockRegistry.PALM_TREE_SLAB.get());
    }


    @Override
    public String getName() {
        return "watersource_block_tags_provider";
    }



}
