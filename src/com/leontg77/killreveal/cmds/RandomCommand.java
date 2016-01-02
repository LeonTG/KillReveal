package com.leontg77.killreveal.cmds;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.leontg77.killreveal.FakeTeam;
import com.leontg77.killreveal.Main;
import com.leontg77.killreveal.Utils;

/**
 * Random command class.
 * 
 * @author LeonTG77
 */
public class RandomCommand implements CommandExecutor {
	private static final String USAGE = Main.PREFIX + "Usage: /random <teamsize> <amount of teams>";

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!sender.hasPermission("killreveal.manage")) {
			sender.sendMessage(Main.NO_PERM_MSG);
			return true;
		}
		
		if (!Main.enabled) {
			sender.sendMessage(Main.PREFIX + "KillReveal is not enabled.");
			return true;
		}

		if (args.length < 2) {
			sender.sendMessage(USAGE);
			return true;
		}
		
		int amount = Integer.parseInt(args[1]);
		int size = Integer.parseInt(args[0]);
		
		Utils.broadcast(Main.PREFIX + "Randomizing §6" + amount + "§7 teams of §6" + size + "§7.");
		
		for (int i = 0; i < amount; i++) {
			List<Player> list = new ArrayList<Player>();
			
			for (Player online : Utils.getPlayers()) {
				if (Main.getTeam(online) == null) {
					list.add(online);
				}
			}
			
			Collections.shuffle(list);

			FakeTeam team = Main.registerNewFakeTeam();
			
			for (int j = 0; j < size; j++) {
				if (list.isEmpty()) {
					break;
				}
				
				Player player = list.remove(0);
				player.sendMessage(Main.PREFIX + "You were added to team §6" + team.getId() + "§7.");
				team.addPlayer(player);
			}
			
			if (team.getSize() > 0) {
				for (UUID entry : team.getPlayers()) {
					Player player = Bukkit.getPlayer(entry);
					
					if (player == null) {
						continue;
					}
					
					if (team.getSize() == 1) {
						player.sendMessage(Main.PREFIX + "You are a solo."); 
						continue;
					}

					player.sendMessage(Main.PREFIX + "Your teammates are:"); 
					
					for (UUID entryTwo : team.getPlayers()) {
						if (entryTwo == entry) {
							continue;
						}
						
						player.sendMessage(Main.PREFIX + "§a" + Bukkit.getPlayer(entryTwo).getName());
					}
				}
			}
		}
		
		addColors();
		Utils.broadcast(Main.PREFIX + "Teams has been randomized.");
		return true;
	}

	/**
	 * Add different colors to the fake teams.
	 */
	private void addColors() {
		List<String> list = new ArrayList<String>();

		list.add(ChatColor.BLACK.toString());
		list.add(ChatColor.DARK_BLUE.toString());
		list.add(ChatColor.DARK_GREEN.toString());
		list.add(ChatColor.DARK_AQUA.toString());
		list.add(ChatColor.DARK_RED.toString());
		list.add(ChatColor.DARK_PURPLE.toString());
		list.add(ChatColor.GOLD.toString());
		list.add(ChatColor.GRAY.toString());
		list.add(ChatColor.DARK_GRAY.toString());
		list.add(ChatColor.BLUE.toString());
		list.add(ChatColor.GREEN.toString());
		list.add(ChatColor.AQUA.toString());
		list.add(ChatColor.RED.toString());
		list.add(ChatColor.LIGHT_PURPLE.toString());
		list.add(ChatColor.YELLOW.toString());
		list.add(ChatColor.WHITE.toString());

		Collections.shuffle(list);

		List<String> tempList = new ArrayList<String>();

		for (String li : list) {
			tempList.add(li + ChatColor.ITALIC);
		}

		for (String li : list) {
			tempList.add(li + ChatColor.BOLD);
		}

		for (String li : list) {
			tempList.add(li + ChatColor.UNDERLINE);
		}

		for (String li : list) {
			tempList.add(li + ChatColor.STRIKETHROUGH);
		}

		list.addAll(tempList);

		for (FakeTeam team : Main.getFakeTeams()) {
			Main.setColor(team, list.remove(0));
		}
	}
}