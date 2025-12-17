package com.wdiscute.starcatcher.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.wdiscute.starcatcher.StarcatcherTags;
import com.wdiscute.starcatcher.io.ModDataComponents;
import com.wdiscute.starcatcher.io.SingleStackContainer;
import com.wdiscute.starcatcher.registry.ModItems;
import com.wdiscute.starcatcher.registry.ModRecipes;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

import java.util.Arrays;
import java.util.stream.Stream;

public record FishingRodSmithingRecipe(
        Ingredient template,
        Ingredient rod
)
        implements SmithingRecipe
{

    public boolean matches(SmithingRecipeInput input, Level level)
    {
        //netherite upgrade
        if(input.template().is(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE)
                && !input.base().has(ModDataComponents.NETHERITE_UPGRADE)
                && input.addition().is(Items.NETHERITE_INGOT)
        ) return true;

        //bobber skins
        if(input.template().is(StarcatcherTags.BOBBER_SKIN_TEMPLATES) && input.addition().isEmpty())
        {
            SingleStackContainer singleStackContainer = input.base().get(ModDataComponents.BOBBER_SKIN);
            if(singleStackContainer == null) return true;

            //if bobber skin is the template, can not craft
            return !singleStackContainer.stack().is(input.template().getItem());
        }

        return false;
    }

    public ItemStack assemble(SmithingRecipeInput input, HolderLookup.Provider registries)
    {
        ItemStack newRod = input.base().copy();

        if(input.template().is(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE) && input.addition().is(Items.NETHERITE_INGOT))
        {
            newRod.set(ModDataComponents.NETHERITE_UPGRADE, true);
            return newRod;
        }

        if(input.template().is(StarcatcherTags.BOBBER_SKIN_TEMPLATES))
        {
            newRod.set(ModDataComponents.BOBBER_SKIN, new SingleStackContainer(input.template()));
            return newRod;
        }

        throw new RuntimeException("starcatcher - that template is not supported >:( talk to @wdiscute on discord");
    }

    @Override
    public boolean canCraftInDimensions(int width, int height)
    {
        return true;
    }

    @Override
    public boolean isTemplateIngredient(ItemStack stack)
    {
        return this.template.test(stack);
    }

    @Override
    public boolean isBaseIngredient(ItemStack stack)
    {
        return this.rod.test(stack);
    }

    @Override
    public boolean isAdditionIngredient(ItemStack stack)
    {
        return true;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider registries)
    {
        return Arrays.stream(this.rod.getItems()).findFirst().get();
    }

    @Override
    public RecipeSerializer<?> getSerializer()
    {
        return ModRecipes.FISHING_ROD_SMITHING.get();
    }

    @Override
    public RecipeType<?> getType()
    {
        return RecipeType.SMITHING;
    }

    @Override
    public boolean isIncomplete()
    {
        return Stream.of(this.template, this.rod).anyMatch(Ingredient::hasNoItems);
    }

    public static class Serializer implements RecipeSerializer<FishingRodSmithingRecipe>
    {
        private static final MapCodec<FishingRodSmithingRecipe> CODEC = RecordCodecBuilder.mapCodec(
                instance -> instance.group(
                        Ingredient.CODEC.fieldOf("template").forGetter(FishingRodSmithingRecipe::template),
                        Ingredient.CODEC.fieldOf("rod").forGetter(FishingRodSmithingRecipe::rod)
                ).apply(instance, FishingRodSmithingRecipe::new)
        );

        public static final StreamCodec<RegistryFriendlyByteBuf, FishingRodSmithingRecipe> STREAM_CODEC = StreamCodec.of(
                FishingRodSmithingRecipe.Serializer::toNetwork, FishingRodSmithingRecipe.Serializer::fromNetwork
        );

        @Override
        public MapCodec<FishingRodSmithingRecipe> codec()
        {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, FishingRodSmithingRecipe> streamCodec()
        {
            return STREAM_CODEC;
        }

        private static FishingRodSmithingRecipe fromNetwork(RegistryFriendlyByteBuf buffer)
        {
            Ingredient template = Ingredient.CONTENTS_STREAM_CODEC.decode(buffer);
            Ingredient rod = Ingredient.CONTENTS_STREAM_CODEC.decode(buffer);
            return new FishingRodSmithingRecipe(template, rod);
        }

        private static void toNetwork(RegistryFriendlyByteBuf buffer, FishingRodSmithingRecipe recipe)
        {
            Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, recipe.template);
            Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, recipe.rod);
        }
    }
}
