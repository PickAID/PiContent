package org.pickaid.picontent.api.item;

public record PiItemModelDeclaration(String preset, String texture) {
    public PiItemModelDeclaration {
        if (preset == null || preset.isBlank()) {
            throw new IllegalArgumentException("preset must not be blank");
        }
        if (texture == null) {
            throw new IllegalArgumentException("texture must not be null");
        }
    }
}
