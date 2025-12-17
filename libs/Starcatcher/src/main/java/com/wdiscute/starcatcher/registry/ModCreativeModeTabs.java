package com.wdiscute.starcatcher.registry;

import com.wdiscute.starcatcher.Starcatcher;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;


public class ModCreativeModeTabs
{
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Starcatcher.MOD_ID);

    public static void register(IEventBus eventBus)
    {
        CREATIVE_MODE_TABS.register(eventBus);
    }


    public static final Supplier<CreativeModeTab> STARCATCHER = CREATIVE_MODE_TABS.register(
            "starcatcher", () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.ROD.get()))
                    .title(Component.translatable("creativetab.starcatcher.starcatcher"))
                    .displayItems((itemDisplayParameters, output) ->
                    {

                        output.accept(ModItems.ROD);

                        //adds items
                        for (DeferredHolder<Item, ? extends Item> item : ModItems.ITEMS_REGISTRY.getEntries())
                            if(!item.equals(ModItems.ROD))
                                output.accept(item.get());

                        //adds blocks
                        for (DeferredHolder<Item, ? extends Item> item : ModItems.BLOCKITEMS_REGISTRY.getEntries())
                            output.accept(item.get());

                        //adds bobbers
                        for (DeferredHolder<Item, ? extends Item> item : ModItems.BOBBERS_REGISTRY.getEntries())
                            output.accept(item.get());

                        //adds hooks
                        for (DeferredHolder<Item, ? extends Item> item : ModItems.HOOKS_REGISTRY.getEntries())
                            output.accept(item.get());

                        //adds baits
                        for (DeferredHolder<Item, ? extends Item> item : ModItems.BAITS_REGISTRY.getEntries())
                            output.accept(item.get());

                        //adds templates
                        for (DeferredHolder<Item, ? extends Item> item : ModItems.TEMPLATES_REGISTRY.getEntries())
                            output.accept(item.get());

                        //adds rods besides default
                        for (DeferredHolder<Item, ? extends Item> item : ModItems.RODS_REGISTRY.getEntries())
                            if(!item.equals(ModItems.ROD))
                                output.accept(item.get());

                        //adds fish
                        for (DeferredHolder<Item, ? extends Item> item : ModItems.FISH_REGISTRY.getEntries())
                            output.accept(item.get());

                        //adds trash
                        for (DeferredHolder<Item, ? extends Item> item : ModItems.TRASH_REGISTRY.getEntries())
                            output.accept(item.get());


                        //adds cheater items
                        for (DeferredHolder<Item, ? extends Item> item : ModItems.DEV_REGISTRY.getEntries())
                            if (!item.equals(ModItems.SETTINGS) && !item.equals(ModItems.UNKNOWN_FISH) && !item.equals(ModItems.MISSINGNO))
                                output.accept(item.get());

                    })
                    .build()
    );
}
