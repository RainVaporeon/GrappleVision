package com.spiritlight.grapplevisualizer.utils;

import com.spiritlight.grapplevisualizer.Main;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.math.RayTraceResult;

public class PlayerUtils {
    public static boolean canGrapple() {
        final EntityPlayer player = Main.mc.player;
        if(player == null) return false;
        if(holdingBow()) return false;
        RayTraceResult result = player.rayTrace(Main.config.getMaxReachDistance(), 10.0f);
        return result != null && result.typeOfHit != RayTraceResult.Type.MISS;
    }

    public static boolean holdingBow() {
        return isWynncraftBow(Main.mc.player.inventory.getCurrentItem());
    }

    public static boolean isWynncraftBow(ItemStack stack) {
        if(stack.getTagCompound() == null) return false;
        NBTTagCompound compound = stack.getTagCompound();
        NBTTagList list = compound.getCompoundTag("display").getTagList("Lore", 8);
        for(NBTBase element : list) {
            NBTTagString string = (NBTTagString) element;
            if(string == null) continue;
            if(string.getString().contains("Archer/Hunter")) return true;
        }
        return false;
    }
}
