package com.wdiscute.starcatcher.registry.fishing.compat;

import com.wdiscute.starcatcher.StarcatcherTags;
import com.wdiscute.starcatcher.U;
import com.wdiscute.starcatcher.registry.fishing.FishingPropertiesRegistry;
import com.wdiscute.starcatcher.storage.FishProperties;

public class EnvironmentalFishingProperties extends FishingPropertiesRegistry
{
    public static void bootstrap()
    {

        //
        //,------.                     ,--.                                                        ,--.            ,--.
        //|  .---' ,--,--,  ,--.  ,--. `--' ,--.--.  ,---.  ,--,--,  ,--,--,--.  ,---.  ,--,--,  ,-'  '-.  ,--,--. |  |
        //|  `--,  |      \  \  `'  /  ,--. |  .--' | .-. | |      \ |        | | .-. : |      \ '-.  .-' ' ,-.  | |  |
        //|  `---. |  ||  |   \    /   |  | |  |    ' '-' ' |  ||  | |  |  |  | \   --. |  ||  |   |  |   \ '-'  | |  |
        //`------' `--''--'    `--'    `--' `--'     `---'  `--''--' `--`--`--'  `----' `--''--'   `--'    `--`--' `--'
        //

        register(fish(U.holderItem("environmental", "koi"))
                .withBucketedFish(U.holderItem("environmental", "koi_bucket"))
                .withEntityToSpawn(U.holderEntity("environmental", "koi"))
                .withSizeAndWeight(FishProperties.sw(60, 20, 3000, 2000))
                .withDifficulty(FishProperties.Difficulty.EASY_FAST_FISH)
                .withRarity(FishProperties.Rarity.UNCOMMON)
                .withWorldRestrictions(FishProperties.WorldRestrictions.OVERWORLD
                        .withBiomes(U.rl("environmental", "blossom_woods"), U.rl("environmental", "blossom_valleys")))
        );

        register(fish(U.holderItem("environmental", "slabfish_bucket"))
                .withAlwaysSpawnEntity(true)
                .withBucketedFish(U.holderItem("environmental", "slabfish_bucket"))
                .withEntityToSpawn(U.holderEntity("environmental", "slabfish_bucket"))
                .withSizeAndWeight(FishProperties.sw(120, 40, 20000, 10000))
                .withDifficulty(FishProperties.Difficulty.HARD)
                .withRarity(FishProperties.Rarity.UNCOMMON)
                .withWorldRestrictions(FishProperties.WorldRestrictions.OVERWORLD
                        .withBiomes(U.rl("environmental", "marsh"))
                        .withBiomesTags(StarcatcherTags.IS_SWAMP))
        );

    }
}
