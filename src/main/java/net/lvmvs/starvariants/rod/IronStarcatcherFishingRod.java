package net.lvmvs.starvariants.rod;

import com.wdiscute.starcatcher.*;
import com.wdiscute.starcatcher.StarcatcherTags;
import com.wdiscute.starcatcher.bob.FishingBobEntity;
import com.wdiscute.starcatcher.io.ModDataAttachments;
import com.wdiscute.starcatcher.io.ModDataComponents;
import com.wdiscute.starcatcher.io.SingleStackContainer;
import com.wdiscute.starcatcher.registry.ModItems;
import com.wdiscute.starcatcher.rod.FishingRodMenu;
import net.minecraft.core.component.DataComponentType;
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
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.neoforged.neoforge.attachment.AttachmentType;
import org.jetbrains.annotations.Nullable;

public class IronStarcatcherFishingRod extends Item implements MenuProvider {
    public IronStarcatcherFishingRod() {
        super((new Item.Properties()).rarity(Rarity.EPIC).fireResistant().stacksTo(1).component((DataComponentType) ModDataComponents.NETHERITE_UPGRADE.get(), false).component((DataComponentType)ModDataComponents.BOBBER_SKIN.get(), SingleStackContainer.EMPTY).component((DataComponentType)ModDataComponents.BOBBER.get(), new SingleStackContainer(new ItemStack((ItemLike) com.wdiscute.starcatcher.registry.ModItems.BOBBER.get()))).component((DataComponentType)ModDataComponents.BAIT.get(), SingleStackContainer.EMPTY).component((DataComponentType)ModDataComponents.HOOK.get(), new SingleStackContainer(new ItemStack((ItemLike) ModItems.HOOK.get()))));
    }

    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (!player.getItemInHand(hand).is(StarcatcherTags.RODS)) {
            return InteractionResultHolder.pass(player.getItemInHand(hand));
        } else if (player.isCrouching() && ((String) ModDataAttachments.get(player, (AttachmentType)ModDataAttachments.FISHING.get())).isEmpty()) {
            player.openMenu(this);
            return InteractionResultHolder.success(player.getItemInHand(hand));
        } else if (level.isClientSide) {
            return InteractionResultHolder.success(player.getItemInHand(hand));
        } else {
            if (((String)ModDataAttachments.get(player, (AttachmentType)ModDataAttachments.FISHING.get())).isEmpty()) {
                level.playSound((Player)null, player.getX(), player.getY(), player.getZ(), SoundEvents.FISHING_BOBBER_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
                if (level instanceof ServerLevel) {
                    Entity entity = new FishingBobEntity(level, player, player.getItemInHand(hand));
                    level.addFreshEntity(entity);
                    ModDataAttachments.set(player, (AttachmentType)ModDataAttachments.FISHING.get(), entity.getStringUUID());
                    SingleStackContainer bobberSkin = (SingleStackContainer)player.getItemInHand(hand).get(ModDataComponents.BOBBER_SKIN);
                    if (bobberSkin != null) {
                        ModDataAttachments.set(entity, (AttachmentType)ModDataAttachments.BOBBER_SKIN.get(), bobberSkin);
                    }
                }
            } else {
                for(Entity entity : level.getEntities((Entity)null, (new AABB((double)-25.0F, (double)-65.0F, (double)-25.0F, (double)25.0F, (double)65.0F, (double)25.0F)).move(player.position()))) {
                    if (entity.getUUID().toString().equals(ModDataAttachments.get(player, (AttachmentType)ModDataAttachments.FISHING.get())) && entity instanceof FishingBobEntity) {
                        FishingBobEntity fbe = (FishingBobEntity)entity;
                        if (!fbe.checkBiting()) {
                            fbe.kill();
                            ModDataAttachments.set(player, (AttachmentType)ModDataAttachments.FISHING.get(), "");
                        }
                    }
                }
            }

            return InteractionResultHolder.success(player.getItemInHand(hand));
        }
    }

    public boolean hasCraftingRemainingItem(ItemStack stack) {
        return true;
    }

    public ItemStack getCraftingRemainingItem(ItemStack itemStack) {
        return itemStack.copy();
    }

    public Component getDisplayName() {
        return Component.literal("Iron Starcatcher's rod");
    }

    public @Nullable AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return player.getMainHandItem().is(StarcatcherTags.RODS) ? new FishingRodMenu(i, inventory, player.getMainHandItem()) : new FishingRodMenu(i, inventory, player.getOffhandItem());
    }
}
