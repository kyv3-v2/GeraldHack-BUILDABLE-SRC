//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\srgtmomentum\Desktop\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package dev.gerald.hack.impl.modules.movement;

import dev.gerald.hack.api.module.*;
import dev.gerald.hack.impl.events.*;
import net.minecraft.network.play.server.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class Velocity extends Module
{
    public Velocity() {
        super("Velocity", Module.Category.MOVEMENT, "Cancels velocity packets.");
        this.setNeedsKeybindSetting(false);
    }
    
    @SubscribeEvent
    public void onUpdate(final PacketEvent.Receive event) {
        if (event.getPacket() instanceof SPacketExplosion || event.getPacket() instanceof SPacketEntityVelocity) {
            event.setCanceled(true);
        }
    }
}
