package org.pickaid.picontent.api.block;

public final class PiBlockPresets {
    private PiBlockPresets() {
    }

    public static PiBlockPropertiesPlan stone() {
        return new PiBlockPropertiesPlan("stone", 0, false);
    }

    public static PiBlockPropertiesPlan stoneMachine() {
        return new PiBlockPropertiesPlan("stone_machine", 0, false);
    }
}
