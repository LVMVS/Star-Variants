package com.wdiscute.starcatcher.io;

import com.mojang.serialization.Codec;
import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.items.ColorfulSmithingTemplate;
import com.wdiscute.starcatcher.secretnotes.SecretNote;
import com.wdiscute.starcatcher.storage.FishProperties;
import com.wdiscute.starcatcher.storage.TrophyProperties;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;
import java.util.function.UnaryOperator;

public class ModDataComponents
{
    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPES =
            DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, Starcatcher.MOD_ID);


    //smithing templates
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Boolean>> NETHERITE_UPGRADE = register(
            "netherite_upgraded",
            builder -> builder.persistent(Codec.BOOL));

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<SingleStackContainer>> BOBBER_SKIN = register(
            "bobber_skin",
            builder -> builder.persistent(SingleStackContainer.CODEC));

    //bucketed fish
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<SingleStackContainer>> BUCKETED_FISH = register(
            "bucketed_fish",
            builder -> builder.persistent(SingleStackContainer.CODEC));

    //rod menu
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<SingleStackContainer>> BOBBER = register(
            "bobber",
            builder -> builder.persistent(SingleStackContainer.CODEC));

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<SingleStackContainer>> BAIT = register(
            "bait",
            builder -> builder.persistent(SingleStackContainer.CODEC));

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<SingleStackContainer>> HOOK = register(
            "hook",
            builder -> builder.persistent(SingleStackContainer.CODEC));


    public static final DeferredHolder<DataComponentType<?>, DataComponentType<ColorfulSmithingTemplate.BobberColor>> BOBBER_COLOR = register(
            "color",
            builder -> builder.persistent(ColorfulSmithingTemplate.BobberColor.CODEC));

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<TrophyProperties>> TROPHY = register(
            "trophy",
            builder -> builder.persistent(TrophyProperties.CODEC));

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<FishProperties>> FISH_PROPERTIES = register(
            "fish_properties",
            builder -> builder.persistent(FishProperties.CODEC));

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<SecretNote.Note>> SECRET_NOTE = register(
            "secret_note",
            builder -> builder.persistent(SecretNote.Note.CODEC));

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<SizeAndWeightInstance>> SIZE_AND_WEIGHT = register(
            "size_and_weight",
            builder -> builder.persistent(SizeAndWeightInstance.CODEC));

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<List<ResourceLocation>>> MODIFIERS = register(
            "modifiers",
            builder -> builder.persistent(ResourceLocation.CODEC.listOf()));

    private static <T> DeferredHolder<DataComponentType<?>, DataComponentType<T>> register(String name,
                                                                                           UnaryOperator<DataComponentType.Builder<T>> builderOperator)
    {
        return DATA_COMPONENT_TYPES.register(name, () -> builderOperator.apply(DataComponentType.builder()).build());
    }

    public static void register(IEventBus eventBus)
    {
        DATA_COMPONENT_TYPES.register(eventBus);
    }

}
