package com.wdiscute.starcatcher.registry;

import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.guide.FishingGuideItem;
import com.wdiscute.starcatcher.items.*;
import com.wdiscute.starcatcher.items.cheater.*;
import com.wdiscute.starcatcher.items.helper.BasicItem;
import com.wdiscute.starcatcher.items.helper.FireResistantBasicItem;
import com.wdiscute.starcatcher.items.helper.SingleStackBasicItem;
import com.wdiscute.starcatcher.minigame.modifiers.ModModifiers;
import com.wdiscute.starcatcher.rod.StarcatcherFishingRod;
import com.wdiscute.starcatcher.secretnotes.NoteContainer;
import com.wdiscute.starcatcher.secretnotes.SecretNote;
import net.dries007.tfc.client.overworld.Star;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public interface ModItems
{

    DeferredRegister.Items ITEMS_REGISTRY = DeferredRegister.createItems(Starcatcher.MOD_ID);
    DeferredRegister.Items RODS_REGISTRY = DeferredRegister.createItems(Starcatcher.MOD_ID);
    DeferredRegister.Items BAITS_REGISTRY = DeferredRegister.createItems(Starcatcher.MOD_ID);
    DeferredRegister.Items HOOKS_REGISTRY = DeferredRegister.createItems(Starcatcher.MOD_ID);
    DeferredRegister.Items BOBBERS_REGISTRY = DeferredRegister.createItems(Starcatcher.MOD_ID);

    //fishes which have a model and swim in water
    DeferredRegister.Items FISH_REGISTRY = DeferredRegister.createItems(Starcatcher.MOD_ID);
    DeferredRegister.Items TRASH_REGISTRY = DeferredRegister.createItems(Starcatcher.MOD_ID);

    DeferredRegister.Items TEMPLATES_REGISTRY = DeferredRegister.createItems(Starcatcher.MOD_ID);
    DeferredRegister.Items BLOCKITEMS_REGISTRY = DeferredRegister.createItems(Starcatcher.MOD_ID);

    DeferredRegister.Items DEV_REGISTRY = DeferredRegister.createItems(Starcatcher.MOD_ID);

    DeferredItem<Item> SETTINGS = DEV_REGISTRY.register(
            "settings", () -> new Item(new Item.Properties())
            {
                //dev stuff

            });

    DeferredItem<Item> MISSINGNO = DEV_REGISTRY.register("missingno", BasicItem::new);
    DeferredItem<Item> UNKNOWN_FISH = DEV_REGISTRY.register("unknown_fish", BasicItem::new);

    DeferredItem<Item> GUIDE = ITEMS_REGISTRY.register("starcatcher_guide", FishingGuideItem::new);

    DeferredItem<Item> FISH_RADAR = ITEMS_REGISTRY.register("fish_radar", SingleStackBasicItem::new);

    DeferredItem<Item> STARCATCHER_TWINE = ITEMS_REGISTRY.register("starcatcher_twine", SingleStackBasicItem::new);

    //hooks
    DeferredItem<Item> HOOK = HOOKS_REGISTRY.register("hook", SingleStackBasicItem::new);
    DeferredItem<Item> SHINY_HOOK = HOOKS_REGISTRY.register("shiny_hook", () -> new ModifierItem(ModModifiers.SPAWN_TREASURE_ON_THREE_HITS));
    DeferredItem<Item> GOLD_HOOK = HOOKS_REGISTRY.register("gold_hook", () -> new ModifierItem(ModModifiers.EXTRA_EXP_BASED_ON_PERFORMANCE));
    DeferredItem<Item> MOSSY_HOOK = HOOKS_REGISTRY.register("mossy_hook", () -> new ModifierItem(ModModifiers.HARDER_WITH_TREASURE_ON_PERFECT));
    DeferredItem<Item> STONE_HOOK = HOOKS_REGISTRY.register("stone_hook", () -> new ModifierItem(ModModifiers.STOP_DECAY_ON_HIT));
    DeferredItem<Item> SPLIT_HOOK = HOOKS_REGISTRY.register("split_hook", () -> new ModifierItem(ModModifiers.DOUBLE_ITEM));
    DeferredItem<Item> STABILIZING_HOOK = HOOKS_REGISTRY.register("stabilizing_hook", () -> new ModifierItem(ModModifiers.NO_FLIP));
    DeferredItem<Item> HEAVY_HOOK = HOOKS_REGISTRY.register("heavy_hook", () -> new ModifierItem(ModModifiers.SLOWER_MOVING_SWEET_SPOTS));

    //bobbers
    DeferredItem<Item> BOBBER = BOBBERS_REGISTRY.register("bobber", SingleStackBasicItem::new);
    DeferredItem<Item> STEADY_BOBBER = BOBBERS_REGISTRY.register("steady_bobber", () -> new ModifierItem(ModModifiers.BIGGER_GREEN_SWEET_SPOTS));
    DeferredItem<Item> CLEAR_BOBBER = BOBBERS_REGISTRY.register("clear_bobber", () -> new ModifierItem(ModModifiers.SLOWER_VANISHING));
    DeferredItem<Item> AQUA_BOBBER = BOBBERS_REGISTRY.register("aqua_bobber", () -> new ModifierItem(ModModifiers.ADD_AQUA_SWEET_SPOT));
    DeferredItem<Item> VANILLA_BOBBER = BOBBERS_REGISTRY.register("vanilla_bobber", () -> new ModifierItem(ModModifiers.VANILLA_LOOT));

    //baits
    DeferredItem<Item> WORM = BAITS_REGISTRY.register("worm", () -> new ModifierItem(64, ModModifiers.DECREASES_LURE_TIME));
    DeferredItem<Item> ALMIGHTY_WORM = BAITS_REGISTRY.register("almighty_worm", () -> new ModifierItem(64, ModModifiers.DECREASES_LURE_TIME, ModModifiers.DECREASES_LURE_TIME));
    DeferredItem<Item> SEEKING_WORM = BAITS_REGISTRY.register("seeking_worm", () -> new ModifierItem(64, ModModifiers.DECREASES_LURE_TIME, ModModifiers.DECREASES_LURE_TIME));

    DeferredItem<Item> GUNPOWDER_BAIT = BAITS_REGISTRY.register("gunpowder_bait", () -> new ModifierItem(64, ModModifiers.DECREASES_LURE_TIME));
    DeferredItem<Item> CHERRY_BAIT = BAITS_REGISTRY.register("cherry_bait", () -> new ModifierItem(64, ModModifiers.DECREASES_LURE_TIME));
    DeferredItem<Item> LUSH_BAIT = BAITS_REGISTRY.register("lush_bait", () -> new ModifierItem(64, ModModifiers.DECREASES_LURE_TIME));
    DeferredItem<Item> SCULK_BAIT = BAITS_REGISTRY.register("sculk_bait", () -> new ModifierItem(64, ModModifiers.DECREASES_LURE_TIME));
    DeferredItem<Item> DRIPSTONE_BAIT = BAITS_REGISTRY.register("dripstone_bait", () -> new ModifierItem(64, ModModifiers.DECREASES_LURE_TIME));
    DeferredItem<Item> MURKWATER_BAIT = BAITS_REGISTRY.register("murkwater_bait", () -> new ModifierItem(64, ModModifiers.DECREASES_LURE_TIME));
    DeferredItem<Item> LEGENDARY_BAIT = BAITS_REGISTRY.register("legendary_bait", () -> new ModifierItem(64, ModModifiers.DECREASES_LURE_TIME));
    DeferredItem<Item> METEOROLOGICAL_BAIT = BAITS_REGISTRY.register("meteorological_bait", () -> new ModifierItem(64, ModModifiers.DECREASES_LURE_TIME));




    //bobber skin templates
    DeferredItem<Item> COLORFUL_BOBBER_SMITHING_TEMPLATE = TEMPLATES_REGISTRY.register("colorful_bobber_smithing_template", ColorfulSmithingTemplate::new);
    DeferredItem<Item> PEARL_BOBBER_SMITHING_TEMPLATE = TEMPLATES_REGISTRY.register("pearl_bobber_smithing_template", BasicItem::new);
    DeferredItem<Item> KIMBE_BOBBER_SMITHING_TEMPLATE = TEMPLATES_REGISTRY.register("kimbe_bobber_smithing_template", BasicItem::new);

    //rods
    DeferredItem<Item> ROD = RODS_REGISTRY.register("starcatcher_rod", StarcatcherFishingRod::new);

    //fishing rod skins
    DeferredItem<Item> NATURALIST_ROD = RODS_REGISTRY.register("naturalist_rod", StarcatcherFishingRod::new);
    DeferredItem<Item> ICEBORN_ROD = RODS_REGISTRY.register("iceborn_rod", StarcatcherFishingRod::new);
    DeferredItem<Item> MAGMAFORGED_ROD = RODS_REGISTRY.register("magmaforged_rod", StarcatcherFishingRod::new);
    DeferredItem<Item> SLIMED_ROD = RODS_REGISTRY.register("slimed_rod", StarcatcherFishingRod::new);
    DeferredItem<Item> SHARKTOOTH_ROD = RODS_REGISTRY.register("sharktooth_rod", StarcatcherFishingRod::new);
    DeferredItem<Item> AZURE_CRYSTAL_ROD = RODS_REGISTRY.register("azure_crystal_rod", StarcatcherFishingRod::new);
    DeferredItem<Item> GOOD_OLD_ROD = RODS_REGISTRY.register("good_old_rod", StarcatcherFishingRod::new);
    DeferredItem<Item> BAMBOO_ROD = RODS_REGISTRY.register("bamboo_rod", StarcatcherFishingRod::new);
    DeferredItem<Item> OBSIDIAN_ROD = RODS_REGISTRY.register("obsidian_rod", StarcatcherFishingRod::new);
    DeferredItem<Item> ALPHA_ROD = RODS_REGISTRY.register("alpha_rod", StarcatcherFishingRod::new);
    DeferredItem<Item> BONER_ROD = RODS_REGISTRY.register("boner_rod", StarcatcherFishingRod::new);
    DeferredItem<Item> SKY_ROD = RODS_REGISTRY.register("sky_rod", StarcatcherFishingRod::new);
    DeferredItem<Item> LUSH_GLOWBERRY_ROD = RODS_REGISTRY.register("lush_glowberry_rod", StarcatcherFishingRod::new);
    DeferredItem<Item> HUMBLE_ROD = RODS_REGISTRY.register("humble_rod", StarcatcherFishingRod::new);

    //secrets
    DeferredItem<Item> SECRET_NOTE = ITEMS_REGISTRY.register("secret_note", SecretNote::new);
    DeferredItem<Item> BROKEN_BOTTLE = ITEMS_REGISTRY.register("broken_bottle", BrokenBottle::new);

    //notes
    DeferredItem<Item> DRIFTING_WATERLOGGED_BOTTLE = ITEMS_REGISTRY.register("drifting_waterlogged_bottle", () -> new NoteContainer(SecretNote.Note.CRYSTAL_HOOK));

    DeferredItem<Item> SCALDING_BOTTLE = ITEMS_REGISTRY.register("scalding_bottle", () -> new NoteContainer(new Item.Properties().stacksTo(1).fireResistant(), SecretNote.Note.ARNWULF_1));

    DeferredItem<Item> BURNING_BOTTLE = ITEMS_REGISTRY.register("burning_bottle", () -> new NoteContainer(new Item.Properties().stacksTo(1).fireResistant(), SecretNote.Note.ARNWULF_2));

    DeferredItem<Item> HOPEFUL_BOTTLE = ITEMS_REGISTRY.register("hopeful_bottle", () -> new NoteContainer(SecretNote.Note.HOPEFUL_NOTE));

    DeferredItem<Item> HOPELESS_BOTTLE = ITEMS_REGISTRY.register("hopeless_bottle", () -> new NoteContainer(SecretNote.Note.HOPELESS_NOTE));

    DeferredItem<Item> TRUE_BLUE_BOTTLE = ITEMS_REGISTRY.register("true_blue_bottle", () -> new NoteContainer(SecretNote.Note.TRUE_BLUE));

    DeferredItem<Item> WITHERED_BOTTLE = ITEMS_REGISTRY.register("withered_bottle", () -> new NoteContainer(SecretNote.Note.WITHER));


    //cheater items
    DeferredItem<Item> AWARD_ALL_FISHES = DEV_REGISTRY.register("award_all_fishes", AwardAllFishes::new);
    DeferredItem<Item> AWARD_ONE_FISH = DEV_REGISTRY.register("award_one_fish", AwardOneFish::new);
    DeferredItem<Item> REVOKE_ALL_FISHES = DEV_REGISTRY.register("revoke_all_fishes", RevokeAllFishes::new);

    DeferredItem<Item> AWARD_ALL_TROPHIES = DEV_REGISTRY.register("award_all_trophies", AwardAllTrophies::new);
    DeferredItem<Item> REVOKE_ALL_TROPHIES = DEV_REGISTRY.register("revoke_all_trophies", RevokeAllTrophies::new);

    DeferredItem<Item> AWARD_ALL_SECRETS = DEV_REGISTRY.register("award_all_secrets", AwardAllSecrets::new);
    DeferredItem<Item> REVOKE_ALL_SECRETS = DEV_REGISTRY.register("revoke_all_secrets", RevokeAllSecrets::new);

    DeferredItem<Item> REVOKE_ALL_EXTRAS = DEV_REGISTRY.register("revoke_all_extras", RevokeAllExtras::new);

    //treasure
    DeferredItem<Item> WATERLOGGED_SATCHEL = ITEMS_REGISTRY.register("waterlogged_satchel", WaterloggedSatchel::new);

    DeferredItem<Item> FISH_BONES = ITEMS_REGISTRY.register("fish_bones", BasicItem::new);

    //
    //  ,---. ,--.         ,--.
    // /  .-' `--'  ,---.  |  ,---.   ,---.   ,---.
    // |  `-, ,--. (  .-'  |  .-.  | | .-. : (  .-'
    // |  .-' |  | .-'  `) |  | |  | \   --. .-'  `)
    // `--'   `--' `----'  `--' `--'  `----' `----'
    //

    //lake
    DeferredItem<Item> OBIDONTIEE = FISH_REGISTRY.register("obidontiee", FishItem::new);
    DeferredItem<Item> SILVERVEIL_PERCH = FISH_REGISTRY.register("silverveil_perch", FishItem::new);
    DeferredItem<Item> ELDERSCALE = FISH_REGISTRY.register("elderscale", FishItem::new);
    DeferredItem<Item> DRIFTFIN = FISH_REGISTRY.register("driftfin", FishItem::new);
    DeferredItem<Item> TWILIGHT_KOI = FISH_REGISTRY.register("twilight_koi", FishItem::new);
    DeferredItem<Item> THUNDER_BASS = FISH_REGISTRY.register("thunder_bass", FishItem::new);
    DeferredItem<Item> LIGHTNING_BASS = FISH_REGISTRY.register("lightning_bass", FishItem::new);
    DeferredItem<Item> BOOT = TRASH_REGISTRY.register("boot", BasicItem::new);

    //swamp
    DeferredItem<Item> SLUDGE_CATFISH = FISH_REGISTRY.register("sludge_catfish", FishItem::new);
    DeferredItem<Item> LILY_SNAPPER = FISH_REGISTRY.register("lily_snapper", FishItem::new);
    DeferredItem<Item> SAGE_CATFISH = FISH_REGISTRY.register("sage_catfish", FishItem::new);
    DeferredItem<Item> MOSSY_BOOT = TRASH_REGISTRY.register("mossy_boot", BasicItem::new);

    //darkoak_forest
    DeferredItem<Item> PALE_CARP = FISH_REGISTRY.register("pale_carp", FishItem::new);
    DeferredItem<Item> PALE_PINFISH = FISH_REGISTRY.register("pale_pinfish", FishItem::new);
    DeferredItem<Item> PINFISH = FISH_REGISTRY.register("pinfish", FishItem::new);

    //icy lake
    DeferredItem<Item> FROSTJAW_TROUT = FISH_REGISTRY.register("frostjaw_trout", FishItem::new);
    DeferredItem<Item> CRYSTALBACK_TROUT = FISH_REGISTRY.register("crystalback_trout", FishItem::new);
    DeferredItem<Item> AURORA = FISH_REGISTRY.register("aurora", FishItem::new);
    DeferredItem<Item> WINTERY_PIKE = FISH_REGISTRY.register("wintery_pike", FishItem::new);

    //warm lake (desert/savanna etc)
    DeferredItem<Item> SANDTAIL = FISH_REGISTRY.register("sandtail", FishItem::new);
    DeferredItem<Item> MIRAGE_CARP = FISH_REGISTRY.register("mirage_carp", FishItem::new);
    DeferredItem<Item> SCORCHFISH = FISH_REGISTRY.register("scorchfish", FishItem::new);
    DeferredItem<Item> CACTIFISH = FISH_REGISTRY.register("cactifish", FishItem::new);
    DeferredItem<Item> AGAVE_BREAM = FISH_REGISTRY.register("agave_bream", FishItem::new);

    //mountain
    DeferredItem<Item> SUNNY_STURGEON = FISH_REGISTRY.register("sunny_sturgeon", FishItem::new);
    DeferredItem<Item> ROCKGILL = FISH_REGISTRY.register("rockgill", FishItem::new);
    DeferredItem<Item> PEAKDWELLER = FISH_REGISTRY.register("peakdweller", FishItem::new);
    DeferredItem<Item> SUN_SEEKING_CARP = FISH_REGISTRY.register("sun_seeking_carp", FishItem::new);

    //cherry grove
    DeferredItem<Item> BLOSSOMFISH = FISH_REGISTRY.register("blossomfish", FishItem::new);
    DeferredItem<Item> PETALDRIFT_CARP = FISH_REGISTRY.register("petaldrift_carp", FishItem::new);
    DeferredItem<Item> PINK_KOI = FISH_REGISTRY.register("pink_koi", FishItem::new);
    DeferredItem<Item> MORGANITE = FISH_REGISTRY.register("morganite", FishItem::new);
    DeferredItem<Item> ROSE_SIAMESE_FISH = FISH_REGISTRY.register("rose_siamese_fish", FishItem::new);
    DeferredItem<Item> VESANI = FISH_REGISTRY.register("vesani", FishItem::new);

    //icy mountain
    DeferredItem<Item> CRYSTALBACK_STURGEON = FISH_REGISTRY.register("crystalback_sturgeon", FishItem::new);
    DeferredItem<Item> ICETOOTH_STURGEON = FISH_REGISTRY.register("icetooth_sturgeon", FishItem::new);
    DeferredItem<Item> BOREAL = FISH_REGISTRY.register("boreal", FishItem::new);
    DeferredItem<Item> CRYSTALBACK_BOREAL = FISH_REGISTRY.register("crystalback_boreal", FishItem::new);

    //rivers
    DeferredItem<Item> SILVERFIN_PIKE = FISH_REGISTRY.register("silverfin_pike", FishItem::new);
    DeferredItem<Item> WILLOW_BREAM = FISH_REGISTRY.register("willow_bream", FishItem::new);
    DeferredItem<Item> DRIFTING_BREAM = FISH_REGISTRY.register("drifting_bream", FishItem::new);
    DeferredItem<Item> DOWNFALL_BREAM = FISH_REGISTRY.register("downfall_bream", FishItem::new);
    DeferredItem<Item> HOLLOWBELLY_DARTER = FISH_REGISTRY.register("hollowbelly_darter", FishItem::new);
    DeferredItem<Item> MISTBACK_CHUB = FISH_REGISTRY.register("mistback_chub", FishItem::new);
    DeferredItem<Item> BLUEGIGI = FISH_REGISTRY.register("bluegigi", FishItem::new);
    DeferredItem<Item> DRIED_SEAWEED = TRASH_REGISTRY.register("dried_seaweed", FishItem::new);

    //icy river
    DeferredItem<Item> FROSTGILL_CHUB = FISH_REGISTRY.register("frostgill_chub", FishItem::new);
    DeferredItem<Item> CRYSTALBACK_MINNOW = FISH_REGISTRY.register("crystalback_minnow", FishItem::new);
    DeferredItem<Item> AZURE_CRYSTALBACK_MINNOW = FISH_REGISTRY.register("azure_crystalback_minnow", FishItem::new);
    DeferredItem<Item> BLUE_CRYSTAL_FIN = FISH_REGISTRY.register("blue_crystal_fin", FishItem::new);

    //saltwater
    DeferredItem<Item> IRONJAW_HERRING = FISH_REGISTRY.register("ironjaw_herring", FishItem::new);
    DeferredItem<Item> DEEPJAW_HERRING = FISH_REGISTRY.register("deepjaw_herring", FishItem::new);
    DeferredItem<Item> DUSKTAIL_SNAPPER = FISH_REGISTRY.register("dusktail_snapper", FishItem::new);
    DeferredItem<Item> JOEL = FISH_REGISTRY.register("joel", FishItem::new);
    DeferredItem<Item> REDSCALED_TUNA = FISH_REGISTRY.register("redscaled_tuna", FishItem::new);
    DeferredItem<Item> BIGEYE_TUNA = FISH_REGISTRY.register("bigeye_tuna", FishItem::new);
    DeferredItem<Item> SEA_BASS = FISH_REGISTRY.register("sea_bass", FishItem::new);
    DeferredItem<Item> WATERLOGGED_BOTTLE = TRASH_REGISTRY.register("waterlogged_bottle", BasicItem::new);

    //beaches
    DeferredItem<Item> CONCH = TRASH_REGISTRY.register("conch", BasicItem::new);
    DeferredItem<Item> CLAM = TRASH_REGISTRY.register("clam", BasicItem::new);

    //mushroom islands
    DeferredItem<Item> SHROOMFISH = FISH_REGISTRY.register("shroomfish", FishItem::new);
    DeferredItem<Item> SPOREFISH = FISH_REGISTRY.register("sporefish", FishItem::new);

    //underground
    DeferredItem<Item> GOLD_FAN = FISH_REGISTRY.register("gold_fan", FishItem::new);
    DeferredItem<Item> GEODE_EEL = FISH_REGISTRY.register("geode_eel", FishItem::new);

    //caves
    DeferredItem<Item> WHITEVEIL = FISH_REGISTRY.register("whiteveil", FishItem::new);
    DeferredItem<Item> BLACK_EEL = FISH_REGISTRY.register("black_eel", FishItem::new);
    DeferredItem<Item> AMETHYSTBACK = FISH_REGISTRY.register("amethystback", FishItem::new);
    DeferredItem<Item> STONEFISH = FISH_REGISTRY.register("stonefish", FishItem::new);

    //dripstone caves
    DeferredItem<Item> FOSSILIZED_ANGELFISH = FISH_REGISTRY.register("fossilized_angelfish", FishItem::new);
    DeferredItem<Item> DRIPFIN = FISH_REGISTRY.register("dripfin", FishItem::new);
    DeferredItem<Item> YELLOWSTONE_FISH = FISH_REGISTRY.register("yellowstone_fish", FishItem::new);

    //lush caves
    DeferredItem<Item> LUSH_PIKE = FISH_REGISTRY.register("lush_pike", FishItem::new);
    DeferredItem<Item> VIVID_MOSS = FISH_REGISTRY.register("vivid_moss", FishItem::new);
    DeferredItem<Item> THE_QUARRISH = FISH_REGISTRY.register("the_quarrish", FishItem::new);

    //deepslate
    DeferredItem<Item> GHOSTLY_PIKE = FISH_REGISTRY.register("ghostly_pike", FishItem::new);
    DeferredItem<Item> AQUAMARINE_PIKE = FISH_REGISTRY.register("aquamarine_pike", FishItem::new);
    DeferredItem<Item> GARNET_MACKEREL = FISH_REGISTRY.register("garnet_mackerel", FishItem::new);
    DeferredItem<Item> BRIGHT_AMETHYST_SNAPPER = FISH_REGISTRY.register("bright_amethyst_snapper", FishItem::new);
    DeferredItem<Item> DARK_AMETHYST_SNAPPER = FISH_REGISTRY.register("dark_amethyst_snapper", FishItem::new);
    DeferredItem<Item> DEEPSLATEFISH = FISH_REGISTRY.register("deepslatefish", FishItem::new);

    //deep dark
    DeferredItem<Item> SCULKFISH = FISH_REGISTRY.register("sculkfish", FishItem::new);
    DeferredItem<Item> WARD = FISH_REGISTRY.register("ward", FishItem::new);
    DeferredItem<Item> GLOWING_DARK = FISH_REGISTRY.register("glowing_dark", FishItem::new);

    //overworld surface lava
    DeferredItem<Item> SUNEATER = FISH_REGISTRY.register("suneater", FireResistantBasicItem::new);
    DeferredItem<Item> PYROTROUT = FISH_REGISTRY.register("pyrotrout", FireResistantBasicItem::new);
    DeferredItem<Item> OBSIDIAN_EEL = FISH_REGISTRY.register("obsidian_eel", FireResistantBasicItem::new);

    //overworld underground lava
    DeferredItem<Item> MOLTEN_SHRIMP = FISH_REGISTRY.register("molten_shrimp", FireResistantBasicItem::new);
    DeferredItem<Item> OBSIDIAN_CRAB = FISH_REGISTRY.register("obsidian_crab", FireResistantBasicItem::new);

    //overworld deepslate lava
    DeferredItem<Item> SCORCHED_BLOODSUCKER = FISH_REGISTRY.register("scorched_bloodsucker", FireResistantBasicItem::new);
    DeferredItem<Item> MOLTEN_DEEPSLATE_CRAB = FISH_REGISTRY.register("molten_deepslate_crab", FireResistantBasicItem::new);

    //nether
    DeferredItem<Item> EMBERGILL = FISH_REGISTRY.register("embergill", FireResistantBasicItem::new);
    DeferredItem<Item> SCALDING_PIKE = FISH_REGISTRY.register("scalding_pike", FireResistantBasicItem::new);
    DeferredItem<Item> CINDER_SQUID = FISH_REGISTRY.register("cinder_squid", FireResistantBasicItem::new);
    DeferredItem<Item> LAVA_CRAB = FISH_REGISTRY.register("lava_crab", FireResistantBasicItem::new);
    DeferredItem<Item> MAGMA_FISH = FISH_REGISTRY.register("magma_fish", FireResistantBasicItem::new);
    DeferredItem<Item> GLOWSTONE_SEEKER = FISH_REGISTRY.register("glowstone_seeker", FireResistantBasicItem::new);
    DeferredItem<Item> GLOWSTONE_PUFFERFISH = FISH_REGISTRY.register("glowstone_pufferfish", FireResistantBasicItem::new);
    DeferredItem<Item> WILLISH = FISH_REGISTRY.register("willish", FireResistantBasicItem::new);

    DeferredItem<Item> LAVA_CRAB_CLAW = TRASH_REGISTRY.register("lava_crab_claw", FireResistantBasicItem::new);

    //the end
    DeferredItem<Item> CHARFISH = FISH_REGISTRY.register("charfish", FishItem::new);
    DeferredItem<Item> CHORUS_CRAB = FISH_REGISTRY.register("chorus_crab", FishItem::new);
    DeferredItem<Item> END_GLOW = FISH_REGISTRY.register("end_glow", FishItem::new);
    DeferredItem<Item> VOIDBITER = FISH_REGISTRY.register("voidbiter", FishItem::new);

    //bucket
    DeferredItem<Item> STARCAUGHT_BUCKET = ITEMS_REGISTRY.register("starcaught_bucket", () -> new StarcaughtBucket(Fluids.WATER));
}
