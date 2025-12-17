package com.wdiscute.starcatcher;

import com.mojang.logging.LogUtils;
import com.wdiscute.starcatcher.bob.FishingBobEntity;
import com.wdiscute.starcatcher.datagen.TrustedHolder;
import com.wdiscute.starcatcher.fishentity.FishEntity;
import com.wdiscute.starcatcher.io.*;
import com.wdiscute.starcatcher.minigame.modifiers.ModModifiers;
import com.wdiscute.starcatcher.registry.ModCriterionTriggers;
import com.wdiscute.starcatcher.registry.ModItems;
import com.wdiscute.starcatcher.storage.FishProperties;
import com.wdiscute.starcatcher.storage.TrophyProperties;
import com.wdiscute.starcatcher.tournament.TournamentHandler;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.function.Supplier;

public class U
{
    public static final Random r = new Random();

    public static void spawnFishFromPlayerFishing(ServerPlayer player, int time, boolean completedTreasure, boolean perfectCatch, int hits)
    {
        ServerLevel level = ((ServerLevel) player.level());

        if (ModDataAttachments.get(player, ModDataAttachments.FISHING).isEmpty()) return;

        Entity levelEntity = level.getEntity(UUID.fromString(ModDataAttachments.get(player, ModDataAttachments.FISHING)));
        if (levelEntity instanceof FishingBobEntity fbe)
        {
            if (time != -1)
            {
                FishProperties fp = fbe.fpToFish;

                ModCriterionTriggers.MINIGAME_COMPLETED.get().trigger((ServerPlayer) player, hits, perfectCatch, completedTreasure, time, fp.catchInfo().fish());

                //pick size and weight
                int size = getRandomSize(fp);
                int weight = getRandomWeight(fp);

                //award fish counter
                FishCaughtCounter.awardFishCaughtCounter(fp, player, time, size, weight, perfectCatch, true);

                //add score to tournaments
                TournamentHandler.addScore(player, fp, perfectCatch, size, weight);

                //play sound
                Vec3 p = player.position();
                level.playSound(null, p.x, p.y, p.z, SoundEvents.VILLAGER_CELEBRATE, SoundSource.AMBIENT);

                //award fish counter
                List<FishProperties> list = new ArrayList<>(U.getFpsFromRls(level, ModDataAttachments.get(player, ModDataAttachments.FISHES_NOTIFICATION)));
                list.add(fbe.fpToFish);
                ModDataAttachments.set(player, ModDataAttachments.FISHES_NOTIFICATION, U.getRlsFromFps(level, list));

                //award exp
                int exp = fp.rarity().getXp();
                if (fbe.hook.is(ModItems.GOLD_HOOK))
                    exp *= (int) ((double) hits / 3) + 1; //extra exp if gold hook is used
                player.giveExperiencePoints(exp);

                //SPAWN ENTITY if ⏬⏬⏬
                if (fp.catchInfo().alwaysSpawnEntity() ||
                        ModList.get().isLoaded("fishingreal") ||
                        ModModifiers.hasModifier(fbe.rod, Starcatcher.rl("fish_entity")))
                {

                    Vec3 objPos = player.position().subtract(fbe.position());

                    double x = objPos.x / 25;
                    double y = objPos.y / 20;
                    double z = objPos.z / 25;

                    x = Math.clamp(x, -1, 1);
                    y = Math.clamp(y, -1, 1);
                    z = Math.clamp(z, -1, 1);

                    x *= 2.5;
                    y *= 2;
                    z *= 2.5;

                    Entity entity = fp.catchInfo().entityToSpawn().value().create(level);

                    if (entity == null)
                    {
                        LogUtils.getLogger().warn("starcatcher doesnt like when the flag or whatever is not enabled");
                        return;
                    }

                    //set fish item if it's a starcatcher fish entity
                    if (entity instanceof FishEntity fe) fe.setFish(getFishedItemstackFromFP(fp, size, weight));

                    entity.setPos(fbe.position().add(0, 1.2f, 0));

                    Vec3 vec3 = new Vec3(x, 0.7 + y, z);
                    entity.setDeltaMovement(vec3);
                    level.addFreshEntity(entity);
                }
                else
                {
                    //SPAWN ITEMSTACK
                    boolean isStarcaught = fp.catchInfo().bucketedFish().is(ModItems.STARCAUGHT_BUCKET.getKey())
                            && fbe.bait.is(Items.BUCKET);
                    boolean isBucketed = !fp.catchInfo().bucketedFish().is(ModItems.MISSINGNO.getKey()) && !isStarcaught && fbe.bait.is(Items.BUCKET);

                    ItemStack is;
                    //create itemStack
                    if (isBucketed)
                    {
                        is = new ItemStack(fp.catchInfo().bucketedFish());
                    }
                    else
                    {
                        //make fish itemstack
                        is = new ItemStack(fp.catchInfo().fish());

                        //store size and weight data component
                        is.set(ModDataComponents.SIZE_AND_WEIGHT, new SizeAndWeightInstance(size, weight));

                        //store fp in itemstack for name color change
                        is.set(ModDataComponents.FISH_PROPERTIES, fp);

                        //split hook double drops unless it's gonna be converted to a starcaught bucket
                        if (perfectCatch && fbe.hook.is(ModItems.SPLIT_HOOK) && !isStarcaught) is.setCount(2);

                        if (isStarcaught)
                        {
                            ItemStack starcaughtBucket = new ItemStack(fp.catchInfo().bucketedFish());
                            starcaughtBucket.set(ModDataComponents.BUCKETED_FISH, new SingleStackContainer(is.copy()));
                            is = starcaughtBucket;
                        }
                    }


                    //make ItemEntities for fish item stack
                    ItemEntity itemFished = new ItemEntity(level, fbe.position().x, fbe.position().y + 1.2f, fbe.position().z, is);

                    //assign delta movement so fish flies towards player
                    double x = Math.clamp((player.position().x - fbe.position().x) / 25, -1, 1);
                    double y = Math.clamp((player.position().y - fbe.position().y) / 20, -1, 1);
                    double z = Math.clamp((player.position().z - fbe.position().z) / 25, -1, 1);
                    Vec3 vec3 = new Vec3(x, 0.7 + y, z);
                    itemFished.setDeltaMovement(vec3);

                    //add item entity to level
                    level.addFreshEntity(itemFished);
                }

                //spawn treasure item
                if (completedTreasure)
                {
                    ItemStack treasure = new ItemStack(fp.catchInfo().treasure());
                    ItemEntity treasureFished = new ItemEntity(level, fbe.position().x, fbe.position().y + 1.2f, fbe.position().z, treasure);
                    double x = Math.clamp((player.position().x - fbe.position().x) / 25, -1, 1);
                    double y = Math.clamp((player.position().y - fbe.position().y) / 20, -1, 1);
                    double z = Math.clamp((player.position().z - fbe.position().z) / 25, -1, 1);
                    Vec3 vec3 = new Vec3(x, 0.7 + y, z);
                    treasureFished.setDeltaMovement(vec3);
                    level.addFreshEntity(treasureFished);
                }

            }
            else
            {
                //if fish minigame failed/canceled, play sound
                Vec3 p = player.position();
                level.playSound(null, p.x, p.y, p.z, SoundEvents.VILLAGER_NO, SoundSource.AMBIENT);
            }

            fbe.kill();
        }

        ModDataAttachments.set(player, ModDataAttachments.FISHING.get(), "");
    }

