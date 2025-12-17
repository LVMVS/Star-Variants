package com.wdiscute.starcatcher.io.network.tournament.stand;


import com.mojang.authlib.GameProfile;
import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.tournament.Tournament;
import com.wdiscute.starcatcher.tournament.TournamentHandler;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.UUIDUtil;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.List;
import java.util.UUID;

public record SBStandTournamentStartCancelPayload(boolean start, UUID uuid) implements CustomPacketPayload
{

    public static final StreamCodec<ByteBuf, GameProfile> GAME_PROFILE_STREAM_CODEC = StreamCodec.composite(
            UUIDUtil.STREAM_CODEC, GameProfile::getId,
            ByteBufCodecs.STRING_UTF8, GameProfile::getName,
            GameProfile::new
    );

    public static final StreamCodec<ByteBuf, List<GameProfile>> GAME_PROFILE_STREAM_CODEC_LIST = GAME_PROFILE_STREAM_CODEC.apply(ByteBufCodecs.list());

    public static final Type<SBStandTournamentStartCancelPayload> TYPE = new Type<>(Starcatcher.rl("sb_stand_tournament_start_cancel"));

    public static final StreamCodec<RegistryFriendlyByteBuf, SBStandTournamentStartCancelPayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.BOOL, SBStandTournamentStartCancelPayload::start,
            UUIDUtil.STREAM_CODEC, SBStandTournamentStartCancelPayload::uuid,
            SBStandTournamentStartCancelPayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type()
    {
        return TYPE;
    }


    public void handle(IPayloadContext context)
    {
        Tournament t = TournamentHandler.getSetupTournamentOrNull(uuid);
        if (t == null) return;

        if (start)
            TournamentHandler.startTournament(context.player(), t);
        else
            TournamentHandler.cancelTournament(context.player(), t);
    }

}
