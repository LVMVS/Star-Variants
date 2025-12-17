package com.wdiscute.starcatcher.minigame.modifiers;

import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.StarcatcherTags;
import com.wdiscute.starcatcher.io.ModDataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public interface ModModifiers
{
    DeferredRegister<Supplier<AbstractModifier>> REGISTRY =
            DeferredRegister.create(Starcatcher.MODIFIERS_REGISTRY, Starcatcher.MOD_ID);

    //ice fishes
    ResourceLocation FREEZE_ON_MISS = registerModifier("freeze_on_miss", FreezeOnMissModifier::new);

    //aurora?
    ResourceLocation SPAWN_FROZEN_SWEET_SPOTS = registerModifier("spawn_frozen_sweet_spots", SpawnFrozenSweetSpotsModifier::new);

    //base modifier
    ResourceLocation LOW_CHANCE_TREASURE_SPAWN = registerModifier("low_chance_treasure_spawn", LowChanceTreasureSpawnModifier::new);

    //gunpowder bait
    ResourceLocation SPAWN_TNT_SWEET_SPOTS = registerModifier("spawn_tnt_sweet_spots", SpawnTntSweetSpotsModifier::new);

    //shiny hook
    ResourceLocation SPAWN_TREASURE_ON_THREE_HITS = registerModifier("spawn_treasure_on_three_hits", ShinyHookModifier::new);

    //gold hook
    ResourceLocation EXTRA_EXP_BASED_ON_PERFORMANCE = registerModifier("extra_exp_based_on_performance", EmptyModifier::new);

    //heavy hook
    ResourceLocation SLOWER_MOVING_SWEET_SPOTS = registerModifier("slower_moving_sweet_spots", HeavyHookModifier::new);

    //stone hook
    ResourceLocation STOP_DECAY_ON_HIT = registerModifier("stop_decay_on_hit", StoneHookModifier::new);

    //mossy hook
    ResourceLocation HARDER_WITH_TREASURE_ON_PERFECT = registerModifier("harder_with_treasure_on_perfect", MossyHookModifier::new);

    //split hook
    ResourceLocation DOUBLE_ITEM = registerModifier("double_item", EmptyModifier::new);

    //stabilizing hook
    ResourceLocation NO_FLIP = registerModifier("no_flip", EmptyModifier::new);

    //steady bobber
    ResourceLocation BIGGER_GREEN_SWEET_SPOTS = registerModifier("bigger_green_sweet_spots", SteadyBobberModifier::new);

    //clear bobber
    ResourceLocation SLOWER_VANISHING = registerModifier("slower_vanishing", ClearBobberModifier::new);

    //aqua bobber
    ResourceLocation ADD_AQUA_SWEET_SPOT = registerModifier("add_aqua_sweet_spot", () -> new AquaBobberModifier(1));

    //unused
    ResourceLocation ADD_THREE_AQUA_SWEET_SPOT = registerModifier("add_three_aqua_sweet_spot", () -> new AquaBobberModifier(3));

    //vanilla bobber
    ResourceLocation VANILLA_LOOT = registerModifier("vanilla_loot", EmptyModifier::new);

    //almighty worm
    ResourceLocation FISH_ENTITY = registerModifier("fish_entity", EmptyModifier::new);

    //every bait
    ResourceLocation DECREASES_LURE_TIME = registerModifier("decrease_lure_time", EmptyModifier::new);

    //seeking worm
    ResourceLocation GUARANTEE_NEW_FISH = registerModifier("guarantee_new_fish", EmptyModifier::new);

    static ResourceLocation registerModifier(String name, Supplier<AbstractModifier> sup)
    {
        REGISTRY.register(name, () -> sup);
        return Starcatcher.rl(name);
    }

    static void register(IEventBus eventBus)
    {
        REGISTRY.register(eventBus);
    }

    static boolean hasModifier(ItemStack is, ResourceLocation rl)
    {
        return hasModifier(is, rl, true);
    }

    static boolean hasModifier(ItemStack is, ResourceLocation rl, boolean checkRodItemStack)
    {

        if (is.is(StarcatcherTags.RODS) && checkRodItemStack)
        {
            return (
                    hasModifier(is.get(ModDataComponents.BOBBER_SKIN).stack(), rl) ||
                            hasModifier(is.get(ModDataComponents.BOBBER).stack(), rl) ||
                            hasModifier(is.get(ModDataComponents.BAIT).stack(), rl) ||
                            hasModifier(is.get(ModDataComponents.HOOK).stack(), rl) ||
                            hasModifier(is, rl, false)
            );
        }

        if (is.has(ModDataComponents.MODIFIERS))
        {
            return is.get(ModDataComponents.MODIFIERS).contains(rl);
        }
        else
        {
            return false;
        }
    }
}
