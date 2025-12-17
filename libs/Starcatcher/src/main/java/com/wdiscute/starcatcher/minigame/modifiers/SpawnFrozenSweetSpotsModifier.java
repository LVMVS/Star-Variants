package com.wdiscute.starcatcher.minigame.modifiers;

import com.wdiscute.starcatcher.minigame.ActiveSweetSpot;
import com.wdiscute.starcatcher.storage.FishProperties;

import java.util.Random;

public class SpawnFrozenSweetSpotsModifier extends AbstractModifier
{
    private final Random r = new Random();

    @Override
    public void tick()
    {
        super.tick();
        if (r.nextFloat() > 0.9f)
        {
            ActiveSweetSpot activeSweetSpot = new ActiveSweetSpot(instance, FishProperties.SweetSpot.FREEZE);
            activeSweetSpot.shouldSudokuOnVanish = true;
            activeSweetSpot.vanishingRate = 0.02f;
            instance.addSweetSpot(activeSweetSpot);
        }
    }
}
