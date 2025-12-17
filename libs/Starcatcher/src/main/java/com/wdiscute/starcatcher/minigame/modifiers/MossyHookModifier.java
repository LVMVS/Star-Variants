package com.wdiscute.starcatcher.minigame.modifiers;

import com.wdiscute.starcatcher.minigame.ActiveSweetSpot;
import com.wdiscute.starcatcher.storage.FishProperties;

public class MossyHookModifier extends AbstractModifier
{

    @Override
    public void tick()
    {
        super.tick();
        if(tickCount == 1)
        {
            instance.removeAllSweetSpots();

            instance.addSweetSpot(new ActiveSweetSpot(instance, FishProperties.SweetSpot.THIN_STEADY));
            instance.addSweetSpot(new ActiveSweetSpot(instance, FishProperties.SweetSpot.AQUA_10));
        }
    }
}
