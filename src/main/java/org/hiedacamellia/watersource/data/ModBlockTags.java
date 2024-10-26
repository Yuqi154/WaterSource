package org.hiedacamellia.watersource.data;

import org.hiedacamellia.watersource.WaterSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ModBlockTags {
    
    public static final TagKey<Block> COCONUT_SOIL = BlockTags.create(new ResourceLocation(WaterSource.MODID,"coconut_soil"));
    public static final TagKey<Block> EVERLASTING_STRAINERS = BlockTags.create(new ResourceLocation(WaterSource.MODID,"everlasting_strainers"));
    public static final TagKey<Block> PALM_TREE_LOGS = BlockTags.create(new ResourceLocation(WaterSource.MODID, "palm_tree_logs"));
    public static final TagKey<Block> PURIFICATION_STRAINERS = BlockTags.create(new ResourceLocation(WaterSource.MODID, "purification_strainers"));
    public static final TagKey<Block> RAIN_COLLECTORS = BlockTags.create(new ResourceLocation(WaterSource.MODID, "rain_collectors"));
    public static final TagKey<Block> SOUL_STRAINERS = BlockTags.create(new ResourceLocation(WaterSource.MODID, "soul_strainers"));
    public static final TagKey<Block> STRAINERS = BlockTags.create(new ResourceLocation(WaterSource.MODID, "strainers"));
    public static final TagKey<Block> WATER_FILTERS = BlockTags.create(new ResourceLocation(WaterSource.MODID, "water_filters"));
    public static final TagKey<Block> PALM_BLOCKS = BlockTags.create(new ResourceLocation(WaterSource.MODID, "palm_blocks"));

}
