package org.pickaid.picontent.api.block;

public record PiBlockLootDeclaration(String preset) {
    public PiBlockLootDeclaration {
        if (preset == null || preset.isBlank()) {
            throw new IllegalArgumentException("preset must not be blank");
        }
    }
}
