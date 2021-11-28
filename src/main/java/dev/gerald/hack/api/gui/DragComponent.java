//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\srgtmomentum\Desktop\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package dev.gerald.hack.api.gui;

public abstract class DragComponent extends AbstractComponent
{
    int dx;
    int dy;
    public boolean dragging;
    
    public DragComponent(final int x, final int y, final int width, final int height) {
        super(x, y, width, height);
        this.dragging = false;
    }
    
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        if (this.dragging) {
            this.x = mouseX - this.dx;
            this.y = mouseY - this.dy;
        }
    }
    
    public void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) {
        if (this.isInside(mouseX, mouseY) && mouseButton == 0) {
            this.dragging = true;
        }
    }
    
    public void mouseReleased(final int mouseX, final int mouseY, final int mouseButton) {
        this.dragging = false;
    }
    
    public void keyTyped(final char typedChar, final int keyCode) {
    }
}
