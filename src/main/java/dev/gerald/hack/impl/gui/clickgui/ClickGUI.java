//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\srgtmomentum\Desktop\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package dev.gerald.hack.impl.gui.clickgui;

import net.minecraft.client.gui.*;
import dev.gerald.hack.impl.gui.clickgui.comps.*;
import dev.gerald.hack.api.module.*;
import java.util.*;
import java.io.*;

public class ClickGUI extends GuiScreen
{
    public ArrayList<CategoryComponent> components;
    
    public ClickGUI() {
        this.components = new ArrayList<CategoryComponent>();
        int offset = 40;
        for (final Module.Category category : Module.Category.values()) {
            this.components.add(new CategoryComponent(category, offset, 40, this.width, this.height));
            offset += 100;
        }
    }
    
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        for (final CategoryComponent component : this.components) {
            component.drawScreen(mouseX, mouseY, partialTicks);
        }
    }
    
    public void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        for (final CategoryComponent component : this.components) {
            component.mouseClicked(mouseX, mouseY, mouseButton);
        }
    }
    
    public void mouseReleased(final int mouseX, final int mouseY, final int mouseButton) {
        super.mouseReleased(mouseX, mouseY, mouseButton);
        for (final CategoryComponent component : this.components) {
            component.mouseReleased(mouseX, mouseY, mouseButton);
        }
    }
    
    public void keyTyped(final char typedChar, final int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);
        for (final CategoryComponent component : this.components) {
            component.keyTyped(typedChar, keyCode);
        }
    }
    
    public boolean doesGuiPauseGame() {
        return false;
    }
}
