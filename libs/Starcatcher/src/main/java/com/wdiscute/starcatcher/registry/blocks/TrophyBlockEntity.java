package com.wdiscute.starcatcher.registry.blocks;

import com.mojang.logging.LogUtils;
import com.mojang.serialization.DataResult;
import com.wdiscute.starcatcher.io.ModDataComponents;
import com.wdiscute.starcatcher.storage.TrophyProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.slf4j.Logger;

public class TrophyBlockEntity extends BlockEntity {
    private static final Logger LOGGER = LogUtils.getLogger();
    private TrophyProperties trophyProperties;

    public TrophyBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.TROPHY.get(), pPos, pBlockState);
    }

    @Override
    protected void applyImplicitComponents(DataComponentInput componentInput) {
        super.applyImplicitComponents(componentInput);
        this.trophyProperties = componentInput.getOrDefault(ModDataComponents.TROPHY.get(), TrophyProperties.builder().build());
        setChanged();
    }

    @Override
    protected void collectImplicitComponents(DataComponentMap.Builder components) {
        super.collectImplicitComponents(components);
        components.set(ModDataComponents.TROPHY, trophyProperties);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);

        if (this.trophyProperties == null) return;

        TrophyProperties.CODEC.encode(this.trophyProperties, NbtOps.INSTANCE, tag)
                .resultOrPartial(LOGGER::warn).ifPresent(tag1 -> tag.put("trophy_properties", tag1));
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);

        if (tag.contains("trophy_properties")) {
            CompoundTag trophyProperties = tag.getCompound("trophy_properties");
            DataResult<TrophyProperties> decode = TrophyProperties.CODEC.parse(NbtOps.INSTANCE, trophyProperties);
            this.trophyProperties = decode.result().orElse(TrophyProperties.builder().build());
        }
    }
}
