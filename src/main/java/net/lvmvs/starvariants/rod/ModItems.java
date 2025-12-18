package net.lvmvs.starvariants.rod;

import net.lvmvs.starvariants.StarVariants;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(StarVariants.MOD_ID);

    public static final DeferredItem<Item> IRONSC = ITEMS.register("iron_starcatcher_uncast",
            () -> new Item(new Item.Properties()));


    public static void register(IEventBus eventbus ) {
        ITEMS.register(eventbus);
    }
}
