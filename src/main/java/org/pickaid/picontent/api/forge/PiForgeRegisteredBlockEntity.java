package org.pickaid.picontent.api.forge;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

public record PiForgeRegisteredBlockEntity<T extends BlockEntity>(PiForgeRegistryEntry<BlockEntityType<T>> type) {
}
