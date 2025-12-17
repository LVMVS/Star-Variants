package com.wdiscute.starcatcher.minigame;

import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.logging.LogUtils;
import com.mojang.math.Axis;
import com.wdiscute.libtooltips.Tooltips;
import com.wdiscute.starcatcher.Config;
import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.StarcatcherTags;
import com.wdiscute.starcatcher.U;
import com.wdiscute.starcatcher.io.ModDataComponents;
import com.wdiscute.starcatcher.io.network.FishingCompletedPayload;
import com.wdiscute.starcatcher.items.ColorfulSmithingTemplate;
import com.wdiscute.starcatcher.minigame.modifiers.BaseModifier;
import com.wdiscute.starcatcher.registry.ModItems;
import com.wdiscute.starcatcher.registry.ModKeymappings;
import com.wdiscute.starcatcher.minigame.modifiers.AbstractModifier;
import com.wdiscute.starcatcher.storage.FishProperties;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.FastColor;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.network.PacketDistributor;
import org.joml.Quaternionf;
import org.joml.Vector2d;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

public class FishingMinigameScreen extends Screen implements GuiEventListener
{
    public static final ResourceLocation TEXTURE = Starcatcher.rl("textures/gui/minigame/minigame.png");
    private static final ResourceLocation NETHER = Starcatcher.rl("textures/gui/minigame/nether.png");
    private static final ResourceLocation CAVE = Starcatcher.rl("textures/gui/minigame/cave.png");
    private static final ResourceLocation SURFACE = Starcatcher.rl("textures/gui/minigame/surface.png");
    public ResourceLocation tankTexture = SURFACE;

    public final FishProperties fishProperties;
    public FishProperties.Difficulty difficulty;

    public final ItemStack itemBeingFished;
    public final ItemStack bobber;
    public final ItemStack bobberSkin;
    public final ItemStack bait;
    public final ItemStack hook;
    public final ItemStack treasureIS;

    public final InteractionHand handToSwing;

    public int penalty;
    public float decay;

    public float kimbeMarkerPos = 0;
    public float kimbeMarkerAlpha = 0;
    public int kimbeMarkerColor = 0x00ff00;

    public int gracePeriod = 80;
    //todo do this smiley face :)
    public boolean minigameStarted;

    public float pointerSpeed;
    public final float pointerBaseSpeed;

    public int tickCount = 0;
    public int pointerPos = 0;
    public int currentRotation = 1;
    public float partial;
    public float hitDelay;

    public int progress = 20;
    public int progressSmooth = 20;

    public boolean perfectCatch = true;
    public int consecutiveHits = 0;

    public boolean treasureActive;
    public int treasureProgress = 0;
    public int treasureProgressSmooth = 0;

    List<HitFakeParticle> hitParticles = new ArrayList<>();

    public int previousGuiScale;


    // Nikdo53 values, these are mine dont steal them
    public final int holdingDelay = 6;
    public int holdingTicks = 0;
    private boolean isHoldingKey = false;
    private boolean isHoldingMouse = false;

    private final List<ActiveSweetSpot> activeSweetSpots = new ArrayList<>();
    private final List<AbstractModifier> modifiers = new ArrayList<>();