    public static ItemStack getFishedItemstackFromFP(FishProperties fp)
    {
        int size = getRandomSize(fp);
        int weight = getRandomWeight(fp);
        return getFishedItemstackFromFP(fp, size, weight);
    }


    public static int getRandomSize(FishProperties fp)
    {
        return ((int) Starcatcher.truncatedNormal(fp.sw().sizeAverage(), fp.sw().sizeDeviation()));

    }

    public static int getRandomWeight(FishProperties fp)
    {
        return ((int) Starcatcher.truncatedNormal(fp.sw().weightAverage(), fp.sw().weightDeviation()));

    }

    public static ItemStack getFishedItemstackFromFP(FishProperties fp, int size, int weight)
    {
        ItemStack is = new ItemStack(fp.catchInfo().fish());
        is.set(ModDataComponents.FISH_PROPERTIES, fp);
        is.set(ModDataComponents.SIZE_AND_WEIGHT, new SizeAndWeightInstance(size, weight));
        return is;
    }

    //List<TrophyProperties> -> List<ResourceLocation>
    public static List<TrophyProperties> getTpsFromRls(Registry<TrophyProperties> registry, List<ResourceLocation> resourceLocations)
    {
        List<TrophyProperties> tps = new ArrayList<>();

        for (ResourceLocation rl : resourceLocations)
        {
            TrophyProperties trophyProperties = registry.get(rl);
            if (trophyProperties != null) tps.add(trophyProperties);
        }
        return tps;
    }

