package com.spiritlight.grapplevisualizer;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault @MethodsReturnNonnullByDefault
public class BaseCommand extends CommandBase {

    @Override
    public String getName() {
        return "gv";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/" + getName();
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        Main.quiet = !Main.quiet;
        sender.sendMessage(new TextComponentString("[" + TextFormatting.GOLD + "GrappleVisualizer" + TextFormatting.RESET + "]" +
                " Toggled visualization status to " + (Main.quiet ? "on" : "off")));
    }
}
