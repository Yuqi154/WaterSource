package org.hiedacamellia.watersource.data.provider;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.level.block.Block;
import org.hiedacamellia.watersource.WaterSource;
import org.hiedacamellia.watersource.data.ModItemTags;
import org.hiedacamellia.watersource.registry.BlockRegistry;
import org.hiedacamellia.watersource.registry.ItemRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends ItemTagsProvider {

    public ModItemTagsProvider(PackOutput generator, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagsProvider.TagLookup<Block>> p_275322_, @Nullable ExistingFileHelper existingFileHelper) {
        super(generator, lookupProvider,p_275322_, WaterSource.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(ModItemTags.COCONUT_SOIL).add(Blocks.SAND.asItem(), Blocks.RED_SAND.asItem());
        this.tag(ModItemTags.EVERLASTING_STRAINERS).add(BlockRegistry.ITEM_EVERLASTING_SOUL_STRAINER.get(), BlockRegistry.ITEM_EVERLASTING_STRAINER.get());
        this.tag(ModItemTags.PALM_TREE_LOGS).add(BlockRegistry.ITEM_PALM_TREE_LOG.get());
        this.tag(ModItemTags.PURIFICATION_STRAINERS).add(BlockRegistry.ITEM_PRIMITIVE_STRAINER.get(), BlockRegistry.ITEM_PAPER_STRAINER.get(), BlockRegistry.ITEM_EVERLASTING_STRAINER.get());
        this.tag(ModItemTags.SOUL_STRAINERS).add(BlockRegistry.ITEM_SOUL_STRAINER.get(), BlockRegistry.ITEM_PAPER_SOUL_STRAINER.get(), BlockRegistry.ITEM_EVERLASTING_SOUL_STRAINER.get());
        this.tag(ModItemTags.STRAINERS).addTags(ModItemTags.SOUL_STRAINERS, ModItemTags.PURIFICATION_STRAINERS);
        this.tag(ModItemTags.WATER_FILTERS).add(BlockRegistry.ITEM_WOODEN_WATER_FILTER.get(), BlockRegistry.ITEM_IRON_WATER_FILTER.get());
        this.tag(ModItemTags.RAIN_COLLECTORS).add(BlockRegistry.ITEM_STONE_RAIN_COLLECTOR.get());
        this.tag(ModItemTags.PALM_ITEMS).add(BlockRegistry.ITEM_WOODEN_WATER_FILTER.get(), BlockRegistry.ITEM_COCONUT.get(), BlockRegistry.ITEM_PALM_TREE_LOG.get(), BlockRegistry.ITEM_PALM_TREE_BUTTON.get(), BlockRegistry.ITEM_PALM_TREE_DOOR.get(), BlockRegistry.ITEM_PALM_TREE_FENCE.get(), BlockRegistry.ITEM_PALM_TREE_HEAD.get(), BlockRegistry.ITEM_PALM_TREE_FENCE_GATE.get(), BlockRegistry.ITEM_PALM_TREE_PLANKS.get(), BlockRegistry.ITEM_PALM_TREE_PRESSURE_PLATE.get(), BlockRegistry.ITEM_PALM_TREE_STAIRS.get(), BlockRegistry.ITEM_PALM_TREE_TRAPDOOR.get(), BlockRegistry.ITEM_PALM_TREE_SLAB.get(), BlockRegistry.ITEM_STRIPPED_PALM_TREE_LOG.get());
        this.tag(ModItemTags.DRINKABLE_CONTAINERS).add(ItemRegistry.WOODEN_CUP_DRINK.get(), ItemRegistry.LEATHER_WATER_BAG.get(), ItemRegistry.FLUID_BOTTLE.get(), ItemRegistry.IRON_BOTTLE.get());

        this.tag(ItemTags.LEAVES).add(BlockRegistry.ITEM_PALM_TREE_LEAF.get());
        this.tag(ItemTags.PLANKS).add(BlockRegistry.ITEM_PALM_TREE_PLANKS.get());
        this.tag(ItemTags.LOGS_THAT_BURN).add(BlockRegistry.ITEM_PALM_TREE_LOG.get());
        this.tag(ItemTags.WOODEN_STAIRS).add(BlockRegistry.ITEM_PALM_TREE_STAIRS.get());
        this.tag(ItemTags.WOODEN_TRAPDOORS).add(BlockRegistry.ITEM_PALM_TREE_TRAPDOOR.get());
        this.tag(ItemTags.WOODEN_PRESSURE_PLATES).add(BlockRegistry.ITEM_PALM_TREE_PRESSURE_PLATE.get());
        this.tag(ItemTags.WOODEN_DOORS).add(BlockRegistry.ITEM_PALM_TREE_DOOR.get());
        this.tag(ItemTags.WOODEN_FENCES).add(BlockRegistry.ITEM_PALM_TREE_FENCE.get());
        this.tag(ItemTags.WOODEN_FENCES).add(BlockRegistry.ITEM_PALM_TREE_FENCE_GATE.get());
        this.tag(ItemTags.WOODEN_SLABS).add(BlockRegistry.ITEM_PALM_TREE_SLAB.get());
    }

    @Override
    public String getName() {
        return "watersource_item_tags_provider";
    }
}