    public FishingMinigameScreen(FishProperties fp, ItemStack rod)
    {
        super(Component.empty());

        handToSwing = Minecraft.getInstance().player.getMainHandItem().is(StarcatcherTags.RODS) ? InteractionHand.MAIN_HAND : InteractionHand.OFF_HAND;

        previousGuiScale = Minecraft.getInstance().options.guiScale().get();
        if (!ModList.get().isLoaded("distanthorizons"))
            Minecraft.getInstance().options.guiScale().set(Config.MINIGAME_GUI_SCALE.get());

        hitDelay = Config.HIT_DELAY.get().floatValue();

        this.fishProperties = fp;
        this.difficulty = fp.dif();

        //if override is not missingno (default) then use the override item set
        if (!fp.catchInfo().overrideMinigameWith().is(ModItems.MISSINGNO.getKey()))
            this.itemBeingFished = new ItemStack(fp.catchInfo().overrideMinigameWith());
        else
            this.itemBeingFished = new ItemStack(fp.catchInfo().fish());

        this.bobberSkin = rod.get(ModDataComponents.BOBBER_SKIN).stack().copy();
        this.bobber = rod.get(ModDataComponents.BOBBER).stack().copy();
        this.bait = rod.get(ModDataComponents.BAIT).stack().copy();
        this.hook = rod.get(ModDataComponents.HOOK).stack().copy();

        this.treasureIS = new ItemStack(fp.catchInfo().treasure());

        //tank texture change
        ClientLevel level = Minecraft.getInstance().level;
        ResourceKey<Level> dim = level.dimension();
        Player player = Minecraft.getInstance().player;
        if (player.getY() < 50 && dim.equals(Level.OVERWORLD))
            tankTexture = CAVE;

        if (dim.equals(Level.NETHER))
            tankTexture = NETHER;

        //base - a lot of these are now hitZone-based
        this.pointerSpeed = difficulty.speed();
        this.pointerBaseSpeed = difficulty.speed();
        this.penalty = difficulty.penalty();
        this.decay = difficulty.decay();

        //add base modifier for kimbe before other modifiers so they can override kimbe if needed
        addModifier(new BaseModifier());

        //add every modifier from fp json which is registered
        for (ResourceLocation rl : fp.dif().modifiers())
        {
            Optional<Supplier<AbstractModifier>> newModifier = level.registryAccess().registryOrThrow(Starcatcher.MODIFIERS).getOptional(rl);
            newModifier.ifPresent(mod -> addModifier(mod.get()));
        }

        //cycle through all the items to check for modifiers
        //todo improve this to check things dynamically, look into baubles compat & armor slots (not inventory otherwise people could offhand modifiers)
        List<ItemStack> allItems = List.of(bobber, rod, bait, hook);
        for (ItemStack is : allItems)
            if (is.has(ModDataComponents.MODIFIERS))
                for (ResourceLocation rl : Objects.requireNonNull(is.get(ModDataComponents.MODIFIERS)))
                {
                    Optional<Supplier<AbstractModifier>> newModifier = level.registryAccess().registryOrThrow(Starcatcher.MODIFIERS).getOptional(rl);
                    newModifier.ifPresent(mod -> addModifier(mod.get()));
                }


        //add every sweet spot from fp json which is registered
        for (FishProperties.SweetSpot ss : fp.dif().sweetSpots())
        {
            var newSweetSpot = new ActiveSweetSpot(this, ss, bobber, bait, hook);
            addSweetSpot(newSweetSpot);
        }

    }

    public void addModifier(AbstractModifier mod)
    {
        mod.onAdd(this);
        if (!mod.removed) this.modifiers.add(mod);
    }

    public void addUniqueModifier(AbstractModifier mod)
    {
        //only adds if theres not a modifier of the same class already present
        if (modifiers.stream().noneMatch(m -> m.getClass() == mod.getClass()))
        {
            mod.onAdd(this);
            if (!mod.removed) this.modifiers.add(mod);
        }
    }

    public void addSweetSpot(ActiveSweetSpot ass)
    {
        ass.behaviour.onAdd(this, ass);

        for (AbstractModifier modifier : modifiers)
        {
            ass = modifier.onSpotAdded(ass);
        }

        if (!ass.removed) this.activeSweetSpots.add(ass);
    }

    public int getRandomFreePosition(int sizeOfTheSweetspotToPlace)
    {
        for (int i = 0; i < 100; i++)
        {
            int posBeingChecked = U.r.nextInt(360);

            if (activeSweetSpots.stream().noneMatch(s -> doDegreesOverlapWithLeeway(posBeingChecked, s.pos, (s.thickness + sizeOfTheSweetspotToPlace) / 2)
            ))
            {
                return posBeingChecked;
            }
        }

        LogUtils.getLogger().warn("Starcatcher's minigame couldn't find a non-overlapping free position! Consider not having so many active sweet spots");
        return U.r.nextInt(360);
    }

