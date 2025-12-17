package com.wdiscute.starcatcher.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.wdiscute.starcatcher.io.ModDataComponents;
import com.wdiscute.starcatcher.registry.ModRecipes;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

import java.util.List;

public class ModifierShapedRecipe implements CraftingRecipe
{
    public final ShapedRecipePattern pattern;
    final ItemStack result;
    final List<ResourceLocation> modifiers;
    final String group;
    final CraftingBookCategory category;
    final boolean showNotification;

    public ModifierShapedRecipe(String group, CraftingBookCategory category, ShapedRecipePattern pattern, ItemStack result, boolean showNotification, List<ResourceLocation> modifiers)
    {
        this.group = group;
        this.category = category;
        this.pattern = pattern;
        this.result = result;
        this.showNotification = showNotification;
        this.modifiers = modifiers;
    }

    public ModifierShapedRecipe(String group, CraftingBookCategory category, ShapedRecipePattern pattern, ItemStack result)
    {
        this(group, category, pattern, result, true, List.of());
    }

    @Override
    public RecipeSerializer<?> getSerializer()
    {
        return ModRecipes.MODIFIER_SHAPED_RECIPE.get();
    }

    @Override
    public String getGroup()
    {
        return this.group;
    }

    @Override
    public CraftingBookCategory category()
    {
        return this.category;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider registries)
    {
        return this.result;
    }

    @Override
    public NonNullList<Ingredient> getIngredients()
    {
        return this.pattern.ingredients();
    }

    @Override
    public boolean showNotification()
    {
        return this.showNotification;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height)
    {
        return width >= this.pattern.width() && height >= this.pattern.height();
    }

    public boolean matches(CraftingInput input, Level level)
    {
        return this.pattern.matches(input);
    }

    public ItemStack assemble(CraftingInput input, HolderLookup.Provider registries)
    {
        var wad = this.getResultItem(registries).copy();
        //only add if its not empty to not clutter itemStacks
        if(!modifiers.isEmpty()) wad.set(ModDataComponents.MODIFIERS, modifiers);
        return wad;
    }

    public int getWidth()
    {
        return this.pattern.width();
    }

    public int getHeight()
    {
        return this.pattern.height();
    }

    @Override
    public boolean isIncomplete()
    {
        NonNullList<Ingredient> nonnulllist = this.getIngredients();
        return nonnulllist.isEmpty() || nonnulllist.stream().filter(p_151277_ -> !p_151277_.isEmpty()).anyMatch(Ingredient::hasNoItems);
    }

    public static class Serializer implements RecipeSerializer<ModifierShapedRecipe>
    {
        public static final MapCodec<ModifierShapedRecipe> CODEC = RecordCodecBuilder.mapCodec(
                p_340778_ -> p_340778_.group(
                                Codec.STRING.optionalFieldOf("group", "").forGetter(p_311729_ -> p_311729_.group),
                                CraftingBookCategory.CODEC.fieldOf("category").orElse(CraftingBookCategory.MISC).forGetter(p_311732_ -> p_311732_.category),
                                ShapedRecipePattern.MAP_CODEC.forGetter(p_311733_ -> p_311733_.pattern),
                                ItemStack.STRICT_CODEC.fieldOf("result").forGetter(p_311730_ -> p_311730_.result),
                                Codec.BOOL.optionalFieldOf("show_notification", Boolean.TRUE).forGetter(p_311731_ -> p_311731_.showNotification),
                                ResourceLocation.CODEC.listOf().fieldOf("modifiers").forGetter(p_311730_ -> p_311730_.modifiers)
                        )
                        .apply(p_340778_, ModifierShapedRecipe::new)
        );
        public static final StreamCodec<RegistryFriendlyByteBuf, ModifierShapedRecipe> STREAM_CODEC = StreamCodec.of(
                ModifierShapedRecipe.Serializer::toNetwork, ModifierShapedRecipe.Serializer::fromNetwork
        );

        @Override
        public MapCodec<ModifierShapedRecipe> codec()
        {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, ModifierShapedRecipe> streamCodec()
        {
            return STREAM_CODEC;
        }

        private static ModifierShapedRecipe fromNetwork(RegistryFriendlyByteBuf buffer)
        {
            String s = buffer.readUtf();
            CraftingBookCategory craftingbookcategory = buffer.readEnum(CraftingBookCategory.class);
            ShapedRecipePattern shapedrecipepattern = ShapedRecipePattern.STREAM_CODEC.decode(buffer);
            ItemStack itemstack = ItemStack.STREAM_CODEC.decode(buffer);
            boolean flag = buffer.readBoolean();
            List<ResourceLocation> modifiers = ResourceLocation.STREAM_CODEC.apply(ByteBufCodecs.list()).decode(buffer);
            return new ModifierShapedRecipe(s, craftingbookcategory, shapedrecipepattern, itemstack, flag, modifiers);
        }

        private static void toNetwork(RegistryFriendlyByteBuf buffer, ModifierShapedRecipe recipe)
        {
            buffer.writeUtf(recipe.group);
            buffer.writeEnum(recipe.category);
            ShapedRecipePattern.STREAM_CODEC.encode(buffer, recipe.pattern);
            ItemStack.STREAM_CODEC.encode(buffer, recipe.result);
            buffer.writeBoolean(recipe.showNotification);
            ResourceLocation.STREAM_CODEC.apply(ByteBufCodecs.list()).encode(buffer, recipe.modifiers);
        }
    }

}
