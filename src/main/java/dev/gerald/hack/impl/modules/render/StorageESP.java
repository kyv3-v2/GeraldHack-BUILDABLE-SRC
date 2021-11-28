//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\srgtmomentum\Desktop\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package dev.gerald.hack.impl.modules.render;

import dev.gerald.hack.api.module.*;
import dev.gerald.hack.api.setting.settings.*;
import dev.gerald.hack.api.setting.*;
import net.minecraftforge.client.event.*;
import net.minecraft.util.math.*;
import dev.gerald.hack.api.util.*;
import net.minecraft.client.renderer.*;
import net.minecraft.tileentity.*;
import java.util.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class StorageESP extends Module
{
    public BoolSetting colorParent;
    public ModeSetting renderMode;
    public ColorSetting color;
    public NumSetting lineWidth;
    public BoolSetting containerParent;
    public BoolSetting chest;
    public BoolSetting enderChest;
    public BoolSetting hopper;
    
    public StorageESP() {
        super("StorageESP", Module.Category.RENDER, "Draws boxes around storage.");
        this.colorParent = (BoolSetting)this.register((Setting)new BoolSetting("Render", false, true));
        this.renderMode = (ModeSetting)this.register((Setting)new ModeSetting("RenderMode", (Enum)RenderMode.Outline, () -> this.colorParent.getValue()));
        this.color = (ColorSetting)this.register((Setting)new ColorSetting("Color", 0, 255, 183, 125, () -> this.colorParent.getValue()));
        this.lineWidth = (NumSetting)this.register((Setting)new NumSetting("LineWidth", 1.0f, 0.0f, 3.0f, () -> this.colorParent.getValue()));
        this.containerParent = (BoolSetting)this.register((Setting)new BoolSetting("Containers", false, true));
        this.chest = (BoolSetting)this.register((Setting)new BoolSetting("Chest", true, false, () -> this.containerParent.getValue()));
        this.enderChest = (BoolSetting)this.register((Setting)new BoolSetting("EnderChest", true, false, () -> this.containerParent.getValue()));
        this.hopper = (BoolSetting)this.register((Setting)new BoolSetting("Hopper", true, false, () -> this.containerParent.getValue()));
    }
    
    @SubscribeEvent
    public void onRender(final RenderWorldLastEvent event) {
        for (final TileEntity entity : this.mc.world.loadedTileEntityList) {
            AxisAlignedBB box = new AxisAlignedBB(entity.getPos());
            box = box.offset(-this.mc.getRenderManager().viewerPosX, -this.mc.getRenderManager().viewerPosY, -this.mc.getRenderManager().viewerPosZ);
            RenderUtil.prepare();
            GlStateManager.glLineWidth(this.lineWidth.getValue());
            if (entity instanceof TileEntityChest && this.chest.getValue()) {
                if (this.renderMode.getValueEnum() == RenderMode.Fill || this.renderMode.getValueEnum() == RenderMode.Both) {
                    RenderGlobal.renderFilledBox(box.minX, box.minY, box.minZ, box.maxX, box.maxY, box.maxZ, this.color.r / 255.0f, this.color.g / 255.0f, this.color.b / 255.0f, this.color.a / 255.0f);
                }
                if (this.renderMode.getValueEnum() == RenderMode.Outline || this.renderMode.getValueEnum() == RenderMode.Both) {
                    RenderGlobal.drawBoundingBox(box.minX, box.minY, box.minZ, box.maxX, box.maxY, box.maxZ, this.color.r / 255.0f, this.color.g / 255.0f, this.color.b / 255.0f, 1.0f);
                }
            }
            if (entity instanceof TileEntityEnderChest && this.enderChest.getValue()) {
                if (this.renderMode.getValueEnum() == RenderMode.Fill || this.renderMode.getValueEnum() == RenderMode.Both) {
                    RenderGlobal.renderFilledBox(box.minX, box.minY, box.minZ, box.maxX, box.maxY, box.maxZ, this.color.r / 255.0f, this.color.g / 255.0f, this.color.b / 255.0f, this.color.a / 255.0f);
                }
                if (this.renderMode.getValueEnum() == RenderMode.Outline || this.renderMode.getValueEnum() == RenderMode.Both) {
                    RenderGlobal.drawBoundingBox(box.minX, box.minY, box.minZ, box.maxX, box.maxY, box.maxZ, this.color.r / 255.0f, this.color.g / 255.0f, this.color.b / 255.0f, 1.0f);
                }
            }
            if (entity instanceof TileEntityHopper && this.hopper.getValue()) {
                if (this.renderMode.getValueEnum() == RenderMode.Fill || this.renderMode.getValueEnum() == RenderMode.Both) {
                    RenderGlobal.renderFilledBox(box.minX, box.minY, box.minZ, box.maxX, box.maxY, box.maxZ, this.color.r / 255.0f, this.color.g / 255.0f, this.color.b / 255.0f, this.color.a / 255.0f);
                }
                if (this.renderMode.getValueEnum() == RenderMode.Outline || this.renderMode.getValueEnum() == RenderMode.Both) {
                    RenderGlobal.drawBoundingBox(box.minX, box.minY, box.minZ, box.maxX, box.maxY, box.maxZ, this.color.r / 255.0f, this.color.g / 255.0f, this.color.b / 255.0f, 1.0f);
                }
            }
            RenderUtil.release();
        }
    }
    
    public enum RenderMode
    {
        Fill, 
        Outline, 
        Both;
    }
}
