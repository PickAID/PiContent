package org.pickaid.picontent.api.item;

public record PiItemPropertiesPlan(String preset, int maxStackSize, boolean hidden) {
    public PiItemPropertiesPlan {
        if (preset == null || preset.isBlank()) {
            throw new IllegalArgumentException("preset must not be blank");
        }
        if (maxStackSize < 1) {
            throw new IllegalArgumentException("maxStackSize must be positive");
        }
    }

    public static PiItemPropertiesPlan material() {
        return new PiItemPropertiesPlan("material", 64, false);
    }
}
