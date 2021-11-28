//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\srgtmomentum\Desktop\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package dev.gerald.hack.impl.modules.player;

import dev.gerald.hack.api.module.*;
import dev.gerald.hack.api.setting.settings.*;
import dev.gerald.hack.api.setting.*;
import net.minecraftforge.client.event.*;
import net.minecraft.client.gui.*;
import com.mojang.realmsclient.gui.*;
import dev.gerald.hack.api.util.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class Respawn extends Module
{
    public NumSetting delay;
    public BoolSetting cancelScreen;
    TimerUtil timer;
    String deathCoords;
    boolean hasAnnounced;
    
    public String getMetaData() {
        return (this.deathCoords != null) ? ("[" + this.deathCoords + "]") : "";
    }
    
    public Respawn() {
        super("Respawn", Module.Category.PLAYER, "Automatically respawns for the player.");
        this.delay = (NumSetting)this.register((Setting)new NumSetting("Delay", 0.0f, 0.0f, 1000.0f));
        this.cancelScreen = (BoolSetting)this.register((Setting)new BoolSetting("CancelScreen", true, false));
        this.timer = new TimerUtil();
        this.hasAnnounced = false;
    }
    
    @SubscribeEvent
    public void onDeath(final GuiOpenEvent event) {
        if (event.getGui() instanceof GuiGameOver && this.timer.passedMs((long)this.delay.getValue())) {
            if (this.cancelScreen.getValue()) {
                event.setCanceled(true);
            }
            ChatFormatting formatting = ChatFormatting.GRAY;
            if (this.mc.player.dimension == -1) {
                formatting = ChatFormatting.RED;
            }
            else if (this.mc.player.dimension == 0) {
                formatting = ChatFormatting.GREEN;
            }
            else if (this.mc.player.dimension == 1) {
                formatting = ChatFormatting.LIGHT_PURPLE;
            }
            this.deathCoords = ChatFormatting.GRAY + "X:" + formatting + this.mc.player.getPosition().getX() + ChatFormatting.GRAY + " Y:" + formatting + this.mc.player.getPosition().getY() + ChatFormatting.GRAY + " Z:" + formatting + this.mc.player.getPosition().getZ() + ChatFormatting.RESET;
            if (!this.hasAnnounced) {
                MessageUtil.sendClientMessage("Player has died at [" + this.deathCoords + ChatFormatting.RESET + "]");
            }
            this.hasAnnounced = true;
            this.mc.player.respawnPlayer();
            this.timer.reset();
            this.hasAnnounced = false;
        }
    }
}
