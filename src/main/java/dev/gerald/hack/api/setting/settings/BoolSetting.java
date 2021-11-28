//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\srgtmomentum\Desktop\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package dev.gerald.hack.api.setting.settings;

import dev.gerald.hack.api.setting.*;

public class BoolSetting extends Setting
{
    private boolean value;
    private boolean parent;
    
    public BoolSetting(final String name, final boolean value, final boolean parent) {
        super(name);
        this.value = value;
        this.parent = parent;
        this.visibility = null;
    }
    
    public BoolSetting(final String name, final boolean value, final boolean parent, final Visibility visibility) {
        super(name);
        this.value = value;
        this.parent = parent;
        this.visibility = visibility;
    }
    
    public boolean getValue() {
        return this.value;
    }
    
    public void setValue(final boolean value) {
        this.value = value;
    }
    
    public boolean isParent() {
        return this.parent;
    }
    
    public void toggle() {
        this.value = !this.value;
    }
}
