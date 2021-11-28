//Sponsored by pingbypasser#5926

//Decompiled by Procyon!

package dev.gerald.hack.impl.modules.misc;

import dev.gerald.hack.api.module.*;
import dev.gerald.hack.api.setting.settings.*;
import net.minecraft.client.entity.*;
import dev.gerald.hack.api.setting.*;
import com.mojang.authlib.*;
import net.minecraft.world.*;
import net.minecraft.entity.*;
import com.mojang.realmsclient.gui.*;
import dev.gerald.hack.api.util.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class FakePlayer extends Module
{
    public BoolSetting autoRespawn;
    public NumSetting distanceToRespawn;
    EntityOtherPlayerMP fakePlayer;
    
    public FakePlayer() {
        super("FakePlayer", Module.Category.MISC, "For testing purposes.");
        this.autoRespawn = (BoolSetting)this.register((Setting)new BoolSetting("AutoRespawn", true, false));
        this.distanceToRespawn = (NumSetting)this.register((Setting)new NumSetting("DistanceToRespawn", 8.0f, 0.0f, 15.0f, () -> this.autoRespawn.getValue()));
    }
    
    public void onEnable() {
        this.fakePlayer = new EntityOtherPlayerMP((World)this.mc.world, new GameProfile(this.mc.player.getUniqueID(), "DooDooFart"));
        this.mc.world.spawnEntity((Entity)this.fakePlayer);
        this.fakePlayer.copyLocationAndAnglesFrom((Entity)this.mc.player);
        this.fakePlayer.rotationYawHead = this.mc.player.rotationYawHead;
        MessageUtil.sendClientMessage(ChatFormatting.GREEN + "Added" + ChatFormatting.RESET + " new Fake Player to the world.");
    }
    
    @SubscribeEvent
    public void onUpdate(final TickEvent.ClientTickEvent event) {
        if (this.fakePlayer != null) {
            this.fakePlayer.inventory = this.mc.player.inventory;
            if (this.autoRespawn.getValue() && this.fakePlayer.getDistance((Entity)this.mc.player) >= this.distanceToRespawn.getValue()) {
                this.fakePlayer.copyLocationAndAnglesFrom((Entity)this.mc.player);
                this.fakePlayer.rotationYawHead = this.mc.player.rotationYawHead;
                MessageUtil.sendClientMessage(ChatFormatting.GOLD + "Teleported" + ChatFormatting.RESET + " Fake Player to player.");
            }
        }
    }
    
    public void onDisable() {
        if (this.fakePlayer != null) {
            this.mc.world.removeEntity((Entity)this.fakePlayer);
            MessageUtil.sendClientMessage(ChatFormatting.RED + "Removed" + ChatFormatting.RESET + " Fake Player from the world");
        }
    }
}
