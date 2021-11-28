//Sponsored by pingbypasser#5926

//Decompiled by Procyon!

package dev.gerald.hack.api.util;

import dev.gerald.hack.*;
import dev.gerald.hack.api.module.*;
import java.nio.file.attribute.*;
import java.nio.charset.*;
import dev.gerald.hack.api.setting.*;
import dev.gerald.hack.api.setting.settings.*;
import org.lwjgl.input.*;
import java.util.*;
import com.google.gson.*;
import java.nio.file.*;
import java.io.*;

public class ConfigManager
{
    public void save() throws IOException {
        this.registerFolders();
        for (final Module module : Gerald.INSTANCE.moduleManager.getModules()) {
            if (module.getDescription().equalsIgnoreCase("hi :)")) {
                continue;
            }
            if (Files.exists(Paths.get("GeraldHack/Modules/" + module.getName() + ".json", new String[0]), new LinkOption[0])) {
                new File("GeraldHack/Modules/" + module.getName() + ".json").delete();
            }
            Files.createFile(Paths.get("GeraldHack/Modules/" + module.getName() + ".json", new String[0]), (FileAttribute<?>[])new FileAttribute[0]);
            final Gson gson = new GsonBuilder().setPrettyPrinting().create();
            final OutputStreamWriter stream = new OutputStreamWriter(new FileOutputStream("GeraldHack/Modules/" + module.getName() + ".json"), StandardCharsets.UTF_8);
            final JsonObject moduleObject = new JsonObject();
            final JsonObject settingObject = new JsonObject();
            moduleObject.add("Module", (JsonElement)new JsonPrimitive(module.getName()));
            for (final Setting setting : module.getSettings()) {
                if (setting instanceof BoolSetting) {
                    settingObject.add(setting.getName(), (JsonElement)new JsonPrimitive(Boolean.valueOf(((BoolSetting)setting).getValue())));
                }
                else if (setting instanceof NumSetting) {
                    settingObject.add(setting.getName(), (JsonElement)new JsonPrimitive((Number)((NumSetting)setting).getValue()));
                }
                else if (setting instanceof ModeSetting) {
                    settingObject.add(setting.getName(), (JsonElement)new JsonPrimitive((Number)((ModeSetting)setting).getValueIndex()));
                }
                else {
                    if (!(setting instanceof ColorSetting)) {
                        continue;
                    }
                    final JsonObject colorObject = new JsonObject();
                    colorObject.add("Red", (JsonElement)new JsonPrimitive((Number)((ColorSetting)setting).getR()));
                    colorObject.add("Green", (JsonElement)new JsonPrimitive((Number)((ColorSetting)setting).getG()));
                    colorObject.add("Blue", (JsonElement)new JsonPrimitive((Number)((ColorSetting)setting).getB()));
                    colorObject.add("Alpha", (JsonElement)new JsonPrimitive((Number)((ColorSetting)setting).getA()));
                    settingObject.add(setting.getName(), (JsonElement)colorObject);
                }
            }
            moduleObject.add("Bind", (JsonElement)new JsonPrimitive(Keyboard.getKeyName(module.getKeybind())));
            moduleObject.add("Enabled", (JsonElement)new JsonPrimitive(Boolean.valueOf(module.isEnabled())));
            moduleObject.add("Visible", (JsonElement)new JsonPrimitive(Boolean.valueOf(module.isVisible())));
            moduleObject.add("Settings", (JsonElement)settingObject);
            stream.write(gson.toJson(new JsonParser().parse(moduleObject.toString())));
            stream.close();
        }
    }
    
