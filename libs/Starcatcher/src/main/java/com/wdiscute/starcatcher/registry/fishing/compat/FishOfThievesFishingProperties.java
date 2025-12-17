package com.wdiscute.starcatcher.registry.fishing.compat;

import com.wdiscute.starcatcher.U;
import com.wdiscute.starcatcher.registry.fishing.FishingPropertiesRegistry;
import com.wdiscute.starcatcher.storage.FishProperties;
import com.wdiscute.starcatcher.storage.FishProperties.WorldRestrictions.Seasons;

public class FishOfThievesFishingProperties extends FishingPropertiesRegistry
{
    public static void bootstrap()
    {

        //
        //  ,---. ,--.         ,--.                   ,---.
        // /  .-' `--'  ,---.  |  ,---.       ,---.  /  .-'
        // |  `-, ,--. (  .-'  |  .-.  |     | .-. | |  `-,
        // |  .-' |  | .-'  `) |  | |  |     ' '-' ' |  .-'
        // `--'   `--' `----'  `--' `--'      `---'  `--'
        //   ,--.   ,--.      ,--.
        // ,-'  '-. |  ,---.  `--'  ,---.  ,--.  ,--.  ,---.   ,---.
        // '-.  .-' |  .-.  | ,--. | .-. :  \  `'  /  | .-. : (  .-'
        //   |  |   |  | |  | |  | \   --.   \    /   \   --. .-'  `)
        //   `--'   `--' `--' `--'  `----'    `--'     `----' `----'
        //

        register(overworldOceanFish(U.holderItem("fishofthieves", "splashtail"))
                .withBucketedFish(U.holderItem("fishofthieves", "splashtail_bucket"))
                .withEntityToSpawn(U.holderEntity("fishofthieves", "splashtail"))
                .withSeasons(Seasons.SUMMER, Seasons.AUTUMN)
                .withSizeAndWeight(FishProperties.sw(250, 70, 7600, 2000))
                .withBaitRestrictions(FishProperties.BaitRestrictions.FISH_OF_THIEVES)
                .withDifficulty(FishProperties.Difficulty.EASY_MOVING)
        );

        register(overworldLakeFish(U.holderItem("fishofthieves", "pondie"))
                .withBucketedFish(U.holderItem("fishofthieves", "pondie_bucket"))
                .withEntityToSpawn(U.holderEntity("fishofthieves", "pondie"))
                .withSeasons(Seasons.SPRING, Seasons.SUMMER)
                .withSizeAndWeight(FishProperties.sw(190, 30, 9000, 3600))
                .withBaitRestrictions(FishProperties.BaitRestrictions.FISH_OF_THIEVES)
                .withDifficulty(FishProperties.Difficulty.MEDIUM)
                .withRarity(FishProperties.Rarity.UNCOMMON)
        );

        register(overworldRiverFish(U.holderItem("fishofthieves", "islehopper"))
                .withBucketedFish(U.holderItem("fishofthieves", "islehopper_bucket"))
                .withEntityToSpawn(U.holderEntity("fishofthieves", "islehopper"))
                .withSizeAndWeight(FishProperties.sw(300, 20, 23000, 3600))
                .withBaitRestrictions(FishProperties.BaitRestrictions.FISH_OF_THIEVES)
                .withDifficulty(FishProperties.Difficulty.MEDIUM_VANISHING)
                .withRarity(FishProperties.Rarity.UNCOMMON)
        );

        register(overworldWarmOceanFish(U.holderItem("fishofthieves", "ancientscale"))
                .withBucketedFish(U.holderItem("fishofthieves", "ancientscale_bucket"))
                .withEntityToSpawn(U.holderEntity("fishofthieves", "ancientscale"))
                .withSeasons(Seasons.SPRING, Seasons.WINTER)
                .withSizeAndWeight(FishProperties.sw(70, 10, 4000, 2000))
                .withBaitRestrictions(FishProperties.BaitRestrictions.FISH_OF_THIEVES)
                .withDifficulty(FishProperties.Difficulty.HARD_VANISHING)
                .withRarity(FishProperties.Rarity.RARE)
        );

        register(overworldWarmOceanFish(U.holderItem("fishofthieves", "plentifin"))
                .withBucketedFish(U.holderItem("fishofthieves", "plentifin_bucket"))
                .withEntityToSpawn(U.holderEntity("fishofthieves", "plentifin"))
                .withSizeAndWeight(FishProperties.sw(90, 10, 4300, 2500))
                .withBaitRestrictions(FishProperties.BaitRestrictions.FISH_OF_THIEVES)
                .withDifficulty(FishProperties.Difficulty.MEDIUM)
                .withRarity(FishProperties.Rarity.UNCOMMON)
        );

        register(overworldLushCavesFish(U.holderItem("fishofthieves", "wildsplash"))
                .withBucketedFish(U.holderItem("fishofthieves", "wildsplash_bucket"))
                .withEntityToSpawn(U.holderEntity("fishofthieves", "wildsplash"))
                .withSeasons(Seasons.SPRING, Seasons.WINTER)
                .withSizeAndWeight(FishProperties.sw(120, 30, 8000, 2200))
                .withBaitRestrictions(FishProperties.BaitRestrictions.FISH_OF_THIEVES)
                .withDifficulty(FishProperties.Difficulty.MEDIUM_MOVING)
                .withRarity(FishProperties.Rarity.UNCOMMON)
        );

        register(overworldDeepslateFish(U.holderItem("fishofthieves", "devilfish"))
                .withBucketedFish(U.holderItem("fishofthieves", "devilfish_bucket"))
                .withEntityToSpawn(U.holderEntity("fishofthieves", "devilfish"))
                .withSizeAndWeight(FishProperties.sw(180, 80, 20000, 2200))
                .withBaitRestrictions(FishProperties.BaitRestrictions.FISH_OF_THIEVES)
                .withDifficulty(FishProperties.Difficulty.HARD)
                .withRarity(FishProperties.Rarity.RARE)
        );

        register(overworldColdOceanFish(U.holderItem("fishofthieves", "battlegill"))
                .withBucketedFish(U.holderItem("fishofthieves", "battlegill_bucket"))
                .withEntityToSpawn(U.holderEntity("fishofthieves", "battlegill"))
                .withSeasons(Seasons.SUMMER, Seasons.AUTUMN)
                .withSizeAndWeight(FishProperties.sw(100, 10, 19000, 4200))
                .withBaitRestrictions(FishProperties.BaitRestrictions.FISH_OF_THIEVES)
                .withDifficulty(FishProperties.Difficulty.MEDIUM_VANISHING)
                .withRarity(FishProperties.Rarity.UNCOMMON)
        );

        register(endFish(U.holderItem("fishofthieves", "wrecker"))
                .withBucketedFish(U.holderItem("fishofthieves", "wrecker_bucket"))
                .withEntityToSpawn(U.holderEntity("fishofthieves", "wrecker"))
                .withSeasons(Seasons.SPRING, Seasons.AUTUMN)
                .withSizeAndWeight(FishProperties.sw(100, 10, 19000, 4200))
                .withBaitRestrictions(FishProperties.BaitRestrictions.FISH_OF_THIEVES)
                .withDifficulty(FishProperties.Difficulty.MEDIUM_VANISHING)
                .withRarity(FishProperties.Rarity.EPIC)
        );

        register(overworldOceanFish(U.holderItem("fishofthieves", "stormfish"))
                .withBucketedFish(U.holderItem("fishofthieves", "stormfish_bucket"))
                .withEntityToSpawn(U.holderEntity("fishofthieves", "stormfish"))
                .withSeasons(Seasons.SUMMER, Seasons.AUTUMN, Seasons.WINTER)
                .withSizeAndWeight(FishProperties.sw(150, 30, 14000, 2000))
                .withDifficulty(FishProperties.Difficulty.MEDIUM_VANISHING)
                .withWeather(FishProperties.Weather.THUNDER)
                .withRarity(FishProperties.Rarity.RARE)
        );
    }
}
