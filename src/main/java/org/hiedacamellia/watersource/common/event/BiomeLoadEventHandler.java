package org.hiedacamellia.watersource.common.event;

import org.hiedacamellia.watersource.WaterSource;
import org.hiedacamellia.watersource.registry.ConfigRegistry;
import org.hiedacamellia.watersource.registry.WorldGenRegistry;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

//@Mod.EventBusSubscriber(modid = WaterSource.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
//public class BiomeLoadEventHandler {
//    @SubscribeEvent
//    public static void onBiomeLoading(BiomeE event) {
//        if (ConfigRegistry.ENABLE_PALM_TREE_GEN.get() && event.getClimate().temperature >= 0.15f && event.getCategory() == Biome.BiomeCategory.BEACH) {
//            event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WorldGenRegistry.PALM_TREE_PLACEMENT);
//        }
//    }
//}
