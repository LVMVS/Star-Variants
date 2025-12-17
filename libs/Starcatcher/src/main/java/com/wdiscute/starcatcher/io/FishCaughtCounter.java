package com.wdiscute.starcatcher.io;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.wdiscute.starcatcher.Config;
import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.compat.FTBTeamsCompat;
import com.wdiscute.starcatcher.io.network.FishCaughtPayload;
import com.wdiscute.starcatcher.storage.FishProperties;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.ArrayList;
import java.util.List;

public record FishCaughtCounter(
        ResourceLocation fp,
        int count,
        int fastestTicks,
        float averageTicks,
        int size,
        int weight,
        boolean caughtGolden,
        boolean perfectCatch
)
{

    public static final Codec<FishCaughtCounter> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    ResourceLocation.CODEC.fieldOf("fps").forGetter(FishCaughtCounter::fp),
                    Codec.INT.optionalFieldOf("count", 0).forGetter(FishCaughtCounter::count),
                    Codec.INT.optionalFieldOf("fastest_ticks", 0).forGetter(FishCaughtCounter::fastestTicks),
                    Codec.FLOAT.optionalFieldOf("average_ticks", 0.0f).forGetter(FishCaughtCounter::averageTicks),
                    Codec.INT.optionalFieldOf("best_size", 0).forGetter(FishCaughtCounter::size),
                    Codec.INT.optionalFieldOf("best_weight", 0).forGetter(FishCaughtCounter::weight),
                    Codec.BOOL.optionalFieldOf("caught_golden", false).forGetter(FishCaughtCounter::caughtGolden),
                    Codec.BOOL.optionalFieldOf("perfect_catch", false).forGetter(FishCaughtCounter::caughtGolden)
            ).apply(instance, FishCaughtCounter::new)
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, FishCaughtCounter> STREAM_CODEC = ExtraComposites.composite(
            ResourceLocation.STREAM_CODEC, FishCaughtCounter::fp,
            ByteBufCodecs.VAR_INT, FishCaughtCounter::count,
            ByteBufCodecs.VAR_INT, FishCaughtCounter::fastestTicks,
            ByteBufCodecs.FLOAT, FishCaughtCounter::averageTicks,
            ByteBufCodecs.INT, FishCaughtCounter::size,
            ByteBufCodecs.INT, FishCaughtCounter::weight,
            ByteBufCodecs.BOOL, FishCaughtCounter::caughtGolden,
            ByteBufCodecs.BOOL, FishCaughtCounter::perfectCatch,
            FishCaughtCounter::new
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, List<FishCaughtCounter>> LIST_STREAM_CODEC = STREAM_CODEC.apply(ByteBufCodecs.list());


    public static final Codec<List<FishCaughtCounter>> LIST_CODEC = FishCaughtCounter.CODEC.listOf();

    public static void awardFishCaughtCounter(FishProperties fpCaught, Player player, int ticks, int size, int weight, boolean perfectCatch, boolean awardToTeam)
    {
        //ftb teams compat to share fishes caught to team, does not share size and weight
        if (ModList.get().isLoaded("ftbteams") && awardToTeam && Config.ENABLE_FTB_TEAM_SHARING.get())
        {
            FTBTeamsCompat.awardToTeam(player, fpCaught);
        }

        List<FishCaughtCounter> listFishCaughtCounter = ModDataAttachments.get(player, ModDataAttachments.FISHES_CAUGHT);
        List<FishCaughtCounter> newlist = new ArrayList<>();

        boolean newFish = true;

        for (FishCaughtCounter fcc : listFishCaughtCounter)
        {
            if (fpCaught.equals(player.level().registryAccess().registryOrThrow(Starcatcher.FISH_REGISTRY).get(fcc.fp)))
            {
                int fastestToSave = Math.min(fcc.fastestTicks, ticks);
                float averageToSave = (fcc.averageTicks * fcc.count + ticks) / (fcc.count + 1);
                int countToSave = fcc.count;
                boolean perfect = perfectCatch || fcc.perfectCatch;

                //if cheated in, fixes trackers
                if (fcc.fastestTicks == 0) fastestToSave = ticks;
                if (fcc.averageTicks == 0) averageToSave = ticks;
                if (fcc.count == 999999) countToSave = 0;

                int sizeToSave = Math.max(size, fcc.size);
                int weightToSave = Math.max(weight, fcc.weight);

                newlist.add(new FishCaughtCounter(
                        player.level().registryAccess().registryOrThrow(Starcatcher.FISH_REGISTRY).getKey(fpCaught),
                        countToSave + 1,
                        fastestToSave,
                        averageToSave, sizeToSave, weightToSave,
                        false,
                        perfect));


                newFish = false;
            }
            else
            {
                newlist.add(fcc);
            }
        }

        if (newFish)
            newlist.add(new FishCaughtCounter(player.level().registryAccess().registryOrThrow(Starcatcher.FISH_REGISTRY).getKey(fpCaught), 1, ticks, ticks, size, weight, false, perfectCatch));

        //send packet to client to display message above exp bar and fish caught toast, unless it alwaysSpawnEntity() (where sw and caught doesnt make sense)
        if (!fpCaught.catchInfo().alwaysSpawnEntity())
            PacketDistributor.sendToPlayer(((ServerPlayer) player), new FishCaughtPayload(fpCaught, newFish, size, weight));

        ModDataAttachments.set(player, ModDataAttachments.FISHES_CAUGHT, newlist);
    }

}
