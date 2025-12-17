package com.wdiscute.starcatcher.tournament;

import com.wdiscute.starcatcher.Config;
import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.io.SingleStackContainer;
import com.wdiscute.starcatcher.io.network.tournament.stand.SBStandTournamentDurationChangePayload;
import com.wdiscute.starcatcher.io.network.tournament.stand.SBStandTournamentNameChangePayload;
import com.wdiscute.starcatcher.io.network.tournament.stand.SBStandTournamentScoringTypeChangePayload;
import com.wdiscute.starcatcher.io.network.tournament.stand.SBStandTournamentStartCancelPayload;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.model.AnimationUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.*;

public class StandScreen extends AbstractContainerScreen<StandMenu>
{
    public Tournament tournamentCache;
    public static Map<UUID, String> gameProfilesCache;
    private final StandMenu standMenu;

    private static String durationCache = "waiting...";
    private EditBox nameEditBox;
    private EditBox durationEditBox;
    private boolean nameWasFocused;
    private boolean durationWasFocused;

    private static final ResourceLocation BACKGROUND = Starcatcher.rl("textures/gui/tournament/background.png");

    int uiX;
    int uiY;

    @Override
    protected void init()
    {
        super.init();
        uiX = (width - imageWidth) / 2;
        uiY = (height - imageHeight) / 2;
        subInit();
    }

    protected void subInit()
    {
        nameEditBox = new EditBox(this.font, uiX + 53, uiY + 36, 210, 12, Component.translatable("container.repair"));
        nameEditBox.setCanLoseFocus(true);
        nameEditBox.setTextColor(0x635040);
        nameEditBox.setBordered(false);
        nameEditBox.setMaxLength(20);
        nameEditBox.setValue("");
        nameEditBox.setTextShadow(false);
        nameEditBox.setEditable(false);
        addWidget(this.nameEditBox);

        durationEditBox = new EditBox(this.font, uiX + 55, uiY + 88, 210, 12, Component.translatable("container.repair"));
        durationEditBox.setCanLoseFocus(true);
        durationEditBox.setBordered(true);
        durationEditBox.setTextColor(0x635040);
        durationEditBox.setBordered(false);
        durationEditBox.setMaxLength(10);
        durationEditBox.setValue("");
        durationEditBox.setTextShadow(false);
        durationEditBox.setEditable(false);
        addWidget(this.durationEditBox);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float v, int i, int i1)
    {
        this.renderBlurredBackground(i);
    }

    private void onFocusNameEditBox()
    {
        nameEditBox.setValue(tournamentCache.name);
        tournamentCache.name = "";
    }

    private void onUnfocusNameEditBox()
    {
        //send packet
        PacketDistributor.sendToServer(new SBStandTournamentNameChangePayload(tournamentCache.tournamentUUID, nameEditBox.getValue()));
        tournamentCache.name = nameEditBox.getValue();
        nameEditBox.setValue("");
    }

    private void onFocusDurationEditBox()
    {
        durationEditBox.setValue(tournamentCache.settings.duration + "");
        tournamentCache.settings.duration = 0;
    }

