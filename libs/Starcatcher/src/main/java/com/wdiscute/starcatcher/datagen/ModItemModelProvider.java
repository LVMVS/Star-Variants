package com.wdiscute.starcatcher.datagen;

import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.registry.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Starcatcher.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        for (DeferredHolder<Item, ? extends Item> item : ModItems.ITEMS_REGISTRY.getEntries()) {
            simpleItem((DeferredItem<? extends Item>) item);
        }

        for (DeferredHolder<Item, ? extends Item> item : ModItems.HOOKS_REGISTRY.getEntries()) {
            simpleItem((DeferredItem<? extends Item>) item);
        }

        for (DeferredHolder<Item, ? extends Item> item : ModItems.BAITS_REGISTRY.getEntries()) {
            simpleItem((DeferredItem<? extends Item>) item);
        }

        for (DeferredHolder<Item, ? extends Item> item : ModItems.BOBBERS_REGISTRY.getEntries()) {
            simpleItem((DeferredItem<? extends Item>) item);
        }

        for (DeferredHolder<Item, ? extends Item> item : ModItems.TEMPLATES_REGISTRY.getEntries()) {
            simpleItem((DeferredItem<? extends Item>) item);
        }

        for (DeferredHolder<Item, ? extends Item> item : ModItems.DEV_REGISTRY.getEntries()) {
            simpleItem((DeferredItem<? extends Item>) item);
        }

        for (DeferredHolder<Item, ? extends Item> item : ModItems.FISH_REGISTRY.getEntries()) {
            simpleItem((DeferredItem<? extends Item>) item);
        }

        for (DeferredHolder<Item, ? extends Item> item : ModItems.TRASH_REGISTRY.getEntries()) {
            simpleItem((DeferredItem<? extends Item>) item);
        }
    }

    private ItemModelBuilder simpleItem(DeferredItem<? extends Item> item) {
        return withExistingParent(item.getId().getPath(),
                mcLoc("item/generated")).texture("layer0",
                modLoc("item/" + item.getId().getPath()));
    }
}
