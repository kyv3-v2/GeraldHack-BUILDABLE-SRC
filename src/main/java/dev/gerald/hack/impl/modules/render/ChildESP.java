//Sponsored by pingbypasser#5926

//Decompiled by Procyon!

package dev.gerald.hack.impl.modules.render;

import dev.gerald.hack.api.module.*;
import dev.gerald.hack.api.setting.settings.*;
import net.minecraft.entity.*;
import dev.gerald.hack.api.setting.*;
import net.minecraftforge.fml.common.gameevent.*;
import com.mojang.realmsclient.gui.*;
import dev.gerald.hack.api.util.*;
import net.minecraft.entity.passive.*;
import java.util.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class ChildESP extends Module
{
    public BoolSetting villager;
    public BoolSetting animal;
    public List<Entity> children;
    
    public ChildESP() {
        super("ChildESP", Module.Category.RENDER, "Makes children glow.");
        this.villager = (BoolSetting)this.register((Setting)new BoolSetting("Villager", true, false));
        this.animal = (BoolSetting)this.register((Setting)new BoolSetting("Animal", true, false));
        this.children = new ArrayList<Entity>();
    }
    
    @SubscribeEvent
    public void onUpdate(final TickEvent event) {
        if (this.nullCheck()) {
            return;
        }
        for (final Entity entity : this.mc.world.loadedEntityList) {
            if (entity instanceof EntityVillager && this.villager.getValue()) {
                final EntityVillager villager = (EntityVillager)entity;
                if (this.children.contains(villager) && villager.getGrowingAge() == 1) {
                    this.children.remove(villager);
                    MessageUtil.sendClientMessage("OH NO A CHILD GREW UP AT " + ChatFormatting.GRAY + "[X:" + villager.getPos().getX() + " Y:" + villager.getPos().getY() + " Z:" + villager.getPos().getZ() + "]");
                    villager.setGlowing(false);
                }
                if (villager.getGrowingAge() < 1 && !this.children.contains(villager)) {
                    this.children.add((Entity)villager);
                    MessageUtil.sendClientMessage("CHILD SPOTTED AT " + ChatFormatting.GRAY + "[X:" + villager.getPos().getX() + " Y:" + villager.getPos().getY() + " Z:" + villager.getPos().getZ() + "]");
                    villager.setGlowing(true);
                }
            }
            if (entity instanceof EntityAnimal && this.animal.getValue()) {
                final EntityAnimal animal = (EntityAnimal)entity;
                if (this.children.contains(animal) && animal.getGrowingAge() == 1) {
                    this.children.remove(animal);
                    MessageUtil.sendClientMessage("OH NO A CHILD GREW UP AT " + ChatFormatting.GRAY + "[X:" + animal.getPosition().getX() + " Y:" + animal.getPosition().getY() + " Z:" + animal.getPosition().getZ() + "]");
                    animal.setGlowing(false);
                }
                if (animal.getGrowingAge() >= 1 || this.children.contains(animal)) {
                    continue;
                }
                this.children.add((Entity)animal);
                MessageUtil.sendClientMessage("CHILD SPOTTED AT " + ChatFormatting.GRAY + "[X:" + animal.getPosition().getX() + " Y:" + animal.getPosition().getY() + " Z:" + animal.getPosition().getZ() + "]");
                animal.setGlowing(true);
            }
        }
    }
}
