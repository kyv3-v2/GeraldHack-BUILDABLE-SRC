//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\srgtmomentum\Desktop\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package dev.gerald.hack.api.setting;

public class Setting
{
    public String name;
    public Visibility visibility;
    
    public Setting(final String name) {
        this.name = name;
        this.visibility = null;
    }
    
    public Setting(final String name, final Visibility visibility) {
        this.name = name;
        this.visibility = visibility;
    }
    
    public String getName() {
        return this.name;
    }
    
    public boolean isVisible() {
        return this.visibility == null || this.visibility.visible();
    }
}
