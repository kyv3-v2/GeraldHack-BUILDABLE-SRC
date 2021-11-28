//Sponsored by pingbypasser#5926

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
