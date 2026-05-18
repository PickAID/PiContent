package org.pickaid.picontent.api.blockentity;

public final class PiContentSync {
    private PiContentSync() {
    }

    public static PiContentDeclaration none() {
        return new PiContentDeclaration("sync", "none", "none");
    }

    public static PiContentDeclaration saveLoad(String id) {
        return new PiContentDeclaration("sync", "save_load", id);
    }

    public static PiContentDeclaration tracking(String id) {
        return new PiContentDeclaration("sync", "tracking", id);
    }
}
