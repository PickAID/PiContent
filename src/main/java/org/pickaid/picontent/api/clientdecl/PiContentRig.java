package org.pickaid.picontent.api.clientdecl;

public final class PiContentRig {
    private PiContentRig() {
    }

    public static PiClientDeclaration geoItem(String rigId) {
        return new PiClientDeclaration("rig", rigId);
    }
}
