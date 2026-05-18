package org.pickaid.picontent.api.block;

public record PiBlockModelDeclaration(String preset, String model) {
    public PiBlockModelDeclaration {
        if (preset == null || preset.isBlank()) {
            throw new IllegalArgumentException("preset must not be blank");
        }
        if (model == null) {
            throw new IllegalArgumentException("model must not be null");
        }
    }
}
