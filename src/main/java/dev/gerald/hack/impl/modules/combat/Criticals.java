//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\srgtmomentum\Desktop\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package dev.gerald.hack.impl.modules.combat;

import dev.gerald.hack.api.module.*;
import dev.gerald.hack.api.setting.settings.*;
import dev.gerald.hack.api.setting.*;
import com.mojang.realmsclient.gui.*;
import net.minecraftforge.event.entity.player.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import net.minecraft.client.entity.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class Criticals extends Module
{
    public ModeSetting criticalMode;
    
    public Criticals() {
        super("Criticals", Module.Category.COMBAT, "Jumps for you.");
        this.criticalMode = (ModeSetting)this.register((Setting)new ModeSetting("Mode", (Enum)Mode.Packet));
    }
    
    public String getMetaData() {
        return "[" + ChatFormatting.GRAY + this.criticalMode.getValueEnum().toString() + ChatFormatting.RESET + "]";
    }
    
    @SubscribeEvent
    public void onAttack(final AttackEntityEvent event) {
        if (this.mc.player.isInWater() || this.mc.player.isInLava()) {
            return;
        }
        if (this.mc.player.onGround) {
            if (this.criticalMode.getValueEnum() == Mode.Packet) {
                this.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(this.mc.player.posX, this.mc.player.posY + 0.1625, this.mc.player.posZ, false));
                this.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(this.mc.player.posX, this.mc.player.posY, this.mc.player.posZ, false));
            }
            else {
                this.mc.player.jump();
                if (this.criticalMode.getValueEnum() == Mode.MiniJump) {
                    final EntityPlayerSP player = this.mc.player;
                    player.motionY /= 2.0;
                }
            }
            this.mc.player.onCriticalHit(event.getTarget());
        }
    }
    
    public enum Mode
    {
        Packet, 
        Jump, 
        MiniJump;
    }
}
