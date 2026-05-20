package org.pickaid.picontent.api.forge;

import com.mojang.datafixers.types.Type;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.pickaid.picontent.api.block.PiBlockPropertiesPlan;
import org.pickaid.picontent.api.item.PiItemPropertiesPlan;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class PiForgeContentRegistry {
    private final PiContentRegistrationPlan plan;
    private final DeferredRegister<Item> items;
    private final DeferredRegister<Block> blocks;
    private final DeferredRegister<BlockEntityType<?>> blockEntities;
    private final Map<String, RegistryObject<Item>> itemEntries = new LinkedHashMap<>();
    private final Map<String, RegistryObject<Block>> blockEntries = new LinkedHashMap<>();
    private final Map<String, RegistryObject<BlockEntityType<?>>> blockEntityEntries = new LinkedHashMap<>();

    private PiForgeContentRegistry(PiContentRegistrationPlan plan) {
        this.plan = plan;
        this.items = DeferredRegister.create(ForgeRegistries.ITEMS, plan.modId());
        this.blocks = DeferredRegister.create(ForgeRegistries.BLOCKS, plan.modId());
        this.blockEntities = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, plan.modId());
    }

    public static PiForgeContentRegistry create(PiContentRegistrationPlan plan) {
        return new PiForgeContentRegistry(plan);
    }

    public PiForgeContentRegistry registerItem(String name) {
        PiContentItemRegistration item = plan.item(name);
        itemEntries.put(name, items.register(name, () -> new Item(itemProperties(item.properties()))));
        return this;
    }

    public PiForgeContentRegistry registerBlock(String name) {
        PiContentBlockRegistration block = plan.block(name);
        if (block.blockEntityHost()) {
            blockEntries.put(name, blocks.register(name, () -> new PiContentEntityBlock(blockProperties(block.properties()), blockEntityEntry(name))));
        } else {
            blockEntries.put(name, blocks.register(name, () -> new Block(blockProperties(block.properties()))));
        }
        if (plan.item(name) != null && !itemEntries.containsKey(name)) {
            itemEntries.put(name, items.register(name, () -> new BlockItem(blockEntry(name).get(), itemProperties(plan.item(name).properties()))));
        }
        return this;
    }

    public <T extends BlockEntity> PiForgeContentRegistry registerBlockEntity(String name, PiForgeBlockEntityFactory<T> factory) {
        PiContentBlockEntityRegistration blockEntity = plan.blockEntity(name);
        blockEntityEntries.put(name, blockEntities.register(name, () -> blockEntityType(blockEntity, factory)));
        return this;
    }

    public PiForgeContentRegistry registerAllContent(IEventBus modEventBus) {
        items.register(modEventBus);
        blocks.register(modEventBus);
        blockEntities.register(modEventBus);
        return this;
    }

    public PiForgeContentEntries entries() {
        return new PiForgeContentEntries(itemEntries, blockEntries, blockEntityEntries);
    }

    @SuppressWarnings("unchecked")
    private <T extends BlockEntity> RegistryObject<BlockEntityType<T>> blockEntityEntry(String name) {
        return (RegistryObject<BlockEntityType<T>>) (RegistryObject<?>) blockEntityEntries.get(name);
    }

    private RegistryObject<Block> blockEntry(String name) {
        return blockEntries.get(name);
    }

    private <T extends BlockEntity> BlockEntityType<T> blockEntityType(
            PiContentBlockEntityRegistration registration,
            PiForgeBlockEntityFactory<T> factory
    ) {
        List<Block> validBlocks = registration.validBlockIds().stream()
                .map(this::blockById)
                .toList();
        return BlockEntityType.Builder.<T>of(
                (pos, state) -> factory.create(new PiForgeRegisteredBlockEntity<>(blockEntityEntry(registration.name())), pos, state),
                validBlocks.toArray(Block[]::new)
        ).build((Type<?>) null);
    }

    private Block blockById(String id) {
        ResourceLocation location = ResourceLocation.tryParse(id);
        if (location == null || !location.getNamespace().equals(plan.modId())) {
            throw new IllegalArgumentException("Only local block ids can be bound during P0 registration: " + id);
        }
        RegistryObject<Block> block = blockEntries.get(location.getPath());
        if (block == null) {
            throw new IllegalStateException("Unknown PiContent block plan: " + id);
        }
        return block.get();
    }

    private static Item.Properties itemProperties(PiItemPropertiesPlan plan) {
        Item.Properties properties = new Item.Properties();
        if (plan.maxStackSize() != 64) {
            properties.stacksTo(plan.maxStackSize());
        }
        return properties;
    }

    private static BlockBehaviour.Properties blockProperties(PiBlockPropertiesPlan plan) {
        BlockBehaviour.Properties properties = BlockBehaviour.Properties.of().strength("stone_machine".equals(plan.preset()) ? 3.0F : 1.5F);
        if (plan.lightLevel() > 0) {
            properties.lightLevel(state -> plan.lightLevel());
        }
        if (plan.occlusionDisabled()) {
            properties.noOcclusion();
        }
        return properties;
    }

    private static final class PiContentEntityBlock extends BaseEntityBlock {
        private final RegistryObject<BlockEntityType<BlockEntity>> blockEntityType;

        private PiContentEntityBlock(
                BlockBehaviour.Properties properties,
                RegistryObject<BlockEntityType<BlockEntity>> blockEntityType
        ) {
            super(properties);
            this.blockEntityType = blockEntityType;
        }

        @Override
        public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
            return blockEntityType.get().create(pos, state);
        }

        @Override
        public RenderShape getRenderShape(BlockState state) {
            return RenderShape.MODEL;
        }
    }
}
