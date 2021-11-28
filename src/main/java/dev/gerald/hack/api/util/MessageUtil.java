//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\srgtmomentum\Desktop\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package dev.gerald.hack.api.util;

import net.minecraft.client.*;
import net.minecraft.util.text.*;
import com.mojang.realmsclient.gui.*;

public class MessageUtil
{
    static String clientPrefix;
    
    public static void sendRawMessage(final String message) {
        if (Minecraft.getMinecraft().player != null) {
            Minecraft.getMinecraft().player.sendMessage((ITextComponent)new TextComponentString(message));
        }
    }
    
    public static void sendClientMessage(final String message) {
        sendRawMessage(MessageUtil.clientPrefix + message);
    }
    
    public static void sendRemovableMessage(final String message, final int id, final boolean watermark) {
        if (Minecraft.getMinecraft().player != null) {
            Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessageWithOptionalDeletion((ITextComponent)new TextComponentString(watermark ? (MessageUtil.clientPrefix + message) : message), id);
        }
    }
    
    static {
        MessageUtil.clientPrefix = ChatFormatting.GRAY + "[" + ChatFormatting.AQUA + "GeraldHack" + ChatFormatting.GRAY + "] " + ChatFormatting.RESET;
    }
}
