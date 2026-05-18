package org.pickaid.picontent.api.clientdecl;

public record PiClientDeclaration(String kind, String id) {
    public PiClientDeclaration {
        if (kind == null || kind.isBlank()) {
            throw new IllegalArgumentException("kind must not be blank");
        }
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("id must not be blank");
        }
    }

    public String key() {
        return kind + ":" + id;
    }
}
