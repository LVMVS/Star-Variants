package com.wdiscute.starcatcher.registry.fishing;

import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.U;
import com.wdiscute.starcatcher.registry.ModItems;
import com.wdiscute.starcatcher.storage.FishProperties;
import net.dries007.tfc.client.overworld.Star;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;

import static com.wdiscute.starcatcher.registry.fishing.FishingPropertiesRegistry.*;


public class MinecraftFishingProperties {
    public static void bootstrap() {

        //ocean
        register(overworldOceanFish(BuiltInRegistries.ITEM.wrapAsHolder(Items.COD))
                .withDifficulty(FishProperties.Difficulty.EASY_MOVING)
                .withSizeAndWeight(FishProperties.sw(80, 40, 12000, 7000)));

        register(overworldOceanFish(BuiltInRegistries.ITEM.wrapAsHolder(Items.PUFFERFISH))
                .withSizeAndWeight(FishProperties.sw(70, 20, 10000, 3000))
                .withDifficulty(FishProperties.Difficulty.MEDIUM_VANISHING)
                .withRarity(FishProperties.Rarity.UNCOMMON)
                .withBaseChance(4));

        //river
        register(overworldRiverFish(BuiltInRegistries.ITEM.wrapAsHolder(Items.SALMON))
                .withSizeAndWeight(FishProperties.sw(80, 40, 10000, 8000)));


        //mobs
        register(fish(BuiltInRegistries.ITEM.wrapAsHolder(Items.NETHER_STAR))
                .withAlwaysSpawnEntity(true)
                .withEntityToSpawn(U.holderEntity("minecraft", "wither"))
                .withBaseChance(0)
                .withBaitRestrictions(
                        FishProperties.BaitRestrictions.DEFAULT
                                .withCorrectBait(BuiltInRegistries.ITEM.getKey(Items.WITHER_SKELETON_SKULL))
                                .withCorrectBaitChanceAdded(200)
                )
                .withDifficulty(FishProperties.Difficulty.WITHER)
                .withItemToOverrideWith(ModItems.UNKNOWN_FISH)
                .withRarity(FishProperties.Rarity.LEGENDARY)
        );

        register(fish(BuiltInRegistries.ITEM.wrapAsHolder(Items.CREEPER_HEAD))
                .withAlwaysSpawnEntity(true)
                .withEntityToSpawn(U.holderEntity("minecraft", "creeper"))
                .withBaseChance(0)
                .withBaitRestrictions(
                        FishProperties.BaitRestrictions.DEFAULT
                                .withCorrectBait(Starcatcher.rl("gunpowder_bait"))
                                .withCorrectBaitChanceAdded(100)
                )
                .withDifficulty(FishProperties.Difficulty.CREEPER)
                .withItemToOverrideWith(ModItems.UNKNOWN_FISH)
                .withRarity(FishProperties.Rarity.EPIC)
        );

        register(overworldSurfaceFish(U.holderItem("minecraft", "rotten_flesh"))
                .withSizeAndWeight(FishProperties.SizeAndWeight.NONE)
                .withBaseChance(1)
                .withDaytime(FishProperties.Daytime.NIGHT)
                .withWeather(FishProperties.Weather.RAIN)
                .withHasGuideEntry(false)
                .withAlwaysSpawnEntity(true)
                .withEntityToSpawn(U.holderEntity("minecraft", "drowned"))
                .withSkipMinigame(true));
    }
}
