package com.wdiscute.starcatcher.minigame.modifiers;

import com.wdiscute.starcatcher.Starcatcher;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;

public class FreezeOnMissModifier extends AbstractModifier
{
    public static final ResourceLocation OVERLAY = Starcatcher.rl("textures/gui/minigame/modifiers/freeze.png");

    @Override
    public void onMiss()
    {
        super.onMiss();

        instance.addUniqueModifier(new FrozenPointerWhileActiveModifier(40, 10));
    }

    @Override
    public void renderBackground(GuiGraphics guiGraphics, float partialTick, int width, int height)
    {
        super.renderBackground(guiGraphics, partialTick, width, height);
        guiGraphics.blit(
                OVERLAY, width / 2 - 48, height / 2 - 48,
                96, 96, 0, 0, 96, 96, 96, 96);
    }
}
