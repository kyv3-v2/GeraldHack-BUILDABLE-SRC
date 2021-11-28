//Sponsored by pingbypasser#5926

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
