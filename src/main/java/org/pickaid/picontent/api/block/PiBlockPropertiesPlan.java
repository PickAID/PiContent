package org.pickaid.picontent.api.block;

public record PiBlockPropertiesPlan(String preset, int lightLevel, boolean occlusionDisabled) {
    public PiBlockPropertiesPlan {
        if (preset == null || preset.isBlank()) {
            throw new IllegalArgumentException("preset must not be blank");
        }
        if (lightLevel < 0 || lightLevel > 15) {
            throw new IllegalArgumentException("lightLevel must be between 0 and 15");
        }
    }

    public PiBlockPropertiesPlan lightLevel(int lightLevel) {
        return new PiBlockPropertiesPlan(preset, lightLevel, occlusionDisabled);
    }

    public PiBlockPropertiesPlan noOcclusion() {
        return new PiBlockPropertiesPlan(preset, lightLevel, true);
    }
}