    @Override
    public void renderBackground(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick)
    {
        super.renderBackground(guiGraphics, mouseX, mouseY, partialTick);

        PoseStack poseStack = guiGraphics.pose();
        partial = partialTick;

        if (treasureActive) renderTreasure(guiGraphics);

        //render tank background
        guiGraphics.blit(tankTexture, width / 2 - 42 - 100, height / 2 - 48, 85, 97, 0, 0, 85, 97, 85, 97);

        //render wheel background
        guiGraphics.blit(TEXTURE, width / 2 - 32, height / 2 - 32, 64, 64, 0, 192, 64, 64, 256, 256);

        //render spacebar
        guiGraphics.blit(TEXTURE, width / 2 - 16, height / 2 + 40, 32, 16, isHoldingKey ? 48 : 0, 112, 32, 16, 256, 256);

        //render modifiers background
        modifiers.forEach(modifier -> modifier.renderBackground(guiGraphics, partialTick, width, height));

        //render all hit zones
        activeSweetSpots.forEach(ass -> ass.behaviour.render(guiGraphics, partialTick, width, height));

        //render wheel second layer
        guiGraphics.blit(TEXTURE, width / 2 - 32, height / 2 - 32, 64, 64, 64, 192, 64, 64, 256, 256);

        //render pointer
        renderPointer(guiGraphics, partialTick, poseStack, width, height, isHoldingKey, pointerPos, pointerSpeed, currentRotation);

        //render kimbe marker
        renderKimbeMarker(guiGraphics);

        //silver thing on top
        guiGraphics.blit(TEXTURE, width / 2 - 16, height / 2 - 16, 32, 32, 208, 208, 32, 32, 256, 256);

        //fishing rod
        guiGraphics.blit(TEXTURE, width / 2 - 32 - 70, height / 2 - 24 - 57, 64, 48, 192, 0, 64, 48, 256, 256);

        //fishing line
        guiGraphics.blit(
                TEXTURE, width / 2 - 6 - 102, height / 2 - 56 - 18,
                16, 112 - progressSmooth,
                176, progressSmooth,
                16, 112 - progressSmooth,
                256, 256);

        //item being fished
        guiGraphics.renderItem(itemBeingFished, width / 2 - 8 - 100, height / 2 - 8 + 35 - progressSmooth);

        //render sweet spots foreground
        activeSweetSpots.forEach(sweetspot -> sweetspot.behaviour.renderForeground(guiGraphics, partialTick, width, height));

        //render modifiers foreground
        modifiers.forEach(modifier -> modifier.renderForeground(guiGraphics, partialTick, width, height));

        //render particles
        hitParticles.forEach(p -> p.render(guiGraphics, width, height));
    }

    private void renderCombo(GuiGraphics guiGraphics, int consecutiveHits, float delta)
    {
        //todo make this into a modifier
        PoseStack pose = guiGraphics.pose();
        pose.translate(width / 2f + 20, height / 2f - 40, 0);
        pose.mulPose(Axis.ZP.rotationDegrees(30));
        float f = 1.8F - Mth.abs(Mth.sin(delta % 20) * ((float) Math.PI * 2F) * 0.01F);
        pose.scale(f, f, 1);
        guiGraphics.drawCenteredString(this.font, Component.translatable("fishing.combo", consecutiveHits).withStyle(ChatFormatting.GOLD), 0, -5, -1);
    }

