package org.pickaid.picontent.api.block;

public final class PiBlockLoot {
    private PiBlockLoot() {
    }

    public static PiBlockLootDeclaration self() {
        return new PiBlockLootDeclaration("self");
    }
}
