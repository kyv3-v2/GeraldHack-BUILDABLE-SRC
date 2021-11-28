//Sponsored by pingbypasser#5926

//Decompiled by Procyon!

package dev.gerald.hack.impl.modules;

import dev.gerald.hack.api.module.*;
import dev.gerald.hack.impl.modules.client.*;
import dev.gerald.hack.impl.modules.combat.*;
import dev.gerald.hack.impl.modules.description.*;
import dev.gerald.hack.impl.modules.misc.*;
import dev.gerald.hack.impl.modules.movement.*;
import dev.gerald.hack.impl.modules.player.*;
import dev.gerald.hack.impl.modules.render.*;
import dev.gerald.hack.*;
import java.util.*;

public class ModuleManager
{
    private List<Module> modules;
    
    public ModuleManager() {
        (this.modules = new ArrayList<Module>()).add((Module)new ClickGui());
        this.modules.add((Module)new DiscordRPC());
        this.modules.add((Module)new Messages());
        this.modules.add((Module)new BowSpam());
        this.modules.add((Module)new Criticals());
        this.modules.add((Module)new KillAura());
        this.modules.add((Module)new Description());
        this.modules.add((Module)new Chat());
        this.modules.add((Module)new ChatSpammer());
        this.modules.add((Module)new Donda());
        this.modules.add((Module)new FakePlayer());
        this.modules.add((Module)new NoWeather());
        this.modules.add(new ReverseStep());
        this.modules.add(new Sprint());
        this.modules.add(new Step());
        this.modules.add(new Velocity());
        this.modules.add(new Log());
        this.modules.add(new PacketMine());
        this.modules.add(new Respawn());
        this.modules.add(new Suicide());
        this.modules.add(new BlockOutline());
        this.modules.add(new ChildESP());
        this.modules.add(new ESP());
        this.modules.add(new FullBright());
        this.modules.add(new HoleESP());
        this.modules.add(new KillEffects());
        this.modules.add(new StorageESP());
        this.modules.add(new VoidESP());
    }
    
    public List<Module> getModules() {
        return this.modules;
    }
    
    public List<Module> getModulesFromCategory(final Module.Category category) {
        final List<Module> modules = new ArrayList<Module>();
        for (final Module module : Gerald.INSTANCE.moduleManager.getModules()) {
            if (module.getCategory() == category) {
                modules.add(module);
            }
        }
        return modules;
    }
    
    public int getModulesInCategoryInt(final Module.Category category) {
        int amountOfModules = 0;
        for (final Module ignored : Gerald.INSTANCE.moduleManager.getModulesFromCategory(category)) {
            ++amountOfModules;
        }
        return amountOfModules;
    }
    
    public Module getModuleByName(final String name) {
        for (final Module module : Gerald.INSTANCE.moduleManager.getModules()) {
            if (module.getName().equalsIgnoreCase(name)) {
                return module;
            }
        }
        return null;
    }
    
    public <T extends Module> T getHack(final Class<T> clazz) {
        for (final Module module : this.getModules()) {
            if (module.getClass() == clazz) {
                return (T)module;
            }
        }
        return null;
    }
    
    public void onLogout() {
        this.modules.forEach(Module::onLogout);
    }
    
    public void onLogin() {
        this.modules.forEach(Module::onLogin);
    }
}
