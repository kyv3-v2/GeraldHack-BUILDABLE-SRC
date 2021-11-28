//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\srgtmomentum\Desktop\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package dev.gerald.hack.impl.modules.player;

import dev.gerald.hack.api.module.*;
import dev.gerald.hack.api.setting.settings.*;
import dev.gerald.hack.api.setting.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraft.client.network.*;
import java.util.*;
import com.mojang.realmsclient.gui.*;
import net.minecraft.network.play.server.*;
import net.minecraft.util.text.*;
import net.minecraft.init.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class Log extends Module
{
    public NumSetting healthToLog;
    public BoolSetting weakness;
    
    public Log() {
        super("Log", Module.Category.PLAYER, "Logs for you.");
        this.healthToLog = (NumSetting)this.register((Setting)new NumSetting("HealthToLog", 10.0f, 0.0f, 36.0f));
        this.weakness = (BoolSetting)this.register((Setting)new BoolSetting("Weakness", true, false));
    }
    
    @SubscribeEvent
    public void onUpdate(final TickEvent.PlayerTickEvent event) {
        if (this.mc.player.isCreative()) {
            return;
        }
        if (this.mc.player.getHealth() + this.mc.player.getAbsorptionAmount() <= this.healthToLog.getValue()) {
            Objects.requireNonNull(this.mc.getConnection()).handleDisconnect(new SPacketDisconnect((ITextComponent)new TextComponentString("Logged at " + ChatFormatting.GRAY + "[" + (this.mc.player.getHealth() + this.mc.player.getAbsorptionAmount()) + "]")));
            this.toggle();
        }
        if (this.mc.player.isPotionActive(MobEffects.WEAKNESS) && this.weakness.getValue()) {
            Objects.requireNonNull(this.mc.getConnection()).handleDisconnect(new SPacketDisconnect((ITextComponent)new TextComponentString("Logged because you got " + ChatFormatting.DARK_GRAY + "WEAKNESSED" + ChatFormatting.RESET + " ew!")));
            this.toggle();
        }
    }
}
