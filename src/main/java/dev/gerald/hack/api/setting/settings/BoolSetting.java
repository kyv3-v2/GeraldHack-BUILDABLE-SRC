//Sponsored by pingbypasser#5926

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
