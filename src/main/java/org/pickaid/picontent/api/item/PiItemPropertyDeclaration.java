package org.pickaid.picontent.api.item;

public record PiItemPropertyDeclaration(String name, String preset) {
    public PiItemPropertyDeclaration {
        if (name == null) {
            throw new IllegalArgumentException("name must not be null");
        }
        if (preset == null || preset.isBlank()) {
            throw new IllegalArgumentException("preset must not be blank");
        }
    }

    PiItemPropertyDeclaration named(String name) {
        return new PiItemPropertyDeclaration(name, preset);
    }
}
