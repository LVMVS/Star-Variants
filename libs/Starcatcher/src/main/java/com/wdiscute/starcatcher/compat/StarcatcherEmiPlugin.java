package com.wdiscute.starcatcher.compat;

import com.wdiscute.starcatcher.recipe.FishingRodSmithingRecipe;
import com.wdiscute.starcatcher.registry.ModItems;
import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.storage.FishProperties;
import com.wdiscute.starcatcher.storage.TrophyProperties;
import dev.emi.emi.api.EmiEntrypoint;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmithingRecipe;

import java.util.function.Supplier;

@EmiEntrypoint
public class StarcatcherEmiPlugin implements EmiPlugin
{
    public static final ResourceLocation MY_SPRITE_SHEET = Starcatcher.rl("textures/gui/emi_simplified_textures.png");
    public static final EmiStack MY_WORKSTATION = EmiStack.of(ModItems.ROD);
    public static final EmiRecipeCategory STARCATCHER_CATEGORY
            = new EmiRecipeCategory(
            Starcatcher.rl("fishing"),
            MY_WORKSTATION);

    @Override
    public void register(EmiRegistry registry)
    {
        // Tell EMI to add a tab for your category
        registry.addCategory(STARCATCHER_CATEGORY);

        // Add all the workstations your category uses
        registry.addWorkstation(STARCATCHER_CATEGORY, MY_WORKSTATION);

        Registry<FishProperties> fps = Minecraft.getInstance().level.registryAccess().registryOrThrow(Starcatcher.FISH_REGISTRY);

        for (FishProperties fp : fps)
        {
            registry.addRecipe(new StarcatcherEmiRecipe(fps.getKey(fp), fp));
        }


        Registry<TrophyProperties> trophies = Minecraft.getInstance().level.registryAccess().registryOrThrow(Starcatcher.TROPHY_REGISTRY);

        for (TrophyProperties fp : trophies)
        {
            if (fp.trophyType().equals(TrophyProperties.TrophyType.TROPHY) || fp.trophyType().equals(TrophyProperties.TrophyType.SECRET))
                registry.addRecipe(new StarcatcherEmiRecipe(trophies.getKey(fp), fp));
        }




        for (SmithingRecipe recipe : getRecipes(registry, RecipeType.SMITHING))
        {
            if (recipe instanceof FishingRodSmithingRecipe dwa)
            {
                registry.addRecipe(new StarcatcherEmiSmithingRecipe(dwa));
            }
        }

    }

    private static void addRecipeSafe(EmiRegistry registry, Supplier<EmiRecipe> supplier)
    {
        registry.addRecipe(supplier.get());
    }

    private static <C extends RecipeInput, T extends Recipe<C>> Iterable<T> getRecipes(EmiRegistry registry, RecipeType<T> type)
    {
        return registry.getRecipeManager().getAllRecipesFor(type).stream().map(e -> e.value())::iterator;
    }

}
