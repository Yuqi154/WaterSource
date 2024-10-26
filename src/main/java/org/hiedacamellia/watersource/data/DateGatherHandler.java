package org.hiedacamellia.watersource.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import org.hiedacamellia.watersource.WaterSource;
import org.hiedacamellia.watersource.data.provider.ModBlockTagsProvider;
import org.hiedacamellia.watersource.data.provider.ModFluidTagsProvider;
import org.hiedacamellia.watersource.data.provider.ModItemTagsProvider;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import org.hiedacamellia.watersource.registry.WorldGenRegistry;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = WaterSource.MODID)
public class DateGatherHandler {
    @SubscribeEvent
    public void onDateGen(GatherDataEvent event){
        if(event.includeServer()) {
            DataGenerator generator = event.getGenerator();
            PackOutput packOutput = generator.getPackOutput();
            CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
            ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
            ModBlockTagsProvider modBlockTagsProvider = new ModBlockTagsProvider(packOutput, lookupProvider, existingFileHelper);
            generator.addProvider(event.includeServer(),modBlockTagsProvider);
            generator.addProvider(event.includeServer(),new ModFluidTagsProvider(packOutput,lookupProvider , existingFileHelper));
            generator.addProvider(event.includeServer(),new ModItemTagsProvider(packOutput,lookupProvider ,modBlockTagsProvider.contentsGetter(), existingFileHelper));

        }
    }
}
