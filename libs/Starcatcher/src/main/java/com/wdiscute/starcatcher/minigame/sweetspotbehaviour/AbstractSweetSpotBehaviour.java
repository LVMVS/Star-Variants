package com.wdiscute.starcatcher.minigame.sweetspotbehaviour;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.wdiscute.starcatcher.minigame.ActiveSweetSpot;
import com.wdiscute.starcatcher.minigame.FishingMinigameScreen;
import net.minecraft.client.gui.GuiGraphics;
import org.joml.Quaternionf;

public abstract class AbstractSweetSpotBehaviour
{
    public int ticksActive;
    protected FishingMinigameScreen instance;
    protected ActiveSweetSpot ass;

    public void onAdd(FishingMinigameScreen instance, ActiveSweetSpot ass)
    {
        this.instance = instance;
        this.ass = ass;
        ass.pos = instance.getRandomFreePosition(ass.thickness);
    }

    public void tick()
    {
        ticksActive++;

        ass.pos += ass.movingRate * ass.currentRotation;
        if (ass.pos > 360) ass.pos -= 360;
        if (ass.pos < 0) ass.pos += 360;

        ass.pos += ass.movingRate * ass.currentRotation;

        ass.alpha -= ass.vanishingRate;

        if (ass.shouldSudokuOnVanish && ass.alpha <= 0) ass.removed = true;
    }

    public void onHit()
    {
    }

    public void onRemove()
    {
    }

    public void renderForeground(GuiGraphics guiGraphics, float partialTick, int width, int height)
    {
    }

    public void render(GuiGraphics guiGraphics, float partialTick, int width, int height)
    {
        if (ass.removed) return;
        float centerX = width / 2f;
        float centerY = height / 2f;

        PoseStack poseStack = guiGraphics.pose();
        poseStack.pushPose();

        poseStack.translate(centerX, centerY, 0);

        // DO NOT REPLACE THIS WITH THE OLD ONE!!! THIS IS TO FIX IT BEING ROTATED WRONG (+ its way simpler)
        poseStack.rotateAround(Axis.ZP.rotationDegrees(ass.pos + partialTick * ass.movingRate), 0, 0, 0);

        RenderSystem.setShaderColor(1, 1, 1, ass.alpha);
        RenderSystem.enableBlend();

        final int spriteSize = 96;

        // Renders the sprite centered to the top-left corner of the screen, to be moved with poseStack
        guiGraphics.blit(
                ass.texture, -spriteSize / 2, -spriteSize / 2,
                spriteSize, spriteSize, 0, 0, spriteSize, spriteSize, spriteSize, spriteSize);

        RenderSystem.disableBlend();
        RenderSystem.setShaderColor(1, 1, 1, 1);

        poseStack.popPose();
    }
}
