package com.wdiscute.starcatcher.minigame.modifiers;

import com.wdiscute.starcatcher.minigame.ActiveSweetSpot;

public class ClearBobberModifier extends AbstractModifier
{
    @Override
    public ActiveSweetSpot onSpotAdded(ActiveSweetSpot ass)
    {
        super.onSpotAdded(ass);
        ass.vanishingRate /= 3;
        return ass;
    }
}
