package com.spiritlight.grapplevisualizer.listeners;

import com.spiritlight.grapplevisualizer.utils.RenderUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBow;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.awt.*;

public class RenderEvent {

    @SubscribeEvent
    public void onRender(RenderWorldLastEvent event) {
        final EntityPlayer player = Minecraft.getMinecraft().player;
        if(player == null) return;
        if(!(player.inventory.getCurrentItem().getItem() instanceof ItemBow)) return;
        RayTraceResult result = player.rayTrace(40.0d, 10.0f);
        if(result == null || result.typeOfHit == RayTraceResult.Type.MISS) return;
        BlockPos pos = result.getBlockPos();
        // modPos(result.sideHit, pos);
        RenderUtils.drawBlockESP(pos, new Color(0, 255, 0, 127), 10.0f);
        RenderUtils.drawOutlinedBox(new AxisAlignedBB(pos));
    }

    private BlockPos modPos(EnumFacing side, BlockPos pos) {
        switch(side) {
            case UP:
                pos = pos.add(0, -1, 0);
                break;
            case DOWN:
                pos = pos.add(0, 1, 0);
                break;
            case NORTH:
                pos = pos.add(0, 0, -1);
                break;
            case SOUTH:
                pos = pos.add(0, 0, 1);
                break;
            case WEST:
                pos = pos.add(1, 0, 0);
                break;
            case EAST:
                pos = pos.add(-1, 0, 0);
                break;
        }
        return pos;
    }
}
