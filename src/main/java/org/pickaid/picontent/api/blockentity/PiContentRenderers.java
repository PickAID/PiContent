package org.pickaid.picontent.api.blockentity;

public final class PiContentRenderers {
    private PiContentRenderers() {
    }

    public static PiContentDeclaration blockEntity(String id) {
        return new PiContentDeclaration("renderer", "block_entity", id);
    }
}
