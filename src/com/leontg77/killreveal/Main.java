package com.leontg77.killreveal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import com.leontg77.killreveal.cmds.KillRevealCommand;
import com.leontg77.killreveal.cmds.RandomCommand;

/**
 * Main class of the plugin.
 * 
 * @author LeonTG77
 */
public class Main extends JavaPlugin {
	public static final String PREFIX = "§c§lKillReveal §8» §7";
	public static Main plugin;

	public static List<FakeTeam> teams = new ArrayList<FakeTeam>();
	public static Map<FakeTeam, String> color = new HashMap<FakeTeam, String>();

	@Override
	public void onDisable() {
		PluginDescriptionFile file = getDescription();
		getLogger().info(file.getName() + " is now disabled.");
		plugin = null;
	}

	@Override
	public void onEnable() {
		PluginDescriptionFile file = getDescription();
		getLogger().info(file.getName() + " v" + file.getVersion() + " is now enabled.");
		getLogger().info("Plugin made by LeonTG77.");
		plugin = this;

		getCommand("random").setExecutor(new RandomCommand());
		getCommand("killreveal").setExecutor(new KillRevealCommand());
	}

	public void addColors() {
		ArrayList<String> list = new ArrayList<String>();

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

		ArrayList<String> tempList = new ArrayList<String>();

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

		tempList.remove(ChatColor.GRAY.toString() + ChatColor.ITALIC.toString());

		list.addAll(tempList);

		for (FakeTeam team : teams) {
			color.put(team, list.remove(0));
		}
	}

	public static FakeTeam getTeam(Player player) {
		for (FakeTeam team : teams) {
			if (team.hasPlayer(player)) {
				return team;
			}
		}
		return null;
	}
}