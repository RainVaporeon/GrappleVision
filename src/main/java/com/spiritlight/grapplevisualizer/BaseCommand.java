package com.spiritlight.grapplevisualizer;

import com.spiritlight.grapplevisualizer.config.DisplayMode;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import org.lwjgl.Sys;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Arrays;
import java.util.Locale;

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
        if(args.length == 0) {
            getHelp(sender);
            return;
        }
        switch(args[0].toLowerCase(Locale.ROOT)) {
            case "toggle":
                Main.quiet = !Main.quiet;
                sender.sendMessage(new TextComponentString("[" + TextFormatting.GOLD + "GrappleVisualizer" + TextFormatting.RESET + "]" +
                        " Toggled visualization status to " + (Main.quiet ? "on" : "off")));
                break;
            case "mode":
                String[] extractedArgs = new String[args.length - 1];
                System.arraycopy(args, 1, extractedArgs, 0, args.length - 1);
                DisplayMode mode = DisplayMode.fromString(String.join(" ", extractedArgs));
                if(mode == null) {
                    sender.sendMessage(new TextComponentString(Main.prefix + " Invalid mode specified."));
                    sender.sendMessage(new TextComponentString("All available modes: " + Arrays.toString(DisplayMode.values())));
                    break;
                }
                Main.config.setMode(mode);
                sender.sendMessage(new TextComponentString(Main.prefix + " Successfully set the mode."));
                break;
            case "maxdistance":
                try {
                    double d = Double.parseDouble(args[1]);
                    Main.config.setMaxReachDistance(d);
                    sender.sendMessage(new TextComponentString("Successfully set max reach distance!"));
                } catch (NumberFormatException nfe) {
                    sender.sendMessage(new TextComponentString("Invalid input. Cannot parse " + args[1] + " as a number."));
                } catch (IllegalArgumentException iae) {
                    sender.sendMessage(new TextComponentString(iae.getMessage()));
                }
                break;
            default:
                getHelp(sender);
                return;
        }
        Main.config.serialize();
    }

    private void getHelp(ICommandSender sender) {
        sender.sendMessage(new TextComponentString(Main.prefix + "/" + getName() + " toggle/mode/maxdistance"));
    }
}
