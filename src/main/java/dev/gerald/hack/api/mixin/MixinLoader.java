//Sponsored by pingbypasser#5926

//Decompiled by Procyon!

package dev.gerald.hack.api.mixin;

import net.minecraftforge.fml.relauncher.*;
import javax.annotation.*;
import java.util.*;
import org.spongepowered.asm.launch.*;
import org.spongepowered.asm.mixin.*;

@IFMLLoadingPlugin.Name("Gerald MIXINS")
@IFMLLoadingPlugin.MCVersion("1.12.2")
public final class MixinLoader implements IFMLLoadingPlugin
{
    public String[] getASMTransformerClass() {
        return new String[0];
    }
    
    public String getModContainerClass() {
        return null;
    }
    
    @Nullable
    public String getSetupClass() {
        return null;
    }
    
    public void injectData(final Map<String, Object> data) {
        MixinBootstrap.init();
        Mixins.addConfiguration("mixins.gerald.json");
        MixinEnvironment.getDefaultEnvironment().setObfuscationContext("searge");
    }
    
    public String getAccessTransformerClass() {
        return null;
    }
}
