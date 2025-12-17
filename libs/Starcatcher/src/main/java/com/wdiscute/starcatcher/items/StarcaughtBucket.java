package com.wdiscute.starcatcher.items;

import com.wdiscute.starcatcher.Config;
import com.wdiscute.starcatcher.fishentity.FishEntity;
import com.wdiscute.starcatcher.guide.SettingsScreen;
import com.wdiscute.starcatcher.io.ModDataComponents;
import com.wdiscute.starcatcher.io.SingleStackContainer;
import com.wdiscute.starcatcher.io.SizeAndWeightInstance;
import com.wdiscute.starcatcher.registry.ModEntities;
import com.wdiscute.starcatcher.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluid;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class StarcaughtBucket extends BucketItem
{
    EntityType<FishEntity> entity;

    public StarcaughtBucket(Fluid fluid)
    {
        super(
                fluid, new Item.Properties().stacksTo(16));

        entity = ModEntities.FISH.get();
    }

    @Override
    public void checkExtraContent(@Nullable Player player, Level level, ItemStack containerStack, BlockPos pos)
    {
        if (level instanceof ServerLevel)
        {
            this.spawn((ServerLevel) level, containerStack, pos);
            level.gameEvent(player, GameEvent.ENTITY_PLACE, pos);
        }
    }

    private void spawn(ServerLevel serverLevel, ItemStack bucketedMobStack, BlockPos pos)
    {
        FishEntity fishEntity = this.entity.spawn(serverLevel, bucketedMobStack, null, pos, MobSpawnType.BUCKET, true, false);
        if(bucketedMobStack.has(ModDataComponents.BUCKETED_FISH))
            fishEntity.setFish(getFish(bucketedMobStack));
        else
            fishEntity.setFish(ModItems.AURORA.toStack());
    }

    private static ItemStack getFish(ItemStack bucket) {
        return bucket.getOrDefault(ModDataComponents.BUCKETED_FISH, new SingleStackContainer(ItemStack.EMPTY)).stack();
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag)
    {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        ItemStack fish = getFish(stack);
        if (fish.isEmpty())
        {
            tooltipComponents.add(1, Component.translatable("tooltip.starcatcher.starcaught_bucket.creative.1").withColor(0x888888));
            tooltipComponents.add(1, Component.translatable("tooltip.starcatcher.starcaught_bucket.creative.0").withColor(0x888888));
        }
    }

    @Override
    public Component getName(ItemStack stack)
    {
        SingleStackContainer ssc = stack.get(ModDataComponents.BUCKETED_FISH);

        if (ssc == null)
            return super.getName(stack);
        else
        {
            return Component.translatable("tooltip.starcatcher.starcaught_bucket.before")
                    .append(ssc.stack().getItem().getName(stack))
                    .append(Component.translatable("tooltip.starcatcher.starcaught_bucket.after"));
        }
    }

    @Override
    public Optional<TooltipComponent> getTooltipImage(ItemStack stack) {
        return Optional.of(new BucketTooltip(getFish(stack)));
    }

    public record BucketTooltip(ItemStack fish) implements TooltipComponent {}
}
