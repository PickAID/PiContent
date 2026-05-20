package org.pickaid.picontent.api.forge;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.RegistryObject;

public record PiForgeRegisteredBlockEntity<T extends BlockEntity>(RegistryObject<BlockEntityType<T>> type) {
}