    public void renderTreasure(GuiGraphics guiGraphics)
    {
        //treasure bar
        guiGraphics.blit(
                TEXTURE, width / 2 - 158, height / 2 - 42 + (int) (64 - (64f * treasureProgressSmooth) / 100),
                5, 64 * treasureProgressSmooth / 100,
                141, 6 + 64 - (float) (64 * treasureProgressSmooth) / 100,
                5, 64 * treasureProgressSmooth / 100,
                256, 256);

        //treasure chest
        guiGraphics.blit(TEXTURE, width / 2 - 16 - 155, height / 2 - 48, 32, 96, 96, 0, 32, 96, 256, 256);

        //render treasure on top of bar
        guiGraphics.renderItem(treasureIS, width / 2 - 163, ((int) ((float) height / 2 - (64f * treasureProgressSmooth) / 100) + 15));

        int color = Tooltips.hueToRGBInt(Tooltips.hue);
        if (bobberSkin.is(ModItems.PEARL_BOBBER_SMITHING_TEMPLATE))
            RenderSystem.setShaderColor(
                    (float) FastColor.ARGB32.red(color) / 255,
                    (float) FastColor.ARGB32.green(color) / 255,
                    (float) FastColor.ARGB32.blue(color) / 255,
                    1);

        if (bobberSkin.is(ModItems.COLORFUL_BOBBER_SMITHING_TEMPLATE))
            color = bobberSkin.get(ModDataComponents.BOBBER_COLOR).getColorAsInt();

        if (bobberSkin.is(ModItems.COLORFUL_BOBBER_SMITHING_TEMPLATE))
            RenderSystem.setShaderColor(
                    (float) FastColor.ARGB32.red(color) / 255,
                    (float) FastColor.ARGB32.green(color) / 255,
                    (float) FastColor.ARGB32.blue(color) / 255,
                    1);

        //outline when treasure complete
        if (treasureProgress > 99)
            guiGraphics.blit(
                    TEXTURE, width / 2 - 16 - 155, height / 2 - 48,
                    32, 96, 64, 0, 32, 96, 256, 256);

        if (bobberSkin.is(ModItems.COLORFUL_BOBBER_SMITHING_TEMPLATE) || bobberSkin.is(ModItems.PEARL_BOBBER_SMITHING_TEMPLATE))
            RenderSystem.setShaderColor(1, 1, 1, 1);
    }

    public void renderKimbeMarker(GuiGraphics guiGraphics)
    {
        PoseStack poseStack = guiGraphics.pose();
        poseStack.pushPose();

        float centerX = width / 2f;
        float centerY = height / 2f;

        poseStack.translate(centerX, centerY, 0);
        poseStack.mulPose(new Quaternionf().rotateZ((float) Math.toRadians(kimbeMarkerPos)));
        poseStack.translate(-centerX, -centerY, 0);

        RenderSystem.setShaderColor(
                (float) U.intToRed(kimbeMarkerColor) / 255,
                (float) U.intToGreen(kimbeMarkerColor) / 255,
                (float) U.intToBlue(kimbeMarkerColor) / 255,
                kimbeMarkerAlpha);
        RenderSystem.enableBlend();

        //16 offset on y for texture centering
        if (!bobber.is(ModItems.KIMBE_BOBBER_SMITHING_TEMPLATE))
        {
            guiGraphics.blit(
                    TEXTURE, width / 2 - 32, height / 2 - 32 - 16,
                    64, 64, 128, 128, 64, 64, 256, 256);
        }

        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.disableBlend();

        poseStack.popPose();
    }

    public static void renderPointer(GuiGraphics guiGraphics, float partialTick, PoseStack poseStack, int width, int height, boolean isHoldingSpace, int pointerPos, float speed, int currentRotation)
    {
        poseStack.pushPose();

        float centerX = width / 2f;
        float centerY = height / 2f;

        poseStack.translate(centerX, centerY, 0);
        // if (isHoldingSpace) poseStack.scale(0.8f, 1f, 1f);

        poseStack.mulPose(new Quaternionf().rotateZ((float) Math.toRadians(pointerPos + ((speed * partialTick) * currentRotation))));
        poseStack.translate(-centerX, -centerY, 0);

        //16 offset on y for texture centering
        guiGraphics.blit(
                TEXTURE, width / 2 - 32, height / 2 - 32 - 16,
                64, 64, 128, 192, 64, 64, 256, 256);

        poseStack.popPose();
    }

