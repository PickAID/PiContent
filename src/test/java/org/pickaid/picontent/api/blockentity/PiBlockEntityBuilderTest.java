package org.pickaid.picontent.api.blockentity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

final class PiBlockEntityBuilderTest {
    @Test
    void blockEntityPlanCarriesBindingsTickersMenuSyncCapabilityAndRendererNeed() {
        PiBlockEntityPlan plan = PiBlockEntityBuilder.named("rune_altar")
                .validBlock("picontent:rune_altar")
                .serverTicker("rune_altar_server")
                .clientTicker("rune_altar_client")
                .menu(PiContentMenus.named("rune_altar"))
                .saveLoad(PiContentSync.saveLoad("rune_altar_state"))
                .dirtySync(PiContentSync.tracking("rune_altar_state"))
                .capability(PiContentCapabilities.itemHandler("items"))
                .renderer(PiContentRenderers.blockEntity("rune_altar"))
                .plan();

        assertEquals("rune_altar", plan.name());
        assertTrue(plan.validBlocks().contains("picontent:rune_altar"));
        assertTrue(plan.serverTickers().contains("rune_altar_server"));
        assertTrue(plan.clientTickers().contains("rune_altar_client"));
        assertEquals("menu:rune_altar", plan.menuDeclarations().get(0).key());
        assertEquals("sync:tracking:rune_altar_state", plan.dirtySync().key());
        assertEquals("capability:item_handler:items", plan.capabilityDeclarations().get(0).key());
        assertEquals("renderer:block_entity:rune_altar", plan.rendererDeclarations().get(0).key());
    }
}
