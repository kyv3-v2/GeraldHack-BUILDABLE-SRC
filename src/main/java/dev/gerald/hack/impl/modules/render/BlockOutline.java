//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\srgtmomentum\Desktop\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package dev.gerald.hack.impl.modules.render;

import dev.gerald.hack.api.module.*;
import dev.gerald.hack.api.setting.settings.*;
import dev.gerald.hack.api.setting.*;
import net.minecraftforge.client.event.*;
import net.minecraft.init.*;
import net.minecraft.world.*;
import dev.gerald.hack.api.util.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.*;
import net.minecraft.util.math.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class BlockOutline extends Module
{
    public ModeSetting renderMode;
    public ColorSetting color;
    public NumSetting lineWidth;
    
    public BlockOutline() {
        super("BlockOutline", Module.Category.RENDER, "Renders a box around the block your looking at.");
        this.renderMode = (ModeSetting)this.register((Setting)new ModeSetting("RenderMode", (Enum)RenderMode.Outline));
        this.color = (ColorSetting)this.register((Setting)new ColorSetting("Color", 0, 255, 183, 125));
        this.lineWidth = (NumSetting)this.register((Setting)new NumSetting("LineWidth", 1.0f, 0.0f, 3.0f));
    }
    
    @SubscribeEvent
    public void onRender(final RenderWorldLastEvent event) {
        if (this.nullCheck()) {
            return;
        }
        if (this.mc.objectMouseOver != null && this.mc.objectMouseOver.typeOfHit == RayTraceResult.Type.BLOCK && this.mc.world.getBlockState(this.mc.objectMouseOver.getBlockPos()).getBlock() != Blocks.AIR) {
            final AxisAlignedBB box = this.mc.world.getBlockState(this.mc.objectMouseOver.getBlockPos()).getSelectedBoundingBox((World)this.mc.world, this.mc.objectMouseOver.getBlockPos()).offset(-this.mc.getRenderManager().viewerPosX, -this.mc.getRenderManager().viewerPosY, -this.mc.getRenderManager().viewerPosZ);
            RenderUtil.prepare();
            GL11.glLineWidth(this.lineWidth.getValue());
            if (this.renderMode.getValueEnum() == RenderMode.Fill || this.renderMode.getValueEnum() == RenderMode.Both) {
                RenderGlobal.renderFilledBox(box.minX, box.minY, box.minZ, box.maxX, box.maxY, box.maxZ, this.color.r / 255.0f, this.color.g / 255.0f, this.color.b / 255.0f, this.color.a / 255.0f);
            }
            if (this.renderMode.getValueEnum() == RenderMode.Outline || this.renderMode.getValueEnum() == RenderMode.Both) {
                RenderGlobal.drawBoundingBox(box.minX, box.minY, box.minZ, box.maxX, box.maxY, box.maxZ, this.color.r / 255.0f, this.color.g / 255.0f, this.color.b / 255.0f, 1.0f);
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
