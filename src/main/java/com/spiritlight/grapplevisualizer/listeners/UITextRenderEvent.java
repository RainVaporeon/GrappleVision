package com.spiritlight.grapplevisualizer.listeners;

import com.spiritlight.grapplevisualizer.Main;
import com.spiritlight.grapplevisualizer.utils.PlayerUtils;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class UITextRenderEvent {
    @SubscribeEvent
    public void onTextRender(RenderGameOverlayEvent event) {
        if (event.getType() == RenderGameOverlayEvent.ElementType.TEXT)
            Main.mc.fontRenderer.drawStringWithShadow(
                    PlayerUtils.holdingBow() ?
                            PlayerUtils.canGrapple() ? TextFormatting.GREEN + "[Can Grapple]" : TextFormatting.RED + "[Cannot Grapple]" :
                            TextFormatting.GRAY + "[Not Holding Bow]",
                    Main.config.getMode().getX(),
                    Main.config.getMode().getY(),
                    0xFFFFFF // white
            );
    }
}
