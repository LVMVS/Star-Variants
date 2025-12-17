package com.wdiscute.starcatcher.registry.fishing.compat;

import com.wdiscute.starcatcher.U;
import com.wdiscute.starcatcher.registry.fishing.FishingPropertiesRegistry;
import com.wdiscute.starcatcher.storage.FishProperties;

public class AquamiraeFishingProperties extends FishingPropertiesRegistry
{
    public static void bootstrap()
    {
        //
        //                                              ,--.
        // ,--,--.  ,---.  ,--.,--.  ,--,--. ,--,--,--. `--' ,--.--.  ,--,--.  ,---.
        //' ,-.  | | .-. | |  ||  | ' ,-.  | |        | ,--. |  .--' ' ,-.  | | .-. :
        //\ '-'  | ' '-' | '  ''  ' \ '-'  | |  |  |  | |  | |  |    \ '-'  | \   --.
        // `--`--'  `-|  |  `----'   `--`--' `--`--`--' `--' `--'     `--`--'  `----'
        //            `--'

        register(fish(U.holderItem("aquamirae", "spinefish"))
                .withBucketedFish(U.holderItem("aquamirae", "spinefish_bucket"))
                .withEntityToSpawn(U.holderEntity("aquamirae", "spinefish"))
                .withSizeAndWeight(FishProperties.sw(30, 10, 500, 300))
                .withRarity(FishProperties.Rarity.UNCOMMON)
                .withDifficulty(FishProperties.Difficulty.MEDIUM_VANISHING)
                .withWorldRestrictions(FishProperties.WorldRestrictions.OVERWORLD
                        .withBiomesTags(U.rl("aquamirae", "ice_maze")))
        );
    }
}
