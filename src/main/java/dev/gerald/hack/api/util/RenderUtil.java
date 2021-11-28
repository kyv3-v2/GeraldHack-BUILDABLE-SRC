//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\srgtmomentum\Desktop\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package dev.gerald.hack.api.util;

import net.minecraft.client.renderer.*;
import org.lwjgl.opengl.*;
import java.awt.*;

public class RenderUtil
{
    public static void prepare() {
        GlStateManager.pushMatrix();
        GlStateManager.disableLighting();
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.enableAlpha();
        GlStateManager.disableCull();
        GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask(false);
        GL11.glEnable(2848);
        GL11.glEnable(34383);
        GL11.glHint(3154, 4354);
    }
    
    public static void release() {
        GL11.glDisable(2848);
        GL11.glDisable(34383);
        GlStateManager.depthMask(true);
        GlStateManager.enableCull();
        GlStateManager.enableDepth();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.enableLighting();
        GlStateManager.popMatrix();
    }
    
    public static Color genRainbow(final int delay) {
        final double rainbowState = Math.ceil((System.currentTimeMillis() + delay) / 20.0);
        return Color.getHSBColor((float)(rainbowState % 360.0 / 360.0), 1.0f, 1.0f);
    }
}
