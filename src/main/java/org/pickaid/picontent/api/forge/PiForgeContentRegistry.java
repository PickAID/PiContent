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
import org.pickaid.picontent.api.block.PiBlockPropertiesPlan;
import org.pickaid.picontent.api.item.PiItemPropertiesPlan;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class PiForgeContentRegistry {
    private final PiContentRegistrationPlan plan;
    private final Map<String, PiForgeRegistryEntry<Item>> itemEntries = new LinkedHashMap<>();
    private final Map<String, PiForgeRegistryEntry<Block>> blockEntries = new LinkedHashMap<>();
    private final Map<String, PiForgeRegistryEntry<BlockEntityType<?>>> blockEntityEntries = new LinkedHashMap<>();
    private final Map<String, PiForgeBlockEntityFactory<?>> blockEntityFactories = new LinkedHashMap<>();

    private PiForgeContentRegistry(PiContentRegistrationPlan plan) {
        this.plan = plan;
    }

    public static PiForgeContentRegistry create(PiContentRegistrationPlan plan) {
        return new PiForgeContentRegistry(plan);
    }

    public PiForgeContentRegistry registerItem(String name) {
        PiContentItemRegistration item = plan.item(name);
        itemEntries.put(name, new PiForgeRegistryEntry<>(name, item.id()));
        return this;
    }

    public PiForgeContentRegistry registerBlock(String name) {
        PiContentBlockRegistration block = plan.block(name);
        blockEntries.put(name, new PiForgeRegistryEntry<>(name, block.id()));
        if (plan.item(name) != null && !itemEntries.containsKey(name)) {
            itemEntries.put(name, new PiForgeRegistryEntry<>(name, plan.item(name).id()));
        }
        return this;
    }

    public <T extends BlockEntity> PiForgeContentRegistry registerBlockEntity(String name, PiForgeBlockEntityFactory<T> factory) {
        PiContentBlockEntityRegistration blockEntity = plan.blockEntity(name);
        blockEntityEntries.put(name, new PiForgeRegistryEntry<>(name, blockEntity.id()));
        blockEntityFactories.put(name, factory);
        return this;
    }

    public PiForgeContentRegistry registerAllContent(IEventBus modEventBus) {
        DeferredRegister<Item> items = DeferredRegister.create(ForgeRegistries.ITEMS, plan.modId());
        DeferredRegister<Block> blocks = DeferredRegister.create(ForgeRegistries.BLOCKS, plan.modId());
        DeferredRegister<BlockEntityType<?>> blockEntities = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, plan.modId());

        blockEntries.forEach((name, entry) -> {
            PiContentBlockRegistration block = plan.block(name);
            if (block.blockEntityHost()) {
                entry.bind(blocks.register(name, () -> new PiContentEntityBlock(blockProperties(block.properties()), blockEntityEntry(name))));
            } else {
                entry.bind(blocks.register(name, () -> new Block(blockProperties(block.properties()))));
            }
        });
        blockEntityEntries.forEach((name, entry) -> entry.bind(blockEntities.register(name, () -> blockEntityType(plan.blockEntity(name), blockEntityFactory(name)))));
        itemEntries.forEach((name, entry) -> {
            PiContentItemRegistration item = plan.item(name);
            if (plan.block(name) != null) {
                entry.bind(items.register(name, () -> new BlockItem(blockEntry(name).get(), itemProperties(item.properties()))));
            } else {
                entry.bind(items.register(name, () -> new Item(itemProperties(item.properties()))));
            }
        });

        items.register(modEventBus);
        blocks.register(modEventBus);
        blockEntities.register(modEventBus);
        return this;
    }

    public PiForgeContentEntries entries() {
        return new PiForgeContentEntries(itemEntries, blockEntries, blockEntityEntries);
    }

    @SuppressWarnings("unchecked")
    private <T extends BlockEntity> PiForgeRegistryEntry<BlockEntityType<T>> blockEntityEntry(String name) {
        return (PiForgeRegistryEntry<BlockEntityType<T>>) (PiForgeRegistryEntry<?>) blockEntityEntries.get(name);
    }

    private PiForgeRegistryEntry<Block> blockEntry(String name) {
        return blockEntries.get(name);
    }

    @SuppressWarnings("unchecked")
    private <T extends BlockEntity> PiForgeBlockEntityFactory<T> blockEntityFactory(String name) {
        return (PiForgeBlockEntityFactory<T>) blockEntityFactories.get(name);
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
        PiForgeRegistryEntry<Block> block = blockEntries.get(location.getPath());
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
        private final PiForgeRegistryEntry<BlockEntityType<BlockEntity>> blockEntityType;

        private PiContentEntityBlock(
                BlockBehaviour.Properties properties,
                PiForgeRegistryEntry<BlockEntityType<BlockEntity>> blockEntityType
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
