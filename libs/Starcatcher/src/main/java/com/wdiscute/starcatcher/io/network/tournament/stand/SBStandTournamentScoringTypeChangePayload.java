package com.wdiscute.starcatcher.io.network.tournament.stand;


import com.mojang.authlib.GameProfile;
import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.tournament.TournamentHandler;
import com.wdiscute.starcatcher.tournament.TournamentSettings;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.UUIDUtil;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.List;
import java.util.UUID;

public record SBStandTournamentScoringTypeChangePayload(UUID uuid, TournamentSettings.Scoring scoringType) implements CustomPacketPayload
{

    public static final StreamCodec<ByteBuf, GameProfile> GAME_PROFILE_STREAM_CODEC = StreamCodec.composite(
            UUIDUtil.STREAM_CODEC, GameProfile::getId,
            ByteBufCodecs.STRING_UTF8, GameProfile::getName,
            GameProfile::new
    );

    public static final StreamCodec<ByteBuf, List<GameProfile>> GAME_PROFILE_STREAM_CODEC_LIST = GAME_PROFILE_STREAM_CODEC.apply(ByteBufCodecs.list());

    public static final Type<SBStandTournamentScoringTypeChangePayload> TYPE = new Type<>(Starcatcher.rl("sb_stand_tournament_scoring"));

    public static final StreamCodec<RegistryFriendlyByteBuf, SBStandTournamentScoringTypeChangePayload> STREAM_CODEC = StreamCodec.composite(
            UUIDUtil.STREAM_CODEC, SBStandTournamentScoringTypeChangePayload::uuid,
            TournamentSettings.Scoring.STREAM_CODEC, SBStandTournamentScoringTypeChangePayload::scoringType,
            SBStandTournamentScoringTypeChangePayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type()
    {
        return TYPE;
    }


    public void handle(IPayloadContext context)
    {
        TournamentHandler.setScoring(((ServerPlayer) context.player()), uuid, scoringType);
    }

}
