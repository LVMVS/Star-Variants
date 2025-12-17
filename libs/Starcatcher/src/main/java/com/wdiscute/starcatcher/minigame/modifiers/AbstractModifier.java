package com.wdiscute.starcatcher.minigame.modifiers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.wdiscute.starcatcher.minigame.ActiveSweetSpot;
import com.wdiscute.starcatcher.minigame.FishingMinigameScreen;
import net.minecraft.client.gui.GuiGraphics;

public abstract class AbstractModifier
{
    public boolean removed = false;
    public int tickCount = 0;
    protected FishingMinigameScreen instance;

    public void onAdd(FishingMinigameScreen instance)
    {
        this.instance = instance;
    }

    /**
     * Runs when removed or the minigame ends
     */
    public void onRemove(){}

    /**
     * Transforms an ActiveSweetSpot before it gets added.
     * Setting removed to true cancels it
     */
    public ActiveSweetSpot onSpotAdded(ActiveSweetSpot spot){
        return spot;
    }

    public void onHit(ActiveSweetSpot ass){};

    public void onMiss(){}

    public void tick(){
        tickCount++;
    }

    public void renderBackground(GuiGraphics guiGraphics, float partialTick, int width, int height){};

    public void renderForeground(GuiGraphics guiGraphics, float partialTick, int width, int height){};
}