    private void onUnfocusDurationEditBox()
    {
        //send packet
        long duration = Long.parseLong(durationEditBox.getValue());
        PacketDistributor.sendToServer(new SBStandTournamentDurationChangePayload(tournamentCache.tournamentUUID, duration));
        tournamentCache.settings.duration = duration;
        durationEditBox.setValue("");
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick)
    {
        super.render(guiGraphics, mouseX, mouseY, partialTick);

        double x = mouseX - uiX;
        double y = mouseY - uiY;

        if (tournamentCache == null) return;
        if (gameProfilesCache == null) return;

        //handle Name editbox focusing
        if (nameWasFocused != nameEditBox.isFocused())
        {
            if (nameEditBox.isFocused())
                onFocusNameEditBox();
            else
                onUnfocusNameEditBox();
        }
        nameWasFocused = nameEditBox.isFocused();

        //handle Duration editbox focusing
        if (durationWasFocused != durationEditBox.isFocused())
        {
            if (durationEditBox.isFocused())
                onFocusDurationEditBox();
            else
                onUnfocusDurationEditBox();
        }
        durationWasFocused = durationEditBox.isFocused();

        //render background
        renderImage(guiGraphics, BACKGROUND);

        //render tournament name
        guiGraphics.drawString(this.font, tournamentCache.name, uiX + 53, uiY + 36, 0x635040, false);

        //organizer
        guiGraphics.drawString(this.font, getPlayerFromUUID(tournamentCache.owner), uiX + 55, uiY + 56, 0x635040, false);
        guiGraphics.drawString(this.font, Component.translatable("gui.starcatcher.tournament.organizer"), uiX + 55, uiY + 68, 0x9c897c, false);

        //status
        guiGraphics.drawString(this.font, Component.translatable(tournamentCache.status.getSerializedName()), uiX + 130, uiY + 56, 0x635040, false);
        guiGraphics.drawString(this.font, Component.translatable("gui.starcatcher.tournament.status"), uiX + 130, uiY + 68, 0x9c897c, false);

        //duration
        guiGraphics.drawString(this.font, Component.literal(durationCache), uiX + 55, uiY + 88, 0x635040, false);
        guiGraphics.drawString(this.font, Component.translatable("gui.starcatcher.tournament.duration"), uiX + 60, uiY + 100, 0x9c897c, false);

        //scoring
        int xOwnerOffset = 0;
        if (Minecraft.getInstance().player.getUUID().equals(tournamentCache.owner))
            xOwnerOffset += 4;
        guiGraphics.drawString(this.font, Component.translatable(tournamentCache.settings.scoring.getSerializedName()), uiX + 130, uiY + 88, 0x635040, false);
        guiGraphics.drawString(this.font, Component.translatable("gui.starcatcher.tournament.scoring"), uiX + 130 + xOwnerOffset, uiY + 100, 0x9c897c, false);


        //signup button
        if (tournamentCache.playerScores.containsKey(Minecraft.getInstance().player.getUUID()))
        {
            guiGraphics.drawString(this.font, Component.translatable("gui.starcatcher.tournament.signed_up"), uiX + 51, uiY + 120, 0x40752c, false);
        }
        else
        {
            int color = tournamentCache.settings.canSignUp(minecraft.player) ? 0x40752c : 0xa34536;
            guiGraphics.drawString(this.font, Component.translatable("gui.starcatcher.tournament.sign_up"), uiX + 51, uiY + 120, color, false);
        }

        if (x > 48 && x < 98 && y > 117 && y < 127 && !tournamentCache.settings.entryCost.isEmpty())
        {
            List<Component> signUpCostList = new ArrayList<>();

            signUpCostList.add(Component.literal("Sign Up Fee:"));


            for (SingleStackContainer ssc : tournamentCache.settings.entryCost)
            {
                if (!ssc.stack().isEmpty())
                    signUpCostList.add(Component.literal(ssc.stack().getCount() + "x ").append(Component.translatable(ssc.stack().getItem().getDescriptionId())));
            }

            guiGraphics.renderTooltip(this.font, signUpCostList, Optional.empty(), mouseX, mouseY);
        }


        //list of players
        int count = 0;
        int xOffset = 53;
        int yOffset = 132;
        boolean drawOthers = false;
        List<Component> others = new ArrayList<>();
        others.add(Component.translatable("gui.starcatcher.tournament.other"));
        for (var entry : tournamentCache.getPlayerScores().entrySet())
        {
            if (count == 11)
            {
                drawOthers = true;
                others.add(Component.literal(getPlayerFromUUID(entry.getKey())));
                continue;
            }
            guiGraphics.drawString(this.font, getPlayerFromUUID(entry.getKey()), uiX + xOffset, uiY + yOffset, 0x635040, false);
            count++;
            yOffset += 12;
            if (count == 6)
            {
                xOffset += 77;
                yOffset = 132;
            }
        }

        if (drawOthers)
        {
            guiGraphics.drawString(this.font, Component.translatable("gui.guide.hover"), uiX + xOffset, uiY + yOffset, 0x635040, false);
            if (x > 125 && x < 190 && y > 188 && y < 202)
                guiGraphics.renderTooltip(
                        this.font,
                        others,
                        Optional.empty(), mouseX, mouseY);
        }


        //
        //                  ,--.     ,--.   ,--.
        // ,---.   ,---.  ,-'  '-. ,-'  '-. `--' ,--,--,   ,---.   ,---.
        //(  .-'  | .-. : '-.  .-' '-.  .-' ,--. |      \ | .-. | (  .-'
        //.-'  `) \   --.   |  |     |  |   |  | |  ||  | ' '-' ' .-'  `)
        //`----'   `----'   `--'     `--'   `--' `--''--' .`-  /  `----'
        //                                                `---'


        //entry fee
        guiGraphics.drawString(this.font, Component.translatable("gui.starcatcher.tournament.entry_fee"), uiX + 212, uiY + 147, 0x9c897c, false);

        //inventory
        guiGraphics.drawString(this.font, Component.translatable("gui.starcatcher.tournament.inventory"), uiX + 212, uiY + 175, 0x9c897c, false);

        //start / cancel
        if (tournamentCache.status == Tournament.Status.SETUP)
        {
            guiGraphics.drawString(this.font, Component.translatable("gui.starcatcher.tournament.start"), uiX + 215, uiY + 50, 0x635040, false);
            if (x > 209 && x < 317 && y > 44 && y < 60)
            {
                List<Component> list = new ArrayList<>();

                list.add(Component.literal("This will start the tournament which will"));
                list.add(Component.literal("automatically end once the duration has"));
                list.add(Component.literal("ended. Settings can not be changed"));
                list.add(Component.literal("once the tournament has started."));
                list.add(Component.literal("Items can still be added to the prize pool."));

                guiGraphics.renderTooltip(this.font, list, Optional.empty(), mouseX, mouseY);
            }

            this.durationEditBox.render(guiGraphics, mouseX, mouseY, partialTick);
            this.nameEditBox.render(guiGraphics, mouseX, mouseY, partialTick);
            renderTooltip(guiGraphics, mouseX, mouseY);
        }
        else if (tournamentCache.status == Tournament.Status.ACTIVE)
        {
            guiGraphics.drawString(this.font, Component.translatable("gui.starcatcher.tournament.cancel"), uiX + 215, uiY + 50, 0xa34536, false);
            if (x > 209 && x < 317 && y > 44 && y < 60)
            {
                List<Component> list = new ArrayList<>();

                list.add(Component.literal("This will start the tournament which will"));
                list.add(Component.literal("automatically end once the duration has"));
                list.add(Component.literal("ended. Settings can not be changed"));
                list.add(Component.literal("once the tournament has started."));
                list.add(Component.literal("Items can still be added to the prize pool."));

                guiGraphics.renderTooltip(this.font, list, Optional.empty(), mouseX, mouseY);
            }

            this.durationEditBox.render(guiGraphics, mouseX, mouseY, partialTick);
            this.nameEditBox.render(guiGraphics, mouseX, mouseY, partialTick);
            renderTooltip(guiGraphics, mouseX, mouseY);
        }


    }

