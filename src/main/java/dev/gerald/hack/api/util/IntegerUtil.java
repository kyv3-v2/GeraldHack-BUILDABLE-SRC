//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\srgtmomentum\Desktop\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package dev.gerald.hack.api.util;

public class IntegerUtil
{
    public int value;
    public int defaultValue;
    
    public IntegerUtil(final int value) {
        this.defaultValue = value;
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public void setValue(final int value) {
        this.value = value;
    }
    
    public void increase(final int count) {
        for (int i = 0; i < count; ++i) {
            ++this.value;
        }
    }
    
    public void decrease(final int count) {
        for (int i = 0; i < count; ++i) {
            --this.value;
        }
    }
    
    public void reset() {
        this.value = this.defaultValue;
    }
    
    public void zero() {
        this.value = 0;
    }
}
