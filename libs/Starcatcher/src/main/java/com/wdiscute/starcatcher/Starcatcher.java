package com.wdiscute.starcatcher;

import com.wdiscute.starcatcher.minigame.sweetspotbehaviour.ModSweetSpotsBehaviour;
import com.wdiscute.starcatcher.registry.blocks.ModBlockEntities;
import com.wdiscute.starcatcher.registry.blocks.ModBlocks;
import com.wdiscute.starcatcher.datagen.TrustedHolder;
import com.wdiscute.starcatcher.guide.FishCaughtToast;
import com.wdiscute.starcatcher.guide.SettingsScreen;
import com.wdiscute.starcatcher.io.*;
import com.wdiscute.starcatcher.minigame.modifiers.AbstractModifier;
import com.wdiscute.starcatcher.minigame.modifiers.ModModifiers;
import com.wdiscute.starcatcher.minigame.sweetspotbehaviour.AbstractSweetSpotBehaviour;
import com.wdiscute.starcatcher.registry.*;
import com.wdiscute.starcatcher.storage.FishProperties;
import com.wdiscute.starcatcher.storage.TrophyProperties;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.registries.RegistryBuilder;

import java.util.Random;
import java.util.function.Supplier;

@Mod(Starcatcher.MOD_ID)
public class Starcatcher
{
    public static final String MOD_ID = "starcatcher";

    public static final ResourceKey<Registry<FishProperties>> FISH_REGISTRY =
            ResourceKey.createRegistryKey(Starcatcher.rl("fish"));

    public static final ResourceKey<Registry<TrophyProperties>> TROPHY_REGISTRY =
            ResourceKey.createRegistryKey(Starcatcher.rl("trophy"));

    public static final ResourceKey<Registry<Supplier<? extends AbstractSweetSpotBehaviour>>> SWEET_SPOT_TYPES =
            ResourceKey.createRegistryKey(Starcatcher.rl("sweet_spot_types"));

    public static final ResourceKey<Registry<Supplier<AbstractModifier>>> MODIFIERS =
            ResourceKey.createRegistryKey(Starcatcher.rl("modifiers"));


    public static final Registry<Supplier<? extends AbstractSweetSpotBehaviour>> SWEET_SPOTS_REGISTRY = new RegistryBuilder<>(SWEET_SPOT_TYPES)
            .sync(true)
            .defaultKey(Starcatcher.rl("normal"))
            .create();

    public static final Registry<Supplier<AbstractModifier>> MODIFIERS_REGISTRY = new RegistryBuilder<>(MODIFIERS)
            .sync(true)
            .defaultKey(Starcatcher.rl("normal"))
            .create();

    public static final Random r = new Random();

    public static double truncatedNormal(double mean, double deviation)
    {
        while (true)
        {
            double value = mean + deviation * r.nextGaussian();
            if (value >= mean - deviation && value <= mean + deviation)
            {
                return value;
            }
        }
    }

    public static ResourceLocation rl(String s)
    {
        return ResourceLocation.fromNamespaceAndPath(Starcatcher.MOD_ID, s);
    }

    public static Holder<Item> fromRL(String ns, String path)
    {
        return TrustedHolder.createStandAlone(BuiltInRegistries.ITEM.holderOwner(), ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(ns, path)));
    }

    @OnlyIn(Dist.CLIENT)
    public static void fishCaughtToast(FishProperties fp, boolean newFish, int sizeCM, int weightCM)
    {
        if (newFish) Minecraft.getInstance().getToasts().addToast(new FishCaughtToast(fp));

        SettingsScreen.Units units = Config.UNIT.get();

        String size = units.getSizeAsString(sizeCM);
        String weight = units.getWeightAsString(weightCM);

        Minecraft.getInstance().player.displayClientMessage(
                Component.literal("")
                        .append(Component.translatable(fp.catchInfo().fish().value().getDescriptionId()))
                        .append(Component.literal(" - " + size + " - " + weight))
                , true);

        Minecraft.getInstance().gui.overlayMessageTime = 180;
    }


    public Starcatcher(IEventBus modEventBus, ModContainer modContainer)
    {
        ModCreativeModeTabs.register(modEventBus);

        ModItems.ITEMS_REGISTRY.register(modEventBus);

        ModItems.BAITS_REGISTRY.register(modEventBus);
        ModItems.HOOKS_REGISTRY.register(modEventBus);
        ModItems.BOBBERS_REGISTRY.register(modEventBus);

        ModItems.FISH_REGISTRY.register(modEventBus);
        ModItems.TRASH_REGISTRY.register(modEventBus);

        ModItems.BLOCKITEMS_REGISTRY.register(modEventBus);
        ModItems.RODS_REGISTRY.register(modEventBus);
        ModItems.TEMPLATES_REGISTRY.register(modEventBus);

        ModItems.DEV_REGISTRY.register(modEventBus);

        ModBlocks.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModDataComponents.register(modEventBus);
        ModSounds.register(modEventBus);
        ModEntities.register(modEventBus);
        ModParticles.register(modEventBus);
        ModRecipes.register(modEventBus);
        ModMenuTypes.register(modEventBus);
        ModDataAttachments.register(modEventBus);
        ModSweetSpotsBehaviour.register(modEventBus);
        ModModifiers.register(modEventBus);
        ModCriterionTriggers.register(modEventBus);

        modContainer.registerConfig(ModConfig.Type.CLIENT, Config.SPEC);
        modContainer.registerConfig(ModConfig.Type.SERVER, Config.SPEC_SERVER);
    }
}
