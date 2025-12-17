package com.wdiscute.starcatcher.minigame.sweetspotbehaviour;

import net.minecraft.client.Minecraft;
import net.minecraft.sounds.SoundEvents;

public class NormalSweetSpotBehaviour extends AbstractSweetSpotBehaviour
{
    @Override
    public void onHit()
    {
        super.onHit();
        instance.addParticles(ass.pos, 30, ass.particleColor);
        instance.progress += ass.reward;
        if(ass.isFlip) instance.currentRotation *= -1;
        ass.pos = instance.getRandomFreePosition(ass.thickness);
        Minecraft.getInstance().player.playSound(SoundEvents.EXPERIENCE_ORB_PICKUP, 0.3f, 1f);
        ass.alpha = 1;
    }
}
