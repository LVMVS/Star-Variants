package com.wdiscute.starcatcher.items.cheater;

import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.U;
import com.wdiscute.starcatcher.io.FishCaughtCounter;
import com.wdiscute.starcatcher.storage.FishProperties;
import com.wdiscute.starcatcher.io.ModDataAttachments;
import com.wdiscute.starcatcher.io.network.FishCaughtPayload;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AwardOneFish extends Item
{
    public AwardOneFish()
    {
        super(new Properties().stacksTo(1));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand)
    {
        if(!player.isCreative())
            return InteractionResultHolder.pass(player.getItemInHand(usedHand));List<FishCaughtCounter> fishCounter;

        List<FishProperties> fishes = new ArrayList<>(U.getFpsFromRls(level, ModDataAttachments.get(player, ModDataAttachments.FISHES_NOTIFICATION)));

        fishCounter = new ArrayList<>(ModDataAttachments.get(player, ModDataAttachments.FISHES_CAUGHT));

        Optional<Holder.Reference<FishProperties>> optional = level.registryAccess().registryOrThrow(Starcatcher.FISH_REGISTRY).getRandom(level.random);

        if(optional.isPresent())
        {
            if(optional.get().is(U.rl("minecraft", "nether_star"))) return InteractionResultHolder.pass(player.getItemInHand(usedHand));
            FishProperties fp = optional.get().value();

            //todo fix this awarding repeated entries. It should check which entries the player doesnt have to award a new one instead
            fishCounter.add(new FishCaughtCounter(U.getRlFromFp(level, fp), 999999, 0, 0, 0, 0, false, false));
            fishes.add(fp);

            if(player instanceof ServerPlayer sp)
            {
                PacketDistributor.sendToPlayer(sp, new FishCaughtPayload(fp, false, 0, 0));
            }
        }

        ModDataAttachments.set(player, ModDataAttachments.FISHES_CAUGHT, fishCounter);
        ModDataAttachments.set(player, ModDataAttachments.FISHES_NOTIFICATION, U.getRlsFromFps(level, fishes));

        return InteractionResultHolder.success(player.getItemInHand(usedHand));
    }


}
