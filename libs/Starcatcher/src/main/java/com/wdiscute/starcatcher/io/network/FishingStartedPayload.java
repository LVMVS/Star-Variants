package com.wdiscute.starcatcher.io.network;

import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.storage.FishProperties;
import com.wdiscute.starcatcher.minigame.FishingMinigameScreen;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record FishingStartedPayload(FishProperties fp, ItemStack rod) implements CustomPacketPayload {

    public static final Type<FishingStartedPayload> TYPE = new Type<>(Starcatcher.rl("fishing_started"));

    public static final StreamCodec<ByteBuf, FishingStartedPayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.fromCodec(FishProperties.CODEC),
            FishingStartedPayload::fp,
            ByteBufCodecs.fromCodec(ItemStack.CODEC),
            FishingStartedPayload::rod,
            FishingStartedPayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }


    public void handle(IPayloadContext context) {
        client(this, context);
    }

    @OnlyIn(Dist.CLIENT)
    public static void client(FishingStartedPayload data, IPayloadContext context) {
        Minecraft.getInstance().setScreen(new FishingMinigameScreen(data.fp(), data.rod()));
    }
}
