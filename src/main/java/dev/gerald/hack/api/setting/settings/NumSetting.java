//Sponsored by pingbypasser#5926

//Decompiled by Procyon!

package dev.gerald.hack.api.setting.settings;

import dev.gerald.hack.api.setting.*;

public class NumSetting extends Setting
{
    private float value;
    private final float min;
    private final float max;
    
    public NumSetting(final String name, final float value, final float min, final float max) {
        super(name);
        this.value = value;
        this.min = min;
        this.max = max;
        this.visibility = null;
    }
    
    public NumSetting(final String name, final float value, final float min, final float max, final Visibility visibility) {
        super(name);
        this.value = value;
        this.min = min;
        this.max = max;
        this.visibility = visibility;
    }
    
    public void setValue(final float value) {
        this.value = value;
    }
    
    public float getValue() {
        return this.value;
    }
    
    public float getMin() {
        return this.min;
    }
    
    public float getMax() {
        return this.max;
    }
}
