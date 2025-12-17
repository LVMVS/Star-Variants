package com.wdiscute.starcatcher.registry;

import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.recipe.FishingRodSmithingRecipe;
import com.wdiscute.starcatcher.recipe.ModifierShapedRecipe;
import com.wdiscute.starcatcher.recipe.ModifierShapelessRecipe;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModRecipes
{
    public static final DeferredRegister<RecipeSerializer<?>> REGISTRY =
            DeferredRegister.create(Registries.RECIPE_SERIALIZER, Starcatcher.MOD_ID);

    public static final  Supplier<RecipeSerializer<FishingRodSmithingRecipe>> FISHING_ROD_SMITHING =
            REGISTRY.register("fishing_rod_smithing", FishingRodSmithingRecipe.Serializer::new);

    public static final  Supplier<RecipeSerializer<ModifierShapedRecipe>> MODIFIER_SHAPED_RECIPE =
            REGISTRY.register("modifier_shaped_recipe", ModifierShapedRecipe.Serializer::new);

    public static final  Supplier<RecipeSerializer<ModifierShapelessRecipe>> MODIFIER_SHAPELESS_RECIPE =
            REGISTRY.register("modifier_shapeless_recipe", ModifierShapelessRecipe.Serializer::new);

    public static void register(IEventBus eventBus)
    {
        REGISTRY.register(eventBus);
    }
}
