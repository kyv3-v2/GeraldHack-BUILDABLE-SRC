//Sponsored by pingbypasser#5926

//Decompiled by Procyon!

package dev.gerald.hack.api.event;

import net.minecraftforge.common.*;
import net.minecraftforge.fml.common.gameevent.*;
import org.lwjgl.input.*;
import dev.gerald.hack.*;
import dev.gerald.hack.api.module.*;
import com.mojang.realmsclient.gui.*;
import java.util.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraftforge.client.event.*;
import net.minecraft.client.*;
import dev.gerald.hack.api.util.*;
import net.minecraftforge.fml.common.network.*;

public class EventListener
{
    public EventListener() {
        MinecraftForge.EVENT_BUS.register((Object)this);
    }
    
    @SubscribeEvent
    public void onKey(final InputEvent.KeyInputEvent event) {
        if (Keyboard.getEventKeyState()) {
            final int key = Keyboard.getEventKey();
            for (final Module module : Gerald.INSTANCE.moduleManager.getModules()) {
                if (module.getKeybind() == key) {
                    System.out.println("Attempting to toggle a module called " + ChatFormatting.GREEN + module.getName());
                    module.toggle();
                }
            }
        }
    }
    
    @SubscribeEvent
    public void onChatSent(final ClientChatEvent event) {
        if (event.getMessage().startsWith(Gerald.CLIENT_PREFIX)) {
            event.setCanceled(true);
            Minecraft.getMinecraft().ingameGUI.getChatGUI().addToSentMessages(event.getMessage());
            if (event.getMessage().length() > 1) {
                Gerald.INSTANCE.commandManager.executeCommand(event.getMessage().substring(Gerald.CLIENT_PREFIX.length() - 1));
            }
            else {
                MessageUtil.sendClientMessage("Please enter a command or do " + ChatFormatting.GRAY + Gerald.CLIENT_PREFIX + ChatFormatting.GREEN + "help " + ChatFormatting.RESET + "for more info.");
            }
        }
    }
    
    @SubscribeEvent
    public void onLogin(final FMLNetworkEvent.ClientConnectedToServerEvent event) {
        Gerald.INSTANCE.moduleManager.onLogin();
    }
    
    @SubscribeEvent
    public void onLogout(final FMLNetworkEvent.ClientDisconnectionFromServerEvent event) {
        Gerald.INSTANCE.moduleManager.onLogout();
    }
}
