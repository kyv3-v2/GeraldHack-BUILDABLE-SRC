//Sponsored by pingbypasser#5926

//Decompiled by Procyon!

package dev.gerald.hack.impl.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.gui.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import net.minecraft.client.*;
import com.mojang.realmsclient.gui.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ GuiMainMenu.class })
public class MixinGuiMainMenu
{
    @Inject(method = { "drawScreen" }, at = { @At("TAIL") })
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks, final CallbackInfo ci) {
        Minecraft.getMinecraft().fontRenderer.drawStringWithShadow("GeraldHack" + ChatFormatting.GRAY + " v" + "1.1", 1.0f, 1.0f, -1);
    }
}
