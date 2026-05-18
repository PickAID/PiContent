package org.pickaid.picontent.api.block;

public final class PiBlockModels {
    private PiBlockModels() {
    }

    public static PiBlockModelDeclaration simple(String model) {
        return new PiBlockModelDeclaration("simple", model);
    }

    public static PiBlockModelDeclaration horizontal(String model) {
        return new PiBlockModelDeclaration("horizontal", model);
    }
}
