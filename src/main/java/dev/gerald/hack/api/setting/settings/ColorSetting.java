//Sponsored by pingbypasser#5926

//Decompiled by Procyon!

package dev.gerald.hack.api.setting.settings;

import dev.gerald.hack.api.setting.*;
import java.awt.*;

public class ColorSetting extends Setting
{
    public int r;
    public int g;
    public int b;
    public int a;
    
    public ColorSetting(final String name, final int r, final int g, final int b, final int a) {
        super(name);
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }
    
    public ColorSetting(final String name, final int r, final int g, final int b, final int a, final Visibility visibility) {
        super(name, visibility);
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }
    
    public Color getAsColor() {
        return new Color(this.r, this.g, this.b, this.a);
    }
    
    public int getR() {
        return this.r;
    }
    
    public void setR(final int r) {
        this.r = r;
    }
    
    public int getG() {
        return this.g;
    }
    
    public void setG(final int g) {
        this.g = g;
    }
    
    public int getB() {
        return this.b;
    }
    
    public void setB(final int b) {
        this.b = b;
    }
    
    public int getA() {
        return this.a;
    }
    
    public void setA(final int a) {
        this.a = a;
    }
}
