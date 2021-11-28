//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\srgtmomentum\Desktop\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package dev.gerald.hack.impl.modules.combat;

import dev.gerald.hack.api.module.*;
import dev.gerald.hack.api.setting.settings.*;
import net.minecraft.entity.*;
import dev.gerald.hack.api.setting.*;
import com.mojang.realmsclient.gui.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraft.item.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.*;
import java.util.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraftforge.client.event.*;
import dev.gerald.hack.api.util.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.*;
import net.minecraft.util.math.*;
import net.minecraft.util.*;

public class KillAura extends Module
{
    public NumSetting range;
    public BoolSetting swordOnly;
    public BoolSetting entityParent;
    public BoolSetting players;
    public BoolSetting mobs;
    public BoolSetting animals;
    public BoolSetting renderParent;
    public ColorSetting color;
    public BoolSetting rainbow;
    public NumSetting rainbowSpeed;
    public NumSetting lineWidth;
    public Entity target;
    
    public KillAura() {
        super("KillAura", Module.Category.COMBAT, "Automatically attacks entities.");
        this.range = (NumSetting)this.register((Setting)new NumSetting("Range", 4.0f, 1.0f, 6.0f));
        this.swordOnly = (BoolSetting)this.register((Setting)new BoolSetting("SwordOnly", true, false));
        this.entityParent = (BoolSetting)this.register((Setting)new BoolSetting("Target", false, true));
        this.players = (BoolSetting)this.register((Setting)new BoolSetting("Players", true, false, () -> this.entityParent.getValue()));
        this.mobs = (BoolSetting)this.register((Setting)new BoolSetting("Mobs", true, false, () -> this.entityParent.getValue()));
        this.animals = (BoolSetting)this.register((Setting)new BoolSetting("Animals", true, false, () -> this.entityParent.getValue()));
        this.renderParent = (BoolSetting)this.register((Setting)new BoolSetting("Render", false, true));
        this.color = (ColorSetting)this.register((Setting)new ColorSetting("Color", 255, 255, 255, 125, () -> this.renderParent.getValue()));
        this.rainbow = (BoolSetting)this.register((Setting)new BoolSetting("Rainbow", true, false, () -> this.renderParent.getValue()));
        this.rainbowSpeed = (NumSetting)this.register((Setting)new NumSetting("RainbowSpeed", 6.0f, 1.0f, 10.0f, () -> this.renderParent.getValue() && this.rainbow.getValue()));
        this.lineWidth = (NumSetting)this.register((Setting)new NumSetting("LineWidth", 1.0f, 0.1f, 5.0f, () -> this.renderParent.getValue()));
        this.target = null;
    }
    
    public String getMetaData() {
        return "[" + ChatFormatting.GRAY + this.range.getValue() + ChatFormatting.RESET + "]";
    }
    
    @SubscribeEvent
    public void onUpdate(final TickEvent.PlayerTickEvent event) {
        if (this.target != null && (this.target.getDistance((Entity)this.mc.player) >= this.range.getValue() || this.target.isDead || !this.target.isEntityAlive())) {
            this.target = null;
        }
        for (final Entity entity : this.mc.world.loadedEntityList) {
            if (entity == this.mc.player) {
                continue;
            }
            if (entity.getDistance((Entity)this.mc.player) > this.range.getValue() || this.mc.player.getCooledAttackStrength(0.0f) < 1.0f) {
                continue;
            }
            if (entity.isDead) {
                continue;
            }
            if (!entity.isEntityAlive()) {
                continue;
            }
            if (!(this.mc.player.getHeldItemMainhand().getItem() instanceof ItemSword) && this.swordOnly.getValue()) {
                continue;
            }
            if (entity instanceof EntityPlayer && this.players.getValue()) {
                this.attack(entity);
            }
            if (entity instanceof EntityMob || (entity instanceof EntitySlime && this.mobs.getValue())) {
                this.attack(entity);
            }
            if (!(entity instanceof EntityAnimal) || !this.animals.getValue()) {
                continue;
            }
            this.attack(entity);
        }
    }
    
    @SubscribeEvent
    public void onRender(final RenderWorldLastEvent event) {
        if (this.target != null) {
            final AxisAlignedBB box = this.target.getRenderBoundingBox().offset(-this.mc.getRenderManager().viewerPosX, -this.mc.getRenderManager().viewerPosY, -this.mc.getRenderManager().viewerPosZ);
            final float r = (this.rainbow.getValue() ? RenderUtil.genRainbow((int)this.rainbowSpeed.getValue()).getRed() : this.color.r) / 255.0f;
            final float g = (this.rainbow.getValue() ? RenderUtil.genRainbow((int)this.rainbowSpeed.getValue()).getGreen() : this.color.g) / 255.0f;
            final float b = (this.rainbow.getValue() ? RenderUtil.genRainbow((int)this.rainbowSpeed.getValue()).getBlue() : this.color.b) / 255.0f;
            RenderUtil.prepare();
            GL11.glLineWidth(this.lineWidth.getValue());
            RenderGlobal.drawBoundingBox(box.minX, box.minY, box.minZ, box.maxX, box.maxY, box.maxZ, r, g, b, 1.0f);
            RenderGlobal.renderFilledBox(box.minX, box.minY, box.minZ, box.maxX, box.maxY, box.maxZ, r, g, b, this.color.a / 255.0f);
            RenderUtil.release();
        }
    }
    
    public void attack(final Entity entity) {
        this.mc.playerController.attackEntity((EntityPlayer)this.mc.player, entity);
        this.mc.player.swingArm(EnumHand.MAIN_HAND);
        this.target = entity;
    }
}