    public void saveModule(final Module module) {
        if (module.getDescription().equalsIgnoreCase("hi :)")) {
            return;
        }
        try {
            this.registerFolders();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        if (Files.exists(Paths.get("GeraldHack/Modules/" + module.getName() + ".json", new String[0]), new LinkOption[0])) {
            new File("GeraldHack/Modules/" + module.getName() + ".json").delete();
        }
        try {
            Files.createFile(Paths.get("GeraldHack/Modules/" + module.getName() + ".json", new String[0]), (FileAttribute<?>[])new FileAttribute[0]);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        final Gson gson = new GsonBuilder().setPrettyPrinting().create();
        OutputStreamWriter stream = null;
        try {
            stream = new OutputStreamWriter(new FileOutputStream("GeraldHack/Modules/" + module.getName() + ".json"), StandardCharsets.UTF_8);
        }
        catch (FileNotFoundException e2) {
            e2.printStackTrace();
        }
        final JsonObject moduleObject = new JsonObject();
        final JsonObject settingObject = new JsonObject();
        moduleObject.add("Module", (JsonElement)new JsonPrimitive(module.getName()));
        for (final Setting setting : module.getSettings()) {
            if (setting instanceof BoolSetting) {
                settingObject.add(setting.getName(), (JsonElement)new JsonPrimitive(Boolean.valueOf(((BoolSetting)setting).getValue())));
            }
            else if (setting instanceof NumSetting) {
                settingObject.add(setting.getName(), (JsonElement)new JsonPrimitive((Number)((NumSetting)setting).getValue()));
            }
            else if (setting instanceof ModeSetting) {
                settingObject.add(setting.getName(), (JsonElement)new JsonPrimitive((Number)((ModeSetting)setting).getValueIndex()));
            }
            else {
                if (!(setting instanceof ColorSetting)) {
                    continue;
                }
                final JsonObject colorObject = new JsonObject();
                colorObject.add("Red", (JsonElement)new JsonPrimitive((Number)((ColorSetting)setting).getR()));
                colorObject.add("Green", (JsonElement)new JsonPrimitive((Number)((ColorSetting)setting).getG()));
                colorObject.add("Blue", (JsonElement)new JsonPrimitive((Number)((ColorSetting)setting).getB()));
                colorObject.add("Alpha", (JsonElement)new JsonPrimitive((Number)((ColorSetting)setting).getA()));
                settingObject.add(setting.getName(), (JsonElement)colorObject);
            }
        }
        moduleObject.add("Bind", (JsonElement)new JsonPrimitive(Keyboard.getKeyName(module.getKeybind())));
        moduleObject.add("Enabled", (JsonElement)new JsonPrimitive(Boolean.valueOf(module.isEnabled())));
        moduleObject.add("Visible", (JsonElement)new JsonPrimitive(Boolean.valueOf(module.isVisible())));
        moduleObject.add("Settings", (JsonElement)settingObject);
        try {
            stream.write(gson.toJson(new JsonParser().parse(moduleObject.toString())));
        }
        catch (IOException e3) {
            e3.printStackTrace();
        }
        try {
            stream.close();
        }
        catch (IOException e3) {
            e3.printStackTrace();
        }
    }
    
    public void load() throws IOException {
        for (final Module module : Gerald.INSTANCE.moduleManager.getModules()) {
            if (module.getDescription().equalsIgnoreCase("hi :)")) {
                continue;
            }
            System.out.println("Attempting to load config for. (" + module.getName() + ")");
            if (!Files.exists(Paths.get("GeraldHack/Modules/" + module.getName() + ".json", new String[0]), new LinkOption[0])) {
                return;
            }
            final InputStream stream = Files.newInputStream(Paths.get("GeraldHack/Modules/" + module.getName() + ".json", new String[0]), new OpenOption[0]);
            final JsonObject moduleObject = new JsonParser().parse((Reader)new InputStreamReader(stream)).getAsJsonObject();
            if (moduleObject.get("Module") == null) {
                return;
            }
            final JsonObject settingObject = moduleObject.get("Settings").getAsJsonObject();
            for (final Setting setting : module.getSettings()) {
                final JsonElement settingDataObject = settingObject.get(setting.getName());
                try {
                    if (settingDataObject == null || (!settingDataObject.isJsonPrimitive() && !settingDataObject.isJsonObject())) {
                        continue;
                    }
                    if (setting instanceof BoolSetting) {
                        ((BoolSetting)setting).setValue(settingDataObject.getAsBoolean());
                    }
                    else if (setting instanceof NumSetting) {
                        ((NumSetting)setting).setValue(settingDataObject.getAsFloat());
                    }
                    else if (setting instanceof ModeSetting) {
                        ((ModeSetting)setting).setValueIndex(settingDataObject.getAsInt());
                    }
                    else {
                        if (!(setting instanceof ColorSetting)) {
                            continue;
                        }
                        final JsonObject colorObject = settingObject.get(setting.getName()).getAsJsonObject();
                        final JsonElement redElement = colorObject.get("Red");
                        final JsonElement greenElement = colorObject.get("Green");
                        final JsonElement blueElement = colorObject.get("Blue");
                        final JsonElement alphaElement = colorObject.get("Alpha");
                        try {
                            ((ColorSetting)setting).setR(redElement.getAsInt());
                            ((ColorSetting)setting).setG(greenElement.getAsInt());
                            ((ColorSetting)setting).setB(blueElement.getAsInt());
                            ((ColorSetting)setting).setA(alphaElement.getAsInt());
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                catch (Exception e2) {
                    System.out.println("Faulty setting found (" + module.getName() + ", " + setting.getName() + ")");
                    e2.printStackTrace();
                }
            }
            final JsonElement bindElement = moduleObject.get("Bind");
            final String bindS = bindElement.getAsString();
            System.out.println(bindS);
            try {
                module.setKeybind(Keyboard.getKeyIndex(bindS));
            }
            catch (Exception e3) {
                e3.printStackTrace();
            }
            final JsonElement enabledElement = moduleObject.get("Enabled");
            final String enabledS = enabledElement.getAsString();
            if (enabledS.contains("true")) {
                try {
                    module.toggle();
                    System.out.println("Toggled (" + module.getName() + ")");
                }
                catch (Exception e4) {
                    e4.printStackTrace();
                }
            }
            final JsonElement visibleElement = moduleObject.get("Visible");
            final String visibleS = visibleElement.getAsString();
            if (visibleS.contains("true")) {
                try {
                    module.setVisible(true);
                }
                catch (Exception e5) {
                    e5.printStackTrace();
                }
            }
            System.out.println("Loaded config for module. (" + module.getName() + ")");
            stream.close();
        }
    }
    
    public void registerFolders() throws IOException {
        if (!Files.exists(Paths.get("GeraldHack/", new String[0]), new LinkOption[0])) {
            Files.createDirectories(Paths.get("GeraldHack/", new String[0]), (FileAttribute<?>[])new FileAttribute[0]);
        }
        if (!Files.exists(Paths.get("GeraldHack/Modules/", new String[0]), new LinkOption[0])) {
            Files.createDirectories(Paths.get("GeraldHack/Modules/", new String[0]), (FileAttribute<?>[])new FileAttribute[0]);
        }
    }
}
