//Sponsored by pingbypasser#5926

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
