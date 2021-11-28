//Sponsored by pingbypasser#5926

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
import java.util.*;
import net.minecraft.util.math.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class VoidESP extends Module
{
    public NumSetting range;
    public NumSetting yToAdd;
    public BoolSetting colorParent;
    public ModeSetting renderMode;
    public ColorSetting color;
    public NumSetting lineWidth;
    
    public VoidESP() {
        super("VoidESP", Module.Category.RENDER, "Renders void holes.");
        this.range = (NumSetting)this.register((Setting)new NumSetting("Range", 12.0f, 0.0f, 30.0f));
        this.yToAdd = (NumSetting)this.register((Setting)new NumSetting("YToAdd", 0.0f, 0.0f, 4.0f));
        this.colorParent = (BoolSetting)this.register((Setting)new BoolSetting("Render", false, true));
        this.renderMode = (ModeSetting)this.register((Setting)new ModeSetting("RenderMode", (Enum)RenderMode.Outline, () -> this.colorParent.getValue()));
        this.color = (ColorSetting)this.register((Setting)new ColorSetting("Color", 0, 255, 183, 125, () -> this.colorParent.getValue()));
        this.lineWidth = (NumSetting)this.register((Setting)new NumSetting("LineWidth", 1.0f, 0.0f, 3.0f, () -> this.colorParent.getValue()));
    }
    
    @SubscribeEvent
    public void onRender(final RenderWorldLastEvent event) {
        if (this.nullCheck()) {
            return;
        }
        for (final BlockPos pos : PlayerUtil.getSphere(this.mc.player.getPosition(), this.range.getValue(), false)) {
            if (pos.getY() == 0 && this.mc.world.getBlockState(pos).getBlock() == Blocks.AIR) {
                final AxisAlignedBB box = this.mc.world.getBlockState(pos).getSelectedBoundingBox((World)this.mc.world, pos).offset(-this.mc.getRenderManager().viewerPosX, -this.mc.getRenderManager().viewerPosY, -this.mc.getRenderManager().viewerPosZ);
                RenderUtil.prepare();
                GL11.glLineWidth(this.lineWidth.getValue());
                if (this.renderMode.getValueEnum() == RenderMode.Fill || this.renderMode.getValueEnum() == RenderMode.Both) {
                    RenderGlobal.renderFilledBox(box.minX, box.minY, box.minZ, box.maxX, box.maxY + this.yToAdd.getValue(), box.maxZ, this.color.r / 255.0f, this.color.g / 255.0f, this.color.b / 255.0f, this.color.a / 255.0f);
                }
                if (this.renderMode.getValueEnum() == RenderMode.Outline || this.renderMode.getValueEnum() == RenderMode.Both) {
                    RenderGlobal.drawBoundingBox(box.minX, box.minY, box.minZ, box.maxX, box.maxY + this.yToAdd.getValue(), box.maxZ, this.color.r / 255.0f, this.color.g / 255.0f, this.color.b / 255.0f, 1.0f);
                }
                RenderUtil.release();
            }
        }
    }
    
    public enum RenderMode
    {
        Fill, 
        Outline, 
        Both;
    }
}
