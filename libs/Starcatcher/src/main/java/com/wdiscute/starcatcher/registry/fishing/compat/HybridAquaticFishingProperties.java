package com.wdiscute.starcatcher.registry.fishing.compat;

import com.wdiscute.starcatcher.U;
import com.wdiscute.starcatcher.registry.ModItems;
import com.wdiscute.starcatcher.registry.fishing.FishingPropertiesRegistry;
import com.wdiscute.starcatcher.storage.FishProperties;

public class HybridAquaticFishingProperties extends FishingPropertiesRegistry
{
    public static void bootstrap()
    {

        //Literally The Coolest Mod Ever (this fact has been fact-checked by true fisherman ✅)

        //
        // ,--.  ,--.           ,--.            ,--.    ,--.       ,---.                               ,--.   ,--.
        // |  '--'  | ,--. ,--. |  |-.  ,--.--. `--'  ,-|  |      /  O  \   ,---.  ,--.,--.  ,--,--. ,-'  '-. `--'  ,---.
        // |  .--.  |  \  '  /  | .-. ' |  .--' ,--. ' .-. |     |  .-.  | | .-. | |  ||  | ' ,-.  | '-.  .-' ,--. | .--'
        // |  |  |  |   \   '   | `-' | |  |    |  | \ `-' |     |  | |  | ' '-' | '  ''  ' \ '-'  |   |  |   |  | \ `--.
        // `--'  `--' .-'  /     `---'  `--'    `--'  `---'      `--' `--'  `-|  |  `----'   `--`--'   `--'   `--'  `---'
        //            `---'                                                   `--'                                                                   |_|

        register(
                overworldWarmOceanFish(U.holderItem("hybrid-aquatic", "clownfish"))
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "clownfish"))
                        .withSizeAndWeight(FishProperties.sw(9, 2, 200, 50))
                        .withDifficulty(FishProperties.Difficulty.EASY_MOVING)
                        .withRarity(FishProperties.Rarity.COMMON)
                        .withDaytime(FishProperties.Daytime.DAY)
                        .withWeather(FishProperties.Weather.CLEAR)
        );

        register(
                overworldWarmOceanFish(U.holderItem("hybrid-aquatic", "surgeonfish"))
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "surgeonfish"))
                        .withSizeAndWeight(FishProperties.sw(25, 10, 450, 150))
                        .withDifficulty(FishProperties.Difficulty.EASY_MOVING)
                        .withRarity(FishProperties.Rarity.COMMON)
                        .withDaytime(FishProperties.Daytime.DAY)
                        .withWeather(FishProperties.Weather.CLEAR));

        register(
                overworldWarmOceanFish(U.holderItem("hybrid-aquatic", "blowfish"))
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "blowfish"))
                        .withSizeAndWeight(FishProperties.sw(18, 4, 5, 3))
                        .withDifficulty(FishProperties.Difficulty.EASY_MOVING)
                        .withRarity(FishProperties.Rarity.UNCOMMON)
                        .withDaytime(FishProperties.Daytime.DAY)
                        .withWeather(FishProperties.Weather.CLEAR));

        register(
                overworldWarmOceanFish(U.holderItem("hybrid-aquatic", "boxfish"))
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "boxfish"))
                        .withSizeAndWeight(FishProperties.sw(30, 20, 150, 50))
                        .withDifficulty(FishProperties.Difficulty.EASY_MOVING)
                        .withRarity(FishProperties.Rarity.UNCOMMON)
                        .withDaytime(FishProperties.Daytime.DAY)
                        .withWeather(FishProperties.Weather.CLEAR));

        register(
                overworldWarmOceanFish(U.holderItem("hybrid-aquatic", "damselfish"))
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "damselfish"))
                        .withSizeAndWeight(FishProperties.sw(10, 5, 20, 5))
                        .withDifficulty(FishProperties.Difficulty.EASY_MOVING)
                        .withRarity(FishProperties.Rarity.COMMON)
                        .withDaytime(FishProperties.Daytime.DAY)
                        .withWeather(FishProperties.Weather.CLEAR));

        register(
                overworldWarmOceanFish(U.holderItem("hybrid-aquatic", "moray_eel"))
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "moray_eel"))
                        .withSizeAndWeight(FishProperties.sw(250, 150, 10000, 8000))
                        .withDifficulty(FishProperties.Difficulty.HARD_VANISHING)
                        .withRarity(FishProperties.Rarity.RARE)
                        .withDaytime(FishProperties.Daytime.NIGHT));

        register(
                overworldWarmOceanFish(U.holderItem("hybrid-aquatic", "blue_spotted_stingray"))
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "stingray"))
                        .withSizeAndWeight(FishProperties.sw(50, 30, 4000, 1000))
                        .withDifficulty(FishProperties.Difficulty.HARD)
                        .withRarity(FishProperties.Rarity.RARE)
                        .withDaytime(FishProperties.Daytime.NOON)
                        .withWeather(FishProperties.Weather.CLEAR));

        register(
                overworldWarmOceanFish(U.holderItem("hybrid-aquatic", "spotted_eagle_ray"))
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "stingray"))
                        .withSizeAndWeight(FishProperties.sw(400, 100, 20000, 3000))
                        .withDifficulty(FishProperties.Difficulty.HARD)
                        .withRarity(FishProperties.Rarity.RARE)
                        .withDaytime(FishProperties.Daytime.NOON)
                        .withWeather(FishProperties.Weather.CLEAR));

        register(
                overworldWarmOceanFish(U.holderItem("hybrid-aquatic", "lionfish"))
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "lionfish"))
                        .withSizeAndWeight(FishProperties.sw(33, 5, 900, 150))
                        .withDifficulty(FishProperties.Difficulty.MEDIUM)
                        .withRarity(FishProperties.Rarity.UNCOMMON)
                        .withDaytime(FishProperties.Daytime.NOON)
                        .withWeather(FishProperties.Weather.CLEAR));

        register(
                overworldWarmOceanFish(U.holderItem("hybrid-aquatic", "needlefish"))
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "needlefish"))
                        .withSizeAndWeight(FishProperties.sw(100, 10, 2300, 300))
                        .withDifficulty(FishProperties.Difficulty.HARD)
                        .withRarity(FishProperties.Rarity.UNCOMMON)
                        .withDaytime(FishProperties.Daytime.DAY)
                        .withWeather(FishProperties.Weather.CLEAR));

        register(
                overworldWarmOceanFish(U.holderItem("hybrid-aquatic", "stonefish"))
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "stonefish"))
                        .withSizeAndWeight(FishProperties.sw(35, 5, 1500, 500))
                        .withDifficulty(FishProperties.Difficulty.MEDIUM)
                        .withRarity(FishProperties.Rarity.UNCOMMON)
                        .withDaytime(FishProperties.Daytime.NOON)
                        .withWeather(FishProperties.Weather.CLEAR));

        register(
                overworldWarmOceanFish(U.holderItem("hybrid-aquatic", "parrotfish"))
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "parrotfish"))
                        .withSizeAndWeight(FishProperties.sw(40, 10, 1600, 1400))
                        .withDifficulty(FishProperties.Difficulty.MEDIUM)
                        .withRarity(FishProperties.Rarity.COMMON)
                        .withDaytime(FishProperties.Daytime.DAY)
                        .withWeather(FishProperties.Weather.CLEAR));

        register(
                overworldWarmOceanFish(U.holderItem("hybrid-aquatic", "seahorse"))
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "seahorse"))
                        .withSizeAndWeight(FishProperties.sw(15, 10, 20, 15))
                        .withDifficulty(FishProperties.Difficulty.EASY_MOVING)
                        .withRarity(FishProperties.Rarity.UNCOMMON)
                        .withDaytime(FishProperties.Daytime.DAY)
                        .withWeather(FishProperties.Weather.CLEAR));

        register(
                overworldWarmOceanFish(U.holderItem("hybrid-aquatic", "triggerfish"))
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "triggerfish"))
                        .withSizeAndWeight(FishProperties.sw(90, 10, 4300, 2500))
                        .withDifficulty(FishProperties.Difficulty.MEDIUM)
                        .withRarity(FishProperties.Rarity.UNCOMMON)
                        .withDaytime(FishProperties.Daytime.DAY)
                        .withWeather(FishProperties.Weather.CLEAR));

        register(
                overworldWarmOceanFish(U.holderItem("hybrid-aquatic", "flying_fish"))
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "flying_fish"))
                        .withSizeAndWeight(FishProperties.sw(90, 10, 4300, 2500))
                        .withDifficulty(FishProperties.Difficulty.EASY_MOVING)
                        .withRarity(FishProperties.Rarity.COMMON));

        register(
                overworldDeepOceanFish(U.holderItem("hybrid-aquatic", "anglerfish"))
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "anglerfish"))
                        .withSizeAndWeight(FishProperties.sw(90, 10, 4300, 2500))
                        .withDifficulty(FishProperties.Difficulty.EASY_VANISHING)
                        .withRarity(FishProperties.Rarity.UNCOMMON));

        register(
                overworldDeepOceanFish(U.holderItem("hybrid-aquatic", "barreleye"))
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "anglerfish"))
                        .withSizeAndWeight(FishProperties.sw(90, 10, 4300, 2500))
                        .withDifficulty(FishProperties.Difficulty.EASY_MOVING)
                        .withRarity(FishProperties.Rarity.UNCOMMON));

        register(
                overworldDeepOceanFish(U.holderItem("hybrid-aquatic", "dragonfish"))
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "dragonfish"))
                        .withSizeAndWeight(FishProperties.sw(12, 3, 13, 3))
                        .withDifficulty(FishProperties.Difficulty.MEDIUM)
                        .withRarity(FishProperties.Rarity.UNCOMMON)
                        .withDaytime(FishProperties.Daytime.NIGHT));

        register(
                overworldDeepOceanFish(U.holderItem("hybrid-aquatic", "coelacanth"))
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "coelacanth"))
                        .withSizeAndWeight(FishProperties.sw(150, 50, 60000, 30000))
                        .withDifficulty(FishProperties.Difficulty.HARD_VANISHING)
                        .withRarity(FishProperties.Rarity.RARE));

        register(
                overworldDeepOceanFish(U.holderItem("hybrid-aquatic", "oarfish"))
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "oarfish"))
                        .withSizeAndWeight(FishProperties.sw(800, 300, 250000, 22000))
                        .withDifficulty(FishProperties.Difficulty.HARD_VANISHING)
                        .withRarity(FishProperties.Rarity.RARE)
                        .withWeather(FishProperties.Weather.THUNDER));

        register(
                overworldDeepOceanFish(U.holderItem("hybrid-aquatic", "ratfish"))
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "ratfish"))
                        .withSizeAndWeight(FishProperties.sw(90, 10, 4300, 2500))
                        .withDifficulty(FishProperties.Difficulty.EASY_MOVING)
                        .withRarity(FishProperties.Rarity.COMMON));

        register(
                overworldDeepOceanFish(U.holderItem("hybrid-aquatic", "snailfish"))
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "snailfish"))
                        .withSizeAndWeight(FishProperties.sw(90, 10, 4300, 2500))
                        .withDifficulty(FishProperties.Difficulty.EASY_MOVING)
                        .withRarity(FishProperties.Rarity.COMMON));

        register(
                overworldDeepOceanFish(U.holderItem("hybrid-aquatic", "john_dory"))
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "john_dory"))
                        .withSizeAndWeight(FishProperties.sw(90, 10, 4300, 2500))
                        .withDifficulty(FishProperties.Difficulty.MEDIUM_VANISHING)
                        .withRarity(FishProperties.Rarity.UNCOMMON));

        register(
                overworldDeepOceanFish(U.holderItem("hybrid-aquatic", "flashlight_fish"))
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "flashlight_fish"))
                        .withSizeAndWeight(FishProperties.sw(90, 10, 4300, 2500))
                        .withDifficulty(FishProperties.Difficulty.EASY_MOVING)
                        .withDaytime(FishProperties.Daytime.NIGHT)
                        .withRarity(FishProperties.Rarity.COMMON));

        register(
                overworldDeepOceanFish(U.holderItem("hybrid-aquatic", "squirrelfish"))
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "squirrelfish"))
                        .withSizeAndWeight(FishProperties.sw(90, 10, 4300, 2500))
                        .withDifficulty(FishProperties.Difficulty.EASY_MOVING)
                        .withDaytime(FishProperties.Daytime.NIGHT)
                        .withRarity(FishProperties.Rarity.COMMON));

        register(
                overworldOceanFish(U.holderItem("hybrid-aquatic", "tuna"))
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "tuna"))
                        .withSizeAndWeight(FishProperties.sw(90, 10, 4300, 2500))
                        .withDifficulty(FishProperties.Difficulty.HARD_MOVING)
                        .withRarity(FishProperties.Rarity.UNCOMMON));

        register(
                overworldOceanFish(U.holderItem("hybrid-aquatic", "mahi"))
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "mahi"))
                        .withSizeAndWeight(FishProperties.sw(90, 10, 4300, 2500))
                        .withDifficulty(FishProperties.Difficulty.HARD_MOVING)
                        .withRarity(FishProperties.Rarity.UNCOMMON));

        register(
                overworldOceanFish(U.holderItem("hybrid-aquatic", "mackerel"))
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "mackerel"))
                        .withSizeAndWeight(FishProperties.sw(90, 10, 4300, 2500))
                        .withDifficulty(FishProperties.Difficulty.EASY_MOVING)
                        .withRarity(FishProperties.Rarity.COMMON));

        register(
                overworldColdOceanFish(U.holderItem("hybrid-aquatic", "herring"))
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "herring"))
                        .withSizeAndWeight(FishProperties.sw(90, 10, 4300, 2500))
                        .withDifficulty(FishProperties.Difficulty.EASY_MOVING)
                        .withRarity(FishProperties.Rarity.COMMON));

        register(
                overworldOceanFish(U.holderItem("hybrid-aquatic", "sea_bass"))
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "sea_bass"))
                        .withSizeAndWeight(FishProperties.sw(90, 10, 4300, 2500))
                        .withDifficulty(FishProperties.Difficulty.MEDIUM)
                        .withRarity(FishProperties.Rarity.COMMON));

        register(
                overworldOceanFish(U.holderItem("hybrid-aquatic", "sheepshead_wrasse"))
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "wrasse"))
                        .withSizeAndWeight(FishProperties.sw(90, 10, 4300, 2500))
                        .withDifficulty(FishProperties.Difficulty.MEDIUM)
                        .withRarity(FishProperties.Rarity.COMMON));

        register(
                overworldOceanFish(U.holderItem("hybrid-aquatic", "pearlfish"))
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "pearlfish"))
                        .withSizeAndWeight(FishProperties.sw(90, 10, 4300, 2500))
                        .withDifficulty(FishProperties.Difficulty.MEDIUM)
                        .withRarity(FishProperties.Rarity.COMMON));

        register(
                overworldOceanFish(U.holderItem("hybrid-aquatic", "rockfish"))
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "rockfish"))
                        .withSizeAndWeight(FishProperties.sw(90, 10, 4300, 2500))
                        .withDifficulty(FishProperties.Difficulty.MEDIUM)
                        .withRarity(FishProperties.Rarity.COMMON));

        register(
                overworldDeepOceanFish(U.holderItem("hybrid-aquatic", "opah"))
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "opah"))
                        .withSizeAndWeight(FishProperties.sw(90, 10, 4300, 2500))
                        .withDifficulty(FishProperties.Difficulty.MEDIUM_MOVING)
                        .withRarity(FishProperties.Rarity.UNCOMMON)
                        .withDaytime(FishProperties.Daytime.NIGHT)
                        .withWeather(FishProperties.Weather.CLEAR));

        register(
                overworldDeepOceanFish(U.holderItem("hybrid-aquatic", "sunfish"))
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "sunfish"))
                        .withSizeAndWeight(FishProperties.sw(90, 10, 4300, 2500))
                        .withDifficulty(FishProperties.Difficulty.HARD_MOVING)
                        .withRarity(FishProperties.Rarity.RARE)
                        .withDaytime(FishProperties.Daytime.DAY)
                        .withWeather(FishProperties.Weather.CLEAR));

        register(
                overworldCherryGroveFish(U.holderItem("hybrid-aquatic", "goldfish"))
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "goldfish"))
                        .withSizeAndWeight(FishProperties.sw(90, 10, 4300, 2500))
                        .withDifficulty(FishProperties.Difficulty.EASY_VANISHING)
                        .withRarity(FishProperties.Rarity.UNCOMMON));

        register(
                overworldRiverFish(U.holderItem("hybrid-aquatic", "carp"))
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "carp"))
                        .withSizeAndWeight(FishProperties.sw(90, 10, 4300, 2500))
                        .withDifficulty(FishProperties.Difficulty.EASY_VANISHING)
                        .withRarity(FishProperties.Rarity.COMMON));

        register(
                overworldJungleFish(U.holderItem("hybrid-aquatic", "golden_dorado"))
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "golden_dorado"))
                        .withSizeAndWeight(FishProperties.sw(90, 10, 4300, 2500))
                        .withDifficulty(FishProperties.Difficulty.HARD)
                        .withRarity(FishProperties.Rarity.RARE));

        register(
                overworldJungleFish(U.holderItem("hybrid-aquatic", "oscar"))
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "oscar"))
                        .withSizeAndWeight(FishProperties.sw(90, 10, 4300, 2500))
                        .withDifficulty(FishProperties.Difficulty.EASY_VANISHING)
                        .withRarity(FishProperties.Rarity.UNCOMMON));

        register(
                overworldJungleFish(U.holderItem("hybrid-aquatic", "neon_tetra"))
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "tetra"))
                        .withSizeAndWeight(FishProperties.sw(90, 10, 4300, 2500))
                        .withDifficulty(FishProperties.Difficulty.EASY_VANISHING)
                        .withRarity(FishProperties.Rarity.COMMON));

        register(
                overworldJungleFish(U.holderItem("hybrid-aquatic", "tiger_barb"))
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "tiger_barb"))
                        .withSizeAndWeight(FishProperties.sw(90, 10, 4300, 2500))
                        .withDifficulty(FishProperties.Difficulty.EASY_VANISHING)
                        .withRarity(FishProperties.Rarity.COMMON));

        register(
                overworldJungleFish(U.holderItem("hybrid-aquatic", "betta"))
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "betta"))
                        .withSizeAndWeight(FishProperties.sw(90, 10, 4300, 2500))
                        .withDifficulty(FishProperties.Difficulty.EASY_VANISHING)
                        .withRarity(FishProperties.Rarity.UNCOMMON));

        register(
                overworldJungleFish(U.holderItem("hybrid-aquatic", "danio"))
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "danio"))
                        .withSizeAndWeight(FishProperties.sw(90, 10, 4300, 2500))
                        .withDifficulty(FishProperties.Difficulty.EASY_VANISHING)
                        .withRarity(FishProperties.Rarity.COMMON));

        register(
                overworldJungleFish(U.holderItem("hybrid-aquatic", "gourami"))
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "gourami"))
                        .withSizeAndWeight(FishProperties.sw(90, 10, 4300, 2500))
                        .withDifficulty(FishProperties.Difficulty.EASY_VANISHING)
                        .withRarity(FishProperties.Rarity.COMMON));

        register(
                overworldJungleFish(U.holderItem("hybrid-aquatic", "discus"))
                        .withEntityToSpawn(U.holderEntity("hybrid-aquatic", "discus"))
                        .withSizeAndWeight(FishProperties.sw(90, 10, 4300, 2500))
                        .withDifficulty(FishProperties.Difficulty.EASY_VANISHING)
                        .withRarity(FishProperties.Rarity.COMMON));
    }
}
