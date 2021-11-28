//Sponsored by pingbypasser#5926

//Decompiled by Procyon!

package dev.gerald.hack.impl.gui.clickgui.comps;

import dev.gerald.hack.api.module.*;
import dev.gerald.hack.api.gui.*;
import dev.gerald.hack.api.setting.*;
import dev.gerald.hack.api.setting.settings.*;
import dev.gerald.hack.impl.gui.clickgui.comps.settings.*;
import java.util.*;
import dev.gerald.hack.impl.modules.client.*;
import dev.gerald.hack.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.*;
import dev.gerald.hack.impl.modules.description.*;
import com.mojang.realmsclient.gui.*;
import dev.gerald.hack.api.util.*;

public class ModuleComponent extends AbstractComponent
{
    public int x;
    public int y;
    public int width;
    public int height;
    public Module module;
    public ArrayList<SettingComponent> settingComponent;
    
    public ModuleComponent(final Module module, final int x, final int y, final int width, final int height) {
        super(x, y, width, height);
        this.module = module;
        this.x = x;
        this.y = y;
        this.width = 100;
        this.height = 13;
        this.settingComponent = new ArrayList<SettingComponent>();
        if (module.needsKeybindSetting()) {
            this.settingComponent.add(new BindComponent(this, x, y, width, height));
        }
        for (final Setting setting : module.getSettings()) {
            if (setting instanceof BoolSetting) {
                this.settingComponent.add(new BoolComponent((BoolSetting)setting, x, y, width, height));
            }
            else if (setting instanceof NumSetting) {
                this.settingComponent.add(new NumComponent((NumSetting)setting, x, y, width, height));
            }
            else if (setting instanceof ModeSetting) {
                this.settingComponent.add(new ModeComponent((ModeSetting)setting, x, y, width, height));
            }
            else {
                if (!(setting instanceof ColorSetting)) {
                    continue;
                }
                this.settingComponent.add(new ColorComponent((ColorSetting)setting, x, y, width, height));
            }
        }
    }
    
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        int yOffset = this.height;
        if (this.module.getCategory() == Module.Category.DESCRIPTION) {
            this.width = 200;
        }
        Gui.drawRect(this.x, this.y, this.x + this.width, this.y + this.height, this.module.isEnabled() ? Gerald.INSTANCE.moduleManager.getHack(ClickGui.class).colorSetting.getAsColor().getRGB() : -1879048192);
        Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(this.module.getName(), (float)(this.x + 2), (float)(this.y + 3), -1);
        if (this.module.getCategory() != Module.Category.DESCRIPTION && (this.module.hasSettings() || this.module.needsKeybindSetting())) {
            if (this.module.isOpen()) {
                Minecraft.getMinecraft().fontRenderer.drawStringWithShadow("-", (float)(this.x + this.width - Minecraft.getMinecraft().fontRenderer.getStringWidth("-") - 1), (float)(this.y + 3), -1);
                Gui.drawRect(this.x, this.y, this.x + 1, this.y + this.height, Gerald.INSTANCE.moduleManager.getHack(ClickGui.class).colorSetting.getAsColor().getRGB());
                for (final SettingComponent component : this.settingComponent) {
                    if (component.isVisible()) {
                        component.x = this.x;
                        component.y = this.y + yOffset;
                        if (component.isVisible()) {
                            yOffset += component.getTotalHeight();
                        }
                        component.drawScreen(mouseX, mouseY, partialTicks);
                    }
                }
            }
            else {
                Minecraft.getMinecraft().fontRenderer.drawStringWithShadow("+", (float)(this.x + this.width - Minecraft.getMinecraft().fontRenderer.getStringWidth("+") - 1), (float)(this.y + 3), -1);
            }
        }
        if (this.isInside(mouseX, mouseY)) {
            Gerald.INSTANCE.moduleManager.getHack(Description.class).setName(this.module.getDescription());
        }
    }
    
    public void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) {
        if (this.isInside(mouseX, mouseY)) {
            if (mouseButton == 0) {
                this.module.toggle();
            }
            else if (mouseButton == 1) {
                this.module.setOpen(!this.module.isOpen());
            }
            else if (mouseButton == 2) {
                this.module.setVisible(!this.module.isVisible());
                MessageUtil.sendClientMessage(ChatFormatting.GRAY + this.module.getName() + ChatFormatting.RESET + " visibility has been set to " + (this.module.isVisible() ? (ChatFormatting.GREEN + "True") : (ChatFormatting.RED + "False")));
            }
        }
        if (this.module.isOpen()) {
            for (final SettingComponent component : this.settingComponent) {
                if (component.isVisible()) {
                    component.mouseClicked(mouseX, mouseY, mouseButton);
                }
            }
        }
    }
    
    public void mouseReleased(final int mouseX, final int mouseY, final int mouseButton) {
        if (this.module.isOpen()) {
            for (final SettingComponent component : this.settingComponent) {
                if (component.isVisible()) {
                    component.mouseReleased(mouseX, mouseY, mouseButton);
                }
            }
        }
    }
    
    public void keyTyped(final char typedChar, final int keyCode) {
        if (this.module.isOpen()) {
            for (final SettingComponent component : this.settingComponent) {
                if (component.isVisible()) {
                    component.keyTyped(typedChar, keyCode);
                }
            }
        }
        Gerald.INSTANCE.configManager.saveModule(this.module);
    }
    
    public int getTotalHeight() {
        if (this.module.isOpen()) {
            int h = 0;
            for (final SettingComponent component : this.settingComponent) {
                if (component.isVisible()) {
                    h += component.getTotalHeight();
                }
            }
            return this.height + h;
        }
        return this.height;
    }
    
    public boolean isVisible() {
        return true;
    }
    
    public boolean isInside(final int mouseX, final int mouseY) {
        return mouseX > this.x && mouseX < this.x + this.width && mouseY > this.y && mouseY < this.y + this.height;
    }
}
