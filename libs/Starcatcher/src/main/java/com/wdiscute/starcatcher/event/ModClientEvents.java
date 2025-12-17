package com.wdiscute.starcatcher.event;

import com.wdiscute.libtooltips.Tooltips;
import com.wdiscute.starcatcher.Config;
import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.bob.FishingBobModel;
import com.wdiscute.starcatcher.bob.FishingBobRenderer;
import com.wdiscute.starcatcher.fishentity.FishRenderer;
import com.wdiscute.starcatcher.fishentity.fishmodels.*;
import com.wdiscute.starcatcher.fishspotter.FishRadarLayer;
import com.wdiscute.starcatcher.guide.SettingsScreen;
import com.wdiscute.starcatcher.io.ModDataComponents;
import com.wdiscute.starcatcher.io.SizeAndWeightInstance;
import com.wdiscute.starcatcher.items.BucketTooltipRenderer;
import com.wdiscute.starcatcher.items.StarcaughtBucket;
import com.wdiscute.starcatcher.particles.FishingBitingLavaParticles;
import com.wdiscute.starcatcher.particles.FishingBitingParticles;
import com.wdiscute.starcatcher.particles.FishingNotificationParticles;
import com.wdiscute.starcatcher.registry.*;
import com.wdiscute.starcatcher.rod.FishingRodScreen;
import com.wdiscute.starcatcher.storage.FishProperties;
import com.wdiscute.starcatcher.storage.TrophyProperties;
import com.wdiscute.starcatcher.tournament.StandScreen;
import com.wdiscute.starcatcher.tournament.TournamentOverlay;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.*;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

import java.util.List;

@EventBusSubscriber(modid = Starcatcher.MOD_ID, value = Dist.CLIENT)
public class ModClientEvents
{

    @SubscribeEvent
    public static void tooltipEvent(ItemTooltipEvent event)
    {
        List<Component> comp = event.getToolTip();
        ItemStack stack = event.getItemStack();

        if (stack.has(ModDataComponents.MODIFIERS))
        {
            List<ResourceLocation> modifiers = stack.get(ModDataComponents.MODIFIERS);

            if (!modifiers.isEmpty())
            {
                comp.add(Component.translatable("tooltip.starcatcher.modifiers").withStyle(ChatFormatting.GRAY));
                for (ResourceLocation rl : modifiers)
                {
                    for (int i = 0; i < 100; i++)
                    {
                        if (I18n.exists("tooltip.modifier." + rl.toLanguageKey() + "." + i))
                        {
                            comp.add(Component.literal("-").append(Component.translatable("tooltip.modifier." + rl.toLanguageKey() + "." + i)).withStyle(ChatFormatting.DARK_GRAY));
                        }
                    }
                }
            }
        }

        //size and weight
        if (stack.has(ModDataComponents.SIZE_AND_WEIGHT))
        {
            SizeAndWeightInstance sw = stack.get(ModDataComponents.SIZE_AND_WEIGHT);

            SettingsScreen.Units units = Config.UNIT.get();

            String size = units.getSizeAsString(sw.sizeInCentimeters());
            String weight = units.getWeightAsString(sw.weightInGrams());

            comp.add(1, Component.literal(size + " - " + weight).withColor(0x888888));
        }

        //rarity name color
        if (stack.has(ModDataComponents.FISH_PROPERTIES))
        {
            FishProperties fp = stack.get(ModDataComponents.FISH_PROPERTIES);

            String s = fp.rarity().getPre() + comp.get(0).getString(100) + fp.rarity().getPost();

            comp.remove(0);
            comp.add(0, Tooltips.decodeString(s));
        }

        //trophy stuff
        if (stack.has(ModDataComponents.TROPHY))
        {
            TrophyProperties tp = stack.get(ModDataComponents.TROPHY);

            if (tp.trophyType() == TrophyProperties.TrophyType.TROPHY)
                if (event.getFlags().hasShiftDown())
                {
                    comp.add(Component.translatable("tooltip.libtooltips.generic.shift_down"));
                    comp.add(Component.translatable("tooltip.libtooltips.generic.empty"));
                    comp.add(Component.translatable("tooltip.starcatcher.trophy.0"));
                    comp.add(Component.translatable("tooltip.starcatcher.trophy.1"));

                    List<Component> list = new java.util.ArrayList<>();

                    //all
                    if (tp.all().total() != 0) list.add(Tooltips.decodeString(
                            I18n.get("tooltip.starcatcher.trophy.total")
                                    .replace("&", tp.all().total() + "")
                                    .replace("$", I18n.get("tooltip.starcatcher.trophy.all"))
                    ));

                    if (tp.all().unique() != 0) list.add(
                            Tooltips.decodeString(I18n.get("tooltip.starcatcher.trophy.unique")
                                    .replace("&", tp.all().unique() + "")
                                    .replace("$", I18n.get("tooltip.starcatcher.trophy.all"))));

                    for (FishProperties.Rarity value : FishProperties.Rarity.values())
                    {
                        TrophyProperties.RarityProgress progress = tp.getProgress(value);
                        if (progress.total() != 0) list.add(
                                Tooltips.decodeString(I18n.get("tooltip.starcatcher.trophy.total")
                                        .replace("&", progress.total() + "")
                                        .replace("$", I18n.get("tooltip.starcatcher.trophy." + value.getSerializedName()))));

                        if (progress.unique() != 0) list.add(
                                Tooltips.decodeString(I18n.get("tooltip.starcatcher.trophy.unique")
                                        .replace("&", progress.unique() + "")
                                        .replace("$", I18n.get("tooltip.starcatcher.trophy." + value.getSerializedName()))));
                    }

                    if (list.size() == 1)
                    {
                        comp.add(Component.translatable("tooltip.starcatcher.trophy.once")
                                .append(list.getFirst())
                                .append(Component.translatable("tooltip.starcatcher.trophy.have_been_caught")));
                    }
                    else
                    {
                        comp.add(Component.translatable("tooltip.starcatcher.trophy.2"));
                        comp.addAll(list);
                    }

                }
                else
                {
                    comp.add(Component.translatable("tooltip.libtooltips.generic.shift_up"));
                }

        }
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event)
    {
        EntityRenderers.register(ModEntities.FISHING_BOB.get(), FishingBobRenderer::new);
        EntityRenderers.register(ModEntities.BOTTLE.get(), ThrownItemRenderer::new);
        EntityRenderers.register(ModEntities.FISH.get(), FishRenderer::new);
        ModItemProperties.addCustomItemProperties();
    }