    public static List<TrophyProperties> getTpsFromRls(RegistryAccess registryAccess, List<ResourceLocation> rls)
    {
        return getTpsFromRls(registryAccess.registryOrThrow(Starcatcher.TROPHY_REGISTRY), rls);
    }

    public static List<TrophyProperties> getTpsFromRls(Level level, List<ResourceLocation> rls)
    {
        return getTpsFromRls(level.registryAccess(), rls);
    }


    //List<FishProperties> -> List<ResourceLocation>
    public static List<ResourceLocation> getRlsFromFps(Registry<FishProperties> registry, List<FishProperties> fishProperties)
    {
        List<ResourceLocation> rls = new ArrayList<>();

        for (FishProperties fp : fishProperties)
        {
            ResourceLocation resourceLocation = registry.getKey(fp);
            if (resourceLocation != null) rls.add(resourceLocation);
        }
        return rls;
    }

    public static List<ResourceLocation> getRlsFromFps(RegistryAccess registryAccess, List<FishProperties> fps)
    {
        return getRlsFromFps(registryAccess.registryOrThrow(Starcatcher.FISH_REGISTRY), fps);
    }

    public static List<ResourceLocation> getRlsFromFps(Level level, List<FishProperties> fps)
    {
        return getRlsFromFps(level.registryAccess(), fps);
    }


    //List<TrophyProperties> -> List<ResourceLocation>
    public static List<ResourceLocation> getRlsFromTps(Registry<TrophyProperties> registry, List<TrophyProperties> trophyProperties)
    {
        List<ResourceLocation> rls = new ArrayList<>();

        for (TrophyProperties tp : trophyProperties)
        {
            ResourceLocation resourceLocation = registry.getKey(tp);
            if (resourceLocation != null) rls.add(resourceLocation);
        }
        return rls;
    }

    public static List<ResourceLocation> getRlsFromTps(RegistryAccess registryAccess, List<TrophyProperties> tps)
    {
        return getRlsFromTps(registryAccess.registryOrThrow(Starcatcher.TROPHY_REGISTRY), tps);
    }

    public static List<ResourceLocation> getRlsFromTps(Level level, List<TrophyProperties> tps)
    {
        return getRlsFromTps(level.registryAccess(), tps);
    }


    //ResourceLocation -> TrophyProperties
    public static TrophyProperties getTpFromRl(Registry<TrophyProperties> registry, ResourceLocation resourceLocation)
    {
        TrophyProperties tp = registry.get(resourceLocation);
        return tp == null ? TrophyProperties.builder().build() : tp;
    }

    public static TrophyProperties getTpFromRl(RegistryAccess registryAccess, ResourceLocation rl)
    {
        return getTpFromRl(registryAccess.registryOrThrow(Starcatcher.TROPHY_REGISTRY), rl);
    }

    public static TrophyProperties getTpFromRl(Level level, ResourceLocation rl)
    {
        return getTpFromRl(level.registryAccess(), rl);
    }


    //TrophyProperties -> ResourceLocation
    public static ResourceLocation getRlFromTp(Registry<TrophyProperties> registry, TrophyProperties tp)
    {
        ResourceLocation rl = registry.getKey(tp);
        return rl == null ? Starcatcher.rl("missingno_rl") : rl;
    }

    public static ResourceLocation getRlFromTp(RegistryAccess registryAccess, TrophyProperties tp)
    {
        return getRlFromTp(registryAccess.registryOrThrow(Starcatcher.TROPHY_REGISTRY), tp);
    }

    public static ResourceLocation getRlFromTp(Level level, TrophyProperties tp)
    {
        return getRlFromTp(level.registryAccess(), tp);
    }


