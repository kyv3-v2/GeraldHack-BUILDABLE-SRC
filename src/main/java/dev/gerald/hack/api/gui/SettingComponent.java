//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\srgtmomentum\Desktop\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package dev.gerald.hack.api.gui;

public abstract class SettingComponent extends AbstractComponent
{
    public SettingComponent(final int x, final int y, final int width, final int height) {
        super(x, y, width, height);
    }
    
    public abstract int getTotalHeight();
    
    public abstract boolean isVisible();
}
