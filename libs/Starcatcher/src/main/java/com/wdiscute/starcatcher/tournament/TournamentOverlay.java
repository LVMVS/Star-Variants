package com.wdiscute.starcatcher.tournament;

import com.mojang.authlib.GameProfile;
import com.mojang.datafixers.util.Pair;
import com.teamtea.eclipticseasons.config.ClientConfig;
import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.U;
import com.wdiscute.starcatcher.io.FishCaughtCounter;
import com.wdiscute.starcatcher.storage.FishProperties;
import com.wdiscute.starcatcher.io.ModDataAttachments;
import com.wdiscute.starcatcher.registry.ModItems;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.LayeredDraw;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class TournamentOverlay implements LayeredDraw.Layer
{
    private static final Logger log = LoggerFactory.getLogger(TournamentOverlay.class);
    public static Tournament tournament;
    public static Map<UUID, String> gameProfilesCache = new HashMap<>();

    public static Pair<Component, Integer> firstPlace = Pair.of(Component.literal("[empty]"), 0);
    public static Pair<Component, Integer> secondPlace = Pair.of(Component.literal("[empty]"), 0);
    public static Pair<Component, Integer> thirdPlace = Pair.of(Component.literal("[empty]"), 0);

    public static Pair<Component, Integer> playerPlace = Pair.of(Component.empty(), 0);


    private static final ResourceLocation BACKGROUND = Starcatcher.rl("textures/gui/tournament/tournament_overlay.png");

    int uiX;
    int uiY;

    float offsetScreen = -150;
    Font font;
    int imageWidth = 160;
    int imageHeight = 90;
    Player player;
    ClientLevel level;

    @Override
    public void render(GuiGraphics guiGraphics, DeltaTracker deltaTracker)
    {
        if (tournament == null) return;
        if (Minecraft.getInstance().level == null) return;
        else level = Minecraft.getInstance().level;
        if (Minecraft.getInstance().player == null) return;
        else player = Minecraft.getInstance().player;

        uiX = Minecraft.getInstance().getWindow().getGuiScaledWidth() - imageWidth;
        uiY = Minecraft.getInstance().getWindow().getGuiScaledHeight() - imageHeight - 80;
        font = Minecraft.getInstance().font;


        //smoothly moves ui in and out of screen
        if (tournament.status.equals(Tournament.Status.ACTIVE))
            offsetScreen = Math.min(offsetScreen + 15, 0);
        else
            offsetScreen = Math.max(offsetScreen - 15, -150);

//        firstPlace = Pair.of(Component.literal("player1"), firstPlace.getSecond());
//        secondPlace = Pair.of(Component.literal("player1"), secondPlace.getSecond());
//        thirdPlace = Pair.of(Component.literal("player1"), thirdPlace.getSecond());

        guiGraphics.pose().pushPose();
        guiGraphics.pose().translate(offsetScreen, 0, 0);

        renderImage(guiGraphics, BACKGROUND);

        guiGraphics.drawString(this.font, firstPlace.getFirst(), 18, uiY + 27, 0x635040, false);
        guiGraphics.drawString(this.font, firstPlace.getSecond() + "", 87, uiY + 27, 0x635040, false);

        guiGraphics.drawString(this.font, secondPlace.getFirst(), 18, uiY + 40, 0x635040, false);
        guiGraphics.drawString(this.font, secondPlace.getSecond() + "", 87, uiY + 40, 0x635040, false);

        guiGraphics.drawString(this.font, thirdPlace.getFirst(), 18, uiY + 53, 0x635040, false);
        guiGraphics.drawString(this.font, thirdPlace.getSecond() + "", 87, uiY + 53, 0x635040, false);

        guiGraphics.drawString(this.font, playerPlace.getFirst(), 18, uiY + 71, 0x635040, false);
        guiGraphics.drawString(this.font, playerPlace.getSecond() + "", 87, uiY + 71, 0x635040, false);

        guiGraphics.pose().popPose();

    }

    public static void onTournamentReceived(Tournament t, List<GameProfile> list)
    {
        //add entries to cached game profile
        list.forEach(e -> gameProfilesCache.put(e.getId(), e.getName()));

        firstPlace = Pair.of(Component.literal("[empty]"), 0);
        secondPlace = Pair.of(Component.literal("[empty]"), 0);
        thirdPlace = Pair.of(Component.literal("[empty]"), 0);

        if (t.status.equals(Tournament.Status.ACTIVE))
        {
            for (Map.Entry<UUID, TournamentPlayerScore> tps : t.playerScores.entrySet())
            {
                if (tps.getValue().score > thirdPlace.getSecond())
                {
                    thirdPlace = Pair.of(
                            Component.literal(gameProfilesCache.get(tps.getKey())),
                            tps.getValue().score
                    );
                }

                if (tps.getValue().score > secondPlace.getSecond())
                {
                    thirdPlace = secondPlace;
                    secondPlace = Pair.of(
                            Component.literal(gameProfilesCache.get(tps.getKey())),
                            tps.getValue().score
                    );
                }

                if (tps.getValue().score > firstPlace.getSecond())
                {
                    secondPlace = firstPlace;
                    firstPlace = Pair.of(
                            Component.literal(gameProfilesCache.get(tps.getKey())),
                            tps.getValue().score
                    );
                }



            }

            playerPlace = Pair.of(
                    Minecraft.getInstance().player.getName(),
                    t.playerScores.get(Minecraft.getInstance().player.getUUID()).score);
        }
        tournament = t;
    }


    private void renderImage(GuiGraphics guiGraphics, ResourceLocation rl)
    {
        guiGraphics.blit(rl, 0, uiY, 0, 0, imageWidth, imageHeight, imageWidth, imageHeight);
    }

    private void drawComp(GuiGraphics guiGraphics, Component comp, int xOffset, int yOffset)
    {
        guiGraphics.drawString(font, comp, uiX + xOffset, uiY + yOffset, 0, false);
    }
}
