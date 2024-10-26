package org.hiedacamellia.watersource.registry;

import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidType;
import org.hiedacamellia.watersource.WaterSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public final class FluidRegistry {
    public static final ResourceLocation STILL_FLUID_TEXTURE = new ResourceLocation(WaterSource.MODID, "block/fluid/water_still");
    public static final ResourceLocation FLOWING_FLUID_TEXTURE = new ResourceLocation(WaterSource.MODID, "block/fluid/water_flow");

    public static final DeferredRegister<Fluid> MOD_FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, WaterSource.MODID);
    public static final DeferredRegister<FluidType> MOD_FLUID_TYPES = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, WaterSource.MODID);

    public static RegistryObject<FlowingFluid> PURIFIED_WATER = registerFluid("purified_water", STILL_FLUID_TEXTURE,FLOWING_FLUID_TEXTURE,0xCC725F45,
            FluidType.Properties.create().temperature(27),p->p.block(BlockRegistry.BLOCK_PURIFIED_WATER_FLUID).slopeFindDistance(3).explosionResistance(100F));

    public static RegistryObject<FlowingFluid> SOUL_WATER = registerFluid("soul_water", STILL_FLUID_TEXTURE,FLOWING_FLUID_TEXTURE,0xCC3ABDFF,
            FluidType.Properties.create().temperature(27),p->p.block(BlockRegistry.BLOCK_SOUL_WATER_FLUID).slopeFindDistance(3).explosionResistance(100F));

    public static RegistryObject<FlowingFluid> COCONUT_JUICE = registerFluid("coconut_juice", STILL_FLUID_TEXTURE,FLOWING_FLUID_TEXTURE,0xCCEAE8E1,
            FluidType.Properties.create().temperature(27),p->p.block(BlockRegistry.BLOCK_COCONUT_JUICE_FLUID).slopeFindDistance(3).explosionResistance(100F));



    public static RegistryObject<FlowingFluid> registerFluid(String name, ResourceLocation still, ResourceLocation flowing, int color, FluidType.Properties properties, UnaryOperator<ForgeFlowingFluid.Properties> blockProperties) {
        RegistryObject<FluidType> GAS=MOD_FLUID_TYPES.register(name,()->new FluidType(properties) {
            @Override
            public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
                consumer.accept(new IClientFluidTypeExtensions() {
                    @Override
                    public int getTintColor() {
                        return color;
                    }

                    @Override
                    public ResourceLocation getStillTexture() {
                        return STILL_FLUID_TEXTURE;
                    }

                    @Override
                    public ResourceLocation getFlowingTexture() {
                        return FLOWING_FLUID_TEXTURE;
                    }
                });
            }


        });
        ReferenceSupplier<FlowingFluid> ls=new ReferenceSupplier<FlowingFluid>();
        return ls.set(MOD_FLUIDS.register(name, ()->new ForgeFlowingFluid.Flowing(
                blockProperties.apply(new ForgeFlowingFluid.Properties(GAS,ls,ls)))));

    }

    public static class ReferenceSupplier<T> implements Supplier<T> {
        Supplier<T> ref;

        public ReferenceSupplier() {
        }

        @Override
        public T get() {

            return ref != null ? ref.get() : null;
        }

        public <E extends Supplier<T>> E set(E sup) {
            ref = sup;
            return sup;
        }

    }
}
