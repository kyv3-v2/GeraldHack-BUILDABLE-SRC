//Sponsored by pingbypasser#5926

//Decompiled by Procyon!

package dev.gerald.hack.impl.modules.render;

import dev.gerald.hack.api.module.*;
import dev.gerald.hack.api.setting.settings.*;
import dev.gerald.hack.api.setting.*;
import net.minecraftforge.event.entity.living.*;
import net.minecraft.entity.effect.*;
import net.minecraft.world.*;
import net.minecraft.entity.*;
import net.minecraft.util.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.*;
import net.minecraft.init.*;

public class KillEffects extends Module
{
    public BoolSetting effectParent;
    public NumSetting timeActive;
    public BoolSetting lightning;
    public BoolSetting totemPop;
    public BoolSetting totemPopSound;
    public BoolSetting firework;
    public BoolSetting fire;
    public BoolSetting water;
    public BoolSetting smoke;
    public BoolSetting targetParent;
    public BoolSetting players;
    public BoolSetting animals;
    public BoolSetting mobs;
    public BoolSetting all;
    
    public KillEffects() {
        super("KillEffects", Module.Category.RENDER, "When you kill something it spawns shit.");
        this.effectParent = (BoolSetting)this.register((Setting)new BoolSetting("Effects", false, true));
        this.timeActive = (NumSetting)this.register((Setting)new NumSetting("TimeActive", 20.0f, 0.0f, 50.0f, () -> this.effectParent.getValue()));
        this.lightning = (BoolSetting)this.register((Setting)new BoolSetting("Lightning", false, false, () -> this.effectParent.getValue()));
        this.totemPop = (BoolSetting)this.register((Setting)new BoolSetting("TotemPop", true, false, () -> this.effectParent.getValue()));
        this.totemPopSound = (BoolSetting)this.register((Setting)new BoolSetting("TotemPopSound", false, false, () -> this.effectParent.getValue() && this.totemPop.getValue()));
        this.firework = (BoolSetting)this.register((Setting)new BoolSetting("FireWork", false, false, () -> this.effectParent.getValue()));
        this.fire = (BoolSetting)this.register((Setting)new BoolSetting("Fire", false, false, () -> this.effectParent.getValue()));
        this.water = (BoolSetting)this.register((Setting)new BoolSetting("Water", false, false, () -> this.effectParent.getValue()));
        this.smoke = (BoolSetting)this.register((Setting)new BoolSetting("Smoke", false, false, () -> this.effectParent.getValue()));
        this.targetParent = (BoolSetting)this.register((Setting)new BoolSetting("Targets", false, true));
        this.players = (BoolSetting)this.register((Setting)new BoolSetting("Players", true, false, () -> this.targetParent.getValue()));
        this.animals = (BoolSetting)this.register((Setting)new BoolSetting("Animals", true, false, () -> this.targetParent.getValue()));
        this.mobs = (BoolSetting)this.register((Setting)new BoolSetting("Mobs", true, false, () -> this.targetParent.getValue()));
        this.all = (BoolSetting)this.register((Setting)new BoolSetting("All", false, false, () -> this.targetParent.getValue()));
    }
    
    @SubscribeEvent
    public void onDeath(final LivingDeathEvent event) {
        if (event.getEntity() == this.mc.player) {
            return;
        }
        if (this.shouldRenderParticle(event.getEntity())) {
            if (this.lightning.getValue()) {
                this.mc.world.addEntityToWorld(-999, (Entity)new EntityLightningBolt((World)this.mc.world, event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ, true));
            }
            if (this.totemPop.getValue()) {
                this.totemPop(event.getEntity());
            }
            if (this.firework.getValue()) {
                this.mc.effectRenderer.emitParticleAtEntity(event.getEntity(), EnumParticleTypes.FIREWORKS_SPARK, (int)this.timeActive.getValue());
            }
            if (this.fire.getValue()) {
                this.mc.effectRenderer.emitParticleAtEntity(event.getEntity(), EnumParticleTypes.FLAME, (int)this.timeActive.getValue());
                this.mc.effectRenderer.emitParticleAtEntity(event.getEntity(), EnumParticleTypes.DRIP_LAVA, (int)this.timeActive.getValue());
            }
            if (this.water.getValue()) {
                this.mc.effectRenderer.emitParticleAtEntity(event.getEntity(), EnumParticleTypes.WATER_BUBBLE, (int)this.timeActive.getValue());
                this.mc.effectRenderer.emitParticleAtEntity(event.getEntity(), EnumParticleTypes.WATER_DROP, (int)this.timeActive.getValue());
            }
            if (this.smoke.getValue()) {
                this.mc.effectRenderer.emitParticleAtEntity(event.getEntity(), EnumParticleTypes.SMOKE_NORMAL, (int)this.timeActive.getValue());
            }
        }
    }
    
    public boolean shouldRenderParticle(final Entity entity) {
        return entity != this.mc.player && (this.all.getValue() || (entity instanceof EntityPlayer && this.players.getValue()) || (entity instanceof EntityMob || (entity instanceof EntitySlime && this.mobs.getValue())) || (entity instanceof EntityAnimal && this.animals.getValue()));
    }
    
    public void totemPop(final Entity entity) {
        this.mc.effectRenderer.emitParticleAtEntity(entity, EnumParticleTypes.TOTEM, (int)this.timeActive.getValue());
        if (this.totemPopSound.getValue()) {
            this.mc.world.playSound(entity.posX, entity.posY, entity.posZ, SoundEvents.ITEM_TOTEM_USE, entity.getSoundCategory(), 1.0f, 1.0f, false);
        }
    }
}
