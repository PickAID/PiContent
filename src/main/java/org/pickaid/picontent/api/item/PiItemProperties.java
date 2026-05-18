package org.pickaid.picontent.api.item;

public final class PiItemProperties {
    private PiItemProperties() {
    }

    public static PiItemPropertyDeclaration using() {
        return new PiItemPropertyDeclaration("", "using");
    }
}
