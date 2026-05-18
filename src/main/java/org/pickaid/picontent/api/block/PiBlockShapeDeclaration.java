package org.pickaid.picontent.api.block;

public record PiBlockShapeDeclaration(
        String preset,
        double minX,
        double minY,
        double minZ,
        double maxX,
        double maxY,
        double maxZ
) {
    public PiBlockShapeDeclaration {
        if (preset == null || preset.isBlank()) {
            throw new IllegalArgumentException("preset must not be blank");
        }
    }
}
