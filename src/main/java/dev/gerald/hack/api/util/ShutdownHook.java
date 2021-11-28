//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\srgtmomentum\Desktop\Minecraft-Deobfuscator3000-1.2.3\1.12 stable mappings"!

//Decompiled by Procyon!

package dev.gerald.hack.api.util;

import dev.gerald.hack.*;
import java.io.*;

public class ShutdownHook extends Thread
{
    @Override
    public void run() {
        try {
            Gerald.INSTANCE.configManager.save();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
