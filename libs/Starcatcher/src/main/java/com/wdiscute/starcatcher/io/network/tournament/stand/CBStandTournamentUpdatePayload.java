package com.wdiscute.starcatcher.io.network.tournament.stand;


import com.mojang.authlib.GameProfile;
import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.tournament.StandScreen;
import com.wdiscute.starcatcher.tournament.Tournament;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.core.UUIDUtil;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.players.GameProfileCache;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public record CBStandTournamentUpdatePayload(List<GameProfile> listSignups,
                                             Tournament tour) implements CustomPacketPayload
{

    public static CBStandTournamentUpdatePayload helper(Player player, Tournament tournament)
    {
        if (player.level().isClientSide) throw new RuntimeException();
        List<GameProfile> list = new ArrayList<>();
        for (var entry : tournament.getPlayerScores().entrySet())
        {
            GameProfileCache profileCache = player.level().getServer().getProfileCache();
            if (profileCache != null)
            {
                Optional<GameProfile> gameProfile = profileCache.get(entry.getKey());
                gameProfile.ifPresent(list::add);
            }
        }

        return new CBStandTournamentUpdatePayload(list, tournament);
    }

    public static final StreamCodec<ByteBuf, GameProfile> GAME_PROFILE_STREAM_CODEC = StreamCodec.composite(
            UUIDUtil.STREAM_CODEC, GameProfile::getId,
            ByteBufCodecs.STRING_UTF8, GameProfile::getName,
            GameProfile::new
    );

    public static final StreamCodec<ByteBuf, List<GameProfile>> GAME_PROFILE_STREAM_CODEC_LIST = GAME_PROFILE_STREAM_CODEC.apply(ByteBufCodecs.list());


    public static final Type<CBStandTournamentUpdatePayload> TYPE = new Type<>(Starcatcher.rl("sb_stand_tournament_update"));

    public static final StreamCodec<RegistryFriendlyByteBuf, CBStandTournamentUpdatePayload> STREAM_CODEC = StreamCodec.composite(
            GAME_PROFILE_STREAM_CODEC_LIST, CBStandTournamentUpdatePayload::listSignups,
            Tournament.STREAM_CODEC, CBStandTournamentUpdatePayload::tour,
            CBStandTournamentUpdatePayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type()
    {
        return TYPE;
    }


    public void handle(IPayloadContext context)
    {
        clientReceiveTournamentData(this, context);
    }

    @OnlyIn(Dist.CLIENT)
    public static void clientReceiveTournamentData(CBStandTournamentUpdatePayload data, final IPayloadContext context)
    {
        //only accept packet if cache is empty, or if it's the correct uuid being disaplyed
        if (Minecraft.getInstance().screen instanceof StandScreen ss)
        {
            if (ss.tournamentCache == null)
                ss.onTournamentReceived(data.tour());
            else if (ss.tournamentCache.tournamentUUID.equals(data.tour.tournamentUUID))
                ss.onTournamentReceived(data.tour());
        }
        StandScreen.gameProfilesCache = new HashMap<>();
        data.listSignups().forEach(e -> StandScreen.gameProfilesCache.put(e.getId(), e.getName()));
    }
}
