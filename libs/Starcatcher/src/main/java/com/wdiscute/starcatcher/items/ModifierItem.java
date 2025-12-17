package com.wdiscute.starcatcher.items;

import com.wdiscute.starcatcher.io.ModDataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.List;

public class ModifierItem extends Item
{
    public ModifierItem(ResourceLocation... modifiers)
    {
        super(new Item.Properties()
                .component(ModDataComponents.MODIFIERS, List.of(modifiers))
                .stacksTo(1)
        );
    }

    public ModifierItem(int maxStackSize, ResourceLocation... modifiers)
    {
        super(new Item.Properties()
                .component(ModDataComponents.MODIFIERS, List.of(modifiers))
                .stacksTo(maxStackSize)
        );
    }
}
