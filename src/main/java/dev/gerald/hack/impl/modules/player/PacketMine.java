//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\srgtmomentum\Desktop\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package dev.gerald.hack.impl.modules.player;

import dev.gerald.hack.api.module.*;
import dev.gerald.hack.api.setting.settings.*;
import dev.gerald.hack.api.setting.*;
import net.minecraftforge.event.entity.player.*;
import net.minecraft.init.*;
import net.minecraft.network.play.client.*;
import net.minecraft.util.*;
import java.util.*;
import net.minecraft.network.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraftforge.client.event.*;
import net.minecraft.util.math.*;
import dev.gerald.hack.api.util.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.*;
import net.minecraft.world.*;

public class PacketMine extends Module
{
    public BoolSetting pickaxeOnly;
    public BoolSetting colorParent;
    public ModeSetting renderMode;
    public ColorSetting color;
    public NumSetting lineWidth;
    public BlockPos targetPos;
    
    public PacketMine() {
        super("PacketMine", Module.Category.PLAYER, "Breaks shit.");
        this.pickaxeOnly = (BoolSetting)this.register((Setting)new BoolSetting("PickaxeOnly", true, false));
        this.colorParent = (BoolSetting)this.register((Setting)new BoolSetting("Render", false, true));
        this.renderMode = (ModeSetting)this.register((Setting)new ModeSetting("RenderMode", (Enum)RenderMode.Outline, () -> this.colorParent.getValue()));
        this.color = (ColorSetting)this.register((Setting)new ColorSetting("Color", 0, 255, 183, 125, () -> this.colorParent.getValue()));
        this.lineWidth = (NumSetting)this.register((Setting)new NumSetting("LineWidth", 1.0f, 0.0f, 3.0f, () -> this.colorParent.getValue()));
        this.targetPos = null;
    }
    
    @SubscribeEvent
    public void onClick(final PlayerInteractEvent.LeftClickBlock event) {
        if (this.targetPos != null && this.mc.world.getBlockState(this.targetPos).getBlock() == Blocks.AIR) {
            this.targetPos = null;
        }
        if (this.canBreak(event.getPos())) {
            if (this.pickaxeOnly.getValue() && this.mc.player.getHeldItemMainhand().getItem() != Items.DIAMOND_PICKAXE) {
                return;
            }
            this.mc.player.swingArm(EnumHand.MAIN_HAND);
            this.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, event.getPos(), (EnumFacing)Objects.requireNonNull(event.getFace())));
            this.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, event.getPos(), event.getFace()));
            this.targetPos = event.getPos();
        }
    }
    
    @SubscribeEvent
    public void onRender(final RenderWorldLastEvent event) {
        if (this.targetPos != null) {
            final AxisAlignedBB box = new AxisAlignedBB(this.targetPos).offset(-this.mc.getRenderManager().viewerPosX, -this.mc.getRenderManager().viewerPosY, -this.mc.getRenderManager().viewerPosZ);
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
    
    public boolean canBreak(final BlockPos pos) {
        return this.mc.world.getBlockState(pos).getBlock() != Blocks.AIR && this.mc.world.getBlockState(pos).getBlockHardness((World)this.mc.world, pos) != -1.0f;
    }
    
    public enum RenderMode
    {
        Fill, 
        Outline, 
        Both;
    }
}
