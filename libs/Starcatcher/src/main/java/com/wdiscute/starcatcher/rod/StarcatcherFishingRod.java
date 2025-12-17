package com.wdiscute.starcatcher.rod;

import com.wdiscute.starcatcher.StarcatcherTags;
import com.wdiscute.starcatcher.bob.FishingBobEntity;
import com.wdiscute.starcatcher.io.ModDataAttachments;
import com.wdiscute.starcatcher.io.ModDataComponents;
import com.wdiscute.starcatcher.io.SingleStackContainer;
import com.wdiscute.starcatcher.registry.ModItems;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class StarcatcherFishingRod extends Item implements MenuProvider {
    public StarcatcherFishingRod() {
        super(new Item.Properties()
                .rarity(Rarity.EPIC)
                .fireResistant()
                .stacksTo(1)
                .component(ModDataComponents.NETHERITE_UPGRADE.get(), false)
                .component(ModDataComponents.BOBBER_SKIN.get(), SingleStackContainer.EMPTY)
                .component(ModDataComponents.BOBBER.get(), new SingleStackContainer(new ItemStack(ModItems.BOBBER.get())))
                .component(ModDataComponents.BAIT.get(), SingleStackContainer.EMPTY)
                .component(ModDataComponents.HOOK.get(), new SingleStackContainer(new ItemStack(ModItems.HOOK.get())))
        );
    }

    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (!player.getItemInHand(hand).is(StarcatcherTags.RODS))
            return InteractionResultHolder.pass(player.getItemInHand(hand));

        if (player.isCrouching() && ModDataAttachments.get(player, ModDataAttachments.FISHING.get()).isEmpty()) {
            player.openMenu(this);
            return InteractionResultHolder.success(player.getItemInHand(hand));
        }

        if (level.isClientSide) return InteractionResultHolder.success(player.getItemInHand(hand));


        if (ModDataAttachments.get(player, ModDataAttachments.FISHING.get()).isEmpty()) {
            level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.FISHING_BOBBER_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));

            if (level instanceof ServerLevel) {
                //TODO ADD CUSTOM STAT FOR NUMBER OF FISHES CAUGHT TOTAL ON STAT SCREEN

                Entity entity = new FishingBobEntity(level, player, player.getItemInHand(hand));
                level.addFreshEntity(entity);

                ModDataAttachments.set(player, ModDataAttachments.FISHING.get(), entity.getStringUUID());
                SingleStackContainer bobberSkin = player.getItemInHand(hand).get(ModDataComponents.BOBBER_SKIN);
                if (bobberSkin != null) ModDataAttachments.set(entity ,ModDataAttachments.BOBBER_SKIN.get(), bobberSkin);
            }
        } else {

            List<Entity> entities = level.getEntities(null, new AABB(-25, -65, -25, 25, 65, 25).move(player.position()));

            for (Entity entity : entities) {
                if (entity.getUUID().toString().equals(ModDataAttachments.get(player, ModDataAttachments.FISHING.get()))) {
                    if (entity instanceof FishingBobEntity fbe && !fbe.checkBiting()) {
                        fbe.kill();
                        ModDataAttachments.set(player, ModDataAttachments.FISHING.get(), "");
                    }
                }
            }

        }


        return InteractionResultHolder.success(player.getItemInHand(hand));
    }


    @Override
    public boolean hasCraftingRemainingItem(ItemStack stack) {
        return true;
    }

    @Override
    public ItemStack getCraftingRemainingItem(ItemStack itemStack) {
        return itemStack.copy();
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Starcatcher's rod");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        if (player.getMainHandItem().is(StarcatcherTags.RODS))
            return new FishingRodMenu(i, inventory, player.getMainHandItem());
        else
            return new FishingRodMenu(i, inventory, player.getOffhandItem());
    }
}

