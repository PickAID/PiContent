package org.pickaid.picontent.api.forge;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

@FunctionalInterface
public interface PiForgeBlockEntityFactory<T extends BlockEntity> {
    T create(PiForgeRegisteredBlockEntity<T> registered, BlockPos pos, BlockState state);
}
