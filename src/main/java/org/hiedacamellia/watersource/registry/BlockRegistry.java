package org.hiedacamellia.watersource.registry;

import net.minecraft.client.resources.model.Material;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import org.hiedacamellia.watersource.WaterSource;
import org.hiedacamellia.watersource.common.block.*;
import org.hiedacamellia.watersource.common.block.grower.CoconutTreeGrower;
import org.hiedacamellia.watersource.common.item.EverlastingStrainerBlockItem;
import org.hiedacamellia.watersource.common.item.ModNormalBlockItem;
import org.hiedacamellia.watersource.common.item.StrainerBlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class BlockRegistry{

    public static final DeferredRegister<Block> MOD_BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, WaterSource.MODID);
    public static final DeferredRegister<Item> MOD_BLOCKITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, WaterSource.MODID);
    public static final RegistryObject<Block> WOODEN_WATER_FILTER = MOD_BLOCKS.register("wooden_water_filter", () -> new WaterFilterBlock( Block.Properties.of().mapColor(MapColor.COLOR_BROWN).sound(SoundType.WOOD).strength(1.0F, 3.0f).noOcclusion()));
    public static final RegistryObject<Block> IRON_WATER_FILTER = MOD_BLOCKS.register("iron_water_filter", () -> new RedstoneWaterFilterBlock(Block.Properties.of().mapColor(MapColor.COLOR_GRAY).sound(SoundType.STONE).strength(2.2F, 3.0f).noOcclusion().requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> PALM_TREE_LOG = MOD_BLOCKS.register("palm_tree_log", () -> log(MapColor.WOOD, MapColor.WOOD));
    public static final RegistryObject<Block> PALM_TREE_HEAD = MOD_BLOCKS.register("palm_tree_head", () -> new PalmTreeHeadBlock(Block.Properties.of().mapColor(MapColor.COLOR_BROWN).sound(SoundType.WOOD).strength(2.0f, 3.0f).randomTicks()));
    public static final RegistryObject<Block> PALM_TREE_LEAF = MOD_BLOCKS.register("palm_tree_leaf", () -> new LeavesBlock(Block.Properties.of().strength(0.2F).randomTicks().sound(SoundType.CROP).requiresCorrectToolForDrops().noOcclusion()));//todo
    public static final RegistryObject<Block> BLOCK_NATURAL_COCONUT = MOD_BLOCKS.register("natural_coconut", () -> new NaturalCoconutBlock(Block.Properties.of().strength(0.4F).sound(SoundType.WOOD).randomTicks().noOcclusion()));
    public static final RegistryObject<Block> BLOCK_COCONUT = MOD_BLOCKS.register("coconut", () -> new CoconutBlock(Block.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(0.4F).sound(SoundType.WOOD).randomTicks().noOcclusion()));
    public static final RegistryObject<Block> BLOCK_PALM_TREE_SAPLING = MOD_BLOCKS.register("palm_tree_sapling", () -> new PalmTreeSaplingBlock(new CoconutTreeGrower(), Block.Properties.of().sound(SoundType.CROP).randomTicks().noCollission()));
    //strainers
    public static final RegistryObject<Block> PRIMITIVE_STRAINER = MOD_BLOCKS.register("primitive_strainer", () -> new StrainerBlock(Block.Properties.of().sound(SoundType.WOOL).noOcclusion().strength(0.6f)));
    public static final RegistryObject<Block> PAPER_STRAINER = MOD_BLOCKS.register("paper_strainer", () -> new StrainerBlock(Block.Properties.of().sound(SoundType.WOOL).noOcclusion().strength(0.4f)));
    public static final RegistryObject<Block> DIRTY_STRAINER = MOD_BLOCKS.register("dirty_strainer", () -> new StrainerBlock(Block.Properties.of().sound(SoundType.WOOL).noOcclusion().strength(0.6f)));
    public static final RegistryObject<Block> PAPER_SOUL_STRAINER = MOD_BLOCKS.register("paper_soul_strainer", () -> new StrainerBlock(Block.Properties.of().sound(SoundType.WOOL).noOcclusion().strength(0.4f)));
    public static final RegistryObject<Block> SOUL_STRAINER = MOD_BLOCKS.register("soul_strainer", () -> new StrainerBlock(Block.Properties.of().sound(SoundType.WOOL).noOcclusion().strength(0.6f)));
    public static final RegistryObject<Block> EVERLASTING_STRAINER = MOD_BLOCKS.register("everlasting_strainer", () -> new StrainerBlock(Block.Properties.of().sound(SoundType.WOOL).noOcclusion().strength(0.6f)));
    public static final RegistryObject<Block> EVERLASTING_SOUL_STRAINER = MOD_BLOCKS.register("everlasting_soul_strainer", () -> new StrainerBlock(Block.Properties.of().sound(SoundType.WOOL).noOcclusion().strength(0.6f)));

    public static final RegistryObject<Block> PALM_TREE_PLANKS = MOD_BLOCKS.register("palm_tree_planks", () -> new Block(Block.Properties.of().mapColor(MapColor.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> PALM_TREE_DOOR = MOD_BLOCKS.register("palm_tree_door", () -> new PalmTreeDoorBlock(Block.Properties.of().mapColor(MapColor.WOOD).strength(3.0F).sound(SoundType.WOOD).noOcclusion()));
    public static final RegistryObject<Block> PALM_TREE_TRAPDOOR = MOD_BLOCKS.register("palm_tree_trapdoor", () -> new PalmTreeTrapdoor(Block.Properties.of().mapColor(MapColor.WOOD).strength(3.0F).sound(SoundType.WOOD).noOcclusion()));
    public static final RegistryObject<Block> PALM_TREE_STAIRS = MOD_BLOCKS.register("palm_tree_stairs", () -> new StairBlock(() -> PALM_TREE_PLANKS.get().getStateDefinition().any(), Block.Properties.copy(PALM_TREE_PLANKS.get())));
    public static final RegistryObject<Block> STRIPPED_PALM_TREE_LOG = MOD_BLOCKS.register("stripped_palm_tree_log", () -> log(MapColor.WOOD, MapColor.WOOD));
    public static final RegistryObject<Block> PALM_TREE_BUTTON = MOD_BLOCKS.register("palm_tree_button", () -> new ModButtonBlock(Block.Properties.of().sound(SoundType.WOOD)));
    public static final RegistryObject<Block> PALM_TREE_SLAB = MOD_BLOCKS.register("palm_tree_slab", () -> new SlabBlock(Block.Properties.copy(PALM_TREE_PLANKS.get())));
    public static final RegistryObject<Block> PALM_TREE_FENCE = MOD_BLOCKS.register("palm_tree_fence", () -> new FenceBlock(Block.Properties.copy(PALM_TREE_PLANKS.get())));
    public static final RegistryObject<Block> PALM_TREE_FENCE_GATE = MOD_BLOCKS.register("palm_tree_fence_gate", () -> new FenceGateBlock(Block.Properties.copy(PALM_TREE_PLANKS.get()), WoodType.OAK));
    public static final RegistryObject<Block> PALM_TREE_PRESSURE_PLATE = MOD_BLOCKS.register("palm_tree_pressure_plate", () -> new ModPressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.copy(PALM_TREE_PLANKS.get())));
    public static final RegistryObject<Block> STONE_RAIN_COLLECTOR = MOD_BLOCKS.register("stone_rain_collector", () -> new RainCollectorBlock(Block.Properties.of().requiresCorrectToolForDrops().strength(3.0F, 3.0f).noOcclusion()));
    public static final RegistryObject<Block> WATER_DISPENSER = MOD_BLOCKS.register("water_dispenser", () -> new WaterDispenserBlock(Block.Properties.of().requiresCorrectToolForDrops().strength(3.0F, 3.0f).noOcclusion()));//todo fill properties
    public static final RegistryObject<Block> STONE_POT = MOD_BLOCKS.register("stone_pot", () -> new PotBlock(Block.Properties.of().requiresCorrectToolForDrops().strength(3.0F, 3.0f).noOcclusion()));

    public static final RegistryObject<Item> ITEM_NATURAL_COCONUT = registryNormalBlockItem(BLOCK_NATURAL_COCONUT);
    public static final RegistryObject<Item> ITEM_COCONUT = registryNormalBlockItem(BLOCK_COCONUT);
    public static final RegistryObject<Item> ITEM_PALM_TREE_LEAF = registryNormalBlockItem(PALM_TREE_LEAF);
    public static final RegistryObject<Item> ITEM_PALM_TREE_HEAD = registryNormalBlockItem(PALM_TREE_HEAD);
    public static final RegistryObject<Item> ITEM_WOODEN_WATER_FILTER = registryNormalBlockItem(WOODEN_WATER_FILTER);
    public static final RegistryObject<Item> ITEM_IRON_WATER_FILTER = registryNormalBlockItem(IRON_WATER_FILTER);
    public static final RegistryObject<Item> ITEM_PALM_TREE_LOG = registryNormalBlockItem(PALM_TREE_LOG);
    public static final RegistryObject<Item> ITEM_COCONUT_SAPLING = registryNormalBlockItem(BLOCK_PALM_TREE_SAPLING);
    public static final RegistryObject<Item> ITEM_PRIMITIVE_STRAINER = registryStrainerBlockItem(PRIMITIVE_STRAINER, 25);
    public static final RegistryObject<Item> ITEM_PAPER_STRAINER = registryStrainerBlockItem(PAPER_STRAINER, 16);
    public static final RegistryObject<Item> ITEM_SOUL_STRAINER = registryStrainerBlockItem(SOUL_STRAINER, 25);
    public static final RegistryObject<Item> ITEM_PAPER_SOUL_STRAINER = registryStrainerBlockItem(PAPER_SOUL_STRAINER, 16);
    public static final RegistryObject<Item> ITEM_DIRTY_STRAINER = registryStrainerBlockItem(DIRTY_STRAINER);
    public static final RegistryObject<Item> ITEM_EVERLASTING_STRAINER = registryEverlastingStrainerBlockItem(EVERLASTING_STRAINER);
    public static final RegistryObject<Item> ITEM_EVERLASTING_SOUL_STRAINER = registryEverlastingStrainerBlockItem(EVERLASTING_SOUL_STRAINER);
    public static final RegistryObject<Item> ITEM_PALM_TREE_PLANKS = registryNormalBlockItem(PALM_TREE_PLANKS);
    public static final RegistryObject<Item> ITEM_PALM_TREE_DOOR = registryNormalBlockItem(PALM_TREE_DOOR);
    public static final RegistryObject<Item> ITEM_PALM_TREE_TRAPDOOR = registryNormalBlockItem(PALM_TREE_TRAPDOOR);
    public static final RegistryObject<Item> ITEM_PALM_TREE_STAIRS = registryNormalBlockItem(PALM_TREE_STAIRS);
    public static final RegistryObject<Item> ITEM_STRIPPED_PALM_TREE_LOG = registryNormalBlockItem(STRIPPED_PALM_TREE_LOG);
    public static final RegistryObject<Item> ITEM_PALM_TREE_BUTTON = registryNormalBlockItem(PALM_TREE_BUTTON);
    public static final RegistryObject<Item> ITEM_PALM_TREE_SLAB = registryNormalBlockItem(PALM_TREE_SLAB);
    public static final RegistryObject<Item> ITEM_PALM_TREE_FENCE = registryNormalBlockItem(PALM_TREE_FENCE);
    public static final RegistryObject<Item> ITEM_PALM_TREE_FENCE_GATE = registryNormalBlockItem(PALM_TREE_FENCE_GATE);
    public static final RegistryObject<Item> ITEM_PALM_TREE_PRESSURE_PLATE = registryNormalBlockItem(PALM_TREE_PRESSURE_PLATE);
    public static final RegistryObject<Item> ITEM_STONE_RAIN_COLLECTOR = registryNormalBlockItem(STONE_RAIN_COLLECTOR);
    public static final RegistryObject<Item> ITEM_WATER_DISPENSER = registryNormalBlockItem(WATER_DISPENSER);
    public static final RegistryObject<Item> ITEM_STONE_POT= registryNormalBlockItem(STONE_POT);

    //Fluid
    public static RegistryObject<LiquidBlock> BLOCK_PURIFIED_WATER_FLUID = MOD_BLOCKS.register("purified_water_fluid", () -> {
        return new LiquidBlock(()->FluidRegistry.PURIFIED_WATER.get(), Block.Properties.of().noCollission().strength(100.0F));
    });
    public static RegistryObject<LiquidBlock> BLOCK_SOUL_WATER_FLUID = MOD_BLOCKS.register("soul_water_fluid", () -> {
        return new LiquidBlock(FluidRegistry.SOUL_WATER, Block.Properties.of().noCollission().strength(100.0F));
    });
    public static RegistryObject<LiquidBlock> BLOCK_COCONUT_JUICE_FLUID = MOD_BLOCKS.register("coconut_juice_fluid", () -> {
        return new LiquidBlock(FluidRegistry.COCONUT_JUICE, Block.Properties.of().noCollission().strength(100.0F));
    });

    //摘自原版
    private static RotatedPillarBlock log(MapColor p_285370_, MapColor p_285126_) {
        return new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor((p_152624_) -> {
            return p_152624_.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? p_285370_ : p_285126_;
        }).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava());
    }
    
    public static RegistryObject<Item> registryNormalBlockItem(RegistryObject<Block> block){
        return  MOD_BLOCKITEMS.register(block.getId().getPath(), () -> new ModNormalBlockItem(block.get()));
    }
    public static RegistryObject<Item> registryStrainerBlockItem(RegistryObject<Block> block, int maxDamage){
        return  MOD_BLOCKITEMS.register(block.getId().getPath(), () -> new StrainerBlockItem(block.get(), maxDamage));
    }
    public static RegistryObject<Item> registryStrainerBlockItem(RegistryObject<Block> block){
        return  MOD_BLOCKITEMS.register(block.getId().getPath(), () -> new StrainerBlockItem(block.get()));
    }
    public static RegistryObject<Item> registryEverlastingStrainerBlockItem(RegistryObject<Block> block){
        return  MOD_BLOCKITEMS.register(block.getId().getPath(), () -> new EverlastingStrainerBlockItem(block.get()));
    }
}

