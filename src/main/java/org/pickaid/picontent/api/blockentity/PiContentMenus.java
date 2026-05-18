package org.pickaid.picontent.api.blockentity;

public final class PiContentMenus {
    private PiContentMenus() {
    }

    public static PiContentDeclaration named(String id) {
        return new PiContentDeclaration("menu", "named", id);
    }
}
