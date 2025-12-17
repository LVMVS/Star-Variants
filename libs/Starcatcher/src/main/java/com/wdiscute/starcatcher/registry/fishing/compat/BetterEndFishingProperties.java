package com.wdiscute.starcatcher.registry.fishing.compat;

import com.wdiscute.starcatcher.U;
import com.wdiscute.starcatcher.registry.ModItems;
import com.wdiscute.starcatcher.registry.fishing.FishingPropertiesRegistry;
import com.wdiscute.starcatcher.storage.FishProperties;

public class BetterEndFishingProperties extends FishingPropertiesRegistry
{
    public static void bootstrap()
    {

        //
        //,--.              ,--.     ,--.                                           ,--.
        //|  |-.   ,---.  ,-'  '-. ,-'  '-.  ,---.  ,--.--.      ,---.  ,--,--,   ,-|  |
        //| .-. ' | .-. : '-.  .-' '-.  .-' | .-. : |  .--'     | .-. : |      \ ' .-. |
        //| `-' | \   --.   |  |     |  |   \   --. |  |        \   --. |  ||  | \ `-' |
        // `---'   `----'   `--'     `--'    `----' `--'         `----' `--''--'  `---'
        //

        register(endOuterIslandsFish(U.holderItem("betterend", "end_fish_raw"))
                .withBucketedFish(U.holderItem("betterend", "bucket_end_fish"))
                .withEntityToSpawn(U.holderEntity("betterend", "end_fish"))
                .withSizeAndWeight(FishProperties.sw(80, 40, 2000, 400))
                .withRarity(FishProperties.Rarity.RARE)
        );

        register(endOuterIslandsFish(U.holderItem("betterend", "gelatine"))
                .withBucketedFish(U.holderItem("betterend", "bucket_cubozoa"))
                .withAlwaysSpawnEntity(true)
                .withEntityToSpawn(U.holderEntity("betterend", "cubozoa"))
                .withSizeAndWeight(FishProperties.sw(70, 20, 200, 60))
                .withRarity(FishProperties.Rarity.EPIC)
        );
    }
}
