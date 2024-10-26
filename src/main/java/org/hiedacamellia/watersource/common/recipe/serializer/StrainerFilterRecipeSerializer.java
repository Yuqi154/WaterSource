package org.hiedacamellia.watersource.common.recipe.serializer;

import com.google.gson.JsonObject;
import org.hiedacamellia.watersource.common.recipe.type.StrainerFilterRecipe;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

public class StrainerFilterRecipeSerializer<T extends StrainerFilterRecipe> implements RecipeSerializer<T>{

    @Override
    public T fromJson(ResourceLocation recipeID, JsonObject json) {
        String fluidInName = GsonHelper.getAsString(json, "fluidIn", "");
        String fluidOutName = GsonHelper.getAsString(json, "fluidOut", "");
        String itemName = GsonHelper.getAsString(json, "item", "");
        ResourceLocation strainerTag = new ResourceLocation(GsonHelper.getAsString(json, "strainer_tag", ""));

        Fluid fluidIn = ForgeRegistries.FLUIDS.getValue(new ResourceLocation(fluidInName));
        Fluid fluidOut = ForgeRegistries.FLUIDS.getValue(new ResourceLocation(fluidOutName));
        Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemName));
        return (T) new StrainerFilterRecipe(recipeID, strainerTag, fluidIn, fluidOut, item);
    }

    @Nullable
    @Override
    public T fromNetwork(ResourceLocation recipeID, FriendlyByteBuf friendlyByteBuf) {
        ResourceLocation strainerTag = friendlyByteBuf.readResourceLocation();
        String fluidInName = friendlyByteBuf.readUtf();
        String fluidOutName = friendlyByteBuf.readUtf();
        String itemName = friendlyByteBuf.readUtf();

        Fluid fluidIn = ForgeRegistries.FLUIDS.getValue(new ResourceLocation(fluidInName));
        Fluid fluidOut = ForgeRegistries.FLUIDS.getValue(new ResourceLocation(fluidOutName));
        Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemName));
        return (T) new StrainerFilterRecipe(recipeID, strainerTag, fluidIn, fluidOut, item);
    }

    @Override
    public void toNetwork(FriendlyByteBuf friendlyByteBuf, T recipe) {
        friendlyByteBuf.writeResourceLocation(recipe.strainerTag.location());
        friendlyByteBuf.writeUtf(ForgeRegistries.FLUIDS.getKey(recipe.inPutFluid).toString());
        friendlyByteBuf.writeUtf(ForgeRegistries.FLUIDS.getKey(recipe.outPutFluid).toString());
        friendlyByteBuf.writeUtf(ForgeRegistries.ITEMS.getKey(recipe.containerItem).toString());
    }
}
