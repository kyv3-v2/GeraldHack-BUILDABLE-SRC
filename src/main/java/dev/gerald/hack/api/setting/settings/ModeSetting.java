//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\srgtmomentum\Desktop\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package dev.gerald.hack.api.setting.settings;

import dev.gerald.hack.api.setting.*;

public class ModeSetting extends Setting
{
    private final Enum<?>[] constants;
    private int modeIndex;
    private final int defaultValueIndex;
    
    public ModeSetting(final String name, final Enum<?> defaultValue) {
        super(name);
        this.constants = (Enum<?>[])defaultValue.getDeclaringClass().getEnumConstants();
        final int index = this.indexOf(defaultValue);
        this.modeIndex = index;
        this.defaultValueIndex = index;
    }
    
    public ModeSetting(final String name, final Enum<?> defaultValue, final Visibility visibility) {
        super(name, visibility);
        this.constants = (Enum<?>[])defaultValue.getDeclaringClass().getEnumConstants();
        final int index = this.indexOf(defaultValue);
        this.modeIndex = index;
        this.defaultValueIndex = index;
    }
    
    public void increase() {
        if (this.modeIndex == this.constants.length - 1) {
            this.modeIndex = 0;
        }
        else {
            ++this.modeIndex;
        }
    }
    
    public void decrease() {
        if (this.modeIndex == 0) {
            this.modeIndex = this.constants.length - 1;
        }
        else {
            --this.modeIndex;
        }
    }
    
    public Enum<?> getValueEnum() {
        return this.constants[this.modeIndex];
    }
    
    public int getValueIndex() {
        return this.modeIndex;
    }
    
    public void setValueIndex(final int value) {
        this.modeIndex = value;
    }
    
    public int getDefaultValueIndex() {
        return this.defaultValueIndex;
    }
    
    private int indexOf(final Enum<?> value) {
        for (int i = 0; i < this.constants.length; ++i) {
            if (this.constants[i] == value) {
                return i;
            }
        }
        return -1;
    }
}
