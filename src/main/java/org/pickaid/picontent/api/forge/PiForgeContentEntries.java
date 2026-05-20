package org.pickaid.picontent.api.forge;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.List;
import java.util.Map;

public record PiForgeContentEntries(
        Map<String, PiForgeRegistryEntry<Item>> items,
        Map<String, PiForgeRegistryEntry<Block>> blocks,
        Map<String, PiForgeRegistryEntry<BlockEntityType<?>>> blockEntities
) {
    public PiForgeContentEntries {
        items = Map.copyOf(items);
        blocks = Map.copyOf(blocks);
        blockEntities = Map.copyOf(blockEntities);
    }

    public PiForgeRegistryEntry<Item> item(String name) {
        return items.get(name);
    }

    public PiForgeRegistryEntry<Block> block(String name) {
        return blocks.get(name);
    }

    @SuppressWarnings("unchecked")
    public <T extends BlockEntity> PiForgeRegistryEntry<BlockEntityType<T>> blockEntity(String name) {
        return (PiForgeRegistryEntry<BlockEntityType<T>>) (PiForgeRegistryEntry<?>) blockEntities.get(name);
    }

    public List<PiForgeRegistryEntry<Item>> itemEntries() {
        return List.copyOf(items.values());
    }

    public List<PiForgeRegistryEntry<Block>> blockEntries() {
        return List.copyOf(blocks.values());
    }

    public List<PiForgeRegistryEntry<BlockEntityType<?>>> blockEntityEntries() {
        return List.copyOf(blockEntities.values());
    }
}
