package org.hiedacamellia.watersource.common.event;

import net.minecraftforge.data.event.GatherDataEvent;
import org.hiedacamellia.watersource.WaterSource;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = WaterSource.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGatherHandler {
    @SubscribeEvent
    public static void onDataGather(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        if (event.includeServer()) {
           // gen.addProvider(new RecipeProvider(gen));
/*            gen.addProvider(new ItemTagProvider(gen, new BlockTagProvider(gen, existingFileHelper), existingFileHelper));
            gen.addProvider(new BlockTagProvider(gen, existingFileHelper));
            gen.addProvider(new FluidTagProvider(gen));*/
        }
    }
}

