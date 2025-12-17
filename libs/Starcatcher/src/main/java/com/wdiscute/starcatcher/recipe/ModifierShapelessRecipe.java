package com.wdiscute.starcatcher.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
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

public class ModifierShapelessRecipe implements CraftingRecipe
{
    final String group;
    final CraftingBookCategory category;
    final ItemStack result;
    final NonNullList<Ingredient> ingredients;
    private final boolean isSimple;
    private final List<ResourceLocation> modifiers;

    public ModifierShapelessRecipe(String group, CraftingBookCategory category, ItemStack result, NonNullList<Ingredient> ingredients, List<ResourceLocation> modifiers)
    {
        this.group = group;
        this.category = category;
        this.result = result;
        this.ingredients = ingredients;
        this.isSimple = ingredients.stream().allMatch(Ingredient::isSimple);
        this.modifiers = modifiers;
    }

    @Override
    public RecipeSerializer<?> getSerializer()
    {
        return ModRecipes.MODIFIER_SHAPELESS_RECIPE.get();
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
        return this.ingredients;
    }

    public boolean matches(CraftingInput input, Level level)
    {
        if (input.ingredientCount() != this.ingredients.size())
        {
            return false;
        }
        else if (!isSimple)
        {
            var nonEmptyItems = new java.util.ArrayList<ItemStack>(input.ingredientCount());
            for (var item : input.items())
                if (!item.isEmpty())
                    nonEmptyItems.add(item);
            return net.neoforged.neoforge.common.util.RecipeMatcher.findMatches(nonEmptyItems, this.ingredients) != null;
        }
        else
        {
            return input.size() == 1 && this.ingredients.size() == 1
                    ? this.ingredients.getFirst().test(input.getItem(0))
                    : input.stackedContents().canCraft(this, null);
        }
    }

    public ItemStack assemble(CraftingInput input, HolderLookup.Provider registries)
    {
        return this.result.copy();
    }

    /**
     * Used to determine if this recipe can fit in a grid of the given width/height
     */
    @Override
    public boolean canCraftInDimensions(int width, int height)
    {
        return width * height >= this.ingredients.size();
    }

    public static class Serializer implements RecipeSerializer<ModifierShapelessRecipe>
    {
        private static final MapCodec<ModifierShapelessRecipe> CODEC = RecordCodecBuilder.mapCodec(
                p_340779_ -> p_340779_.group(
                                Codec.STRING.optionalFieldOf("group", "").forGetter(p_301127_ -> p_301127_.group),
                                CraftingBookCategory.CODEC.fieldOf("category").orElse(CraftingBookCategory.MISC).forGetter(p_301133_ -> p_301133_.category),
                                ItemStack.STRICT_CODEC.fieldOf("result").forGetter(p_301142_ -> p_301142_.result),
                                Ingredient.CODEC_NONEMPTY
                                        .listOf()
                                        .fieldOf("ingredients")
                                        .flatXmap(
                                                p_301021_ ->
                                                {
                                                    Ingredient[] aingredient = p_301021_.toArray(Ingredient[]::new); // Neo skip the empty check and immediately create the array.
                                                    if (aingredient.length == 0)
                                                    {
                                                        return DataResult.error(() -> "No ingredients for shapeless recipe");
                                                    }
                                                    else
                                                    {
                                                        return aingredient.length > 3 * 3
                                                                ? DataResult.error(() -> "Too many ingredients for shapeless recipe. The maximum is: %s".formatted(3 * 3))
                                                                : DataResult.success(NonNullList.of(Ingredient.EMPTY, aingredient));
                                                    }
                                                },
                                                DataResult::success
                                        )
                                        .forGetter(p_300975_ -> p_300975_.ingredients),
                                ResourceLocation.CODEC.listOf().fieldOf("modifiers").forGetter(p_311730_ -> p_311730_.modifiers)
                        )
                        .apply(p_340779_, ModifierShapelessRecipe::new)
        );
        public static final StreamCodec<RegistryFriendlyByteBuf, ModifierShapelessRecipe> STREAM_CODEC = StreamCodec.of(
                ModifierShapelessRecipe.Serializer::toNetwork, ModifierShapelessRecipe.Serializer::fromNetwork
        );

        @Override
        public MapCodec<ModifierShapelessRecipe> codec()
        {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, ModifierShapelessRecipe> streamCodec()
        {
            return STREAM_CODEC;
        }

        private static ModifierShapelessRecipe fromNetwork(RegistryFriendlyByteBuf buffer)
        {
            String s = buffer.readUtf();
            CraftingBookCategory craftingbookcategory = buffer.readEnum(CraftingBookCategory.class);
            int i = buffer.readVarInt();
            NonNullList<Ingredient> nonnulllist = NonNullList.withSize(i, Ingredient.EMPTY);
            nonnulllist.replaceAll(p_319735_ -> Ingredient.CONTENTS_STREAM_CODEC.decode(buffer));
            ItemStack itemstack = ItemStack.STREAM_CODEC.decode(buffer);
            List<ResourceLocation> modifiers = ResourceLocation.STREAM_CODEC.apply(ByteBufCodecs.list()).decode(buffer);
            return new ModifierShapelessRecipe(s, craftingbookcategory, itemstack, nonnulllist, modifiers);
        }

        private static void toNetwork(RegistryFriendlyByteBuf buffer, ModifierShapelessRecipe recipe)
        {
            buffer.writeUtf(recipe.group);
            buffer.writeEnum(recipe.category);
            buffer.writeVarInt(recipe.ingredients.size());

            for (Ingredient ingredient : recipe.ingredients)
            {
                Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, ingredient);
            }

            ItemStack.STREAM_CODEC.encode(buffer, recipe.result);
            ResourceLocation.STREAM_CODEC.apply(ByteBufCodecs.list()).encode(buffer, recipe.modifiers);
        }
    }


}