    @Override
    public boolean keyReleased(int keyCode, int scanCode, int modifiers)
    {
        if (keyCode == ModKeymappings.MINIGAME_HIT.getKey().getValue())
        {
            isHoldingKey = false;
            holdingTicks = 0;
        }
        return super.keyReleased(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button)
    {
        isHoldingMouse = false;
        holdingTicks = 0;
        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button)
    {
        inputPressed();
        isHoldingMouse = true;
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers)
    {
        //closes when pressing E
        InputConstants.Key mouseKey = InputConstants.getKey(keyCode, scanCode);
        if (this.minecraft.options.keyInventory.isActiveAndMatches(mouseKey))
        {
            this.onClose();
            return true;
        }

        //hit input
        if (ModKeymappings.MINIGAME_HIT.isActiveAndMatches(mouseKey))
        {
            if (!isHoldingKey) inputPressed();

            isHoldingKey = true;
        }

        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    private void inputPressed()
    {
        if (gracePeriod > 0) gracePeriod = 0;

        Minecraft.getInstance().player.swing(handToSwing, true);

        boolean hitSomething = false;

        Vec3 pos = Minecraft.getInstance().player.position();
        ClientLevel level = Minecraft.getInstance().level;

        kimbeMarkerPos = getPointerPosPrecise();

        ActiveSweetSpot hitSweetSpot = null;
        for (ActiveSweetSpot ass : activeSweetSpots)
        {
            if (doDegreesOverlapWithLeeway(getPointerPosPrecise(), ass.pos, ass.thickness / 2))
            {
                ass.behaviour.onHit();

                hitSweetSpot = ass;
                hitSomething = true;
                break;
            }
        }


        if (hitSomething)
        {
            ActiveSweetSpot finalHitSweetSpot = hitSweetSpot;
            modifiers.forEach(modifier -> modifier.onHit(finalHitSweetSpot));
        }
        else
        {
            this.modifiers.forEach(modifier -> modifier.onMiss());

            if (bobber.is(ModItems.KIMBE_BOBBER_SMITHING_TEMPLATE))
                Minecraft.getInstance().player.playSound(SoundEvents.VILLAGER_NO, 1, 1);
            consecutiveHits = 0;
            level.playLocalSound(pos.x, pos.y, pos.z, SoundEvents.COMPARATOR_CLICK, SoundSource.BLOCKS, 1, 1, false);
            progress -= penalty;
            perfectCatch = false;
        }
    }

    public float getPointerPosPrecise()
    {
        float pointerPosPrecise = (pointerPos + ((pointerSpeed * partial) * currentRotation));

        pointerPosPrecise += hitDelay * pointerSpeed * currentRotation;
        return pointerPosPrecise;
    }

    public static boolean doDegreesOverlapWithLeeway(float degrees1, float degrees2, int leeway)
    {
        boolean b = Math.abs(degrees2 - degrees1) < (float) leeway;
        boolean b2 = Math.abs(degrees2 - degrees1) > 360 - ((float) leeway);
        return b || b2;
    }

    @Override
    public void tick()
    {
        if (isHoldingInput())
        { //mimics the keyboard behaviour
            holdingTicks++;
            if (holdingTicks > holdingDelay) inputPressed();
        }

        //tick modifiers
        modifiers.forEach(AbstractModifier::tick);
        //tick behaviour
        activeSweetSpots.forEach(s -> s.behaviour.tick());

        //remove activeSweetSpots marked for removal
        activeSweetSpots.removeIf(s ->
        {
            if (s.removed) s.behaviour.onRemove();
            return s.removed;
        });

        //remove modifiers marked for removal
        modifiers.removeIf(m ->
        {
            if (m.removed) m.onRemove();
            return m.removed;
        });

        //move pointer
        pointerPos += (int) (pointerSpeed * currentRotation);

        //decrease kimbe markers alpha
        kimbeMarkerAlpha -= 0.1f;

        if (pointerPos > 360) pointerPos -= 360;
        if (pointerPos < 0) pointerPos += 360;

        gracePeriod--;

        tickCount++;

        progressSmooth += (int) Math.signum(progress - progressSmooth);
        progressSmooth += (int) Math.signum(progress - progressSmooth);

        treasureProgressSmooth += (int) Math.signum(treasureProgress - treasureProgressSmooth);

        if (tickCount % 5 == 0 && gracePeriod < 0)
        {
            progress -= decay;
        }

        if (progressSmooth < 0)
        {
            this.onClose();
        }

        if (progressSmooth > 75)
        {
            //if completed treasure minigame, or is a perfect catch with the mossy hook
            boolean awardTreasure = treasureProgress > 100;

            PacketDistributor.sendToServer(new FishingCompletedPayload(tickCount, awardTreasure, perfectCatch, consecutiveHits));
            this.onClose();
        }

        hitParticles.removeIf(HitFakeParticle::tick);
    }

    @Override
    public void onClose()
    {
        modifiers.forEach(AbstractModifier::onRemove);

        if (!ModList.get().isLoaded("distanthorizons"))
            Minecraft.getInstance().options.guiScale().set(previousGuiScale);

        PacketDistributor.sendToServer(new FishingCompletedPayload(-1, false, false, consecutiveHits));
        this.minecraft.popGuiLayer();
    }

    public void addParticles(float posInDegrees, int count, int color)
    {
        int xPos = (int) (30 * Math.cos(Math.toRadians(posInDegrees - 90)));
        int yPos = (int) (30 * Math.sin(Math.toRadians(posInDegrees - 90)));

        for (int i = 0; i < count; i++)
        {
            if (bobber.is(ModItems.PEARL_BOBBER_SMITHING_TEMPLATE))
            {
                hitParticles.add(new HitFakeParticle(
                        xPos, yPos, new Vector2d(U.r.nextFloat() * 2 - 1, U.r.nextFloat() * 2 - 1),
                        U.r.nextFloat(),
                        U.r.nextFloat(),
                        U.r.nextFloat(),
                        1
                ));
                continue;
            }

            if (bobber.is(ModItems.COLORFUL_BOBBER_SMITHING_TEMPLATE))
            {
                ColorfulSmithingTemplate.BobberColor bobberColor = bobber.get(ModDataComponents.BOBBER_COLOR);
                hitParticles.add(new HitFakeParticle(
                        xPos, yPos, new Vector2d(U.r.nextFloat() * 2 - 1, U.r.nextFloat() * 2 - 1),
                        bobberColor.r(),
                        bobberColor.g(),
                        bobberColor.b(),
                        1
                ));
                continue;
            }

            hitParticles.add(
                    new HitFakeParticle(
                            xPos,
                            yPos,
                            new Vector2d(U.r.nextFloat() * 2 - 1, U.r.nextFloat() * 2 - 1),
                            (float) U.intToRed(color) / 255,
                            (float) U.intToGreen(color) / 255,
                            (float) U.intToBlue(color) / 255,
                            1
                    ));
        }
    }

    public boolean isHoldingInput()
    {
        return isHoldingMouse || isHoldingKey;
    }

    @Override
    public boolean isPauseScreen()
    {
        return false;
    }

    public void refreshSweetSpotsAlphas()
    {
        activeSweetSpots.forEach(s -> s.alpha = 1);
    }

    public void removeAllSweetSpots()
    {
        for (var dws : activeSweetSpots)
        {
            dws.removed = true;
        }
    }
}
