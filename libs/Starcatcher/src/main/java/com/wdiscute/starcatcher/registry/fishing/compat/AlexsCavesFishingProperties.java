package com.wdiscute.starcatcher.registry.fishing.compat;

import com.wdiscute.starcatcher.U;
import com.wdiscute.starcatcher.registry.ModItems;
import com.wdiscute.starcatcher.registry.fishing.FishingPropertiesRegistry;
import com.wdiscute.starcatcher.storage.FishProperties;

import java.util.List;

public class AlexsCavesFishingProperties extends FishingPropertiesRegistry
{
    public static void bootstrap()
    {

        //
        //  ,---.   ,--.                    ,--.              ,-----.
        // /  O  \  |  |  ,---.  ,--.  ,--. |  |  ,---.      '  .--./  ,--,--. ,--.  ,--.  ,---.   ,---.
        //|  .-.  | |  | | .-. :  \  `'  /  `-'  (  .-'      |  |     ' ,-.  |  \  `'  /  | .-. : (  .-'
        //|  | |  | |  | \   --.  /  /.  \       .-'  `)     '  '--'\ \ '-'  |   \    /   \   --. .-'  `)
        //`--' `--' `--'  `----' '--'  '--'      `----'       `-----'  `--`--'    `--'     `----' `----'
        //

        register(fish(U.holderItem("alexscaves", "radgill"))
                .withBucketedFish(U.holderItem("alexscaves", "radgill_bucket"))
                .withEntityToSpawn(U.holderEntity("alexscaves", "radgill"))
                .withSizeAndWeight(FishProperties.sw(80, 40, 12000, 7000))
                .withRarity(FishProperties.Rarity.UNCOMMON)
                .withWorldRestrictions(FishProperties.WorldRestrictions.OVERWORLD
                        .withFluids(U.rl("alexscaves", "acid"))
                        .withBiomes(U.rl("alexscaves", "toxic_caves")))
        );

        register(fish(U.holderItem("alexscaves", "sweetish_fish_blue"))
                .withBucketedFish(U.holderItem("alexscaves", "sweetish_fish_blue_bucket"))
                .withEntityToSpawn(U.holderEntity("alexscaves", "sweetish_fish"))
                .withSizeAndWeight(FishProperties.sw(80, 40, 12000, 7000))
                .withRarity(FishProperties.Rarity.UNCOMMON)
                .withWorldRestrictions(FishProperties.WorldRestrictions.OVERWORLD
                        .withFluids(U.rl("alexscaves", "purple_soda"))
                        .withBiomes(U.rl("alexscaves", "candy_cavity")))
        );

        register(fish(U.holderItem("alexscaves", "sweetish_fish_green"))
                .withBucketedFish(U.holderItem("alexscaves", "sweetish_fish_blue_bucket"))
                .withEntityToSpawn(U.holderEntity("alexscaves", "sweetish_fish"))
                .withSizeAndWeight(FishProperties.sw(80, 40, 12000, 7000))
                .withRarity(FishProperties.Rarity.UNCOMMON)
                .withWorldRestrictions(FishProperties.WorldRestrictions.OVERWORLD
                        .withFluids(U.rl("alexscaves", "purple_soda"))
                        .withBiomes(U.rl("alexscaves", "candy_cavity")))
        );

        register(fish(U.holderItem("alexscaves", "sweetish_fish_pink"))
                .withBucketedFish(U.holderItem("alexscaves", "sweetish_fish_pink_bucket"))
                .withEntityToSpawn(U.holderEntity("alexscaves", "sweetish_fish"))
                .withSizeAndWeight(FishProperties.sw(80, 40, 12000, 70000))
                .withRarity(FishProperties.Rarity.UNCOMMON)
                .withWorldRestrictions(FishProperties.WorldRestrictions.OVERWORLD
                        .withFluids(U.rl("alexscaves", "purple_soda"))
                        .withBiomes(U.rl("alexscaves", "candy_cavity")))
        );

        register(fish(U.holderItem("alexscaves", "sweetish_fish_red"))
                .withBucketedFish(U.holderItem("alexscaves", "sweetish_fish_red_bucket"))
                .withEntityToSpawn(U.holderEntity("alexscaves", "sweetish_fish"))
                .withSizeAndWeight(FishProperties.sw(80, 40, 12000, 7000))
                .withRarity(FishProperties.Rarity.UNCOMMON)
                .withWorldRestrictions(FishProperties.WorldRestrictions.OVERWORLD
                        .withFluids(U.rl("alexscaves", "purple_soda"))
                        .withBiomes(U.rl("alexscaves", "candy_cavity")))
        );

        register(fish(U.holderItem("alexscaves", "sweetish_fish_yellow"))
                .withBucketedFish(U.holderItem("alexscaves", "sweetish_fish_yellow_bucket"))
                .withEntityToSpawn(U.holderEntity("alexscaves", "sweetish_fish"))
                .withSizeAndWeight(FishProperties.sw(80, 40, 12000, 7000))
                .withRarity(FishProperties.Rarity.UNCOMMON)
                .withWorldRestrictions(FishProperties.WorldRestrictions.OVERWORLD
                        .withFluids(U.rl("alexscaves", "purple_soda"))
                        .withBiomes(U.rl("alexscaves", "candy_cavity")))
        );


        register(fish(U.holderItem("alexscaves", "lanternfish"))
                .withBucketedFish(U.holderItem("alexscaves", "lanternfish_bucket"))
                .withEntityToSpawn(U.holderEntity("alexscaves", "lanternfish"))
                .withSizeAndWeight(FishProperties.sw(100, 50, 15000, 10000))
                .withRarity(FishProperties.Rarity.RARE)
                .withWorldRestrictions(FishProperties.WorldRestrictions.OVERWORLD
                        .withBiomes(U.rl("alexscaves", "abyssal_chasm")))
        );

        register(fish(U.holderItem("alexscaves", "tripodfish"))
                .withBucketedFish(U.holderItem("alexscaves", "tripodfish_bucket"))
                .withEntityToSpawn(U.holderEntity("alexscaves", "tripodfish"))
                .withSizeAndWeight(FishProperties.sw(30, 10, 1000, 5000))
                .withRarity(FishProperties.Rarity.RARE)
                .withWorldRestrictions(FishProperties.WorldRestrictions.OVERWORLD
                        .withBiomes(U.rl("alexscaves", "abyssal_chasm")))
        );


        register(fish(U.holderItem("alexscaves", "trilocaris_tail"))
                .withBucketedFish(U.holderItem("alexscaves", "trilocaris_bucket"))
                .withEntityToSpawn(U.holderEntity("alexscaves", "trilocaris"))
                .withSizeAndWeight(FishProperties.sw(30, 10, 1000, 5000))
                .withRarity(FishProperties.Rarity.RARE)
                .withWorldRestrictions(FishProperties.WorldRestrictions.OVERWORLD
                        .withBiomes(U.rl("alexscaves", "primordial_caves")))
                .withAlwaysSpawnEntity(true)
                .withEntityToSpawn(U.holderEntity("alexscaves", "trilocaris"))
                .withItemToOverrideWith(ModItems.UNKNOWN_FISH)
        );
    }
}
