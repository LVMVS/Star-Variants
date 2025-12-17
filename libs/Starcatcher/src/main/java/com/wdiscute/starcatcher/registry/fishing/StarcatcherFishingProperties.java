package com.wdiscute.starcatcher.registry.fishing;

import com.wdiscute.starcatcher.registry.ModItems;
import com.wdiscute.starcatcher.storage.FishProperties;
import com.wdiscute.starcatcher.storage.FishProperties.WorldRestrictions.Seasons;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biomes;

import static com.wdiscute.starcatcher.registry.fishing.FishingPropertiesRegistry.*;

public class StarcatcherFishingProperties
{
    public static void bootstrap()
    {
        registerStarcatcherBucketAndEntity(overworldLakeFish(ModItems.OBIDONTIEE)
                .withSizeAndWeight(FishProperties.sw(17.7f, 5, 1200, 200)));

        registerStarcatcherBucketAndEntity(overworldLakeFish(ModItems.MORGANITE)
                .withSeasons(Seasons.SUMMER, Seasons.AUTUMN)
                .withSizeAndWeight(FishProperties.sw(120, 80, 7000, 1000))
                .withWeather(FishProperties.Weather.RAIN)
                .withRarity(FishProperties.Rarity.UNCOMMON)
                .withDifficulty(FishProperties.Difficulty.MEDIUM));

        registerStarcatcherBucketAndEntity(overworldLakeFish(ModItems.SILVERVEIL_PERCH)
                .withSeasons(Seasons.SPRING, Seasons.WINTER)
                .withSizeAndWeight(FishProperties.sw(27.0f, 11, 500, 352))
                .withRarity(FishProperties.Rarity.UNCOMMON)
                .withDifficulty(FishProperties.Difficulty.MEDIUM_MOVING));

        registerStarcatcherBucketAndEntity(overworldLakeFish(ModItems.ELDERSCALE)
                .withSizeAndWeight(FishProperties.sw(160.0f, 85, 2300, 652))
                .withSeasons(Seasons.LATE_SPRING, Seasons.SUMMER, Seasons.AUTUMN)
                .withDifficulty(FishProperties.Difficulty.EASY_VANISHING)
                .withRarity(FishProperties.Rarity.UNCOMMON)
                .withBaseChance(3));

        registerStarcatcherBucketAndEntity(overworldLakeFish(ModItems.DRIFTFIN)
                .withSizeAndWeight(FishProperties.sw(16.0f, 3, 167, 70))
                .withSeasons(Seasons.SUMMER, Seasons.AUTUMN)
                .withWeather(FishProperties.Weather.CLEAR));

        registerStarcatcherBucketAndEntity(overworldLakeFish(ModItems.TWILIGHT_KOI)
                .withSizeAndWeight(FishProperties.sw(60, 13, 3500, 731))
                .withDaytime(FishProperties.Daytime.MIDNIGHT)
                .withRarity(FishProperties.Rarity.EPIC)
                .withWeather(FishProperties.Weather.RAIN)
                .withDifficulty(FishProperties.Difficulty.HARD_MOVING));

        registerStarcatcherBucketAndEntity(overworldLakeFish(ModItems.THUNDER_BASS)
                .withSizeAndWeight(FishProperties.sw(40, 12, 1200, 800))
                .withSeasons(Seasons.SUMMER, Seasons.AUTUMN)
                .withRarity(FishProperties.Rarity.RARE)
                .withWeather(FishProperties.Weather.THUNDER)
                .withDifficulty(FishProperties.Difficulty.HARD));

        registerStarcatcherBucketAndEntity(overworldLakeFish(ModItems.LIGHTNING_BASS)
                .withSizeAndWeight(FishProperties.sw(40, 12, 1300, 620))
                .withSeasons(Seasons.SUMMER, Seasons.AUTUMN)
                .withRarity(FishProperties.Rarity.RARE)
                .withWeather(FishProperties.Weather.THUNDER)
                .withDifficulty(FishProperties.Difficulty.HARD_VANISHING));

        register(overworldLakeFish(ModItems.BOOT).withBaseChance(1)
                .withDifficulty(FishProperties.Difficulty.TRASH)
                .withHasGuideEntry(false));


        //cold lake
        registerStarcatcherBucketAndEntity(overworldColdLakeFish(ModItems.FROSTJAW_TROUT)
                .withSizeAndWeight(FishProperties.sw(35, 8, 1600, 1200))
                .withDifficulty(FishProperties.Difficulty.FOUR_BIG_VANISHING));

        registerStarcatcherBucketAndEntity(overworldColdLakeFish(ModItems.CRYSTALBACK_TROUT)
                .withSizeAndWeight(FishProperties.sw(35, 8, 1600, 1200))
                .withSeasons(Seasons.AUTUMN, Seasons.WINTER)
                .withDifficulty(FishProperties.Difficulty.MEDIUM));

        registerStarcatcherBucketAndEntity(overworldColdLakeFish(ModItems.AURORA)
                .withTreasure(ModItems.AZURE_CRYSTAL_ROD)
                .withSizeAndWeight(FishProperties.sw(10, 8, 120, 30))
                .withRarity(FishProperties.Rarity.LEGENDARY)
                .withBaitRestrictions(FishProperties.BaitRestrictions.LEGENDARY_BAIT)
                .withBaseChance(2)
                .withDifficulty(FishProperties.Difficulty.NON_STOP_ACTION_AQUA));

        registerStarcatcherBucketAndEntity(overworldColdLakeFish(ModItems.WINTERY_PIKE)
                .withSeasons(Seasons.EARLY_SPRING, Seasons.LATE_AUTUMN, Seasons.WINTER)
                .withSizeAndWeight(FishProperties.sw(75, 20, 5000, 3000))
                .withDifficulty(FishProperties.Difficulty.EASY_MOVING));


        //lake warm
        registerStarcatcherBucketAndEntity(overworldWarmLakeFish(ModItems.SANDTAIL)
                .withSizeAndWeight(FishProperties.sw(200, 100, 1600, 1200))
                .withSeasons(Seasons.SUMMER, Seasons.AUTUMN)
                .withDaytime(FishProperties.Daytime.NIGHT));

        registerStarcatcherBucketAndEntity(overworldWarmLakeFish(ModItems.MIRAGE_CARP)
                .withSizeAndWeight(FishProperties.sw(60, 20, 6000, 4000))
                .withDifficulty(FishProperties.Difficulty.MEDIUM)
                .withDaytime(FishProperties.Daytime.DAY)
                .withWeather(FishProperties.Weather.CLEAR)
                .withRarity(FishProperties.Rarity.UNCOMMON));

        registerStarcatcherBucketAndEntity(overworldWarmLakeFish(ModItems.SCORCHFISH)
                .withSizeAndWeight(FishProperties.sw(60, 20, 6000, 4000))
                .withSeasons(Seasons.SPRING, Seasons.SUMMER, Seasons.AUTUMN)
                .withWeather(FishProperties.Weather.CLEAR));

        registerStarcatcherBucketAndEntity(overworldWarmLakeFish(ModItems.CACTIFISH)
                .withSizeAndWeight(FishProperties.sw(100, 50, 10000, 3000))
                .withSeasons(Seasons.SUMMER, Seasons.SUMMER)
                .withDaytime(FishProperties.Daytime.DAY));

        registerStarcatcherBucketAndEntity(overworldWarmLakeFish(ModItems.AGAVE_BREAM)
                .withSizeAndWeight(FishProperties.sw(36, 12, 2000, 1000))
                .withRarity(FishProperties.Rarity.RARE)
                .withDaytime(FishProperties.Daytime.NIGHT)
                .withWeather(FishProperties.Weather.CLEAR)
                .withDifficulty(FishProperties.Difficulty.HARD));


        //mountain
        registerStarcatcherBucketAndEntity(overworldMountainFish(ModItems.SUNNY_STURGEON)
                .withSizeAndWeight(FishProperties.sw(400, 200, 100000, 50000))
                .withSeasons(Seasons.SPRING, Seasons.SUMMER)
                .withDifficulty(FishProperties.Difficulty.HARD_MOVING)
                .withRarity(FishProperties.Rarity.RARE)
                .withDaytime(FishProperties.Daytime.DAY)
                .withBaseChance(2));

        registerStarcatcherBucketAndEntity(overworldMountainFish(ModItems.PEAKDWELLER)
                .withSeasons(Seasons.EARLY_SPRING, Seasons.AUTUMN, Seasons.WINTER)
                .withSizeAndWeight(FishProperties.sw(100, 50, 10000, 5000))
                .withDifficulty(FishProperties.Difficulty.HARD));

        registerStarcatcherBucketAndEntity(overworldMountainFish(ModItems.ROCKGILL)
                .withSizeAndWeight(FishProperties.sw(100, 50, 10000, 5000))
                .withDifficulty(FishProperties.Difficulty.MEDIUM));

        registerStarcatcherBucketAndEntity(overworldMountainFish(ModItems.SUN_SEEKING_CARP)
                .withSeasons(Seasons.LATE_SPRING, Seasons.SUMMER, Seasons.EARLY_AUTUMN)
                .withSizeAndWeight(FishProperties.sw(60, 20, 6000, 4000))
                .withRarity(FishProperties.Rarity.RARE)
                .withBaseChance(2)
                .withDaytime(FishProperties.Daytime.NOON));


        //swamp
        registerStarcatcherBucketAndEntity(overworldSwampFish(ModItems.SLUDGE_CATFISH)
                .withSizeAndWeight(FishProperties.sw(100, 50, 10000, 3000))
                .withSeasons(Seasons.SPRING, Seasons.SUMMER)
                .withRarity(FishProperties.Rarity.UNCOMMON));

        registerStarcatcherBucketAndEntity(overworldSwampFish(ModItems.LILY_SNAPPER)
                .withSizeAndWeight(FishProperties.sw(60, 20, 7000, 2000))
                .withRarity(FishProperties.Rarity.RARE)
                .withDifficulty(FishProperties.Difficulty.MEDIUM));

        registerStarcatcherBucketAndEntity(overworldSwampFish(ModItems.SAGE_CATFISH)
                .withSizeAndWeight(FishProperties.sw(100, 50, 10000, 3000))
                .withSeasons(Seasons.EARLY_SPRING, Seasons.AUTUMN, Seasons.WINTER)
                .withRarity(FishProperties.Rarity.EPIC)
                .withDifficulty(FishProperties.Difficulty.SINGLE_BIG_FAST)
                .withDaytime(FishProperties.Daytime.NIGHT)
                .withWeather(FishProperties.Weather.CLEAR));

        register(overworldSwampFish(ModItems.MOSSY_BOOT).withBaseChance(1)
                .withDifficulty(FishProperties.Difficulty.TRASH)
                .withHasGuideEntry(false));


        //darkoak forest
        registerStarcatcherBucketAndEntity(overworldDarkForestFish(ModItems.PALE_PINFISH)
                .withSizeAndWeight(FishProperties.sw(15, 5, 150, 100))
                .withSeasons(Seasons.EARLY_SPRING, Seasons.LATE_AUTUMN, Seasons.WINTER)
                .withDaytime(FishProperties.Daytime.MIDNIGHT)
                .withRarity(FishProperties.Rarity.RARE)
                .withDifficulty(FishProperties.Difficulty.THREE_AQUA_ONE_BIG_ONE_SMALL));

        registerStarcatcherBucketAndEntity(overworldDarkForestFish(ModItems.PINFISH)
                .withSizeAndWeight(FishProperties.sw(15, 5, 150, 100))
                .withDaytime(FishProperties.Daytime.NIGHT)
                .withRarity(FishProperties.Rarity.UNCOMMON)
                .withDifficulty(FishProperties.Difficulty.MEDIUM));

        registerStarcatcherBucketAndEntity(overworldDarkForestFish(ModItems.PALE_CARP)
                .withSeasons(Seasons.EARLY_SPRING, Seasons.LATE_AUTUMN, Seasons.WINTER)
                .withSizeAndWeight(FishProperties.sw(60, 20, 6000, 4000))
                .withDaytime(FishProperties.Daytime.DAY));


        //cherry grove
        registerStarcatcherBucketAndEntity(overworldCherryGroveFish(ModItems.VESANI)
                .withSeasons(Seasons.SPRING, Seasons.WINTER)
                .withSizeAndWeight(FishProperties.sw(10, 3, 67, 0))
                .withRarity(FishProperties.Rarity.LEGENDARY)
                .withDifficulty(FishProperties.Difficulty.THREE_AQUA_ONE_BIG_ONE_SMALL_VANISHING));

        registerStarcatcherBucketAndEntity(overworldCherryGroveFish(ModItems.BLOSSOMFISH)
                .withSeasons(Seasons.SPRING, Seasons.SUMMER)
                .withSizeAndWeight(FishProperties.sw(60, 20, 6000, 4000))
                .withWeather(FishProperties.Weather.CLEAR));

        registerStarcatcherBucketAndEntity(overworldCherryGroveFish(ModItems.PETALDRIFT_CARP)
                .withSizeAndWeight(FishProperties.sw(60, 20, 6000, 4000))
                .withDifficulty(FishProperties.Difficulty.MEDIUM)
                .withWeather(FishProperties.Weather.RAIN)
                .withRarity(FishProperties.Rarity.UNCOMMON));

        registerStarcatcherBucketAndEntity(overworldCherryGroveFish(ModItems.PINK_KOI)
                .withSeasons(Seasons.SPRING, Seasons.AUTUMN)
                .withSizeAndWeight(FishProperties.sw(60, 20, 3000, 2000))
                .withWeather(FishProperties.Weather.RAIN));

        registerStarcatcherBucketAndEntity(overworldCherryGroveFish(ModItems.ROSE_SIAMESE_FISH)
                .withSeasons(Seasons.SPRING, Seasons.AUTUMN)
                .withSizeAndWeight(FishProperties.sw(30, 10, 1000, 500))
                .withDifficulty(FishProperties.Difficulty.MEDIUM_VANISHING)
                .withDaytime(FishProperties.Daytime.DAY)
                .withWeather(FishProperties.Weather.RAIN)
                .withRarity(FishProperties.Rarity.EPIC));


        //cold mountain
        registerStarcatcherBucketAndEntity(overworldColdMountainFish(ModItems.CRYSTALBACK_STURGEON)
                .withSizeAndWeight(FishProperties.sw(400, 200, 100000, 50000)));

        registerStarcatcherBucketAndEntity(overworldColdMountainFish(ModItems.ICETOOTH_STURGEON)
                .withSeasons(Seasons.EARLY_SPRING, Seasons.WINTER)
                .withSizeAndWeight(FishProperties.sw(400, 200, 100000, 50000))
                .withDifficulty(FishProperties.Difficulty.MEDIUM));

        registerStarcatcherBucketAndEntity(overworldColdMountainFish(ModItems.BOREAL)
                .withSizeAndWeight(FishProperties.sw(30, 15, 1000, 200))
                .withDifficulty(FishProperties.Difficulty.MEDIUM_VANISHING_MOVING)
                .withRarity(FishProperties.Rarity.LEGENDARY)
                .withBaitRestrictions(FishProperties.BaitRestrictions.LEGENDARY_BAIT)
                .withBaseChance(3));

        registerStarcatcherBucketAndEntity(overworldColdMountainFish(ModItems.CRYSTALBACK_BOREAL)
                .withSeasons(Seasons.SUMMER, Seasons.WINTER)
                .withSizeAndWeight(FishProperties.sw(30, 15, 6000, 2000))
                .withDifficulty(FishProperties.Difficulty.MEDIUM));


        //river
        registerStarcatcherBucketAndEntity(overworldRiverFish(ModItems.DOWNFALL_BREAM)
                .withSizeAndWeight(FishProperties.sw(36, 12, 2000, 1000))
                .withDaytime(FishProperties.Daytime.NIGHT)
                .withWeather(FishProperties.Weather.RAIN)
                .withDifficulty(FishProperties.Difficulty.EASY_VANISHING));

        registerStarcatcherBucketAndEntity(overworldRiverFish(ModItems.DRIFTING_BREAM)
                .withSeasons(Seasons.SUMMER, Seasons.AUTUMN)
                .withSizeAndWeight(FishProperties.sw(36, 12, 2000, 1000))
                .withDaytime(FishProperties.Daytime.NIGHT)
                .withDifficulty(FishProperties.Difficulty.EASY_MOVING));

        registerStarcatcherBucketAndEntity(overworldRiverFish(ModItems.WILLOW_BREAM)
                .withSeasons(Seasons.SUMMER, Seasons.AUTUMN)
                .withSizeAndWeight(FishProperties.sw(36, 12, 2000, 1000))
                .withDaytime(FishProperties.Daytime.NIGHT)
                .withDifficulty(FishProperties.Difficulty.HARD_VANISHING)
                .withRarity(FishProperties.Rarity.EPIC)
                .withBaseChance(2));

        registerStarcatcherBucketAndEntity(overworldRiverFish(ModItems.HOLLOWBELLY_DARTER)
                .withSeasons(Seasons.SPRING, Seasons.SUMMER)
                .withSizeAndWeight(FishProperties.sw(6, 2, 7, 6))
                .withDifficulty(FishProperties.Difficulty.EASY_MOVING));

        registerStarcatcherBucketAndEntity(overworldRiverFish(ModItems.MISTBACK_CHUB)
                .withSeasons(Seasons.SPRING, Seasons.SUMMER)
                .withSizeAndWeight(FishProperties.sw(30, 10, 1400, 600)));

        registerStarcatcherBucketAndEntity(overworldRiverFish(ModItems.BLUEGIGI)
                .withSeasons(Seasons.SPRING, Seasons.SUMMER)
                .withSizeAndWeight(FishProperties.sw(20, 5, 400, 100)));

        registerStarcatcherBucketAndEntity(overworldRiverFish(ModItems.SILVERFIN_PIKE)
                .withSizeAndWeight(FishProperties.sw(75, 20, 5000, 3000))
                .withDifficulty(FishProperties.Difficulty.MEDIUM)
                .withDifficulty(FishProperties.Difficulty.EASY_MOVING)
                .withRarity(FishProperties.Rarity.UNCOMMON));

        register(overworldRiverFish(ModItems.DRIED_SEAWEED)
                .withDifficulty(FishProperties.Difficulty.TRASH)
                .withBaseChance(1)
                .withHasGuideEntry(false));


        //cold river
        registerStarcatcherBucketAndEntity(overworldColdRiverFish(ModItems.FROSTGILL_CHUB)
                .withSizeAndWeight(FishProperties.sw(30, 10, 1400, 600))
                .withSeasons(Seasons.SPRING, Seasons.SUMMER, Seasons.AUTUMN));

        registerStarcatcherBucketAndEntity(overworldColdRiverFish(ModItems.CRYSTALBACK_MINNOW)
                .withSizeAndWeight(FishProperties.sw(6, 4, 5, 3))
                .withSeasons(Seasons.WINTER)
                .withDifficulty(FishProperties.Difficulty.EASY_MOVING)
                .withDaytime(FishProperties.Daytime.NIGHT));

        registerStarcatcherBucketAndEntity(overworldColdRiverFish(ModItems.AZURE_CRYSTALBACK_MINNOW)
                .withSizeAndWeight(FishProperties.sw(6, 4, 5, 3))
                .withDaytime(FishProperties.Daytime.MIDNIGHT)
                .withRarity(FishProperties.Rarity.LEGENDARY)
                .withBaitRestrictions(FishProperties.BaitRestrictions.LEGENDARY_BAIT)
                .withBaseChance(1)
                .withDifficulty(FishProperties.Difficulty.NON_STOP_ACTION_AQUA));

        registerStarcatcherBucketAndEntity(overworldColdRiverFish(ModItems.BLUE_CRYSTAL_FIN)
                .withSeasons(Seasons.WINTER)
                .withSizeAndWeight(FishProperties.sw(12, 4, 70, 30))
                .withDaytime(FishProperties.Daytime.DAY)
                .withDifficulty(FishProperties.Difficulty.EASY_MOVING)
                .withRarity(FishProperties.Rarity.UNCOMMON));


        //ocean
        registerStarcatcherBucketAndEntity(overworldOceanFish(ModItems.SEA_BASS)
                .withSeasons(Seasons.EARLY_SPRING, Seasons.MID_AUTUMN, Seasons.LATE_AUTUMN, Seasons.WINTER)
                .withSizeAndWeight(FishProperties.sw(40, 12, 1600, 1100))
                .withBaseChance(15)
                .withDifficulty(FishProperties.Difficulty.EASY_MOVING)
                .withDaytime(FishProperties.Daytime.DAY));

        registerStarcatcherBucketAndEntity(overworldOceanFish(ModItems.IRONJAW_HERRING)
                .withSizeAndWeight(FishProperties.sw(30, 8, 300, 100))
                .withDifficulty(FishProperties.Difficulty.EIGHT_THIN_VANISHING)
                .withBaseChance(2)
                .withRarity(FishProperties.Rarity.UNCOMMON));

        registerStarcatcherBucketAndEntity(overworldOceanFish(ModItems.DEEPJAW_HERRING)
                .withSeasons(Seasons.SPRING, Seasons.SUMMER)
                .withSizeAndWeight(FishProperties.sw(30, 8, 300, 100))
                .withDifficulty(FishProperties.Difficulty.MEDIUM));

        registerStarcatcherBucketAndEntity(overworldOceanFish(ModItems.DUSKTAIL_SNAPPER)
                .withSeasons(Seasons.SPRING, Seasons.SUMMER)
                .withSizeAndWeight(FishProperties.sw(60, 20, 7000, 2000)));

        registerStarcatcherBucketAndEntity(overworldOceanFish(ModItems.JOEL)
                .withSeasons(Seasons.SUMMER)
                .withSizeAndWeight(FishProperties.sw(69, 0, 2000, 600))
                .withDifficulty(FishProperties.Difficulty.JOEL)
                .withBaseChance(1)
                .withBaitRestrictions(FishProperties.BaitRestrictions.LEGENDARY_BAIT)
                .withRarity(FishProperties.Rarity.LEGENDARY));

        registerStarcatcherBucketAndEntity(overworldOceanFish(ModItems.REDSCALED_TUNA)
                .withSizeAndWeight(FishProperties.sw(150, 50, 120000, 60000))
                .withDaytime(FishProperties.Daytime.NIGHT)
                .withRarity(FishProperties.Rarity.UNCOMMON)
                .withDifficulty(FishProperties.Difficulty.SINGLE_BIG_FAST));

        register(overworldOceanFish(ModItems.WATERLOGGED_BOTTLE)
                .withBaseChance(1)
                .withDifficulty(FishProperties.Difficulty.TRASH)
                .withHasGuideEntry(false));

        //deep ocean
        registerStarcatcherBucketAndEntity(overworldDeepOceanFish(ModItems.BIGEYE_TUNA)
                .withSeasons(Seasons.SPRING, Seasons.WINTER)
                .withSizeAndWeight(FishProperties.sw(150, 50, 120000, 60000))
                .withDaytime(FishProperties.Daytime.NIGHT)
                .withRarity(FishProperties.Rarity.UNCOMMON)
                .withDifficulty(FishProperties.Difficulty.HARD_MOVING));

        //beach
        register(overworldBeachFish(ModItems.CONCH)
                .withSizeAndWeight(FishProperties.sw(5, 2, 100, 49))
                .withDifficulty(FishProperties.Difficulty.TRASH));

        register(overworldBeachFish(ModItems.CLAM)
                .withSizeAndWeight(FishProperties.sw(20, 5, 1000, 400))
                .withDifficulty(FishProperties.Difficulty.TRASH));

        //mushroom islands
        registerStarcatcherBucketAndEntity(overworldMushroomFieldsFish(ModItems.SHROOMFISH)
                .withSizeAndWeight(FishProperties.sw(70, 50, 4000, 2000))
                .withRarity(FishProperties.Rarity.LEGENDARY)
                .withBaitRestrictions(FishProperties.BaitRestrictions.LEGENDARY_BAIT)
                .withDifficulty(FishProperties.Difficulty.EIGHT_THIN_MOVING_VANISHING));

        registerStarcatcherBucketAndEntity(overworldMushroomFieldsFish(ModItems.SPOREFISH)
                .withSizeAndWeight(FishProperties.sw(70, 50, 4000, 2000))
                .withRarity(FishProperties.Rarity.RARE)
                .withDifficulty(FishProperties.Difficulty.HARD_MOVING));


        //underground
        registerStarcatcherBucketAndEntity(overworldUndergroundFish(ModItems.GOLD_FAN)
                .withSizeAndWeight(FishProperties.sw(70, 50, 4000, 2000)));

        registerStarcatcherBucketAndEntity(overworldUndergroundFish(ModItems.GEODE_EEL)
                .withSizeAndWeight(FishProperties.sw(500, 150, 10000, 2000))
                .withRarity(FishProperties.Rarity.EPIC)
                .withBaseChance(1)
                .withDifficulty(FishProperties.Difficulty.HARD_VANISHING));

        //caves
        registerStarcatcherBucketAndEntity(overworldCavesFish(ModItems.WHITEVEIL)
                .withSizeAndWeight(FishProperties.sw(100, 30, 3000, 2000))
                .withDifficulty(FishProperties.Difficulty.EASY_MOVING));

        registerStarcatcherBucketAndEntity(overworldCavesFish(ModItems.BLACK_EEL)
                .withSizeAndWeight(FishProperties.sw(500, 150, 6000, 2000))
                .withDifficulty(FishProperties.Difficulty.MEDIUM)
                .withRarity(FishProperties.Rarity.UNCOMMON));

        registerStarcatcherBucketAndEntity(overworldCavesFish(ModItems.STONEFISH)
                .withSizeAndWeight(FishProperties.sw(300, 150, 26000, 7000))
                .withDifficulty(FishProperties.Difficulty.MEDIUM_MOVING));

        registerStarcatcherBucketAndEntity(overworldCavesFish(ModItems.AMETHYSTBACK)
                .withSizeAndWeight(FishProperties.sw(300, 150, 16000, 7000))
                .withDifficulty(FishProperties.Difficulty.SINGLE_BIG_FAST)
                .withRarity(FishProperties.Rarity.EPIC)
                .withWorldRestrictions(FishProperties.WorldRestrictions.OVERWORLD_STONE_CAVES.withMustBeCaughtBelowY(-20)
                        .withMustBeCaughtAboveY(-40)));

        //dripstone caves
        registerStarcatcherBucketAndEntity(overworldDripstoneCavesFish(ModItems.FOSSILIZED_ANGELFISH)
                .withSizeAndWeight(FishProperties.sw(700, 150, 36000, 7000))
                .withRarity(FishProperties.Rarity.RARE)
                .withDifficulty(FishProperties.Difficulty.THIN_NO_DECAY));

        registerStarcatcherBucketAndEntity(overworldDripstoneCavesFish(ModItems.DRIPFIN)
                .withSizeAndWeight(FishProperties.sw(300, 150, 16000, 7000))
                .withDifficulty(FishProperties.Difficulty.EASY_MOVING));

        registerStarcatcherBucketAndEntity(overworldDripstoneCavesFish(ModItems.YELLOWSTONE_FISH)
                .withSizeAndWeight(FishProperties.sw(600, 150, 22000, 7000))
                .withDifficulty(FishProperties.Difficulty.MEDIUM)
                .withRarity(FishProperties.Rarity.UNCOMMON));


        //lush caves
        registerStarcatcherBucketAndEntity(overworldLushCavesFish(ModItems.LUSH_PIKE)
                .withSizeAndWeight(FishProperties.sw(75, 20, 5000, 3000))
                .withDifficulty(FishProperties.Difficulty.HEAVY_EIGHT_AQUA_MOVING)
                .withBaitRestrictions(FishProperties.BaitRestrictions.LEGENDARY_BAIT)
                .withRarity(FishProperties.Rarity.LEGENDARY)
                .withBaseChance(2));

        registerStarcatcherBucketAndEntity(overworldLushCavesFish(ModItems.VIVID_MOSS)
                .withSizeAndWeight(FishProperties.sw(120, 70, 7000, 3000))
                .withDifficulty(FishProperties.Difficulty.HARD_MOVING)
                .withRarity(FishProperties.Rarity.UNCOMMON)
                .withBaseChance(4));

        registerStarcatcherBucketAndEntity(overworldLushCavesFish(ModItems.THE_QUARRISH)
                .withSizeAndWeight(FishProperties.sw(620, 270, 700000, 300000))
                .withDifficulty(FishProperties.Difficulty.HEAVY_FIVE_NORMAL)
                .withRarity(FishProperties.Rarity.EPIC)
                .withWorldRestrictions(FishProperties.WorldRestrictions.OVERWORLD.withBiomes(Biomes.LUSH_CAVES.location())
                        .withBiomesTags(BiomeTags.IS_JUNGLE.location())));


        //deepslate
        registerStarcatcherBucketAndEntity(overworldDeepslateFish(ModItems.GHOSTLY_PIKE)
                .withSizeAndWeight(FishProperties.sw(75, 20, 5000, 3000))
                .withRarity(FishProperties.Rarity.UNCOMMON)
                .withBaseChance(2));

        registerStarcatcherBucketAndEntity(overworldDeepslateFish(ModItems.DEEPSLATEFISH)
                .withSizeAndWeight(FishProperties.sw(420, 70, 70000, 20000))
                .withDifficulty(FishProperties.Difficulty.HARD));

        registerStarcatcherBucketAndEntity(overworldDeepslateFish(ModItems.AQUAMARINE_PIKE)
                .withSizeAndWeight(FishProperties.sw(75, 20, 5000, 3000))
                .withDifficulty(FishProperties.Difficulty.MEDIUM));

        registerStarcatcherBucketAndEntity(overworldDeepslateFish(ModItems.GARNET_MACKEREL)
                .withSizeAndWeight(FishProperties.sw(40, 20, 2000, 1500))
                .withDifficulty(FishProperties.Difficulty.HARD_VANISHING)
                .withRarity(FishProperties.Rarity.UNCOMMON));

        registerStarcatcherBucketAndEntity(overworldDeepslateFish(ModItems.BRIGHT_AMETHYST_SNAPPER)
                .withSizeAndWeight(FishProperties.sw(60, 20, 7000, 2000))
                .withDifficulty(FishProperties.Difficulty.HARD)
                .withRarity(FishProperties.Rarity.EPIC)
                .withBaseChance(2));

        registerStarcatcherBucketAndEntity(overworldDeepslateFish(ModItems.DARK_AMETHYST_SNAPPER)
                .withSizeAndWeight(FishProperties.sw(60, 20, 7000, 2000))
                .withDifficulty(FishProperties.Difficulty.EIGHT_THIN_MOVING)
                .withRarity(FishProperties.Rarity.EPIC)
                .withBaseChance(2));


        //deep dark
        registerStarcatcherBucketAndEntity(overworldDeepDarkFish(ModItems.SCULKFISH)
                .withSizeAndWeight(FishProperties.sw(30, 10, 2000, 600))
                .withDifficulty(FishProperties.Difficulty.HARD_MOVING)
                .withRarity(FishProperties.Rarity.UNCOMMON));

        registerStarcatcherBucketAndEntity(overworldDeepDarkFish(ModItems.WARD)
                .withSizeAndWeight(FishProperties.sw(50, 10, 2600, 600))
                .withDifficulty(FishProperties.Difficulty.HEAVY_EIGHT_AQUA)
                .withBaitRestrictions(FishProperties.BaitRestrictions.LEGENDARY_BAIT)
                .withRarity(FishProperties.Rarity.LEGENDARY)
                .withBaseChance(2));

        registerStarcatcherBucketAndEntity(overworldDeepDarkFish(ModItems.GLOWING_DARK)
                .withSizeAndWeight(FishProperties.sw(100, 10, 3000, 600))
                .withRarity(FishProperties.Rarity.UNCOMMON)
                .withDifficulty(FishProperties.Difficulty.SINGLE_BIG_FAST_MOVING));


        //overworld surface lava
        registerStarcatcherBucketAndEntity(overworldSurfaceLava(ModItems.SUNEATER)
                .withSizeAndWeight(FishProperties.sw(100, 10, 3000, 600))
                .withRarity(FishProperties.Rarity.RARE)
                .withDifficulty(FishProperties.Difficulty.SINGLE_BIG_FAST_MOVING));

        registerStarcatcherBucketAndEntity(overworldSurfaceLava(ModItems.PYROTROUT)
                .withSizeAndWeight(FishProperties.sw(40, 20, 1200, 700))
                .withRarity(FishProperties.Rarity.UNCOMMON)
                .withDifficulty(FishProperties.Difficulty.MEDIUM));

        registerStarcatcherBucketAndEntity(overworldSurfaceLava(ModItems.OBSIDIAN_EEL)
                .withSizeAndWeight(FishProperties.sw(500, 150, 70000, 13000))
                .withWeather(FishProperties.Weather.RAIN)
                .withDifficulty(FishProperties.Difficulty.MEDIUM_VANISHING_MOVING)
                .withBaitRestrictions(FishProperties.BaitRestrictions.LEGENDARY_BAIT)
                .withRarity(FishProperties.Rarity.LEGENDARY));

        //overworld underground lava
        registerStarcatcherBucketAndEntity(overworldUndergroundLava(ModItems.MOLTEN_SHRIMP)
                .withSizeAndWeight(FishProperties.sw(10, 3, 20, 10))
                .withRarity(FishProperties.Rarity.RARE)
                .withDifficulty(FishProperties.Difficulty.HARD));

        registerStarcatcherBucketAndEntity(overworldUndergroundLava(ModItems.OBSIDIAN_CRAB)
                .withSizeAndWeight(FishProperties.sw(15, 8, 700, 300))
                .withDifficulty(FishProperties.Difficulty.OBSIDIAN_CRAB)
                .withRarity(FishProperties.Rarity.EPIC));

        //overworld deepslate lava
        registerStarcatcherBucketAndEntity(overworldDeepslateLava(ModItems.SCORCHED_BLOODSUCKER)
                .withSizeAndWeight(FishProperties.sw(60, 30, 1700, 300))
                .withRarity(FishProperties.Rarity.EPIC)
                .withDifficulty(FishProperties.Difficulty.HARD));

        registerStarcatcherBucketAndEntity(overworldDeepslateLava(ModItems.MOLTEN_DEEPSLATE_CRAB)
                .withSizeAndWeight(FishProperties.sw(15, 8, 700, 300))
                .withRarity(FishProperties.Rarity.EPIC)
                .withDifficulty(FishProperties.Difficulty.DEEPSLATE_CRAB));


        //nether
        registerStarcatcherBucketAndEntity(netherLavaFish(ModItems.EMBERGILL)
                .withSizeAndWeight(FishProperties.sw(220, 70, 5700, 900))
                .withDifficulty(FishProperties.Difficulty.HARD));

        registerStarcatcherBucketAndEntity(netherLavaFish(ModItems.LAVA_CRAB)
                .withSizeAndWeight(FishProperties.sw(15, 8, 700, 300))
                .withRarity(FishProperties.Rarity.EPIC)
                .withDifficulty(FishProperties.Difficulty.NETHER_CRAB));

        registerStarcatcherBucketAndEntity(netherLavaFish(ModItems.MAGMA_FISH)
                .withSizeAndWeight(FishProperties.sw(120, 40, 3700, 900))
                .withRarity(FishProperties.Rarity.UNCOMMON)
                .withDifficulty(FishProperties.Difficulty.HARD));

        registerStarcatcherBucketAndEntity(netherLavaFish(ModItems.GLOWSTONE_SEEKER)
                .withSizeAndWeight(FishProperties.sw(120, 40, 3700, 900))
                .withDifficulty(FishProperties.Difficulty.NON_STOP_ACTION_THREE_BIG));

        registerStarcatcherBucketAndEntity(netherLavaFish(ModItems.CINDER_SQUID)
                .withSizeAndWeight(FishProperties.sw(40, 20, 1300, 700))
                .withDifficulty(FishProperties.Difficulty.FOUR_AQUA)
                .withRarity(FishProperties.Rarity.RARE)
                .withBaseChance(2));

        registerStarcatcherBucketAndEntity(netherLavaFish(ModItems.SCALDING_PIKE)
                .withSizeAndWeight(FishProperties.sw(75, 20, 5000, 3000))
                .withDifficulty(FishProperties.Difficulty.MEDIUM_VANISHING));

        registerStarcatcherBucketAndEntity(netherLavaFish(ModItems.GLOWSTONE_PUFFERFISH)
                .withSizeAndWeight(FishProperties.sw(35, 25, 1000, 700))
                .withRarity(FishProperties.Rarity.RARE)
                .withDifficulty(FishProperties.Difficulty.MEDIUM_VANISHING));

        registerStarcatcherBucketAndEntity(netherLavaBasaltDeltasFish(ModItems.WILLISH)
                .withSizeAndWeight(FishProperties.sw(75, 25, 4000, 700))
                .withRarity(FishProperties.Rarity.UNCOMMON)
                .withDifficulty(FishProperties.Difficulty.MEDIUM_MOVING));

        register(netherLavaFish(ModItems.LAVA_CRAB_CLAW).withBaseChance(1)
                .withDifficulty(FishProperties.Difficulty.TRASH)
                .withHasGuideEntry(false));


        //the end
        registerStarcatcherBucketAndEntity(endFish(ModItems.CHARFISH)
                .withSizeAndWeight(FishProperties.sw(135, 25, 4000, 700))
                .withRarity(FishProperties.Rarity.RARE)
                .withDifficulty(FishProperties.Difficulty.HARD));

        registerStarcatcherBucketAndEntity(endFish(ModItems.CHORUS_CRAB)
                .withSizeAndWeight(FishProperties.sw(15, 8, 700, 300))
                .withRarity(FishProperties.Rarity.EPIC)
                .withDifficulty(FishProperties.Difficulty.END_CRAB));

        registerStarcatcherBucketAndEntity(endFish(ModItems.END_GLOW)
                .withSizeAndWeight(FishProperties.sw(235, 25, 7000, 700))
                .withRarity(FishProperties.Rarity.UNCOMMON)
                .withDifficulty(FishProperties.Difficulty.MEDIUM));

        registerStarcatcherBucketAndEntity(endOuterIslandsFish(ModItems.VOIDBITER)
                .withSizeAndWeight(FishProperties.sw(50, 15, 2000, 200))
                .withRarity(FishProperties.Rarity.EPIC)
                .withDifficulty(FishProperties.Difficulty.VOIDBITER)
                .withBaitRestrictions(FishProperties.BaitRestrictions.LEGENDARY_BAIT_VOIDBITER));


    }
}
