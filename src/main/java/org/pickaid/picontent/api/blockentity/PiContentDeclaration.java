package org.pickaid.picontent.api.blockentity;

public record PiContentDeclaration(String kind, String channel, String id) {
    public PiContentDeclaration {
        if (kind == null || kind.isBlank()) {
            throw new IllegalArgumentException("kind must not be blank");
        }
        if (channel == null || channel.isBlank()) {
            throw new IllegalArgumentException("channel must not be blank");
        }
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("id must not be blank");
        }
    }

    public String key() {
        if ("menu".equals(kind) && "named".equals(channel)) {
            return kind + ":" + id;
        }
        return kind + ":" + channel + ":" + id;
    }
}
