package com.wdiscute.starcatcher.datagen;

import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.StarcatcherTags;
import com.wdiscute.starcatcher.registry.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemsTagProvider extends ItemTagsProvider
{

    public ModItemsTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                               CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper)
    {
        super(output, lookupProvider, blockTags, Starcatcher.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider)
    {
        //fishes, cat_food, foods/raw_fish
        for (var item : ModItems.FISH_REGISTRY.getEntries())
        {
            tag(ItemTags.FISHES).add(item.get());
            tag(ItemTags.CAT_FOOD).add(item.get());
            tag(Tags.Items.FOODS_RAW_FISH).add(item.get());
        }

        //baits tag
        for (DeferredHolder<Item, ? extends Item> item : ModItems.BAITS_REGISTRY.getEntries())
        {
            tag(StarcatcherTags.BAITS).add(item.get());
        }

        //extra baits
        tag(StarcatcherTags.BAITS).add(Items.BUCKET);
        tag(StarcatcherTags.BAITS).add(Items.WITHER_SKELETON_SKULL);

        tag(StarcatcherTags.BAITS).addOptional(rl("fishofthieves", "earthworms"));
        tag(StarcatcherTags.BAITS).addOptional(rl("fishofthieves", "grubs"));
        tag(StarcatcherTags.BAITS).addOptional(rl("fishofthieves", "leeches"));

        tag(StarcatcherTags.BAITS).addOptional(rl("tfc", "food/bluegill"));
        tag(StarcatcherTags.BAITS).addOptional(rl("tfc", "food/cod"));
        tag(StarcatcherTags.BAITS).addOptional(rl("tfc", "food/salmon"));
        tag(StarcatcherTags.BAITS).addOptional(rl("tfc", "food/tropical_fish"));

        //hooks tag
        for (DeferredHolder<Item, ? extends Item> item : ModItems.HOOKS_REGISTRY.getEntries())
        {
            tag(StarcatcherTags.HOOKS).add(item.get());
        }

        //bobbers tag
        for (DeferredHolder<Item, ? extends Item> item : ModItems.BOBBERS_REGISTRY.getEntries())
        {
            tag(StarcatcherTags.BOBBERS).add(item.get());
        }

        //rods and tools/fishing_rod
        for (DeferredHolder<Item, ? extends Item> item : ModItems.RODS_REGISTRY.getEntries())
        {
            tag(Tags.Items.TOOLS_FISHING_ROD).add(item.get());
            tag(StarcatcherTags.RODS).add(item.get());
        }

    }


    public static ResourceLocation rl(String ns, String path)
    {
        return ResourceLocation.fromNamespaceAndPath(ns, path);
    }
}
