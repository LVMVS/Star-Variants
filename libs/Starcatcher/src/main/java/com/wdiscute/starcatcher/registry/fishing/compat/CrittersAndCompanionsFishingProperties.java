package com.wdiscute.starcatcher.registry.fishing.compat;

import com.wdiscute.starcatcher.U;
import com.wdiscute.starcatcher.registry.fishing.FishingPropertiesRegistry;
import com.wdiscute.starcatcher.storage.FishProperties;

public class CrittersAndCompanionsFishingProperties extends FishingPropertiesRegistry
{


    public static void bootstrap()
    {

        //
        // ,-----.         ,--.   ,--.     ,--.                                                    ,--.      ,-----.                                              ,--.
        //'  .--./ ,--.--. `--' ,-'  '-. ,-'  '-.  ,---.  ,--.--.  ,---.       ,--,--. ,--,--,   ,-|  |     '  .--./  ,---.  ,--,--,--.  ,---.   ,--,--. ,--,--,  `--'  ,---.  ,--,--,   ,---.
        //|  |     |  .--' ,--. '-.  .-' '-.  .-' | .-. : |  .--' (  .-'      ' ,-.  | |      \ ' .-. |     |  |     | .-. | |        | | .-. | ' ,-.  | |      \ ,--. | .-. | |      \ (  .-'
        //'  '--'\ |  |    |  |   |  |     |  |   \   --. |  |    .-'  `)     \ '-'  | |  ||  | \ `-' |     '  '--'\ ' '-' ' |  |  |  | | '-' ' \ '-'  | |  ||  | |  | ' '-' ' |  ||  | .-'  `)
        // `-----' `--'    `--'   `--'     `--'    `----' `--'    `----'       `--`--' `--''--'  `---'       `-----'  `---'  `--`--`--' |  |-'   `--`--' `--''--' `--'  `---'  `--''--' `----'
        //                                                                                                                              `--'


        register(overworldBeachFish(U.holderItem("crittersandcompanions", "clam"))  //no mini game
                .withSkipMinigame(true)
                .withWorldRestrictions(FishProperties.WorldRestrictions.OVERWORLD_RIVER)
        );

        register(overworldRiverFish(U.holderItem("crittersandcompanions", "koi_fish"))
                .withBucketedFish(U.holderItem("crittersandcompanions", "koi_fish_bucket"))
                .withEntityToSpawn(U.holderEntity("crittersandcompanions", "koi_fish"))
                .withSizeAndWeight(FishProperties.sw(60, 20, 3000, 2000))
                .withDifficulty(FishProperties.Difficulty.MEDIUM)
                .withRarity(FishProperties.Rarity.COMMON)
                .withWorldRestrictions(FishProperties.WorldRestrictions.OVERWORLD_RIVER)
        );

        register(overworldDeepOceanFish(U.holderItem("crittersandcompanions", "dumbo_octopus_bucket"))
                .withAlwaysSpawnEntity(true)
                .withBucketedFish(U.holderItem("crittersandcompanions", "dumbo_octopus_bucket"))
                .withEntityToSpawn(U.holderEntity("crittersandcompanions", "dumbo_octopus"))
                .withSizeAndWeight(FishProperties.sw(30, 10, 1000, 300))
                .withDifficulty(FishProperties.Difficulty.MEDIUM_VANISHING)
                .withRarity(FishProperties.Rarity.UNCOMMON)
                .withWorldRestrictions(FishProperties.WorldRestrictions.OVERWORLD_RIVER)
        );

        register(overworldDeepOceanFish(U.holderItem("crittersandcompanions", "sea_bunny_bucket"))
                .withAlwaysSpawnEntity(true)
                .withBucketedFish(U.holderItem("crittersandcompanions", "sea_bunny_bucket"))
                .withEntityToSpawn(U.holderEntity("crittersandcompanions", "sea_bunny"))
                .withSizeAndWeight(FishProperties.sw(40, 10, 200, 60))
                .withDifficulty(FishProperties.Difficulty.HARD)
                .withRarity(FishProperties.Rarity.RARE)
                .withWorldRestrictions(FishProperties.WorldRestrictions.OVERWORLD_RIVER)
        );

    }
}
