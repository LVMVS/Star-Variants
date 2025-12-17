package com.wdiscute.starcatcher.registry.fishing.compat;

import com.wdiscute.starcatcher.StarcatcherTags;
import com.wdiscute.starcatcher.U;
import com.wdiscute.starcatcher.registry.fishing.FishingPropertiesRegistry;
import com.wdiscute.starcatcher.storage.FishProperties;
import com.wdiscute.starcatcher.storage.FishProperties.WorldRestrictions.Seasons;
import net.minecraft.tags.BiomeTags;

public class TideFishingProperties extends FishingPropertiesRegistry
{
    public static void bootstrap()
    {
        //
        //  ,--.   ,--.    ,--.
        //,-'  '-. `--'  ,-|  |  ,---.
        //'-.  .-' ,--. ' .-. | | .-. :
        //  |  |   |  | \ `-' | \   --.
        //  `--'   `--'  `---'   `----'
        //

        register(overworldColdLakeFish(U.holderItem("tide", "trout"))
                .withBucketedFish(U.holderItem("tide", "trout_bucket"))
                .withEntityToSpawn(U.holderEntity("tide", "trout"))
                .withSeasons(Seasons.SPRING, Seasons.WINTER)
                .withSizeAndWeight(FishProperties.sw(50, 20, 2000, 1600))
                .withDifficulty(FishProperties.Difficulty.EASY_MOVING)
                .withDaytime(FishProperties.Daytime.DAY)
                .withRarity(FishProperties.Rarity.UNCOMMON)
        );

        register(overworldLakeFish(U.holderItem("tide", "bass"))
                .withBucketedFish(U.holderItem("tide", "bass_bucket"))
                .withEntityToSpawn(U.holderEntity("tide", "bass"))
                .withSeasons(Seasons.SPRING, Seasons.WINTER)
                .withSizeAndWeight(FishProperties.sw(50, 20, 2000, 1600))
                .withWeather(FishProperties.Weather.CLEAR)
        );

        register(overworldLakeFish(U.holderItem("tide", "yellow_perch"))
                .withBucketedFish(U.holderItem("tide", "yellow_perch_bucket"))
                .withEntityToSpawn(U.holderEntity("tide", "yellow_perch"))
                .withSeasons(Seasons.SPRING, Seasons.SUMMER)
                .withSizeAndWeight(FishProperties.sw(25, 10, 200, 20))
                .withDifficulty(FishProperties.Difficulty.EASY_VANISHING)
                .withWeather(FishProperties.Weather.RAIN)
        );

        register(overworldMountainFish(U.holderItem("tide", "bluegill"))
                .withBucketedFish(U.holderItem("tide", "bluegill_bucket"))
                .withEntityToSpawn(U.holderEntity("tide", "bluegill"))
                .withSeasons(Seasons.SPRING, Seasons.SUMMER)
                .withSizeAndWeight(FishProperties.sw(15, 5, 200, 20))
        );

        register(overworldWarmMountainFish(U.holderItem("tide", "mint_carp"))
                .withBucketedFish(U.holderItem("tide", "mint_carp_bucket"))
                .withEntityToSpawn(U.holderEntity("tide", "mint_carp"))
                .withSeasons(Seasons.SUMMER, Seasons.AUTUMN)
                .withSizeAndWeight(FishProperties.sw(80, 40, 10000, 5000))
                .withDifficulty(FishProperties.Difficulty.EASY_VANISHING)
                .withWeather(FishProperties.Weather.RAIN)
                .withRarity(FishProperties.Rarity.UNCOMMON)
        );

        register(overworldColdRiverFish(U.holderItem("tide", "pike"))
                .withBucketedFish(U.holderItem("tide", "pike_bucket"))
                .withEntityToSpawn(U.holderEntity("tide", "pike"))
                .withSeasons(Seasons.WINTER)
                .withSizeAndWeight(FishProperties.sw(100, 50, 15000, 5000))
        );

        register(overworldWarmLakeFish(U.holderItem("tide", "guppy"))
                .withBucketedFish(U.holderItem("tide", "guppy_bucket"))
                .withEntityToSpawn(U.holderEntity("tide", "guppy"))
                .withSizeAndWeight(FishProperties.sw(4, 1, 2, 1))
                .withDifficulty(FishProperties.Difficulty.MEDIUM)
                .withDaytime(FishProperties.Daytime.NIGHT)
        );

        register(overworldColdLakeFish(U.holderItem("tide", "catfish"))
                .withBucketedFish(U.holderItem("tide", "catfish_bucket"))
                .withEntityToSpawn(U.holderEntity("tide", "catfish"))
                .withSizeAndWeight(FishProperties.sw(100, 50, 15000, 5000))
                .withDifficulty(FishProperties.Difficulty.EASY_VANISHING)
                .withRarity(FishProperties.Rarity.UNCOMMON)
        );

        register(overworldColdLakeFish(U.holderItem("tide", "clayfish"))
                .withBucketedFish(U.holderItem("tide", "clayfish_bucket"))
                .withEntityToSpawn(U.holderEntity("tide", "clayfish"))
                .withSizeAndWeight(FishProperties.sw(15, 5, 200, 100))
                .withWeather(FishProperties.Weather.RAIN)
        );

        //tide saltwater
        register(overworldOceanFish(U.holderItem("tide", "tuna"))
                .withBucketedFish(U.holderItem("tide", "tuna_bucket"))
                .withEntityToSpawn(U.holderEntity("tide", "tuna"))
                .withSeasons(Seasons.AUTUMN, Seasons.WINTER)
                .withSizeAndWeight(FishProperties.sw(200, 100, 200000, 150000))
        );

        register(overworldColdOceanFish(U.holderItem("tide", "ocean_perch"))
                .withBucketedFish(U.holderItem("tide", "ocean_perch_bucket"))
                .withEntityToSpawn(U.holderEntity("tide", "ocean_perch"))
                .withSeasons(Seasons.AUTUMN, Seasons.WINTER)
                .withSizeAndWeight(FishProperties.sw(50, 20, 2000, 1600))
                .withDifficulty(FishProperties.Difficulty.EASY_MOVING)
                .withDaytime(FishProperties.Daytime.NIGHT)
                .withRarity(FishProperties.Rarity.UNCOMMON)
        );

        register(overworldOceanFish(U.holderItem("tide", "mackerel"))
                .withBucketedFish(U.holderItem("tide", "mackerel_bucket"))
                .withEntityToSpawn(U.holderEntity("tide", "mackerel"))
                .withSeasons(Seasons.AUTUMN)
                .withSizeAndWeight(FishProperties.sw(35, 15, 500, 400))
                .withDifficulty(FishProperties.Difficulty.MEDIUM)
        );

        register(overworldWarmOceanFish(U.holderItem("tide", "angelfish"))
                .withBucketedFish(U.holderItem("tide", "angelfish_bucket"))
                .withEntityToSpawn(U.holderEntity("tide", "angelfish"))
                .withSeasons(Seasons.SUMMER)
                .withSizeAndWeight(FishProperties.sw(15, 5, 100, 5))
                .withWeather(FishProperties.Weather.RAIN)
        );

        register(overworldOceanFish(U.holderItem("tide", "barracuda"))
                .withBucketedFish(U.holderItem("tide", "barracuda_bucket"))
                .withEntityToSpawn(U.holderEntity("tide", "barracuda"))
                .withSizeAndWeight(FishProperties.sw(150, 50, 30000, 20000))
                .withRarity(FishProperties.Rarity.RARE)
                .withDaytime(FishProperties.Daytime.NIGHT)
                .withWeather(FishProperties.Weather.RAIN)
                .withDifficulty(FishProperties.Difficulty.MEDIUM_MOVING)
        );

        register(overworldWarmOceanFish(U.holderItem("tide", "sailfish"))
                .withBucketedFish(U.holderItem("tide", "sailfish_bucket"))
                .withEntityToSpawn(U.holderEntity("tide", "sailfish"))
                .withSeasons(Seasons.SUMMER)
                .withSizeAndWeight(FishProperties.sw(250, 50, 100000, 50000))
                .withWeather(FishProperties.Weather.RAIN)
        );

        //tide underground
        register(overworldCavesFish(U.holderItem("tide", "cave_eel"))
                //no bucketed version
                //no entity version
                .withSizeAndWeight(FishProperties.sw(15, 5, 5, 3))
                .withDifficulty(FishProperties.Difficulty.EASY_MOVING)
        );

        register(overworldCavesFish(U.holderItem("tide", "crystal_shrimp"))
                //no bucketed version
                //no entity version
                .withSizeAndWeight(FishProperties.sw(10, 5, 2, 1))
        );

        register(overworldCavesFish(U.holderItem("tide", "iron_tetra"))
                //no bucketed version
                //no entity version
                .withSizeAndWeight(FishProperties.sw(4, 1, 2, 1))
                .withDifficulty(FishProperties.Difficulty.MEDIUM_VANISHING)
        );

        register(overworldCavesFish(U.holderItem("tide", "glowfish"))
                //no bucketed version
                //no entity version
                .withSizeAndWeight(FishProperties.sw(20, 10, 10, 5))
                .withRarity(FishProperties.Rarity.UNCOMMON)
                .withDifficulty(FishProperties.Difficulty.MEDIUM)
        );

        register(overworldCavesFish(U.holderItem("tide", "anglerfish"))
                //no bucketed version
                //no entity version
                .withSizeAndWeight(FishProperties.sw(100, 50, 20000, 15000))
        );

        register(overworldCavesFish(U.holderItem("tide", "cave_crawler"))
                //no bucketed version
                //no entity version
                .withSizeAndWeight(FishProperties.sw(30, 10, 1000, 500))
                .withDifficulty(FishProperties.Difficulty.EASY_MOVING)
        );

        register(overworldCavesFish(U.holderItem("tide", "gilded_minnow"))
                //no bucketed version
                //no entity version
                .withSizeAndWeight(FishProperties.sw(5, 2, 10, 6))
        );

        //tide deepslate
        register(overworldDeepslateFish(U.holderItem("tide", "deep_grouper"))
                //no bucketed version
                //no entity version
                .withSizeAndWeight(FishProperties.sw(200, 50, 200000, 100000))
                .withDifficulty(FishProperties.Difficulty.EASY_VANISHING)
        );

        register(overworldDeepslateFish(U.holderItem("tide", "shadow_snapper"))
                //no bucketed version
                //no entity version
                .withSizeAndWeight(FishProperties.sw(80, 40, 10000, 5000))
                .withDifficulty(FishProperties.Difficulty.MEDIUM)
        );

        register(overworldDeepslateFish(U.holderItem("tide", "abyss_angler"))
                //no bucketed version
                //no entity version
                .withSizeAndWeight(FishProperties.sw(100, 50, 20000, 15000))
                .withRarity(FishProperties.Rarity.EPIC)
                .withDifficulty(FishProperties.Difficulty.HARD_MOVING)
                .withBaseChance(2)
        );

        register(overworldDeepslateFish(U.holderItem("tide", "lapis_lanternfish"))
                //no bucketed version
                //no entity version
                .withSizeAndWeight(FishProperties.sw(20, 10, 100, 5))
                .withDifficulty(FishProperties.Difficulty.MEDIUM)
        );

        register(overworldDeepslateFish(U.holderItem("tide", "luminescent_jellyfish"))
                //no bucketed version
                //no entity version
                .withSizeAndWeight(FishProperties.sw(50, 30, 5000, 3000))
                .withRarity(FishProperties.Rarity.UNCOMMON)
                .withDifficulty(FishProperties.Difficulty.EASY_VANISHING)
        );

        register(overworldDeepslateFish(U.holderItem("tide", "crystalline_carp"))
                //no bucketed version
                //no entity version
                .withSizeAndWeight(FishProperties.sw(80, 30, 10000, 5000))
                .withDifficulty(FishProperties.Difficulty.HARD)
                .withRarity(FishProperties.Rarity.RARE)
        );

        register(overworldDeepslateFish(U.holderItem("tide", "bedrock_tetra"))
                //no bucketed version
                //no entity version
                .withSizeAndWeight(FishProperties.sw(4, 1, 2, 1))
                .withRarity(FishProperties.Rarity.EPIC)
                .withDifficulty(FishProperties.Difficulty.HARD)
        );

        //tide biome specific
        register(fish(U.holderItem("tide", "prarie_pike"))
                //no bucketed version
                //no entity version
                .withSizeAndWeight(FishProperties.sw(100, 50, 15000, 10000))
                .withWorldRestrictions(FishProperties.WorldRestrictions.DEFAULT.withBiomes(U.rl("minecraft", "plains")))
                .withRarity(FishProperties.Rarity.UNCOMMON)
        );

        register(fish(U.holderItem("tide", "sandskipper"))
                //no bucketed version
                //no entity version
                .withSeasons(Seasons.SUMMER)
                .withSizeAndWeight(FishProperties.sw(30, 10, 1000, 500))
                .withWorldRestrictions(FishProperties.WorldRestrictions.DEFAULT.withBiomes(U.rl("minecraft", "desert")))
                .withRarity(FishProperties.Rarity.UNCOMMON)
                .withDifficulty(FishProperties.Difficulty.SINGLE_BIG_FAST_MOVING)
        );

        register(overworldCherryGroveFish(U.holderItem("tide", "blossom_bass"))
                //no bucketed version
                //no entity version
                .withSeasons(Seasons.SPRING)
                .withSizeAndWeight(FishProperties.sw(50, 20, 5000, 3000))
                .withDifficulty(FishProperties.Difficulty.MEDIUM)
                .withRarity(FishProperties.Rarity.UNCOMMON)
        );

        register(overworldFish(U.holderItem("tide", "oakfish"))
                //no bucketed version
                //no entity version
                .withSeasons(Seasons.SPRING, Seasons.AUTUMN)
                .withSizeAndWeight(FishProperties.sw(40, 20, 3000, 2000))
                .withWorldRestrictions(FishProperties.WorldRestrictions.OVERWORLD.withBiomesTags(BiomeTags.IS_FOREST.location()))
                .withDifficulty(FishProperties.Difficulty.MEDIUM)
        );

        register(overworldColdLakeFish(U.holderItem("tide", "frostbite_flounder"))
                //no bucketed version
                //no entity version
                .withSeasons(Seasons.AUTUMN, Seasons.WINTER)
                .withSizeAndWeight(FishProperties.sw(60, 30, 6000, 4000))
                .withDifficulty(FishProperties.Difficulty.EASY_VANISHING)
        );

        register(overworldFish(U.holderItem("tide", "mirage_catfish"))
                //no bucketed version
                //no entity version
                .withSeasons(Seasons.SUMMER)
                .withSizeAndWeight(FishProperties.sw(100, 50, 15000, 10000))
                .withRarity(FishProperties.Rarity.EPIC)
                .withDifficulty(FishProperties.Difficulty.FOUR_BIG)
                .withWorldRestrictions(FishProperties.WorldRestrictions.OVERWORLD.withBiomesTags(BiomeTags.IS_BADLANDS.location()))
        );

        register(overworldDeepDarkFish(U.holderItem("tide", "echofin_snapper"))
                //no bucketed version
                //no entity version
                .withSizeAndWeight(FishProperties.sw(80, 30, 10000, 5000))
                .withRarity(FishProperties.Rarity.RARE)
                .withDifficulty(FishProperties.Difficulty.HARD_VANISHING)
        );

        register(overworldFish(U.holderItem("tide", "sunspike_goby"))
                //no bucketed version
                //no entity version
                .withSizeAndWeight(FishProperties.sw(15, 5, 100, 5))
                .withWorldRestrictions(FishProperties.WorldRestrictions.OVERWORLD.withBiomesTags(BiomeTags.IS_BADLANDS.location()))
                .withRarity(FishProperties.Rarity.EPIC)
                .withDifficulty(FishProperties.Difficulty.HARD)
        );

        register(overworldFish(U.holderItem("tide", "birch_trout"))
                //no bucketed version
                //no entity version
                .withSizeAndWeight(FishProperties.sw(50, 20, 2000, 1700))
                .withWorldRestrictions(FishProperties.WorldRestrictions.OVERWORLD.withBiomesTags(StarcatcherTags.IS_BIRCH_FOREST))
                .withDifficulty(FishProperties.Difficulty.MEDIUM)
        );

        register(overworldMountainFish(U.holderItem("tide", "stonefish"))
                //no bucketed version
                //no entity version
                .withSizeAndWeight(FishProperties.sw(40, 20, 2000, 1700))
                .withDifficulty(FishProperties.Difficulty.MEDIUM)
        );

        register(overworldDripstoneCavesFish(U.holderItem("tide", "dripstone_darter"))
                //no bucketed version
                //no entity version
                .withSizeAndWeight(FishProperties.sw(5, 2, 5, 1))
                .withRarity(FishProperties.Rarity.UNCOMMON)
                .withDifficulty(FishProperties.Difficulty.HARD)
        );

        register(overworldSwampFish(U.holderItem("tide", "slimefin_snapper"))
                //no bucketed version
                //no entity version
                .withSizeAndWeight(FishProperties.sw(80, 40, 10000, 10000))
                .withRarity(FishProperties.Rarity.RARE)
                .withDifficulty(FishProperties.Difficulty.SINGLE_BIG_FAST)
        );

        register(overworldMushroomFieldsFish(U.holderItem("tide", "sporestalker"))
                //no bucketed version
                //no entity version
                .withSizeAndWeight(FishProperties.sw(50, 20, 5000, 3000))
                .withRarity(FishProperties.Rarity.UNCOMMON)
                .withDifficulty(FishProperties.Difficulty.THIN_NO_DECAY)
        );

        register(overworldJungleFish(U.holderItem("tide", "leafback"))
                //no bucketed version
                //no entity version
                .withSeasons(Seasons.SPRING, Seasons.AUTUMN)
                .withSizeAndWeight(FishProperties.sw(40, 20, 3000, 2000))
                .withRarity(FishProperties.Rarity.EPIC)
                .withDifficulty(FishProperties.Difficulty.FOUR_BIG_MOVING)
        );

        register(overworldLushCavesFish(U.holderItem("tide", "fluttergill"))
                //no bucketed version
                //no entity version
                .withSizeAndWeight(FishProperties.sw(30, 10, 1000, 500))
                .withRarity(FishProperties.Rarity.UNCOMMON)
                .withDifficulty(FishProperties.Difficulty.MEDIUM)
        );

        register(overworldTaigaFish(U.holderItem("tide", "pine_perch"))
                //no bucketed version
                //no entity version
                .withSeasons(Seasons.SPRING, Seasons.AUTUMN)
                .withSizeAndWeight(FishProperties.sw(25, 10, 500, 300))
                .withDifficulty(FishProperties.Difficulty.MEDIUM)
        );

        //missing structure restriction support to add windbass and aquathorn from tide mod

        //tide overworld lava
        register(overworldSurfaceLava(U.holderItem("tide", "ember_koi"))
                //no bucketed version
                //no entity version
                .withSizeAndWeight(FishProperties.sw(40, 20, 3000, 2000))
                .withDifficulty(FishProperties.Difficulty.FOUR_BIG)
                .withRarity(FishProperties.Rarity.EPIC)
        );

        register(overworldSurfaceLava(U.holderItem("tide", "inferno_guppy"))
                //no bucketed version
                //no entity version
                .withSizeAndWeight(FishProperties.sw(4, 1, 20, 2))
                .withDifficulty(FishProperties.Difficulty.HARD_MOVING)
                .withRarity(FishProperties.Rarity.RARE)
        );

        register(overworldSurfaceLava(U.holderItem("tide", "obsidian_pike"))
                //no bucketed version
                //no entity version
                .withSeasons(Seasons.SUMMER)
                .withSizeAndWeight(FishProperties.sw(100, 5, 15000, 10000))
                .withRarity(FishProperties.Rarity.UNCOMMON)
                .withDifficulty(FishProperties.Difficulty.HARD)
        );

        register(overworldSurfaceLava(U.holderItem("tide", "volcano_tuna"))
                //no bucketed version
                //no entity version
                .withSizeAndWeight(FishProperties.sw(200, 100, 150000, 50000))
                .withRarity(FishProperties.Rarity.RARE)
                .withDifficulty(FishProperties.Difficulty.HARD_MOVING)
        );

        //tide nether
        register(netherLavaFish(U.holderItem("tide", "magma_mackerel"))
                //no bucketed version
                //no entity version
                .withSizeAndWeight(FishProperties.sw(35, 15, 500, 300))
                .withDifficulty(FishProperties.Difficulty.HARD)
        );

        register(netherLavaBasaltDeltasFish(U.holderItem("tide", "ashen_perch"))
                //no bucketed version
                //no entity version
                .withSizeAndWeight(FishProperties.sw(25, 10, 200, 100))
                .withDifficulty(FishProperties.Difficulty.MEDIUM)
        );

        register(netherLavaSoulSandValleyFish(U.holderItem("tide", "soulscaler"))
                //no bucketed version
                //no entity version
                .withSizeAndWeight(FishProperties.sw(80, 30, 10000, 5000))
                .withRarity(FishProperties.Rarity.RARE)
                .withDifficulty(FishProperties.Difficulty.MEDIUM_VANISHING_MOVING)
        );

        register(netherLavaWarpedForestFish(U.holderItem("tide", "warped_guppy"))
                //no bucketed version
                //no entity version
                .withSizeAndWeight(FishProperties.sw(4, 1, 4, 1))
                .withDifficulty(FishProperties.Difficulty.HARD_MOVING)
        );

        register(netherLavaCrimsonForestFish(U.holderItem("tide", "crimson_fangjaw"))
                //no bucketed version
                //no entity version
                .withSizeAndWeight(FishProperties.sw(100, 50, 15000, 10000))
                .withDifficulty(FishProperties.Difficulty.MEDIUM)
                .withRarity(FishProperties.Rarity.UNCOMMON)
        );

        register(netherLavaSoulSandValleyFish(U.holderItem("tide", "witherfin"))
                //no bucketed version
                //no entity version
                .withSizeAndWeight(FishProperties.sw(100, 50, 15000, 10000))
                .withRarity(FishProperties.Rarity.EPIC)
                .withDifficulty(FishProperties.Difficulty.HARD_VANISHING)
        );

        register(netherLavaFish(U.holderItem("tide", "blazing_swordfish"))
                //no bucketed version
                //no entity version
                .withSizeAndWeight(FishProperties.sw(200, 100, 100000, 50000))
                .withRarity(FishProperties.Rarity.EPIC)
                .withDifficulty(FishProperties.Difficulty.FOUR_BIG)
        );

        //tide end
        register(endFish(U.holderItem("tide", "endstone_perch"))
                //no bucketed version
                //no entity version
                .withSizeAndWeight(FishProperties.sw(25, 10, 500, 300))
                .withDifficulty(FishProperties.Difficulty.MEDIUM_MOVING)
        );

        register(endFish(U.holderItem("tide", "enderfin"))
                //no bucketed version
                //no entity version
                .withSizeAndWeight(FishProperties.sw(50, 20, 5000, 3000))
                .withRarity(FishProperties.Rarity.UNCOMMON)
                .withDifficulty(FishProperties.Difficulty.MEDIUM)
        );

        register(endFish(U.holderItem("tide", "endergazer"))
                //no bucketed version
                //no entity version
                .withSizeAndWeight(FishProperties.sw(60, 30, 6000, 4000))
                .withDifficulty(FishProperties.Difficulty.HARD_MOVING)
                .withRarity(FishProperties.Rarity.EPIC)
        );

        register(endOuterIslandsFish(U.holderItem("tide", "purpur_pike"))
                //no bucketed version
                //no entity version
                .withSizeAndWeight(FishProperties.sw(100, 50, 15000, 10000))
                .withRarity(FishProperties.Rarity.UNCOMMON)
                .withDifficulty(FishProperties.Difficulty.MEDIUM_VANISHING)
        );

        register(endOuterIslandsFish(U.holderItem("tide", "chorus_cod"))
                //no bucketed version
                //no entity version
                .withSizeAndWeight(FishProperties.sw(100, 50, 15000, 10000))
                .withDifficulty(FishProperties.Difficulty.MEDIUM_VANISHING_MOVING)
                .withRarity(FishProperties.Rarity.EPIC)
        );

        register(endFish(U.holderItem("tide", "elytrout"))
                //no bucketed version
                //no entity version
                .withSizeAndWeight(FishProperties.sw(50, 20, 5000, 3000))
                .withRarity(FishProperties.Rarity.RARE)
                .withDifficulty(FishProperties.Difficulty.HARD)
        );

        register(endFish(U.holderItem("tide", "voidseeker"))
                //no bucketed version
                //no entity version
                .withSizeAndWeight(FishProperties.sw(100, 50, 15000, 10000))
                .withBaitRestrictions(FishProperties.BaitRestrictions.LEGENDARY_BAIT)
                .withRarity(FishProperties.Rarity.LEGENDARY)
                .withDifficulty(FishProperties.Difficulty.THIN_NO_DECAY_NOT_FORGIVING)
        );

        //TODO put into corresponding category
        register(overworldLakeFish(U.holderItem("tide", "midas_fish"))
                //no bucketed version
                //no entity version
                .withSizeAndWeight(FishProperties.sw(800, 50, 310000, 120000))
                .withWeather(FishProperties.Weather.THUNDER)
                .withBaitRestrictions(FishProperties.BaitRestrictions.LEGENDARY_BAIT)
                .withRarity(FishProperties.Rarity.LEGENDARY)
                .withDifficulty(FishProperties.Difficulty.THREE_BIG_TWO_THIN_VANISHING)
        );

        register(overworldOceanFish(U.holderItem("tide", "shooting_starfish"))
                //no bucketed version
                //no entity version
                .withSizeAndWeight(FishProperties.sw(30, 10, 1000, 500))
                .withBaitRestrictions(FishProperties.BaitRestrictions.LEGENDARY_BAIT)
                .withRarity(FishProperties.Rarity.LEGENDARY)
                .withDaytime(FishProperties.Daytime.MIDNIGHT)
                .withDifficulty(FishProperties.Difficulty.HARD)
        );
    }
}
