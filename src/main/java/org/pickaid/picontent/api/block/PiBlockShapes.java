package org.pickaid.picontent.api.block;

public final class PiBlockShapes {
    private PiBlockShapes() {
    }

    public static PiBlockShapeDeclaration fullCube() {
        return box16(0, 0, 0, 16, 16, 16);
    }

    public static PiBlockShapeDeclaration box16(
            double minX,
            double minY,
            double minZ,
            double maxX,
            double maxY,
            double maxZ
    ) {
        return new PiBlockShapeDeclaration("box16", minX, minY, minZ, maxX, maxY, maxZ);
    }
}
