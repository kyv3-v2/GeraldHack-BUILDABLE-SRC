//Sponsored by pingbypasser#5926

//Decompiled by Procyon!

package dev.gerald.hack.api.util;

import club.minnced.discord.rpc.*;
import net.minecraft.client.*;

public class DiscordUtil
{
    public static final DiscordRPC discordRPC;
    public static final DiscordRichPresence discordRichPresence;
    
    public static void start() {
        final DiscordEventHandlers eventHandlers = new DiscordEventHandlers();
        eventHandlers.disconnected = ((var1, var2) -> System.out.println("Discord RPC disconnected, var1: " + var1 + ", var2: " + var2));
        final String discordID = "908941447355580457";
        DiscordUtil.discordRPC.Discord_Initialize(discordID, eventHandlers, true, (String)null);
        DiscordUtil.discordRichPresence.startTimestamp = System.currentTimeMillis() / 1000L;
        final String ip = (Minecraft.getMinecraft().getCurrentServerData() != null) ? Minecraft.getMinecraft().getCurrentServerData().serverIP : "SinglePlayer";
        DiscordUtil.discordRichPresence.details = "Playing [" + ip + "]";
        DiscordUtil.discordRichPresence.largeImageKey = "big_nigga";
        DiscordUtil.discordRichPresence.largeImageText = "Im the biggest nigga.";
        DiscordUtil.discordRichPresence.smallImageKey = "gerald_head";
        DiscordUtil.discordRichPresence.smallImageText = "Join the discord! https://discord.gg/H2cTCe7q6Y";
        DiscordUtil.discordRichPresence.state = null;
        DiscordUtil.discordRPC.Discord_UpdatePresence(DiscordUtil.discordRichPresence);
    }
    
    public static void stop() {
        DiscordUtil.discordRPC.Discord_Shutdown();
        DiscordUtil.discordRPC.Discord_ClearPresence();
    }
    
    static {
        discordRPC = DiscordRPC.INSTANCE;
        discordRichPresence = new DiscordRichPresence();
    }
}
