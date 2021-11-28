//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\srgtmomentum\Desktop\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package dev.gerald.hack.api.gui;

public abstract class AbstractComponent extends Rect
{
    public AbstractComponent(final int x, final int y, final int width, final int height) {
        super(x, y, width, height);
    }
    
    public abstract void drawScreen(final int p0, final int p1, final float p2);
    
    public abstract void mouseClicked(final int p0, final int p1, final int p2);
    
    public abstract void mouseReleased(final int p0, final int p1, final int p2);
    
    public abstract void keyTyped(final char p0, final int p1);
    
    public abstract boolean isVisible();
    
    public abstract int getTotalHeight();
    
    public boolean isInside(final int mouseX, final int mouseY) {
        return mouseX > this.x && mouseX < this.x + this.width && mouseY > this.y && mouseY < this.y + this.height;
    }
}
