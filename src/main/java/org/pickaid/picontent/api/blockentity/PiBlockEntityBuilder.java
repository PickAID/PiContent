package org.pickaid.picontent.api.blockentity;

import java.util.ArrayList;
import java.util.List;

public final class PiBlockEntityBuilder {
    private final String name;
    private final List<String> validBlocks = new ArrayList<>();
    private final List<String> serverTickers = new ArrayList<>();
    private final List<String> clientTickers = new ArrayList<>();
    private final List<PiContentDeclaration> menuDeclarations = new ArrayList<>();
    private PiContentDeclaration saveLoad = PiContentSync.none();
    private PiContentDeclaration dirtySync = PiContentSync.none();
    private final List<PiContentDeclaration> capabilityDeclarations = new ArrayList<>();
    private final List<PiContentDeclaration> rendererDeclarations = new ArrayList<>();

    private PiBlockEntityBuilder(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name must not be blank");
        }
        this.name = name;
    }

    public static PiBlockEntityBuilder named(String name) {
        return new PiBlockEntityBuilder(name);
    }

    public PiBlockEntityBuilder validBlock(String blockId) {
        validBlocks.add(blockId);
        return this;
    }

    public PiBlockEntityBuilder serverTicker(String tickerId) {
        serverTickers.add(tickerId);
        return this;
    }

    public PiBlockEntityBuilder clientTicker(String tickerId) {
        clientTickers.add(tickerId);
        return this;
    }

    public PiBlockEntityBuilder menu(PiContentDeclaration menu) {
        menuDeclarations.add(menu);
        return this;
    }

    public PiBlockEntityBuilder saveLoad(PiContentDeclaration saveLoad) {
        this.saveLoad = saveLoad;
        return this;
    }

    public PiBlockEntityBuilder dirtySync(PiContentDeclaration dirtySync) {
        this.dirtySync = dirtySync;
        return this;
    }

    public PiBlockEntityBuilder capability(PiContentDeclaration capability) {
        capabilityDeclarations.add(capability);
        return this;
    }

    public PiBlockEntityBuilder renderer(PiContentDeclaration renderer) {
        rendererDeclarations.add(renderer);
        return this;
    }

    public PiBlockEntityPlan plan() {
        return new PiBlockEntityPlan(
                name,
                validBlocks,
                serverTickers,
                clientTickers,
                menuDeclarations,
                saveLoad,
                dirtySync,
                capabilityDeclarations,
                rendererDeclarations
        );
    }
}
