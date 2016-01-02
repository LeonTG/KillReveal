package com.leontg77.killreveal.cmds;

import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.leontg77.killreveal.FakeTeam;
import com.leontg77.killreveal.Main;

/**
 * Random command class.
 * 
 * @author LeonTG77
 */
public class RandomCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length < 2) {
			sender.sendMessage(Main.PREFIX + "Usage: /random <teamsize> <amount of teams>");
			return true;
		}
		
		int amount = Integer.parseInt(args[1]);
		int size = Integer.parseInt(args[0]);
		
		Bukkit.broadcastMessage(Main.PREFIX + "Randomizing §6" + amount + "§7 teams of §6" + size + "§7.");
		
		for (int i = 0; i < amount; i++) {
			ArrayList<Player> list = new ArrayList<Player>();
			
			for (Player online : Bukkit.getOnlinePlayers()) {
				if (Main.getTeam(online) == null) {
					list.add(online);
				}
			}
			
			Collections.shuffle(list);

			FakeTeam team = new FakeTeam(Main.teams.size() + 1);
			Main.teams.add(team);
			
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
		
		Main.plugin.addColors();
		Bukkit.broadcastMessage(Main.PREFIX + "Teams has been randomized.");
		return true;
	}
}