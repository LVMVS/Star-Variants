package com.wdiscute.starcatcher.minigame.modifiers;

import com.wdiscute.starcatcher.U;
import com.wdiscute.starcatcher.minigame.ActiveSweetSpot;
import com.wdiscute.starcatcher.storage.FishProperties;

import java.util.Random;

public class LowChanceTreasureSpawnModifier extends AbstractModifier
{
    @Override
    public void onHit(ActiveSweetSpot ass)
    {
        super.onHit(ass);
        if (U.r.nextFloat() > 0.975 && instance.progress == 0)
        {
            removed = true;
            ActiveSweetSpot newTreasureSweetSpot = new ActiveSweetSpot(instance, FishProperties.SweetSpot.TREASURE);
            instance.addSweetSpot(newTreasureSweetSpot);
        }
    }
}
