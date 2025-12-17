package com.wdiscute.starcatcher.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.datafixers.util.Either;
import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.StarcatcherTags;
import com.wdiscute.starcatcher.io.ModDataComponents;
import com.wdiscute.starcatcher.io.network.FishingStartedPayload;
import com.wdiscute.starcatcher.minigame.modifiers.AbstractModifier;
import com.wdiscute.starcatcher.storage.FishProperties;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.ResourceOrTagArgument;
import net.minecraft.commands.arguments.ResourceOrTagKeyArgument;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public class ModCommands
{
    private static final DynamicCommandExceptionType ERROR_ROD = new DynamicCommandExceptionType(
            o -> Component.translatableEscape("commands.starcatcher.rod_not_found", o)
    );

    private static final DynamicCommandExceptionType ERROR_EMPTY = new DynamicCommandExceptionType(
            o -> Component.translatableEscape("commands.starcatcher.rod_not_found", o)
    );

    private static final DynamicCommandExceptionType ERROR_FISH_ENTRY_INVALID = new DynamicCommandExceptionType(
            o -> Component.translatableEscape("commands.starcatcher.fish_entry_not_found", o)
    );

    private static final DynamicCommandExceptionType ERROR_MODIFIER_INVALID = new DynamicCommandExceptionType(
            o -> Component.translatableEscape("commands.starcatcher.modifier_not_found", o)
    );

    public static final LiteralArgumentBuilder<CommandSourceStack>
            COMMAND = Commands.literal("starcatcher")
            .requires(sourceStack -> sourceStack.hasPermission(2))
            .then(Commands.literal("simulate_fish")
                    .then(Commands.argument("fish_entry", ResourceOrTagKeyArgument.resourceOrTagKey(Starcatcher.FISH_REGISTRY))
                            .executes(context ->
                                    startMinigame(
                                            context.getSource().getPlayerOrException(),
                                            ResourceOrTagKeyArgument.getResourceOrTagKey(
                                                    context,
                                                    "fish_entry",
                                                    Starcatcher.FISH_REGISTRY,
                                                    ERROR_FISH_ENTRY_INVALID)
                                    )
                            )
                    )

            )
            .then(Commands.literal("add_modifier")
                    .then(Commands.argument("modifier", ResourceOrTagKeyArgument.resourceOrTagKey(Starcatcher.MODIFIERS))
                            .executes(context ->
                                    addModifier(
                                            context.getSource().getPlayerOrException(),
                                            ResourceOrTagKeyArgument.getResourceOrTagKey(
                                                    context,
                                                    "modifier",
                                                    Starcatcher.MODIFIERS,
                                                    ERROR_FISH_ENTRY_INVALID)
                                    )
                            ))
            );

    private static int addModifier(ServerPlayer player, ResourceOrTagKeyArgument.Result<Supplier<AbstractModifier>> fishPropertiesResult) throws CommandSyntaxException
    {
        if (player.getMainHandItem().isEmpty()) throw ERROR_EMPTY.create(null);

        Either<ResourceKey<Supplier<AbstractModifier>>, TagKey<Supplier<AbstractModifier>>> unwrap = fishPropertiesResult.unwrap();
        Optional<ResourceKey<Supplier<AbstractModifier>>> left = unwrap.left();

        if (left.isPresent())
        {
            ResourceLocation location = left.get().location();

            if (player.getMainHandItem().has(ModDataComponents.MODIFIERS))
            {
                List<ResourceLocation> mods = new ArrayList<>(player.getMainHandItem().get(ModDataComponents.MODIFIERS));
                mods.add(location);
                player.getMainHandItem().set(ModDataComponents.MODIFIERS, mods);
            }
            else
            {
                player.getMainHandItem().set(ModDataComponents.MODIFIERS, List.of(location));
            }
            return 1;
        }
        throw ERROR_MODIFIER_INVALID.create(unwrap.left());
    }

    private static int startMinigame(ServerPlayer player, ResourceOrTagKeyArgument.Result<FishProperties> fishPropertiesResult) throws CommandSyntaxException
    {
        //serverside
        Level level = player.level();

        if (!player.getMainHandItem().is(StarcatcherTags.RODS)) throw ERROR_ROD.create(null);

        Either<ResourceKey<FishProperties>, TagKey<FishProperties>> unwrap = fishPropertiesResult.unwrap();
        Optional<ResourceKey<FishProperties>> left = unwrap.left();

        if (left.isPresent())
        {
            ResourceLocation location = left.get().location();
            Optional<FishProperties> optionalFP = level.registryAccess().registryOrThrow(Starcatcher.FISH_REGISTRY).getOptional(location);

            if (optionalFP.isPresent())
            {
                PacketDistributor.sendToPlayer(
                        player,
                        new FishingStartedPayload(optionalFP.get(), player.getMainHandItem()));
                return 1;
            }
            else
            {
                throw ERROR_FISH_ENTRY_INVALID.create(location);
            }
        }
        throw ERROR_FISH_ENTRY_INVALID.create(unwrap.left());
    }
}
