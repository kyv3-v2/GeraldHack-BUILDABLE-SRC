//Sponsored by pingbypasser#5926

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

public class ModeComponent extends SettingComponent
{
    public ModeSetting setting;
    
    public ModeComponent(final ModeSetting setting, final int x, final int y, final int width, final int height) {
        super(x, y, width, height);
        this.setting = setting;
        this.x = x;
        this.y = y;
        this.width = 100;
        this.height = 13;
    }
    
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        Gui.drawRect(this.x, this.y, this.x + this.width, this.y + this.height, new Color(Gerald.INSTANCE.moduleManager.getHack(ClickGui.class).colorSetting.r, Gerald.INSTANCE.moduleManager.getHack(ClickGui.class).colorSetting.g, Gerald.INSTANCE.moduleManager.getHack(ClickGui.class).colorSetting.b, 115).getRGB());
        Gui.drawRect(this.x, this.y, this.x + 1, this.y + this.height, Gerald.INSTANCE.moduleManager.getHack(ClickGui.class).colorSetting.getAsColor().getRGB());
        Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(this.setting.getName() + ":", (float)(this.x + 5), (float)(this.y + 2), -1);
        Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(ChatFormatting.GRAY + this.setting.getValueEnum().toString(), (float)(this.x + this.width - Minecraft.getMinecraft().fontRenderer.getStringWidth(this.setting.getValueEnum().toString()) - 1), (float)(this.y + 2), -1);
    }
    
    public void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) {
        if (this.isInside(mouseX, mouseY)) {
            if (mouseButton == 0) {
                this.setting.decrease();
            }
            else if (mouseButton == 1) {
                this.setting.increase();
            }
        }
    }
    
    public void mouseReleased(final int mouseX, final int mouseY, final int mouseButton) {
    }
    
    public void keyTyped(final char typedChar, final int key) {
    }
    
    public int getTotalHeight() {
        return this.height;
    }
    
    public boolean isVisible() {
        return this.setting.isVisible();
    }
}
