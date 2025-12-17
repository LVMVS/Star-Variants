package com.wdiscute.starcatcher.minigame;

import com.wdiscute.starcatcher.storage.FishProperties;
import net.minecraft.world.item.Item;

public interface MinigameOnCloseFunction
{
    void onClose(Item fish, FishProperties fp, int timeInTicks, boolean completedTreasure, boolean perfectCatch, int maxConsecutiveHits);
}
