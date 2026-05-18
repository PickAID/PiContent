package org.pickaid.picontent;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.common.Mod;

@Mod(PiContent.MOD_ID)
public final class PiContent {
    public static final String MOD_ID = "picontent";

    public PiContent() {
    }

    public static ResourceLocation id(String path) {
        return new ResourceLocation(MOD_ID, path);
    }
}
