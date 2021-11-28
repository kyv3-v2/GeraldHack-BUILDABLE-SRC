//Sponsored by pingbypasser#5926

//Decompiled by Procyon!

package dev.gerald.hack.api.util;

import net.minecraft.util.math.*;
import java.util.*;

public class PlayerUtil
{
    public static List<BlockPos> getSphere(final BlockPos pos, final float r, final float h, final boolean hollow, final boolean sphere, final int plusY) {
        final List<BlockPos> blocks = new ArrayList<BlockPos>();
        for (int x = pos.getX() - (int)r; x <= pos.getX() + r; ++x) {
            for (int y = sphere ? (pos.getY() - (int)r) : pos.getY(); y < (sphere ? (pos.getY() + r) : (pos.getY() + h)); ++y) {
                for (int z = pos.getZ() - (int)r; z <= pos.getZ() + r; ++z) {
                    final double dist = (pos.getX() - x) * (pos.getX() - x) + (pos.getZ() - z) * (pos.getZ() - z) + (sphere ? ((pos.getY() - y) * (pos.getY() - y)) : 0);
                    if (dist < r * r && (!hollow || dist >= (r - 1.0f) * (r - 1.0f))) {
                        blocks.add(new BlockPos(x, y + plusY, z));
                    }
                }
            }
        }
        return blocks;
    }
    
    public static List<BlockPos> getSphere(final BlockPos pos, final float r, final boolean hollow) {
        return getSphere(pos, r, (float)(int)r, hollow, true, 0);
    }
}