    @SubscribeEvent
    public static void registerGuiLayers(RegisterGuiLayersEvent event)
    {
        event.registerAboveAll(Starcatcher.rl("fish_tracker"), new FishRadarLayer());
        event.registerAboveAll(Starcatcher.rl("tournament"), new TournamentOverlay());
    }

    @SubscribeEvent
    public static void registerParticleFactories(RegisterParticleProvidersEvent event)
    {
        event.registerSpriteSet(ModParticles.FISHING_NOTIFICATION.get(), FishingNotificationParticles.Provider::new);
        event.registerSpriteSet(ModParticles.FISHING_BITING.get(), FishingBitingParticles.Provider::new);
        event.registerSpriteSet(ModParticles.FISHING_BITING_LAVA.get(), FishingBitingLavaParticles.Provider::new);
    }

    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event)
    {
        event.register(ModMenuTypes.FISHING_ROD_MENU.get(), FishingRodScreen::new);
        event.register(ModMenuTypes.STAND_MENU.get(), StandScreen::new);
    }

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event)
    {
        event.registerLayerDefinition(FishingBobModel.LAYER_LOCATION, FishingBobModel::createBodyLayer);
        event.registerLayerDefinition(AgaveBream.LAYER_LOCATION, AgaveBream::createBodyLayer);
        event.registerLayerDefinition(BigeyeTuna.LAYER_LOCATION, BigeyeTuna::createBodyLayer);
        event.registerLayerDefinition(Boreal.LAYER_LOCATION, Boreal::createBodyLayer);
        event.registerLayerDefinition(CactiFish.LAYER_LOCATION, CactiFish::createBodyLayer);
        event.registerLayerDefinition(Charfish.LAYER_LOCATION, Charfish::createBodyLayer);
        event.registerLayerDefinition(CrystalbackBoreal.LAYER_LOCATION, CrystalbackBoreal::createBodyLayer);
        event.registerLayerDefinition(CrystalbackMinnow.LAYER_LOCATION, CrystalbackMinnow::createBodyLayer);
        event.registerLayerDefinition(DeepjawHerring.LAYER_LOCATION, DeepjawHerring::createBodyLayer);
        event.registerLayerDefinition(DownfallBream.LAYER_LOCATION, DownfallBream::createBodyLayer);
        event.registerLayerDefinition(Driftfin.LAYER_LOCATION, Driftfin::createBodyLayer);
        event.registerLayerDefinition(DriftingBream.LAYER_LOCATION, DriftingBream::createBodyLayer);
        event.registerLayerDefinition(DusktailSnapper.LAYER_LOCATION, DusktailSnapper::createBodyLayer);
        event.registerLayerDefinition(LilySnapper.LAYER_LOCATION, LilySnapper::createBodyLayer);
        event.registerLayerDefinition(PinkKoi.LAYER_LOCATION, PinkKoi::createBodyLayer);
        event.registerLayerDefinition(SilverveilPerch.LAYER_LOCATION, SilverveilPerch::createBodyLayer);
        event.registerLayerDefinition(SludgeCatfish.LAYER_LOCATION, SludgeCatfish::createBodyLayer);
        event.registerLayerDefinition(Whiteveil.LAYER_LOCATION, Whiteveil::createBodyLayer);
        event.registerLayerDefinition(WillowBream.LAYER_LOCATION, WillowBream::createBodyLayer);
        event.registerLayerDefinition(WinteryPike.LAYER_LOCATION, WinteryPike::createBodyLayer);
        event.registerLayerDefinition(CrystalbackTrout.LAYER_LOCATION, CrystalbackTrout::createBodyLayer);
        event.registerLayerDefinition(Embergill.LAYER_LOCATION, Embergill::createBodyLayer);
        event.registerLayerDefinition(FrostgillChub.LAYER_LOCATION, FrostgillChub::createBodyLayer);
        event.registerLayerDefinition(FrostjawTrout.LAYER_LOCATION, FrostjawTrout::createBodyLayer);
        event.registerLayerDefinition(HollowbellyDarter.LAYER_LOCATION, HollowbellyDarter::createBodyLayer);
        event.registerLayerDefinition(IcetoothSturgeon.LAYER_LOCATION, IcetoothSturgeon::createBodyLayer);
        event.registerLayerDefinition(MistbackChub.LAYER_LOCATION, MistbackChub::createBodyLayer);
    }

    @SubscribeEvent
    public static void onRegisterKeyMappings(RegisterKeyMappingsEvent event)
    {
        event.register(ModKeymappings.MINIGAME_HIT);
    }

    @SubscribeEvent
    public static void onRegisterTooltips(RegisterClientTooltipComponentFactoriesEvent event)
    {
        event.register(StarcaughtBucket.BucketTooltip.class, BucketTooltipRenderer::new);
    }

}