    public void onTournamentReceived(Tournament tournament)
    {
        tournamentCache = tournament;
        nameEditBox.setEditable(tournamentCache.owner.equals(Minecraft.getInstance().player.getUUID()));
        durationEditBox.setEditable(tournamentCache.owner.equals(Minecraft.getInstance().player.getUUID()));
        updateDurationCache();
    }

    private void updateDurationCache()
    {
        if (Config.DURATION.get().equals(DurationDisplay.MINUTES))
        {
            String s = "";
            long totalSeconds = tournamentCache.settings.duration / 20;
            long hours = totalSeconds / 3600;

            if (hours > 0)
            {
                totalSeconds -= 3600 * hours;
                s = hours + "h ";
            }

            long minutes = totalSeconds / 60;

            if (minutes > 0)
            {
                totalSeconds -= 60 * minutes;
                s += minutes + "m ";
            }

            s += totalSeconds + "s ";

            durationCache = s;
        }

        if (Config.DURATION.get().equals(DurationDisplay.MINECRAFT_DAYS))
        {
            durationCache = tournamentCache.settings.duration / 24000 + " days";
        }

        if (Config.DURATION.get().equals(DurationDisplay.TICKS))
        {
            durationCache = tournamentCache.settings.duration + " ticks";
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button)
    {

        double x = mouseX - uiX;
        double y = mouseY - uiY;

        System.out.println("clicked relative x:" + x);
        System.out.println("clicked relative y:" + y);

        //sign up
        if (x > 48 && x < 98 && y > 117 && y < 127)
        {
            minecraft.gameMode.handleInventoryButtonClick(this.menu.containerId, 67);
        }

        //start
        if (x > 209 && x < 317 && y > 44 && y < 60)
        {
            if (tournamentCache.status == Tournament.Status.SETUP)
                PacketDistributor.sendToServer(new SBStandTournamentStartCancelPayload(true, tournamentCache.tournamentUUID));
            else if (tournamentCache.status == Tournament.Status.ACTIVE)
                PacketDistributor.sendToServer(new SBStandTournamentStartCancelPayload(false, tournamentCache.tournamentUUID));
        }

        //duration cycling previous
        if (x > 50 && x < 60 && y > 98 && y < 107)
        {
            Config.DURATION.set(Config.DURATION.get().previous());
            Config.SORT.save();
            updateDurationCache();
        }

        //duration cycling next
        if (x > 109 && x < 119 && y > 99 && y < 109)
        {
            Config.DURATION.set(Config.DURATION.get().next());
            Config.SORT.save();
            updateDurationCache();
        }

        //duration cycling previous
        if (x > 124 && x < 134 && y > 99 && y < 109)
        {
            PacketDistributor.sendToServer(new SBStandTournamentScoringTypeChangePayload(tournamentCache.tournamentUUID, tournamentCache.settings.scoring.previous()));
        }

        //duration cycling next
        if (x > 182 && x < 192 && y > 99 && y < 109)
        {
            PacketDistributor.sendToServer(new SBStandTournamentScoringTypeChangePayload(tournamentCache.tournamentUUID, tournamentCache.settings.scoring.next()));
        }


        nameEditBox.setFocused(false);
        durationEditBox.setFocused(false);

        return super.mouseClicked(mouseX, mouseY, button);
    }

    public static String getPlayerFromUUID(UUID uuid)
    {
        if (gameProfilesCache != null)
        {
            if (gameProfilesCache.containsKey(uuid))
            {
                return gameProfilesCache.get(uuid);
            }
        }
        return "Unknown";
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY)
    {
    }

    private void renderImage(GuiGraphics guiGraphics, ResourceLocation rl)
    {
        guiGraphics.blit(rl, uiX, uiY, 0, 0, 420, 260, 420, 260);
    }

    private void renderImage(GuiGraphics guiGraphics, ResourceLocation rl, int xOffset, int yOffset)
    {
        guiGraphics.blit(rl, uiX + xOffset, uiY + yOffset, 0, 0, 420, 260, 420, 260);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers)
    {
        if (keyCode == 256)
        {
            this.minecraft.player.closeContainer();
        }

        boolean editbox = this.nameEditBox.keyPressed(keyCode, scanCode, modifiers) || this.nameEditBox.canConsumeInput();
        boolean durationBox = this.durationEditBox.keyPressed(keyCode, scanCode, modifiers) || this.durationEditBox.canConsumeInput();
        return editbox || durationBox || super.keyPressed(keyCode, scanCode, modifiers);
    }


    public enum DurationDisplay
    {
        TICKS,
        MINUTES,
        MINECRAFT_DAYS;

        private static final DurationDisplay[] vals = values();

        public DurationDisplay previous()
        {
            if (this.ordinal() == 0) return vals[vals.length - 1];
            return vals[(this.ordinal() - 1) % vals.length];
        }

        public DurationDisplay next()
        {
            return vals[(this.ordinal() + 1) % vals.length];
        }
    }

    public StandScreen(StandMenu menu, Inventory playerInventory, Component title)
    {
        super(menu, playerInventory, title);
        standMenu = menu;
        imageWidth = 420;
        imageHeight = 260;
    }
}
