package org.pickaid.picontent.api.datagen;

public record PiModelDeclaration(String kind, String name, String preset, String model) {
    public PiModelDeclaration {
        if (kind == null || kind.isBlank()) {
            throw new IllegalArgumentException("kind must not be blank");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name must not be blank");
        }
        if (preset == null || preset.isBlank()) {
            throw new IllegalArgumentException("preset must not be blank");
        }
        if (model == null) {
            throw new IllegalArgumentException("model must not be null");
        }
    }
}
