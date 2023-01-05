package com.spiritlight.grapplevisualizer;

import com.spiritlight.grapplevisualizer.config.ModInitializer;
import com.spiritlight.grapplevisualizer.listeners.RenderEvent;
import com.spiritlight.grapplevisualizer.listeners.UITextRenderEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Main.MODID, name = Main.NAME, version = Main.VERSION)
public class Main
{
    public static final String MODID = "grapplevisualizer";
    public static final String NAME = "Grapple Hook Visualizer";
    public static final String VERSION = "1.0";
    public static final Minecraft mc = Minecraft.getMinecraft();
    public static boolean quiet = false;
    public static final ModInitializer config = ModInitializer.deserialize();
    public static final String prefix = "[" + TextFormatting.GOLD + "GrappleVisualizer" + TextFormatting.RESET + "]";
    

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(new RenderEvent());
        MinecraftForge.EVENT_BUS.register(new UITextRenderEvent());
        ClientCommandHandler.instance.registerCommand(new BaseCommand());
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    }
}
