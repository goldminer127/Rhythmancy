package com.example.examplemod;

import com.mojang.serialization.Codec;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

public class ModRecipes implements Recipe<SimpleContainer> {
    private final ResourceLocation id;
    private final ItemStack output;
    private final NonNullList<Ingredient> recipeItem;

    public ModRecipes(ResourceLocation id, ItemStack output, NonNullList<Ingredient> recipeItem){
        this.id = id;
        this.output = output;
        this.recipeItem = recipeItem;
    }

    @Override
    public boolean matches(SimpleContainer simpleContainer, Level level) {
        if(level.isClientSide()) {
            return false;
        }
        return recipeItem.get(0).test(simpleContainer.getItem(0));
    }

    @Override
    public ItemStack assemble(SimpleContainer simpleContainer, RegistryAccess registryAccess) {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int i, int i1) {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess registryAccess) {
        return output.copy();
    }

    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<ModRecipes> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "get_triangle";
    }

    public static class Serializer implements RecipeSerializer<ModRecipes> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation(ExampleMod.MODID, "get_triangle");


        @Override
        public Codec<ModRecipes> codec() {
            return null;
        }

        @Override
        public ModRecipes fromNetwork(FriendlyByteBuf buffer) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(buffer.readInt(), Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromNetwork(buffer));
            }
            ItemStack output = buffer.readItem();
            return new ModRecipes(ID, output, inputs);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, ModRecipes recipe) {
            buffer.writeInt(recipe.recipeItem.size());

            for (Ingredient ingredient : recipe.recipeItem) {
                ingredient.toNetwork(buffer);
            }
            buffer.writeItem(recipe.output);
        }
    }
}
