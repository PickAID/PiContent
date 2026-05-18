package org.pickaid.picontent.api.datagen;

public record PiLootDeclaration(String name, String preset) {
    public PiLootDeclaration {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name must not be blank");
        }
        if (preset == null || preset.isBlank()) {
            throw new IllegalArgumentException("preset must not be blank");
        }
    }
}
