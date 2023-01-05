package com.spiritlight.grapplevisualizer.listeners;

import com.spiritlight.grapplevisualizer.Main;
import com.spiritlight.grapplevisualizer.config.DisplayMode;
import com.spiritlight.grapplevisualizer.utils.PlayerUtils;
import com.spiritlight.grapplevisualizer.utils.RenderUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.awt.*;

public class RenderEvent {

    @SubscribeEvent
    public void onRender(RenderWorldLastEvent event) {
        if(!PlayerUtils.canGrapple()) return;
        if(Main.config.getMode() == DisplayMode.DISABLED) return;
        final EntityPlayer player = Minecraft.getMinecraft().player;
        RayTraceResult result = player.rayTrace(Main.config.getMaxReachDistance(), 0.0f);
        BlockPos pos = result.getBlockPos(); // result != null if PlayerUtils.canGrapple() == true
        if(pos.getDistance((int) player.posX ,(int) player.posY,(int) player.posZ) <= 5.0d) return;
        RenderUtils.drawBlockESP(pos, new Color(64, 255, 64, 0), 0.0f);
        RenderUtils.drawOutlinedBox(new AxisAlignedBB(pos));
    }
}
