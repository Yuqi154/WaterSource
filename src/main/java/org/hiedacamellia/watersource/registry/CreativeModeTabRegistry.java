package org.hiedacamellia.watersource.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.hiedacamellia.watersource.helper.FluidHelper;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import static org.hiedacamellia.watersource.WaterSource.MODID;

public class CreativeModeTabRegistry {

    public static final DeferredRegister<CreativeModeTab> TABS=DeferredRegister.create(Registries.CREATIVE_MODE_TAB,MODID);

    public static final RegistryObject<CreativeModeTab> WATER_SOURCE_TAB=TABS.register("water_source",()->
            CreativeModeTab.builder().icon(() -> ItemRegistry.FLUID_BOTTLE.get().getDefaultInstance())
                    .title(Component.translatable("itemGroup.watersource_tab"))
                    .displayItems((parameters, tabData)->{
                        for(var item:BlockRegistry.MOD_BLOCKITEMS.getEntries()){
                            tabData.accept(item.get());
                        }
                        for(var item:ItemRegistry.MOD_ITEMS.getEntries()){
                            tabData.accept(item.get());
                        }
                    })
                    .build()
            );
}