    //List<ResourceLocation> -> List<TrophyProperties>
    public static List<FishProperties> getFpsFromRls(Registry<FishProperties> registry, List<ResourceLocation> resourceLocations)
    {
        List<FishProperties> fps = new ArrayList<>();

        for (ResourceLocation rl : resourceLocations)
        {
            FishProperties fishProperties = registry.get(rl);
            if (fishProperties != null) fps.add(fishProperties);
        }
        return fps;
    }

    public static List<FishProperties> getFpsFromRls(RegistryAccess registryAccess, List<ResourceLocation> rls)
    {
        return getFpsFromRls(registryAccess.registryOrThrow(Starcatcher.FISH_REGISTRY), rls);
    }

    public static List<FishProperties> getFpsFromRls(Level level, List<ResourceLocation> rls)
    {
        return getFpsFromRls(level.registryAccess(), rls);
    }


    //ResourceLocation -> FishProperties
    public static FishProperties getFpFromRl(Registry<FishProperties> registry, ResourceLocation resourceLocation)
    {
        FishProperties fp = registry.get(resourceLocation);
        return fp == null ? FishProperties.builder().build() : fp;
    }

    public static FishProperties getFpFromRl(RegistryAccess registryAccess, ResourceLocation rl)
    {
        return getFpFromRl(registryAccess.registryOrThrow(Starcatcher.FISH_REGISTRY), rl);
    }

    public static FishProperties getFpFromRl(Level level, ResourceLocation rl)
    {
        return getFpFromRl(level.registryAccess(), rl);
    }


    //resource location from fish properties
    public static ResourceLocation getRlFromFp(Registry<FishProperties> registry, FishProperties fp)
    {
        ResourceLocation rl = registry.getKey(fp);
        return rl == null ? Starcatcher.rl("missingno_rl") : rl;
    }

    public static ResourceLocation getRlFromFp(RegistryAccess registryAccess, FishProperties tp)
    {
        return getRlFromFp(registryAccess.registryOrThrow(Starcatcher.FISH_REGISTRY), tp);
    }

    public static ResourceLocation getRlFromFp(Level level, FishProperties tp)
    {
        return getRlFromFp(level.registryAccess(), tp);
    }


    @SafeVarargs
    public static <T> boolean containsAny(List<T> list, T... contains)
    {
        for (T s : contains)
            if (list.contains(s)) return true;

        return false;
    }

    @SafeVarargs
    public static <T> boolean containsAll(List<T> list, T... contains)
    {
        for (T s : contains)
            if (!list.contains(s)) return false;
        return true;
    }

    @SafeVarargs
    public static <T> boolean containsNone(List<T> list, T... contains)
    {
        return !containsAny(list, contains);
    }

    public static ResourceLocation rl(String ns, String path)
    {
        return ResourceLocation.fromNamespaceAndPath(ns, path);
    }

    public static Holder<Item> holderItem(String ns, String path)
    {
        return TrustedHolder.createStandAlone(BuiltInRegistries.ITEM.holderOwner(), ResourceKey.create(Registries.ITEM, rl(ns, path)));
    }

    public static Holder<Item> holderItem(DeferredItem<Item> item)
    {
        return Holder.direct(item.get());
    }

    public static Holder<Item> holderItem(Item item)
    {
        return Holder.direct(item);
    }

    public static Holder<EntityType<?>> holderEntity(EntityType<?> entityType)
    {
        return Holder.direct(entityType);
    }

    public static Holder<EntityType<?>> holderEntity(String ns, String path)
    {
        return TrustedHolder.createStandAlone(BuiltInRegistries.ENTITY_TYPE.holderOwner(), ResourceKey.create(Registries.ENTITY_TYPE, rl(ns, path)));
    }

    public static Holder<EntityType<?>> holderEntity(Supplier<EntityType<FishEntity>> entity)
    {
        return Holder.direct(entity.get());
    }

    public static int intToRed(int packedColor)
    {
        return packedColor >> 16 & 255;
    }

    public static int intToGreen(int packedColor)
    {
        return packedColor >> 8 & 255;
    }

    public static int intToBlue(int packedColor)
    {
        return packedColor & 255;
    }

    public static int sign(float x)
    {
        return x >= 0 ? 1 : -1;
    }
}
