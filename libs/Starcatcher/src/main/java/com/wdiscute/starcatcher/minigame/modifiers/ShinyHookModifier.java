package com.wdiscute.starcatcher.minigame.modifiers;

import com.wdiscute.starcatcher.minigame.ActiveSweetSpot;
import com.wdiscute.starcatcher.storage.FishProperties;

public class ShinyHookModifier extends AbstractModifier
{
    private int hits = 0;

    @Override
    public void onMiss()
    {
        super.onMiss();
        hits = 0;
    }

    @Override
    public void onHit(ActiveSweetSpot ass)
    {
        super.onHit(ass);
        hits++;

        if(hits == 3 && !instance.treasureActive && instance.treasureProgress == 0)
        {
            instance.addSweetSpot(new ActiveSweetSpot(instance, FishProperties.SweetSpot.TREASURE));
            removed = true;
        }
    }
}
