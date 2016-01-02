package com.leontg77.killreveal.cmds;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

import com.leontg77.killreveal.Main;
import com.leontg77.killreveal.listeners.KillListener;

/**
 * Kill Reveal command class
 * 
 * @author LeonTG77
 */
public class KillRevealCommand implements CommandExecutor {
	private boolean enabled = false;

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!sender.hasPermission("killreveal.manage")) {
			sender.sendMessage(ChatColor.RED + "You can't use this command.");
			return true;
		}
		
		if (args.length == 0) {
			sender.sendMessage(Main.PREFIX + "Usage: /killreveal <enable|disable>");
			return true;
		}
		
		String enable = Main.PREFIX + "KillReveal has been enabled.";
		String disable = Main.PREFIX + "KillReveal has been disabled.";
		
		if (args[0].equalsIgnoreCase("enable")) {
			if (enabled) {
				sender.sendMessage(Main.PREFIX + "KillReveal is already enabled.");
				return true;
			}
			
			for (Player online : Bukkit.getServer().getOnlinePlayers()) {
				online.sendMessage(enable);
			}
			
			Bukkit.getPluginManager().registerEvents(new KillListener(), Main.plugin);
			enabled = true;
		} else if (args[0].equalsIgnoreCase("disable")) {
			if (!enabled) {
				sender.sendMessage(Main.PREFIX + "KillReveal is not enabled.");
				return true;
			}
			
			for (Player online : Bukkit.getServer().getOnlinePlayers()) {
				online.sendMessage(disable);
			}
			
			HandlerList.unregisterAll(Main.plugin);
			enabled = false;
		} else {
			sender.sendMessage(Main.PREFIX + "Usage: /killreveal <enable|disable>");
		}
		return true;
	}
}