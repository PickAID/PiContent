package org.pickaid.picontent.api.clientdecl;

public final class PiContentAvatarUse {
    private PiContentAvatarUse() {
    }

    public static PiClientDeclaration castPose(String poseId) {
        return new PiClientDeclaration("avatar_use", poseId);
    }
}
