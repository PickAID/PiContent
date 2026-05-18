package org.pickaid.picontent.api.item;

public final class PiItemPresets {
    private PiItemPresets() {
    }

    public static PiItemPropertiesPlan material() {
        return PiItemPropertiesPlan.material();
    }

    public static PiItemPropertiesPlan staff() {
        return new PiItemPropertiesPlan("staff", 1, false);
    }
}
