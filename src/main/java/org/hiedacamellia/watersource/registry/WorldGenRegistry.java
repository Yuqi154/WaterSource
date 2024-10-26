package org.hiedacamellia.watersource.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import org.hiedacamellia.watersource.WaterSource;
import org.hiedacamellia.watersource.common.world.gen.feature.CoconutTreeFeature;
import org.hiedacamellia.watersource.common.world.gen.placement.PalmTreePlacement;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountOnEveryLayerPlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class WorldGenRegistry {
    public static final DeferredRegister<Feature<?>> MOD_FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, WaterSource.MODID);
    //Features
    public static final ResourceKey<Feature<?>> COCONUT_TREE_KEY = ResourceKey.create(Registries.FEATURE, new ResourceLocation(WaterSource.MODID,"palm_tree"));

    public static final RegistryObject<Feature<NoneFeatureConfiguration>> COCONUT_TREE = MOD_FEATURES.register("palm_tree", () -> new CoconutTreeFeature(NoneFeatureConfiguration.CODEC));
   // public static Holder<ConfiguredFeature<?, ?>> CON_COCONUT_TREE;

    //Placement Features
   // public static Holder<PlacedFeature> PALM_TREE_PLACEMENT;

    public static void RegistryConfiguredFeatures(BootstapContext<ConfiguredFeature<?, ?>> p_256637_){

       // FeatureUtils.register( p_256637_ , ResourceKey.create(Registries.CONFIGURED_FEATURE,new ResourceLocation(WaterSource.MODID,"palm_tree")),COCONUT_TREE.get());
        //PALM_TREE_PLACEMENT = PlacementUtils.register("palm_tree", CON_COCONUT_TREE, CountOnEveryLayerPlacement.of(1), BiomeFilter.biome());
    }
}
