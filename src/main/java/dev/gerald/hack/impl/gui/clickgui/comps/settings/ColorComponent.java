//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\srgtmomentum\Desktop\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package dev.gerald.hack.impl.gui.clickgui.comps.settings;

import dev.gerald.hack.api.gui.*;
import dev.gerald.hack.api.setting.settings.*;
import dev.gerald.hack.impl.modules.client.*;
import dev.gerald.hack.*;
import java.awt.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.*;
import java.util.*;

public class ColorComponent extends SettingComponent
{
    public ColorSetting setting;
    public boolean open;
    public ArrayList<NumComponent> numComponents;
    
    public ColorComponent(final ColorSetting setting, final int x, final int y, final int width, final int height) {
        super(x, y, width, height);
        this.open = false;
        this.setting = setting;
        this.x = x;
        this.y = y;
        this.width = 100;
        this.height = 13;
        (this.numComponents = new ArrayList<NumComponent>()).add(new NumComponent(new NumSetting("Red", 255.0f, 0.0f, 255.0f), x, y, width, height));
        this.numComponents.add(new NumComponent(new NumSetting("Green", 255.0f, 0.0f, 255.0f), x, y, width, height));
        this.numComponents.add(new NumComponent(new NumSetting("Blue", 255.0f, 0.0f, 255.0f), x, y, width, height));
        this.numComponents.add(new NumComponent(new NumSetting("Alpha", 255.0f, 0.0f, 255.0f), x, y, width, height));
    }
    
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        int yOffset = this.height;
        Gui.drawRect(this.x, this.y, this.x + this.width, this.y + this.height, new Color(Gerald.INSTANCE.moduleManager.getHack(ClickGui.class).colorSetting.r, Gerald.INSTANCE.moduleManager.getHack(ClickGui.class).colorSetting.g, Gerald.INSTANCE.moduleManager.getHack(ClickGui.class).colorSetting.b, 115).getRGB());
        Gui.drawRect(this.x, this.y, this.x + 1, this.y + this.height, Gerald.INSTANCE.moduleManager.getHack(ClickGui.class).colorSetting.getAsColor().getRGB());
        Gui.drawRect(this.x + this.width - 12, this.y + 1, this.x + this.width - 2, this.y + 12, new Color(this.setting.getR(), this.setting.getG(), this.setting.getB(), 255).getRGB());
        Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(this.setting.getName() + ":", (float)(this.x + 5), (float)(this.y + 2), -1);
        if (this.open) {
            for (final SettingComponent component : this.numComponents) {
                component.x = this.x;
                component.y = this.y + yOffset;
                if (component.isVisible()) {
                    yOffset += component.getTotalHeight();
                }
                component.drawScreen(mouseX, mouseY, partialTicks);
            }
            this.setting.setR((int)this.numComponents.get(0).setting.getValue());
            this.setting.setG((int)this.numComponents.get(1).setting.getValue());
            this.setting.setB((int)this.numComponents.get(2).setting.getValue());
            this.setting.setA((int)this.numComponents.get(3).setting.getValue());
        }
    }
    
    public void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) {
        if (this.isInside(mouseX, mouseY) && mouseButton == 1) {
            this.open = !this.open;
        }
        if (this.open) {
            for (final SettingComponent component : this.numComponents) {
                component.mouseClicked(mouseX, mouseY, mouseButton);
            }
        }
    }
    
    public void mouseReleased(final int mouseX, final int mouseY, final int mouseButton) {
        if (this.open) {
            for (final SettingComponent component : this.numComponents) {
                component.mouseReleased(mouseX, mouseY, mouseButton);
            }
        }
    }
    
    public void keyTyped(final char typedChar, final int key) {
    }
    
    public int getTotalHeight() {
        if (this.open) {
            int h = 0;
            for (final SettingComponent component : this.numComponents) {
                if (component.isVisible()) {
                    h += component.getTotalHeight();
                }
            }
            return this.height + h;
        }
        return this.height;
    }
    
    public boolean isVisible() {
        return this.setting.isVisible();
    }
}
