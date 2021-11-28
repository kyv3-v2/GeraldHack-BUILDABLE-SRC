//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\srgtmomentum\Desktop\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package dev.gerald.hack.impl.modules.movement;

import dev.gerald.hack.api.module.*;
import dev.gerald.hack.api.setting.settings.*;
import dev.gerald.hack.api.setting.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class ReverseStep extends Module
{
    public NumSetting stepSpeed;
    
    public ReverseStep() {
        super("ReverseStep", Module.Category.MOVEMENT, "Pulls the player down.");
        this.stepSpeed = (NumSetting)this.register((Setting)new NumSetting("StepSpeed", 9.0f, 1.0f, 9.0f));
    }
    
    public void onLogin() {
        if (this.isEnabled()) {
            this.toggle();
        }
    }
    
    @SubscribeEvent
    public void onUpdate(final TickEvent.PlayerTickEvent event) {
        if (this.nullCheck()) {
            return;
        }
        if (this.mc.player.onGround && !this.mc.player.isSneaking() && !this.mc.player.isInLava() && !this.mc.player.isInWater()) {
            this.mc.player.motionY = -this.stepSpeed.getValue();
        }
    }
}
