package com.wdiscute.starcatcher.minigame.modifiers;

import com.wdiscute.starcatcher.minigame.ActiveSweetSpot;

public class StoneHookModifier extends AbstractModifier
{
    @Override
    public void onHit(ActiveSweetSpot ass)
    {
        super.onHit(ass);
        instance.gracePeriod = instance.fishProperties.rarity().getStoneHookGraceTicks();;
    }
}
