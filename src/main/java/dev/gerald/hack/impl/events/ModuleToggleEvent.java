//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\srgtmomentum\Desktop\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package dev.gerald.hack.impl.events;

import net.minecraftforge.fml.common.eventhandler.*;
import dev.gerald.hack.api.module.*;

public class ModuleToggleEvent extends Event
{
    public Module module;
    
    public ModuleToggleEvent(final Module module) {
        this.module = module;
    }
    
    public static class Enable extends ModuleToggleEvent
    {
        public Enable(final Module module) {
            super(module);
        }
        
        public Module getModule() {
            return this.module;
        }
    }
    
    public static class Disable extends ModuleToggleEvent
    {
        public Disable(final Module module) {
            super(module);
        }
        
        public Module getModule() {
            return this.module;
        }
    }
}
