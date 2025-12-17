package com.wdiscute.starcatcher.registry.fishing.compat;

import com.wdiscute.starcatcher.U;
import com.wdiscute.starcatcher.registry.ModItems;
import com.wdiscute.starcatcher.registry.fishing.FishingPropertiesRegistry;
import com.wdiscute.starcatcher.storage.FishProperties;
import com.wdiscute.starcatcher.storage.FishProperties.WorldRestrictions.Seasons;

public class CollectorsReapFishingProperties extends FishingPropertiesRegistry
{
    public static void bootstrap()
    {
        //
        // ,-----.         ,--. ,--.                  ,--.                   ,--.             ,------.
        //'  .--./  ,---.  |  | |  |  ,---.   ,---. ,-'  '-.  ,---.  ,--.--. |  |  ,---.      |  .--. '  ,---.   ,--,--.  ,---.
        //|  |     | .-. | |  | |  | | .-. : | .--' '-.  .-' | .-. | |  .--' `-'  (  .-'      |  '--'.' | .-. : ' ,-.  | | .-. |
        //'  '--'\ ' '-' ' |  | |  | \   --. \ `--.   |  |   ' '-' ' |  |         .-'  `)     |  |\  \  \   --. \ '-'  | | '-' '
        // `-----'  `---'  `--' `--'  `----'  `---'   `--'    `---'  `--'         `----'      `--' '--'  `----'  `--`--' |  |-'
        //                                                                                                               `--'

        register(fish(U.holderItem("collectorsreap", "platinum_bass"))
                .withBucketedFish(U.holderItem("collectorsreap", "platinum_bass_bucket"))
                .withEntityToSpawn(U.holderEntity("collectorsreap", "platinum_bass"))
                .withSizeAndWeight(FishProperties.sw(40, 12, 1600, 1100))
                .withSeasons(Seasons.MID_AUTUMN, Seasons.LATE_AUTUMN, Seasons.WINTER)
                .withDifficulty(FishProperties.Difficulty.EASY_MOVING)
                .withWorldRestrictions(FishProperties.WorldRestrictions.OVERWORLD
                        .withBiomesTags(U.rl("collectorsreap", "biome/has_spawn/platinum_bass")))
        );


        register(fish(U.holderItem("collectorsreap", "tiger_prawn"))
                .withBucketedFish(U.holderItem("collectorsreap", "tiger_prawn_bucket"))
                .withEntityToSpawn(U.holderEntity("collectorsreap", "tiger_prawn"))
                .withSizeAndWeight(FishProperties.sw(28, 8, 260, 60))
                .withDifficulty(FishProperties.Difficulty.MEDIUM_VANISHING)
                .withRarity(FishProperties.Rarity.RARE)
                .withWorldRestrictions(FishProperties.WorldRestrictions.OVERWORLD
                        .withBiomesTags(U.rl("collectorsreap", "biome/has_spawn/tiger_prawn")))
        );

        register(fish(U.holderItem("collectorsreap", "clam"))  //no mini game
                .withBucketedFish(U.holderItem("collectorsreap", "clam_bucket"))
                .withEntityToSpawn(U.holderEntity("collectorsreap", "clam"))
                .withSkipMinigame(true)
                .withWorldRestrictions(FishProperties.WorldRestrictions.OVERWORLD
                        .withBiomesTags(U.rl("collectorsreap", "biome/has_spawn/clam")))
        );

        register(fish(U.holderItem("collectorsreap", "urchin"))  //no mini game
                .withBucketedFish(U.holderItem("collectorsreap", "urchin_bucket"))
                .withEntityToSpawn(U.holderEntity("collectorsreap", "urchin"))
                .withHasGuideEntry(false)
                .withSkipMinigame(true)
                .withWorldRestrictions(FishProperties.WorldRestrictions.OVERWORLD
                        .withBiomesTags(U.rl("collectorsreap", "biome/has_spawn/urchin")))
        );


        register(fish(U.holderItem("collectorsreap", "chieftain_crab"))
                .withBucketedFish(U.holderItem("collectorsreap", "chieftain_crab_bucket"))
                .withEntityToSpawn(U.holderEntity("collectorsreap", "chieftain_crab"))
                .withSizeAndWeight(FishProperties.sw(28, 8, 260, 60))
                .withDifficulty(FishProperties.Difficulty.NETHER_CRAB)
                .withRarity(FishProperties.Rarity.UNCOMMON)
                .withWorldRestrictions(FishProperties.WorldRestrictions.OVERWORLD
                        .withBiomesTags(U.rl("collectorsreap", "biome/has_spawn/chieftain_crab")))
                .withAlwaysSpawnEntity(true)
                .withItemToOverrideWith(ModItems.UNKNOWN_FISH)
        );
    }
}
