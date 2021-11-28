//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\srgtmomentum\Desktop\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package dev.gerald.hack.impl.gui;

import dev.gerald.hack.*;
import net.minecraftforge.common.*;
import net.minecraftforge.client.event.*;
import net.minecraft.client.*;
import dev.gerald.hack.api.module.*;
import java.util.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class HUD
{
    public Gerald main;
    
    public HUD(final Gerald main) {
        this.main = main;
        MinecraftForge.EVENT_BUS.register((Object)this);
    }
    
    @SubscribeEvent
    public void onRender(final RenderGameOverlayEvent.Text event) {
        int yOffset = 4;
        Minecraft.getMinecraft().fontRenderer.drawStringWithShadow("GeraldHack", 1.0f, 1.0f, -1);
        for (final Module module : Gerald.INSTANCE.moduleManager.getModules()) {
            if (module.isEnabled() && module.isVisible()) {
                Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(module.getName() + module.getMetaData(), 1.0f, (float)(Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT + yOffset), -1);
                yOffset += Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT + 2;
            }
        }
    }
}
