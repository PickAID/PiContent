package org.pickaid.picontent.api.block;

public record PiBlockPlacementDeclaration(String preset) {
    public PiBlockPlacementDeclaration {
        if (preset == null || preset.isBlank()) {
            throw new IllegalArgumentException("preset must not be blank");
        }
    }
}
