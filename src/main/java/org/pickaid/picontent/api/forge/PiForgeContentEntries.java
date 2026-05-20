package org.pickaid.picontent.api.forge;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.Map;

public record PiForgeContentEntries(
        Map<String, RegistryObject<Item>> items,
        Map<String, RegistryObject<Block>> blocks,
        Map<String, RegistryObject<BlockEntityType<?>>> blockEntities
) {
    public PiForgeContentEntries {
        items = Map.copyOf(items);
        blocks = Map.copyOf(blocks);
        blockEntities = Map.copyOf(blockEntities);
    }

    public RegistryObject<Item> item(String name) {
        return items.get(name);
    }

    public RegistryObject<Block> block(String name) {
        return blocks.get(name);
    }

    @SuppressWarnings("unchecked")
    public <T extends BlockEntity> RegistryObject<BlockEntityType<T>> blockEntity(String name) {
        return (RegistryObject<BlockEntityType<T>>) (RegistryObject<?>) blockEntities.get(name);
    }

    public List<RegistryObject<Item>> itemEntries() {
        return List.copyOf(items.values());
    }

    public List<RegistryObject<Block>> blockEntries() {
        return List.copyOf(blocks.values());
    }

    public List<RegistryObject<BlockEntityType<?>>> blockEntityEntries() {
        return List.copyOf(blockEntities.values());
    }
}
