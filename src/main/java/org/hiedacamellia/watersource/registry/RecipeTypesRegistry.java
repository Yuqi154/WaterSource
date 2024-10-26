package org.hiedacamellia.watersource.registry;

import net.minecraftforge.registries.ForgeRegistries;
import org.hiedacamellia.watersource.WaterSource;
import org.hiedacamellia.watersource.common.recipe.ThirstRecipe;
import org.hiedacamellia.watersource.common.recipe.WaterFilterRecipe;
import org.hiedacamellia.watersource.common.recipe.WaterLevelAndEffectRecipe;
import org.hiedacamellia.watersource.common.recipe.type.StrainerFilterRecipe;
import net.minecraft.core.Registry;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class RecipeTypesRegistry {
    public static final DeferredRegister<RecipeType<?>> MOD_RECIPE_TYPES = DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, WaterSource.MODID);
    static {
        ThirstRecipe.TYPE = MOD_RECIPE_TYPES.register( "thirst", () -> new RecipeType<>(){});
    }

    public static final RegistryObject<RecipeType<WaterLevelAndEffectRecipe>> WATER_LEVEL_RECIPE = MOD_RECIPE_TYPES.register("water_level", () -> new RecipeType<>(){});

    public static final RegistryObject<RecipeType<StrainerFilterRecipe>> STRAINER_FILTER_RECIPE_RECIPE = MOD_RECIPE_TYPES.register("strainer_filter", () -> new RecipeType<>(){});
    public static final RegistryObject<RecipeType<WaterFilterRecipe>> WATER_FILTER_RECIPE = MOD_RECIPE_TYPES.register("water_filter", () -> new RecipeType<>(){});

}
