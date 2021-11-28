//Sponsored by pingbypasser#5926

//Decompiled by Procyon!

package dev.gerald.hack.impl.modules.render;

import dev.gerald.hack.api.module.*;
import dev.gerald.hack.api.setting.settings.*;
import dev.gerald.hack.api.setting.*;
import net.minecraftforge.client.event.*;
import dev.gerald.hack.api.util.*;
import net.minecraft.world.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.*;
import java.util.*;
import net.minecraft.util.math.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.init.*;

public class HoleESP extends Module
{
    public NumSetting range;
    public NumSetting yToAdd;
    public BoolSetting colorParent;
    public ModeSetting renderMode;
    public ColorSetting safeColor;
    public ColorSetting unSafeColor;
    public NumSetting lineWidth;
    
    public HoleESP() {
        super("HoleESP", Module.Category.RENDER, "Renders holes.");
        this.range = (NumSetting)this.register((Setting)new NumSetting("Range", 12.0f, 0.0f, 30.0f));
        this.yToAdd = (NumSetting)this.register((Setting)new NumSetting("YToAdd", 0.0f, 0.0f, 4.0f));
        this.colorParent = (BoolSetting)this.register((Setting)new BoolSetting("Render", false, true));
        this.renderMode = (ModeSetting)this.register((Setting)new ModeSetting("RenderMode", (Enum)RenderMode.Outline, () -> this.colorParent.getValue()));
        this.safeColor = (ColorSetting)this.register((Setting)new ColorSetting("SafeColor", 0, 255, 183, 125, () -> this.colorParent.getValue()));
        this.unSafeColor = (ColorSetting)this.register((Setting)new ColorSetting("UnSafeColor", 0, 255, 183, 125, () -> this.colorParent.getValue()));
        this.lineWidth = (NumSetting)this.register((Setting)new NumSetting("LineWidth", 1.0f, 0.0f, 3.0f, () -> this.colorParent.getValue()));
    }
    
    @SubscribeEvent
    public void onRender(final RenderWorldLastEvent event) {
        for (final BlockPos pos : PlayerUtil.getSphere(this.mc.player.getPosition(), this.range.getValue(), false)) {
            RenderUtil.prepare();
            final AxisAlignedBB box = this.mc.world.getBlockState(pos).getSelectedBoundingBox((World)this.mc.world, pos).offset(-this.mc.getRenderManager().viewerPosX, -this.mc.getRenderManager().viewerPosY, -this.mc.getRenderManager().viewerPosZ);
            if (this.isSafeHole(pos)) {
                GL11.glLineWidth(this.lineWidth.getValue());
                if (this.renderMode.getValueEnum() == RenderMode.Fill || this.renderMode.getValueEnum() == RenderMode.Both) {
                    RenderGlobal.renderFilledBox(box.minX, box.minY, box.minZ, box.maxX, box.maxY + this.yToAdd.getValue(), box.maxZ, this.safeColor.r / 255.0f, this.safeColor.g / 255.0f, this.safeColor.b / 255.0f, this.safeColor.a / 255.0f);
                }
                if (this.renderMode.getValueEnum() == RenderMode.Outline || this.renderMode.getValueEnum() == RenderMode.Both) {
                    RenderGlobal.drawBoundingBox(box.minX, box.minY, box.minZ, box.maxX, box.maxY + this.yToAdd.getValue(), box.maxZ, this.safeColor.r / 255.0f, this.safeColor.g / 255.0f, this.safeColor.b / 255.0f, 1.0f);
                }
            }
            else if (this.isUnSafeHole(pos)) {
                GL11.glLineWidth(this.lineWidth.getValue());
                if (this.renderMode.getValueEnum() == RenderMode.Fill || this.renderMode.getValueEnum() == RenderMode.Both) {
                    RenderGlobal.renderFilledBox(box.minX, box.minY, box.minZ, box.maxX, box.maxY + this.yToAdd.getValue(), box.maxZ, this.unSafeColor.r / 255.0f, this.unSafeColor.g / 255.0f, this.unSafeColor.b / 255.0f, this.unSafeColor.a / 255.0f);
                }
                if (this.renderMode.getValueEnum() == RenderMode.Outline || this.renderMode.getValueEnum() == RenderMode.Both) {
                    RenderGlobal.drawBoundingBox(box.minX, box.minY, box.minZ, box.maxX, box.maxY + this.yToAdd.getValue(), box.maxZ, this.unSafeColor.r / 255.0f, this.unSafeColor.g / 255.0f, this.unSafeColor.b / 255.0f, 1.0f);
                }
            }
            RenderUtil.release();
        }
    }
    
    public boolean isSafeHole(final BlockPos pos) {
        return this.mc.world.getBlockState(pos).getBlock() == Blocks.AIR && this.mc.world.getBlockState(pos.down()).getBlock() == Blocks.BEDROCK && this.mc.world.getBlockState(pos.up()).getBlock() == Blocks.AIR && this.mc.world.getBlockState(pos.north()).getBlock() == Blocks.BEDROCK && this.mc.world.getBlockState(pos.south()).getBlock() == Blocks.BEDROCK && this.mc.world.getBlockState(pos.west()).getBlock() == Blocks.BEDROCK && this.mc.world.getBlockState(pos.east()).getBlock() == Blocks.BEDROCK;
    }
    
    public boolean isUnSafeHole(final BlockPos pos) {
        return this.mc.world.getBlockState(pos).getBlock() == Blocks.AIR && (this.mc.world.getBlockState(pos.down()).getBlock() == Blocks.BEDROCK || this.mc.world.getBlockState(pos.down()).getBlock() == Blocks.OBSIDIAN) && this.mc.world.getBlockState(pos.up()).getBlock() == Blocks.AIR && (this.mc.world.getBlockState(pos.north()).getBlock() == Blocks.BEDROCK || this.mc.world.getBlockState(pos.north()).getBlock() == Blocks.OBSIDIAN) && (this.mc.world.getBlockState(pos.south()).getBlock() == Blocks.BEDROCK || this.mc.world.getBlockState(pos.south()).getBlock() == Blocks.OBSIDIAN) && (this.mc.world.getBlockState(pos.west()).getBlock() == Blocks.BEDROCK || this.mc.world.getBlockState(pos.west()).getBlock() == Blocks.OBSIDIAN) && (this.mc.world.getBlockState(pos.east()).getBlock() == Blocks.BEDROCK || this.mc.world.getBlockState(pos.east()).getBlock() == Blocks.OBSIDIAN);
    }
    
    public enum RenderMode
    {
        Fill, 
        Outline, 
        Both;
    }
}
