//Sponsored by pingbypasser#5926

//Decompiled by Procyon!

package dev.gerald.hack.impl.gui.clickgui.comps.settings;

import dev.gerald.hack.api.gui.*;
import dev.gerald.hack.impl.gui.clickgui.comps.*;
import dev.gerald.hack.impl.modules.client.*;
import dev.gerald.hack.*;
import java.awt.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.*;
import com.mojang.realmsclient.gui.*;
import org.lwjgl.input.*;

public class BindComponent extends SettingComponent
{
    public ModuleComponent module;
    public boolean listening;
    
    public BindComponent(final ModuleComponent module, final int x, final int y, final int width, final int height) {
        super(x, y, width, height);
        this.listening = false;
        this.module = module;
        this.x = x;
        this.y = y;
        this.width = 100;
        this.height = 13;
    }
    
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        Gui.drawRect(this.x, this.y, this.x + this.width, this.y + this.height, new Color(Gerald.INSTANCE.moduleManager.getHack(ClickGui.class).colorSetting.r, Gerald.INSTANCE.moduleManager.getHack(ClickGui.class).colorSetting.g, Gerald.INSTANCE.moduleManager.getHack(ClickGui.class).colorSetting.b, 115).getRGB());
        Gui.drawRect(this.x, this.y, this.x + 1, this.y + this.height, Gerald.INSTANCE.moduleManager.getHack(ClickGui.class).colorSetting.getAsColor().getRGB());
        Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(this.listening ? "Listening... " : "Keybind:", (float)(this.x + 5), (float)(this.y + 2), -1);
        if (!this.listening) {
            Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(ChatFormatting.GRAY + Keyboard.getKeyName(this.module.module.getKeybind()), (float)(this.x + this.width - Minecraft.getMinecraft().fontRenderer.getStringWidth(Keyboard.getKeyName(this.module.module.getKeybind())) - 1), (float)(this.y + 2), -1);
        }
    }
    
    public void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) {
        if (this.isInside(mouseX, mouseY) && mouseButton == 0) {
            this.listening = !this.listening;
        }
    }
    
    public void mouseReleased(final int mouseX, final int mouseY, final int mouseButton) {
    }
    
    public void keyTyped(final char typedChar, final int key) {
        if (this.listening) {
            this.listening = false;
            if (key == 211 || key == 14) {
                this.module.module.setKeybind(0);
                return;
            }
            this.module.module.setKeybind(key);
        }
    }
    
    public int getTotalHeight() {
        return this.height;
    }
    
    public boolean isVisible() {
        return true;
    }
}
