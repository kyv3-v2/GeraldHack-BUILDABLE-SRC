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
import com.mojang.realmsclient.gui.*;
import java.math.*;

public class NumComponent extends SettingComponent
{
    public NumSetting setting;
    public boolean dragging;
    public float sliderWidth;
    
    public NumComponent(final NumSetting setting, final int x, final int y, final int width, final int height) {
        super(x, y, width, height);
        this.dragging = false;
        this.setting = setting;
        this.x = x;
        this.y = y;
        this.width = 100;
        this.height = 13;
    }
    
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        this.updateSliderLogic(mouseX);
        Gui.drawRect(this.x, this.y, this.x + this.width, this.y + this.height, new Color(Gerald.INSTANCE.moduleManager.getHack(ClickGui.class).colorSetting.r, Gerald.INSTANCE.moduleManager.getHack(ClickGui.class).colorSetting.g, Gerald.INSTANCE.moduleManager.getHack(ClickGui.class).colorSetting.b, 115).getRGB());
        Gui.drawRect(this.x, this.y, this.x + (int)this.sliderWidth, this.y + this.height, new Color(Gerald.INSTANCE.moduleManager.getHack(ClickGui.class).colorSetting.r, Gerald.INSTANCE.moduleManager.getHack(ClickGui.class).colorSetting.g, Gerald.INSTANCE.moduleManager.getHack(ClickGui.class).colorSetting.b, 255).getRGB());
        Gui.drawRect(this.x, this.y, this.x + 1, this.y + this.height, Gerald.INSTANCE.moduleManager.getHack(ClickGui.class).colorSetting.getAsColor().getRGB());
        Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(this.setting.getName() + ":", (float)(this.x + 5), (float)(this.y + 2), -1);
        Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(ChatFormatting.GRAY + String.valueOf(this.setting.getValue()), (float)(this.x + this.width - Minecraft.getMinecraft().fontRenderer.getStringWidth(String.valueOf(this.setting.getValue())) - 1), (float)(this.y + 2), -1);
    }
    
    public void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) {
        if (this.isInside(mouseX, mouseY) && mouseButton == 0) {
            this.dragging = !this.dragging;
        }
    }
    
    public void mouseReleased(final int mouseX, final int mouseY, final int mouseButton) {
        if (this.dragging) {
            this.dragging = false;
        }
    }
    
    public void keyTyped(final char typedChar, final int key) {
    }
    
    public int getTotalHeight() {
        return this.height;
    }
    
    public boolean isVisible() {
        return this.setting.isVisible();
    }
    
    protected void updateSliderLogic(final int mouseX) {
        final float diff = (float)Math.min(this.width, Math.max(0, mouseX - this.x));
        final float min = this.setting.getMin();
        final float max = this.setting.getMax();
        this.sliderWidth = this.width * (this.setting.getValue() - min) / (max - min);
        if (this.dragging) {
            if (diff == 0.0f) {
                this.setting.setValue(this.setting.getMin());
            }
            else {
                final float value = roundToPlace(diff / this.width * (max - min) + min, 1);
                this.setting.setValue(value);
            }
        }
    }
    
    public static float roundToPlace(final float value, final int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.floatValue();
    }
}
