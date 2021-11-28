//Sponsored by pingbypasser#5926

//Decompiled by Procyon!

package dev.gerald.hack.impl.modules.movement;

import dev.gerald.hack.api.module.*;
import dev.gerald.hack.api.setting.settings.*;
import dev.gerald.hack.api.setting.*;
import com.mojang.realmsclient.gui.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class Sprint extends Module
{
    public ModeSetting mode;
    
    public Sprint() {
        super("Sprint", Module.Category.MOVEMENT, "Automatically sprints.");
        this.mode = (ModeSetting)this.register((Setting)new ModeSetting("Mode", (Enum)Mode.Rage));
    }
    
    public String getMetaData() {
        return "[" + ChatFormatting.GRAY + this.mode.getValueEnum() + ChatFormatting.RESET + "]";
    }
    
    @SubscribeEvent
    public void onUpdate(final TickEvent.PlayerTickEvent event) {
        if (this.nullCheck()) {
            return;
        }
        if (this.mode.getValueEnum() == Mode.Rage) {
            if (!this.mc.player.isSprinting()) {
                this.mc.player.setSprinting(true);
            }
        }
        else if (this.mode.getValueEnum() == Mode.Legit && ((!this.mc.player.collidedHorizontally && !this.mc.player.isSneaking() && this.mc.player.moveForward != 0.0f) || this.mc.player.moveStrafing != 0.0f) && !this.mc.player.isSprinting()) {
            this.mc.player.setSprinting(true);
        }
    }
    
    public enum Mode
    {
        Rage, 
        Legit;
    }
}
