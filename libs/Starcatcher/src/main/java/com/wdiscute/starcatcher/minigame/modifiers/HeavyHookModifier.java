package com.wdiscute.starcatcher.minigame.modifiers;

import com.wdiscute.starcatcher.minigame.ActiveSweetSpot;

public class HeavyHookModifier extends AbstractModifier
{

    @Override
    public ActiveSweetSpot onSpotAdded(ActiveSweetSpot ass)
    {
        super.onSpotAdded(ass);
        if(ass.movingRate > 2) ass.movingRate /= 2;
        return ass;
    }
}
