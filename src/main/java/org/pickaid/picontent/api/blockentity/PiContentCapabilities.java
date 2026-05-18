package org.pickaid.picontent.api.blockentity;

public final class PiContentCapabilities {
    private PiContentCapabilities() {
    }

    public static PiContentDeclaration itemHandler(String id) {
        return new PiContentDeclaration("capability", "item_handler", id);
    }
}
