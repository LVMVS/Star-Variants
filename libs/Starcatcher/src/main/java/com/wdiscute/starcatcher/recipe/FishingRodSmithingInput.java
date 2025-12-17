package com.wdiscute.starcatcher.recipe;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;

public record FishingRodSmithingInput(ItemStack template, ItemStack rod) implements RecipeInput
{
    @Override
    public ItemStack getItem(int slot) {
        return switch (slot) {
            case 0 -> this.template;
            case 1 -> this.rod;
            default -> throw new IllegalArgumentException("Recipe does not contain slot " + slot);
        };
    }

    @Override
    public int size() {
        return 2;
    }

    @Override
    public boolean isEmpty() {
        return this.template.isEmpty() && this.rod.isEmpty();
    }
}