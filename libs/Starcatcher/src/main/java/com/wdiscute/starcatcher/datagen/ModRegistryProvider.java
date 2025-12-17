package com.wdiscute.starcatcher.datagen;

import com.wdiscute.starcatcher.Starcatcher;
import com.wdiscute.starcatcher.registry.fishing.ModTrophyProperties;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModRegistryProvider extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder REGISTRY = new RegistrySetBuilder()
            //trophies
            .add(Starcatcher.TROPHY_REGISTRY, ModTrophyProperties::bootstrap);

    public ModRegistryProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, REGISTRY, Set.of(
                "starcatcher",
                "minecraft"
        ));
    }
}
