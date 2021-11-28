//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\srgtmomentum\Desktop\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package dev.gerald.hack.impl.modules.render;

import dev.gerald.hack.api.module.*;
import dev.gerald.hack.api.setting.settings.*;
import net.minecraft.entity.player.*;
import dev.gerald.hack.api.setting.*;
import net.minecraftforge.client.event.*;
import net.minecraft.entity.*;
import dev.gerald.hack.api.util.*;
import net.minecraft.client.renderer.*;
import net.minecraft.entity.item.*;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.*;
import java.util.*;
import net.minecraft.util.math.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class ESP extends Module
{
    public BoolSetting colorParent;
    public ModeSetting renderMode;
    public ColorSetting color;
    public NumSetting lineWidth;
    public BoolSetting containerParent;
    public BoolSetting players;
    public BoolSetting items;
    public BoolSetting mobs;
    public BoolSetting animals;
    public EntityPlayer player;
    
    public ESP() {
        super("ESP", Module.Category.RENDER, "Renders a box around entities.");
        this.colorParent = (BoolSetting)this.register((Setting)new BoolSetting("Render", false, true));
        this.renderMode = (ModeSetting)this.register((Setting)new ModeSetting("RenderMode", (Enum)RenderMode.Outline, () -> this.colorParent.getValue()));
        this.color = (ColorSetting)this.register((Setting)new ColorSetting("Color", 0, 255, 183, 125, () -> this.colorParent.getValue()));
        this.lineWidth = (NumSetting)this.register((Setting)new NumSetting("LineWidth", 1.0f, 0.0f, 3.0f, () -> this.colorParent.getValue()));
        this.containerParent = (BoolSetting)this.register((Setting)new BoolSetting("Entities", false, true));
        this.players = (BoolSetting)this.register((Setting)new BoolSetting("Players", true, false, () -> this.containerParent.getValue()));
        this.items = (BoolSetting)this.register((Setting)new BoolSetting("Items", true, false, () -> this.containerParent.getValue()));
        this.mobs = (BoolSetting)this.register((Setting)new BoolSetting("Mobs", true, false, () -> this.containerParent.getValue()));
        this.animals = (BoolSetting)this.register((Setting)new BoolSetting("Animals", true, false, () -> this.containerParent.getValue()));
    }
    
    @SubscribeEvent
    public void onRender(final RenderWorldLastEvent event) {
        for (final Entity e : this.mc.world.loadedEntityList) {
            if (e == this.mc.player) {
                continue;
            }
            AxisAlignedBB box = e.getRenderBoundingBox();
            box = box.offset(-this.mc.getRenderManager().viewerPosX, -this.mc.getRenderManager().viewerPosY, -this.mc.getRenderManager().viewerPosZ);
            RenderUtil.prepare();
            GlStateManager.glLineWidth(this.lineWidth.getValue());
            if (e instanceof EntityPlayer && this.players.getValue()) {
                if (this.renderMode.getValueEnum() == RenderMode.Fill || this.renderMode.getValueEnum() == RenderMode.Both) {
                    RenderGlobal.renderFilledBox(box.minX, box.minY, box.minZ, box.maxX, box.maxY, box.maxZ, this.color.r / 255.0f, this.color.g / 255.0f, this.color.b / 255.0f, this.color.a / 255.0f);
                }
                if (this.renderMode.getValueEnum() == RenderMode.Outline || this.renderMode.getValueEnum() == RenderMode.Both) {
                    RenderGlobal.drawBoundingBox(box.minX, box.minY, box.minZ, box.maxX, box.maxY, box.maxZ, this.color.r / 255.0f, this.color.g / 255.0f, this.color.b / 255.0f, 1.0f);
                }
            }
            if (e instanceof EntityItem && this.items.getValue()) {
                if (this.renderMode.getValueEnum() == RenderMode.Fill || this.renderMode.getValueEnum() == RenderMode.Both) {
                    RenderGlobal.renderFilledBox(box.minX, box.minY, box.minZ, box.maxX, box.maxY, box.maxZ, this.color.r / 255.0f, this.color.g / 255.0f, this.color.b / 255.0f, this.color.a / 255.0f);
                }
                if (this.renderMode.getValueEnum() == RenderMode.Outline || this.renderMode.getValueEnum() == RenderMode.Both) {
                    RenderGlobal.drawBoundingBox(box.minX, box.minY, box.minZ, box.maxX, box.maxY, box.maxZ, this.color.r / 255.0f, this.color.g / 255.0f, this.color.b / 255.0f, 1.0f);
                }
            }
            if (e instanceof EntityMob || (e instanceof EntitySlime && this.mobs.getValue())) {
                if (this.renderMode.getValueEnum() == RenderMode.Fill || this.renderMode.getValueEnum() == RenderMode.Both) {
                    RenderGlobal.renderFilledBox(box.minX, box.minY, box.minZ, box.maxX, box.maxY, box.maxZ, this.color.r / 255.0f, this.color.g / 255.0f, this.color.b / 255.0f, this.color.a / 255.0f);
                }
                if (this.renderMode.getValueEnum() == RenderMode.Outline || this.renderMode.getValueEnum() == RenderMode.Both) {
                    RenderGlobal.drawBoundingBox(box.minX, box.minY, box.minZ, box.maxX, box.maxY, box.maxZ, this.color.r / 255.0f, this.color.g / 255.0f, this.color.b / 255.0f, 1.0f);
                }
            }
            if (e instanceof EntityAnimal && this.animals.getValue()) {
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
