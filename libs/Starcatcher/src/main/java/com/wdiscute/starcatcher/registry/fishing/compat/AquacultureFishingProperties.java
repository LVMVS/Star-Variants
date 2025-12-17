package com.wdiscute.starcatcher.registry.fishing.compat;

import com.wdiscute.starcatcher.U;
import com.wdiscute.starcatcher.registry.fishing.FishingPropertiesRegistry;
import com.wdiscute.starcatcher.storage.FishProperties;
import com.wdiscute.starcatcher.storage.FishProperties.WorldRestrictions.Seasons;

public class AquacultureFishingProperties extends FishingPropertiesRegistry
{
    public static void bootstrap()
    {

        //
        //                                                   ,--.   ,--.                                 ,---.
        // ,--,--.  ,---.  ,--.,--.  ,--,--.  ,---. ,--.,--. |  | ,-'  '-. ,--.,--. ,--.--.  ,---.      '.-.  \
        //' ,-.  | | .-. | |  ||  | ' ,-.  | | .--' |  ||  | |  | '-.  .-' |  ||  | |  .--' | .-. :      .-' .'
        //\ '-'  | ' '-' | '  ''  ' \ '-'  | \ `--. '  ''  ' |  |   |  |   '  ''  ' |  |    \   --.     /   '-.
        // `--`--'  `-|  |  `----'   `--`--'  `---'  `----'  `--'   `--'    `----'  `--'     `----'     '-----'
        //            `--'

        //freshwater
        register(overworldRiverFish(U.holderItem("aquaculture", "smallmouth_bass"))
                .withBucketedFish(U.holderItem("aquaculture", "smallmouth_bass_bucket"))
                .withEntityToSpawn(U.holderEntity("aquaculture", "smallmouth_bass"))
                .withSeasons(Seasons.SPRING, Seasons.SUMMER, Seasons.AUTUMN)
                .withSizeAndWeight(FishProperties.sw(30, 10, 1500, 500))
                .withDifficulty(FishProperties.Difficulty.MEDIUM)
                .withDaytime(FishProperties.Daytime.DAY)
                .withRarity(FishProperties.Rarity.UNCOMMON)
        );

        register(overworldRiverFish(U.holderItem("aquaculture", "bluegill"))
                .withBucketedFish(U.holderItem("aquaculture", "smallmouth_bass_bucket"))
                .withEntityToSpawn(U.holderEntity("aquaculture", "smallmouth_bass"))
                .withSeasons(Seasons.SUMMER, Seasons.AUTUMN)
                .withSizeAndWeight(FishProperties.sw(15, 3, 300, 200))
        );

        register(overworldRiverFish(U.holderItem("aquaculture", "brown_trout"))
                .withBucketedFish(U.holderItem("aquaculture", "brown_trout_bucket"))
                .withEntityToSpawn(U.holderEntity("aquaculture", "brown_trout"))
                .withSeasons(Seasons.SUMMER, Seasons.AUTUMN, Seasons.WINTER)
                .withSizeAndWeight(FishProperties.sw(45, 15, 3000, 2000))
                .withDaytime(FishProperties.Daytime.NIGHT)
                .withWeather(FishProperties.Weather.CLEAR)
        );

        register(overworldRiverFish(U.holderItem("aquaculture", "carp"))
                .withBucketedFish(U.holderItem("aquaculture", "carp_bucket"))
                .withEntityToSpawn(U.holderEntity("aquaculture", "carp"))
                .withSeasons(Seasons.SPRING, Seasons.WINTER)
                .withSizeAndWeight(FishProperties.sw(60, 20, 10000, 4000))
                .withDifficulty(FishProperties.Difficulty.HARD)
                .withRarity(FishProperties.Rarity.RARE)
                .withWeather(FishProperties.Weather.RAIN)
        );

        register(overworldMountainFish(U.holderItem("aquaculture", "catfish"))
                .withBucketedFish(U.holderItem("aquaculture", "catfish_bucket"))
                .withEntityToSpawn(U.holderEntity("aquaculture", "catfish"))
                .withSizeAndWeight(FishProperties.sw(150, 40, 100000, 25000))
                .withDifficulty(FishProperties.Difficulty.THIN_NO_DECAY_NOT_FORGIVING)
                .withRarity(FishProperties.Rarity.EPIC)
                .withWeather(FishProperties.Weather.RAIN)
        );

        register(overworldMountainFish(U.holderItem("aquaculture", "gar"))
                .withBucketedFish(U.holderItem("aquaculture", "gar_bucket"))
                .withEntityToSpawn(U.holderEntity("aquaculture", "gar"))
                .withSeasons(Seasons.SPRING, Seasons.WINTER)
                .withSizeAndWeight(FishProperties.sw(160, 30, 160000, 20000))
        );

        register(overworldLakeFish(U.holderItem("aquaculture", "minnow"))
                .withBucketedFish(U.holderItem("aquaculture", "minnow_bucket"))
                .withEntityToSpawn(U.holderEntity("aquaculture", "minnow"))
                .withSizeAndWeight(FishProperties.sw(6, 4, 10, 4))
        );

        register(overworldLakeFish(U.holderItem("aquaculture", "muskellunge"))
                .withBucketedFish(U.holderItem("aquaculture", "muskellunge_bucket"))
                .withEntityToSpawn(U.holderEntity("aquaculture", "muskellunge"))
                .withSizeAndWeight(FishProperties.sw(100, 10, 7000, 3000))
                .withRarity(FishProperties.Rarity.RARE)
                .withDaytime(FishProperties.Daytime.MIDNIGHT)
        );

        register(overworldLakeFish(U.holderItem("aquaculture", "perch"))
                .withBucketedFish(U.holderItem("aquaculture", "perch_bucket"))
                .withEntityToSpawn(U.holderEntity("aquaculture", "perch"))
                .withSeasons(Seasons.SUMMER, Seasons.AUTUMN)
                .withSizeAndWeight(FishProperties.sw(20, 5, 500, 200))
        );

        //arid
        register(overworldWarmMountainFish(U.holderItem("aquaculture", "bayad"))
                .withBucketedFish(U.holderItem("aquaculture", "bayad_bucket"))
                .withEntityToSpawn(U.holderEntity("aquaculture", "bayad"))
                .withSeasons(Seasons.SUMMER, Seasons.AUTUMN)
                .withSizeAndWeight(FishProperties.sw(170, 30, 150000, 20000))
                .withRarity(FishProperties.Rarity.UNCOMMON)
                .withDifficulty(FishProperties.Difficulty.MEDIUM)
                .withDaytime(FishProperties.Daytime.NIGHT)
        );

        register(overworldWarmLakeFish(U.holderItem("aquaculture", "boulti"))
                .withBucketedFish(U.holderItem("aquaculture", "boulti_bucket"))
                .withEntityToSpawn(U.holderEntity("aquaculture", "boulti"))
                .withSeasons(Seasons.SUMMER)
                .withSizeAndWeight(FishProperties.sw(40, 10, 4000, 300))
                .withRarity(FishProperties.Rarity.RARE)
                .withDaytime(FishProperties.Daytime.DAY)
                .withDifficulty(FishProperties.Difficulty.HARD)
        );

        register(overworldWarmMountainFish(U.holderItem("aquaculture", "capitaine"))
                .withBucketedFish(U.holderItem("aquaculture", "capitaine_bucket"))
                .withEntityToSpawn(U.holderEntity("aquaculture", "capitaine"))
                .withSeasons(Seasons.SPRING, Seasons.SUMMER)
                .withSizeAndWeight(FishProperties.sw(130, 50, 12000, 3000))
        );

        register(overworldWarmMountainFish(U.holderItem("aquaculture", "synodontis"))
                .withBucketedFish(U.holderItem("aquaculture", "synodontis_bucket"))
                .withEntityToSpawn(U.holderEntity("aquaculture", "synodontis"))
                .withSizeAndWeight(FishProperties.sw(35, 15, 1000, 300))
                .withDifficulty(FishProperties.Difficulty.HARD_MOVING)
                .withRarity(FishProperties.Rarity.EPIC)
        );

        //arctic ocean
        register(overworldColdOceanFish(U.holderItem("aquaculture", "atlantic_cod"))
                .withBucketedFish(U.holderItem("aquaculture", "atlantic_cod_bucket"))
                .withEntityToSpawn(U.holderEntity("aquaculture", "atlantic_cod"))
                .withSeasons(Seasons.WINTER)
                .withSizeAndWeight(FishProperties.sw(100, 50, 15000, 10000))
                .withDaytime(FishProperties.Daytime.DAY)
        );

        register(overworldColdOceanFish(U.holderItem("aquaculture", "blackfish"))
                .withBucketedFish(U.holderItem("aquaculture", "blackfish_bucket"))
                .withEntityToSpawn(U.holderEntity("aquaculture", "blackfish"))
                .withSeasons(Seasons.WINTER)
                .withSizeAndWeight(FishProperties.sw(50, 20, 5000, 3000))
                .withDaytime(FishProperties.Daytime.NIGHT)
                .withRarity(FishProperties.Rarity.UNCOMMON)
        );

        register(overworldColdOceanFish(U.holderItem("aquaculture", "pacific_halibut"))
                .withBucketedFish(U.holderItem("aquaculture", "pacific_halibut_bucket"))
                .withEntityToSpawn(U.holderEntity("aquaculture", "pacific_halibut"))
                .withSeasons(Seasons.WINTER, Seasons.AUTUMN, Seasons.SPRING)
                .withSizeAndWeight(FishProperties.sw(150, 50, 80000, 5000))
        );

        register(overworldColdOceanFish(U.holderItem("aquaculture", "atlantic_halibut"))
                .withBucketedFish(U.holderItem("aquaculture", "atlantic_halibut_bucket"))
                .withEntityToSpawn(U.holderEntity("aquaculture", "atlantic_halibut"))
                .withSizeAndWeight(FishProperties.sw(200, 80, 150000, 10000))
                .withDifficulty(FishProperties.Difficulty.MEDIUM)
                .withWeather(FishProperties.Weather.RAIN)
        );

        register(overworldColdOceanFish(U.holderItem("aquaculture", "atlantic_herring"))
                .withBucketedFish(U.holderItem("aquaculture", "atlantic_herring_bucket"))
                .withEntityToSpawn(U.holderEntity("aquaculture", "atlantic_herring"))
                .withSeasons(Seasons.WINTER, Seasons.SPRING)
                .withSizeAndWeight(FishProperties.sw(25, 5, 200, 100))
                .withDifficulty(FishProperties.Difficulty.HARD_MOVING)
                .withRarity(FishProperties.Rarity.RARE)
                .withDaytime(FishProperties.Daytime.DAY)
        );

        register(overworldColdOceanFish(U.holderItem("aquaculture", "pink_salmon"))
                .withBucketedFish(U.holderItem("aquaculture", "pink_salmon_bucket"))
                .withEntityToSpawn(U.holderEntity("aquaculture", "pink_salmon"))
                .withSeasons(Seasons.WINTER, Seasons.SPRING)
                .withSizeAndWeight(FishProperties.sw(50, 10, 2000, 1000))
                .withRarity(FishProperties.Rarity.EPIC)
                .withWeather(FishProperties.Weather.THUNDER)
                .withDifficulty(FishProperties.Difficulty.HARD)
        );

        register(overworldColdOceanFish(U.holderItem("aquaculture", "pollock"))
                .withBucketedFish(U.holderItem("aquaculture", "pollock_bucket"))
                .withEntityToSpawn(U.holderEntity("aquaculture", "pollock"))
                .withSizeAndWeight(FishProperties.sw(70, 30, 5000, 4000))
        );

        register(overworldColdOceanFish(U.holderItem("aquaculture", "rainbow_trout"))
                .withBucketedFish(U.holderItem("aquaculture", "rainbow_trout_bucket"))
                .withEntityToSpawn(U.holderEntity("aquaculture", "rainbow_trout"))
                .withSeasons(Seasons.WINTER)
                .withSizeAndWeight(FishProperties.sw(60, 20, 2000, 1500))
                .withRarity(FishProperties.Rarity.UNCOMMON)
                .withDaytime(FishProperties.Daytime.DAY)
        );

        //saltwater
        register(overworldOceanFish(U.holderItem("aquaculture", "jellyfish"))
                .withBucketedFish(U.holderItem("aquaculture", "jellyfish_bucket"))
                .withEntityToSpawn(U.holderEntity("aquaculture", "jellyfish_trout"))
                .withSeasons(Seasons.SUMMER)
                .withSizeAndWeight(FishProperties.sw(100, 70, 50000, 40000))
                .withRarity(FishProperties.Rarity.UNCOMMON)
                .withDifficulty(FishProperties.Difficulty.HARD)
                .withBaseChance(3)
        );

        register(overworldOceanFish(U.holderItem("aquaculture", "red_grouper"))
                .withBucketedFish(U.holderItem("aquaculture", "red_grouper_bucket"))
                .withEntityToSpawn(U.holderEntity("aquaculture", "red_grouper"))
                .withSeasons(Seasons.AUTUMN)
                .withSizeAndWeight(FishProperties.sw(100, 50, 15000, 10000))
        );

        register(overworldOceanFish(U.holderItem("aquaculture", "tuna"))
                .withBucketedFish(U.holderItem("aquaculture", "tuna_bucket"))
                .withEntityToSpawn(U.holderEntity("aquaculture", "tuna"))
                .withSizeAndWeight(FishProperties.sw(200, 100, 200000, 150000))
        );

        //jungle
        register(overworldJungleFish(U.holderItem("aquaculture", "arapaima"))
                .withBucketedFish(U.holderItem("aquaculture", "arapaima_bucket"))
                .withEntityToSpawn(U.holderEntity("aquaculture", "arapaima"))
                .withSeasons(Seasons.AUTUMN, Seasons.WINTER)
                .withSizeAndWeight(FishProperties.sw(250, 50, 50000, 150000))
                .withRarity(FishProperties.Rarity.RARE)
                .withDifficulty(FishProperties.Difficulty.HARD)
                .withWeather(FishProperties.Weather.RAIN)
        );

        register(overworldJungleFish(U.holderItem("aquaculture", "arrau_turtle"))
                //no bucketed version
                .withEntityToSpawn(U.holderEntity("aquaculture", "arrau_turtle"))
                .withSizeAndWeight(FishProperties.sw(100, 30, 80000, 150000))
                .withDifficulty(FishProperties.Difficulty.MEDIUM)
        );


        register(overworldJungleFish(U.holderItem("aquaculture", "piranha"))
                .withBucketedFish(U.holderItem("aquaculture", "piranha_bucket"))
                .withEntityToSpawn(U.holderEntity("aquaculture", "piranha"))
                .withSeasons(Seasons.SPRING, Seasons.SUMMER, Seasons.AUTUMN)
                .withSizeAndWeight(FishProperties.sw(30, 10, 500, 300))
                .withBaitRestrictions(FishProperties.BaitRestrictions.LEGENDARY_BAIT)
                .withRarity(FishProperties.Rarity.LEGENDARY)
                .withDifficulty(FishProperties.Difficulty.FOUR_BIG)
                .withDaytime(FishProperties.Daytime.NOON)
        );

        register(overworldJungleFish(U.holderItem("aquaculture", "tambaqui"))
                .withBucketedFish(U.holderItem("aquaculture", "tambaqui_bucket"))
                .withEntityToSpawn(U.holderEntity("aquaculture", "tambaqui"))
                .withSizeAndWeight(FishProperties.sw(100, 30, 150000, 10000))
                .withRarity(FishProperties.Rarity.UNCOMMON)
                .withDifficulty(FishProperties.Difficulty.MEDIUM)
        );

        //swamp
        register(overworldSwampFish(U.holderItem("aquaculture", "leech"))
                //no bucketed version
                //no entity
                .withSeasons(Seasons.AUTUMN)
                .withSizeAndWeight(FishProperties.sw(10, 5, 5, 3))
                .withRarity(FishProperties.Rarity.RARE)
                .withDifficulty(FishProperties.Difficulty.HARD)
        );

        register(overworldSwampFish(U.holderItem("aquaculture", "box_turtle"))
                //no bucketed version
                .withEntityToSpawn(U.holderEntity("aquaculture", "box_turtle"))
                .withSizeAndWeight(FishProperties.sw(20, 5, 1000, 500))
                .withRarity(FishProperties.Rarity.EPIC)
                .withDifficulty(FishProperties.Difficulty.HARD)
                .withWeather(FishProperties.Weather.RAIN)
        );

        //mushroom island
        register(overworldMushroomFieldsFish(U.holderItem("aquaculture", "brown_shrooma"))
                .withBucketedFish(U.holderItem("aquaculture", "brown_shrooma_bucket"))
                .withEntityToSpawn(U.holderEntity("aquaculture", "brown_shrooma"))
                .withSizeAndWeight(FishProperties.sw(100, 20, 3000, 500))
                .withRarity(FishProperties.Rarity.EPIC)
                .withDifficulty(FishProperties.Difficulty.FOUR_BIG)
        );

        register(overworldMushroomFieldsFish(U.holderItem("aquaculture", "red_shrooma"))
                .withBucketedFish(U.holderItem("aquaculture", "brown_shrooma_bucket"))
                .withEntityToSpawn(U.holderEntity("aquaculture", "brown_shrooma"))
                .withSizeAndWeight(FishProperties.sw(100, 20, 3000, 500))
                .withRarity(FishProperties.Rarity.EPIC)
                .withDifficulty(FishProperties.Difficulty.FOUR_BIG)
        );

        //anywhere
        register(overworldOceanFish(U.holderItem("aquaculture", "goldfish"))
                //no bucketed version
                //no entity
                .withSeasons(Seasons.SUMMER)
                .withSizeAndWeight(FishProperties.sw(15, 5, 100, 5))
                .withBaseChance(1)
        );

    }
}
