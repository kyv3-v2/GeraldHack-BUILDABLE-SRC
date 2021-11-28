//Sponsored by pingbypasser#5926

//Decompiled by Procyon!

package dev.gerald.hack.api.util;

import net.minecraft.client.*;
import net.minecraft.item.*;

public class InventoryUtil
{
    public static int getItemHotbar(final Item item) {
        for (int i = 0; i < 9; ++i) {
            final ItemStack stack = Minecraft.getMinecraft().player.inventory.getStackInSlot(i);
            if (stack.getItem() == item) {
                return i;
            }
        }
        return -1;
    }
    
    public static int getItemInventory(final Item item) {
        for (int i = 0; i < 35; ++i) {
            final ItemStack stack = Minecraft.getMinecraft().player.inventory.getStackInSlot(i);
            if (stack.getItem() == item) {
                return i;
            }
        }
        return -1;
    }
    
    public static int getTotalAmountOfItem(final Item item) {
        int amountOfItem = 0;
        for (int i = 0; i < 35; ++i) {
            final ItemStack stack = Minecraft.getMinecraft().player.inventory.getStackInSlot(i);
            if (stack.getItem() == item) {
                amountOfItem += stack.getCount();
            }
        }
        return amountOfItem;
    }
}
