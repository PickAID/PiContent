package org.pickaid.picontent.api.block;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

final class PiBlockBuilderTest {
    @Test
    void blockPlanCarriesTagsShapePlacementLootModelItemAndCreativeSection() {
        PiBlockPlan plan = PiBlockBuilder.named("rune_altar")
                .section("rituals")
                .properties(PiBlockPresets.stoneMachine().lightLevel(4).noOcclusion())
                .blockTag("test:ritual_bases")
                .itemTag("test:ritual_blocks")
                .shape(PiBlockShapes.box16(1, 0, 1, 15, 12, 15))
                .placement(PiBlockPlacement.horizontal())
                .loot(PiBlockLoot.self())
                .blockstate(PiBlockModels.horizontal("block/rune_altar"))
                .simpleItem()
                .creative("rituals")
                .plan();

        assertEquals("rune_altar", plan.name());
        assertEquals("stone_machine", plan.properties().preset());
        assertEquals("horizontal", plan.placement().preset());
        assertTrue(plan.blockTags().contains("test:ritual_bases"));
        assertTrue(plan.itemTags().contains("test:ritual_blocks"));
        assertTrue(plan.simpleItem());
        assertEquals("rituals", plan.creativeSection());
    }
}
