package org.pickaid.picontent.api.blockentity;

import java.util.List;

public record PiBlockEntityPlan(
        String name,
        List<String> validBlocks,
        List<String> serverTickers,
        List<String> clientTickers,
        List<PiContentDeclaration> menuDeclarations,
        PiContentDeclaration saveLoad,
        PiContentDeclaration dirtySync,
        List<PiContentDeclaration> capabilityDeclarations,
        List<PiContentDeclaration> rendererDeclarations
) {
    public PiBlockEntityPlan {
        validBlocks = List.copyOf(validBlocks);
        serverTickers = List.copyOf(serverTickers);
        clientTickers = List.copyOf(clientTickers);
        menuDeclarations = List.copyOf(menuDeclarations);
        capabilityDeclarations = List.copyOf(capabilityDeclarations);
        rendererDeclarations = List.copyOf(rendererDeclarations);
    }
}
