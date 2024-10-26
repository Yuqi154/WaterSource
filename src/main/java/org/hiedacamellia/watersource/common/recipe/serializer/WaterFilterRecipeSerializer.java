package org.hiedacamellia.watersource.common.recipe.serializer;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.hiedacamellia.watersource.common.recipe.WaterFilterRecipe;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.registries.ForgeRegistries;

public class WaterFilterRecipeSerializer<T extends WaterFilterRecipe>  implements RecipeSerializer<T> {
    private final IFactory<T> factory;
    private final Fluid inputFluid, outputFluid;

    public WaterFilterRecipeSerializer(IFactory<T> factory, Fluid inputFluid, Fluid outputFluid) {
        this.factory = factory;
        this.inputFluid = inputFluid;
        this.outputFluid = outputFluid;
    }

    @Override
    public T fromJson(ResourceLocation recipeId, JsonObject json) {
        String group = GsonHelper.getAsString(json, "group", "");
        JsonElement jsonelement = GsonHelper.isArrayNode(json, "strainerIngredient") ? GsonHelper.getAsJsonArray(json, "strainerIngredient") : GsonHelper.getAsJsonObject(json, "strainerIngredient");
        Ingredient ingredient = Ingredient.fromJson(jsonelement);
        String fluidName = GsonHelper.getAsString(json, "inputFluid", "");
        String fluidName1 = GsonHelper.getAsString(json, "outputFluid", "");
        Fluid fluid = ForgeRegistries.FLUIDS.getValue(new ResourceLocation(fluidName));
        Fluid fluid1 = ForgeRegistries.FLUIDS.getValue(new ResourceLocation(fluidName1));
        return this.factory.create(recipeId, group, ingredient, fluid, fluid1);
    }

    @Override
    public T fromNetwork(ResourceLocation resourceLocation, FriendlyByteBuf packetBuffer) {
        String group = packetBuffer.readUtf(32767);
        Ingredient ingredient = Ingredient.fromNetwork(packetBuffer);
        String input = packetBuffer.readUtf(19235);
        String output = packetBuffer.readUtf(19236);

        return this.factory.create(resourceLocation, group, ingredient, ForgeRegistries.FLUIDS.getValue(new ResourceLocation(input)), ForgeRegistries.FLUIDS.getValue(new ResourceLocation(output)));
    }

    public void toNetwork(FriendlyByteBuf buffer, T recipe) {
        buffer.writeUtf(recipe.getGroup());
        recipe.getStrainerIngredient().toNetwork(buffer);
        buffer.writeUtf(ForgeRegistries.FLUIDS.getKey(recipe.getInputFluid()).toString(),19235);
        buffer.writeUtf(ForgeRegistries.FLUIDS.getKey(recipe.getOutputFluid()).toString(),19236);
    }

    public interface IFactory<T extends WaterFilterRecipe> {
        T create(ResourceLocation resourceLocation, String group, Ingredient ingredient, Fluid inputFluid, Fluid outputFluid);
    }
}
