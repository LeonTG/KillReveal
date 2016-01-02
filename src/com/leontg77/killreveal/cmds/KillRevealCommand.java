package com.leontg77.killreveal.cmds;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.HandlerList;

import com.leontg77.killreveal.Main;
import com.leontg77.killreveal.Utils;
import com.leontg77.killreveal.listeners.KillListener;

/**
 * Kill Reveal command class
 * 
 * @author LeonTG77
 */
public class KillRevealCommand implements CommandExecutor {
	private KillListener listener = new KillListener();
	
	private static final String USAGE = Main.PREFIX + "Usage: /killreveal <enable|disable>";
	private static final String PLUGIN_NAME = "KillReveal";

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!sender.hasPermission("killreveal.manage")) {
			sender.sendMessage(Main.NO_PERM_MSG);
			return true;
		}
		
		if (args.length == 0) {
			sender.sendMessage(USAGE);
			return true;
		}
		
		if (args[0].equalsIgnoreCase("enable")) {
			if (Main.enabled) {
				sender.sendMessage(Main.PREFIX + PLUGIN_NAME + " is already enabled.");
				return true;
			}

			Utils.broadcast(Main.PREFIX + PLUGIN_NAME + " has been enabled.");
			
			Bukkit.getPluginManager().registerEvents(listener, Main.plugin);
			Main.enabled = true;
			return true;
		} 

		if (args[0].equalsIgnoreCase("disable")) {
			if (!Main.enabled) {
				sender.sendMessage(Main.PREFIX + PLUGIN_NAME + " is not enabled.");
				return true;
			}
			
			Utils.broadcast(Main.PREFIX + PLUGIN_NAME + " has been disabled.");
			
			HandlerList.unregisterAll(listener);
			Main.enabled = false;
			return true;
		} 
		
		sender.sendMessage(USAGE);
		return true;
	}
}