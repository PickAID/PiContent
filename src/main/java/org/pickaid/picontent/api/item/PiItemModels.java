package org.pickaid.picontent.api.item;

public final class PiItemModels {
    private PiItemModels() {
    }

    public static PiItemModelDeclaration generated(String texture) {
        return new PiItemModelDeclaration("generated", texture);
    }

    public static PiItemModelDeclaration handheld(String texture) {
        return new PiItemModelDeclaration("handheld", texture);
    }
}
