package org.pickaid.picontent.api.block;

public final class PiBlockPlacement {
    private PiBlockPlacement() {
    }

    public static PiBlockPlacementDeclaration none() {
        return new PiBlockPlacementDeclaration("none");
    }

    public static PiBlockPlacementDeclaration horizontal() {
        return new PiBlockPlacementDeclaration("horizontal");
    }
}
