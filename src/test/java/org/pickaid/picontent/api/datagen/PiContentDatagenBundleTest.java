package org.pickaid.picontent.api.datagen;

import org.junit.jupiter.api.Test;
import org.pickaid.picontent.api.block.PiBlockBuilder;
import org.pickaid.picontent.api.block.PiBlockLoot;
import org.pickaid.picontent.api.block.PiBlockModels;
import org.pickaid.picontent.api.block.PiBlockPlacement;
import org.pickaid.picontent.api.block.PiBlockPresets;
import org.pickaid.picontent.api.block.PiBlockShapes;
import org.pickaid.picontent.api.blockentity.PiBlockEntityBuilder;
import org.pickaid.picontent.api.blockentity.PiContentRenderers;
import org.pickaid.picontent.api.blockentity.PiContentSync;
import org.pickaid.picontent.api.item.PiItemBuilder;
import org.pickaid.picontent.api.item.PiItemModels;
import org.pickaid.picontent.api.item.PiItemPresets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

final class PiContentDatagenBundleTest {
    @Test
    void bundleCollectsLogicalOutputsForStaffItemAndAltarBlockEntity() {
        PiContentDatagenBundle bundle = PiContentDatagenBundle.empty()
                .item(PiItemBuilder.named("fire_staff")
                        .section("spells")
                        .properties(PiItemPresets.staff())
                        .itemTag("test:staves")
                        .lang("Fire Staff")
                        .model(PiItemModels.handheld("item/fire_staff"))
                        .plan())
                .block(PiBlockBuilder.named("rune_altar")
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
                        .plan())
                .blockEntity(PiBlockEntityBuilder.named("rune_altar")
                        .validBlock("picontent:rune_altar")
                        .dirtySync(PiContentSync.tracking("rune_altar_state"))
                        .renderer(PiContentRenderers.blockEntity("rune_altar"))
                        .plan());

        assertEquals("Fire Staff", bundle.lang().get(0).value());
        assertTrue(bundle.itemModels().contains(new PiModelDeclaration("item", "fire_staff", "handheld", "item/fire_staff")));
        assertTrue(bundle.blockstates().contains(new PiModelDeclaration("blockstate", "rune_altar", "horizontal", "block/rune_altar")));
        assertTrue(bundle.blockModels().contains(new PiModelDeclaration("block", "rune_altar", "horizontal", "block/rune_altar")));
        assertTrue(bundle.loot().contains(new PiLootDeclaration("rune_altar", "self")));
        assertTrue(bundle.tags().contains(new PiTagDeclaration("block", "test:ritual_bases", "rune_altar")));
        assertTrue(bundle.tags().contains(new PiTagDeclaration("item", "test:ritual_blocks", "rune_altar")));
        assertTrue(bundle.creativeSamples().contains(new PiCreativeSampleDeclaration("rituals", "rune_altar")));
    }
}
