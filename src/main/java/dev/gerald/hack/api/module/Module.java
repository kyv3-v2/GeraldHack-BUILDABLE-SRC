//Sponsored by pingbypasser#5926

//Decompiled by Procyon!

package dev.gerald.hack.api.module;

import java.util.*;
import dev.gerald.hack.api.setting.*;
import net.minecraft.client.*;
import net.minecraftforge.common.*;
import dev.gerald.hack.impl.events.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class Module
{
    private String name;
    private final Category category;
    private final String description;
    private int keybind;
    private boolean needsKeybindSetting;
    private boolean isEnabled;
    private boolean open;
    private boolean visible;
    private final ArrayList<Setting> settings;
    public Minecraft mc;
    
    public Module(final String name, final Category category, final String desciption) {
        this.needsKeybindSetting = true;
        this.open = false;
        this.visible = true;
        this.settings = new ArrayList<Setting>();
        this.mc = Minecraft.getMinecraft();
        this.name = name;
        this.category = category;
        this.description = desciption;
        this.keybind = (this.getName().equalsIgnoreCase("ClickGui") ? 22 : 0);
    }
    
    public void toggle() {
        this.isEnabled = !this.isEnabled;
        if (this.isEnabled) {
            this.enable();
        }
        else {
            this.disable();
        }
    }
    
    public void enable() {
        MinecraftForge.EVENT_BUS.register((Object)this);
        MinecraftForge.EVENT_BUS.post((Event)new ModuleToggleEvent.Enable(this));
        this.onEnable();
    }
    
    public void disable() {
        MinecraftForge.EVENT_BUS.unregister((Object)this);
        MinecraftForge.EVENT_BUS.post((Event)new ModuleToggleEvent.Disable(this));
        this.onDisable();
    }
    
    public void onEnable() {
    }
    
    public void onDisable() {
    }
    
    public void onLogout() {
    }
    
    public void onLogin() {
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public Category getCategory() {
        return this.category;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public int getKeybind() {
        return this.keybind;
    }
    
    public void setKeybind(final int key) {
        this.keybind = key;
    }
    
    public boolean isEnabled() {
        return this.isEnabled;
    }
    
    public void setEnabled(final boolean status) {
        this.isEnabled = status;
    }
    
    public boolean isOpen() {
        return this.open;
    }
    
    public void setOpen(final boolean open) {
        this.open = open;
    }
    
    public boolean isVisible() {
        return this.visible;
    }
    
    public void setVisible(final boolean visible) {
        this.visible = visible;
    }
    
    public ArrayList<Setting> getSettings() {
        return this.settings;
    }
    
    public boolean hasSettings() {
        return !this.settings.isEmpty();
    }
    
    public boolean nullCheck() {
        return this.mc.player == null && this.mc.world == null;
    }
    
    public String getMetaData() {
        return "";
    }
    
    public boolean needsKeybindSetting() {
        return this.needsKeybindSetting;
    }
    
    public void setNeedsKeybindSetting(final boolean needsKeybindSetting) {
        this.needsKeybindSetting = needsKeybindSetting;
    }
    
    public <T extends Setting> T register(final T setting) {
        this.settings.add(setting);
        return setting;
    }
    
    public enum Category
    {
        COMBAT, 
        MOVEMENT, 
        RENDER, 
        PLAYER, 
        MISC, 
        CLIENT, 
        DESCRIPTION;
    }
}
