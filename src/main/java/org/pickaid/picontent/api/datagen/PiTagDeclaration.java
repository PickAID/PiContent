package org.pickaid.picontent.api.datagen;

public record PiTagDeclaration(String kind, String tag, String entryName) {
    public PiTagDeclaration {
        if (kind == null || kind.isBlank()) {
            throw new IllegalArgumentException("kind must not be blank");
        }
        if (tag == null || tag.isBlank()) {
            throw new IllegalArgumentException("tag must not be blank");
        }
        if (entryName == null || entryName.isBlank()) {
            throw new IllegalArgumentException("entryName must not be blank");
        }
    }
}
