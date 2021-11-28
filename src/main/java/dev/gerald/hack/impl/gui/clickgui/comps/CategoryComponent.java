//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\srgtmomentum\Desktop\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package dev.gerald.hack.impl.gui.clickgui.comps;

import dev.gerald.hack.api.gui.*;
import dev.gerald.hack.api.module.*;
import dev.gerald.hack.*;
import java.util.*;
import dev.gerald.hack.impl.modules.client.*;
import net.minecraft.client.gui.*;
import com.mojang.realmsclient.gui.*;
import net.minecraft.client.*;

public class CategoryComponent extends DragComponent
{
    public int x;
    public int y;
    public int width;
    public int height;
    public Module.Category category;
    public boolean open;
    public ArrayList<ModuleComponent> components;
    int yOffset;
    
    public CategoryComponent(final Module.Category category, final int x, final int y, final int width, final int height) {
        super(x, y, width, height);
        this.open = true;
        this.yOffset = this.height;
        this.category = category;
        this.x = x;
        this.y = y;
        this.width = 100;
        this.height = 13;
        this.components = new ArrayList<ModuleComponent>();
        if (this.open) {
            for (final Module module : Gerald.INSTANCE.moduleManager.getModulesFromCategory(category)) {
                this.components.add(new ModuleComponent(module, x, y + this.yOffset, width, height));
            }
        }
    }
    
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        if (this.category == Module.Category.DESCRIPTION) {
            this.width = 200;
        }
        Gui.drawRect(this.x, this.y, this.x + this.width, this.y + this.height, Gerald.INSTANCE.moduleManager.getHack(ClickGui.class).colorSetting.getAsColor().getRGB());
        final String categoryName = (Gerald.INSTANCE.moduleManager.getHack(ClickGui.class).bold.getValue() ? ChatFormatting.BOLD : "") + this.category.name() + (Gerald.INSTANCE.moduleManager.getHack(ClickGui.class).moduleCounter.getValue() ? (" (" + ChatFormatting.GRAY + Gerald.INSTANCE.moduleManager.getModulesInCategoryInt(this.category) + ChatFormatting.WHITE + (Gerald.INSTANCE.moduleManager.getHack(ClickGui.class).bold.getValue() ? ChatFormatting.BOLD : "") + ")") : "");
        int xToRender;
        if (Gerald.INSTANCE.moduleManager.getHack(ClickGui.class).centerMode.getValueEnum() == ClickGui.CenterMode.Left) {
            xToRender = this.x + 1;
        }
        else if (Gerald.INSTANCE.moduleManager.getHack(ClickGui.class).centerMode.getValueEnum() == ClickGui.CenterMode.Center) {
            xToRender = (int)(this.x + this.width / 2.0f - Minecraft.getMinecraft().fontRenderer.getStringWidth(categoryName) / 2.0f);
        }
        else {
            xToRender = this.x + this.width - Minecraft.getMinecraft().fontRenderer.getStringWidth(categoryName) - 1;
        }
        Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(categoryName, (float)xToRender, (float)(this.y + 3), -1);
        int yOffset = this.height;
        if (this.open) {
            for (final ModuleComponent component : this.components) {
                component.x = this.x;
                component.y = this.y + yOffset;
                yOffset += component.getTotalHeight();
                component.drawScreen(mouseX, mouseY, partialTicks);
            }
        }
    }
    
    public void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) {
        if (this.isInside(mouseX, mouseY) && mouseButton == 1) {
            this.open = !this.open;
        }
        if (this.open) {
            for (final ModuleComponent component : this.components) {
                component.mouseClicked(mouseX, mouseY, mouseButton);
            }
        }
    }
    
    public void mouseReleased(final int mouseX, final int mouseY, final int mouseButton) {
        if (this.open) {
            for (final ModuleComponent component : this.components) {
                component.mouseReleased(mouseX, mouseY, mouseButton);
            }
        }
    }
    
    public void keyTyped(final char typedChar, final int keyCode) {
        if (this.open) {
            for (final ModuleComponent component : this.components) {
                component.keyTyped(typedChar, keyCode);
            }
        }
    }
    
    public boolean isVisible() {
        return true;
    }
    
    public int getTotalHeight() {
        return this.height;
    }
    
    public boolean isInside(final int mouseX, final int mouseY) {
        return mouseX > this.x && mouseX < this.x + this.width && mouseY > this.y && mouseY < this.y + this.height;
    }
}
