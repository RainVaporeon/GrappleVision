package com.spiritlight.grapplevisualizer.utils;

import com.spiritlight.grapplevisualizer.Main;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBow;
import net.minecraft.util.math.RayTraceResult;

public class PlayerUtils {
    public static boolean canGrapple() {
        final EntityPlayer player = Main.mc.player;
        if(player == null) return false;
        if(!(player.inventory.getCurrentItem().getItem() instanceof ItemBow)) return false;
        RayTraceResult result = player.rayTrace(Main.config.getMaxReachDistance(), 10.0f);
        return result != null && result.typeOfHit != RayTraceResult.Type.MISS;
    }

    public static boolean holdingBow() {
        return Main.mc.player.inventory.getCurrentItem().getItem() instanceof ItemBow;
    }
}
