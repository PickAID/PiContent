package org.pickaid.picontent.api.forge;

import org.junit.jupiter.api.Test;
import org.pickaid.picontent.api.block.PiBlockBuilder;
import org.pickaid.picontent.api.block.PiBlockLoot;
import org.pickaid.picontent.api.block.PiBlockModels;
import org.pickaid.picontent.api.block.PiBlockPlacement;
import org.pickaid.picontent.api.block.PiBlockPresets;
import org.pickaid.picontent.api.block.PiBlockShapes;
import org.pickaid.picontent.api.blockentity.PiBlockEntityBuilder;
import org.pickaid.picontent.api.item.PiItemBuilder;
import org.pickaid.picontent.api.item.PiItemModels;
import org.pickaid.picontent.api.item.PiItemPresets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

final class PiContentForgeRegistryTest {
    @Test
    void registryCollectsStaffBlockSimpleItemAndBlockEntityEntries() {
        PiContentRegistrationPlan plan = PiContentRegistrationPlan.create("pimorset")
                .item(PiItemBuilder.named("storm_staff")
                        .properties(PiItemPresets.staff())
                        .model(PiItemModels.handheld("item/storm_staff"))
                        .plan())
                .blockEntityBlock(PiBlockBuilder.named("storm_altar")
                        .properties(PiBlockPresets.stoneMachine().lightLevel(4).noOcclusion())
                        .shape(PiBlockShapes.box16(1, 0, 1, 15, 12, 15))
                        .placement(PiBlockPlacement.horizontal())
                        .loot(PiBlockLoot.self())
                        .blockstate(PiBlockModels.horizontal("block/storm_altar"))
                        .simpleItem()
                        .plan())
                .blockEntity(PiBlockEntityBuilder.named("storm_altar")
                        .validBlock("pimorset:storm_altar")
                        .plan());

        assertEquals("pimorset", plan.modId());
        assertEquals("pimorset:storm_staff", plan.item("storm_staff").id());
        assertEquals("pimorset:storm_altar", plan.block("storm_altar").id());
        assertEquals("pimorset:storm_altar", plan.item("storm_altar").id());
        assertEquals("pimorset:storm_altar", plan.blockEntity("storm_altar").id());
        assertEquals("staff", plan.item("storm_staff").properties().preset());
        assertEquals("stone_machine", plan.block("storm_altar").properties().preset());
        assertTrue(plan.block("storm_altar").blockEntityHost());
        assertEquals("pimorset:storm_altar", plan.blockEntity("storm_altar").validBlockIds().get(0));
        assertFalse(plan.items().isEmpty());
        assertFalse(plan.blocks().isEmpty());
        assertFalse(plan.blockEntities().isEmpty());
    }
}
