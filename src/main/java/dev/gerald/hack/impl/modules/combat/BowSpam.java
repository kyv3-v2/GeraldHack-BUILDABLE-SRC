//Sponsored by pingbypasser#5926

//Decompiled by Procyon!

package dev.gerald.hack.impl.modules.combat;

import dev.gerald.hack.api.module.*;
import dev.gerald.hack.api.setting.settings.*;
import dev.gerald.hack.api.setting.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraft.init.*;
import net.minecraft.util.math.*;
import net.minecraft.network.*;
import net.minecraft.network.play.client.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class BowSpam extends Module
{
    public NumSetting delay;
    
    public BowSpam() {
        super("BowSpam", Module.Category.COMBAT, "2019 shit.");
        this.delay = (NumSetting)this.register((Setting)new NumSetting("Delay", 0.1f, 0.0f, 6.0f));
    }
    
    @SubscribeEvent
    public void onUseItem(final TickEvent.PlayerTickEvent event) {
        if (this.nullCheck()) {
            return;
        }
        if (this.mc.player.getHeldItemMainhand().getItem() == Items.BOW && this.mc.player.isHandActive() && this.mc.player.getItemInUseMaxCount() >= 3 + (int)this.delay.getValue()) {
            this.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, this.mc.player.getHorizontalFacing()));
            this.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItem(this.mc.player.getActiveHand()));
            this.mc.player.stopActiveHand();
        }
    }
}
