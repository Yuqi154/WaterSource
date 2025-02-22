package org.hiedacamellia.watersource.common.recipe;

import org.hiedacamellia.watersource.registry.RecipeTypesRegistry;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ModRecipeManager {
    public static WaterLevelAndEffectRecipe getWERecipeFromItem(Level level, ItemStack itemStack) {
        List<WaterLevelAndEffectRecipe> recipes = new ArrayList<>();
        for (WaterLevelAndEffectRecipe recipe : level.getRecipeManager().getAllRecipesFor(RecipeTypesRegistry.WATER_LEVEL_RECIPE.get())) {
            if (recipe.conform(itemStack)) {
                recipes.add(recipe);
            }
        }
        if (recipes.size() > 0){
            Collections.sort(recipes);
            return recipes.get(0);
        }
        return null;
    }

    public static ThirstRecipe getThirstRecipeFromItem(Level world, ItemStack itemStack) {
        return ThirstRecipe.getRecipeFromItem(world, itemStack);
    }
}
