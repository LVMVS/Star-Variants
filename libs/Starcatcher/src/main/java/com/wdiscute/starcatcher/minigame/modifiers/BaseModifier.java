package com.wdiscute.starcatcher.minigame.modifiers;

import com.wdiscute.starcatcher.minigame.ActiveSweetSpot;

public class BaseModifier extends AbstractModifier
{
    @Override
    public void onMiss()
    {
        super.onMiss();
        //kimbe marker
        instance.kimbeMarkerAlpha = 1;
        //You have to make the actual texture white before trying to recolor like this, dummy
        instance.kimbeMarkerColor = 0xff6767;
        instance.kimbeMarkerPos = instance.getPointerPosPrecise();

        //refresh all vanishes
        instance.refreshSweetSpotsAlphas();

    }

    @Override
    public void onHit(ActiveSweetSpot ass)
    {
        super.onHit(ass);
        instance.kimbeMarkerAlpha = 1;
        instance.kimbeMarkerColor = 0x2ce17d;
        instance.kimbeMarkerPos = instance.getPointerPosPrecise();
    }
}
