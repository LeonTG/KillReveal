package com.leontg77.killreveal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import com.google.common.collect.ImmutableSet;
import com.leontg77.killreveal.cmds.KillRevealCommand;
import com.leontg77.killreveal.cmds.RandomCommand;

/**
 * Main class of the plugin.
 * 
 * @author LeonTG77
 */
public class Main extends JavaPlugin {
	public static boolean enabled = false;
	public static Main plugin;
	
	public static final String NO_PERM_MSG = "§cYou don't have permission.";
	public static final String PREFIX = "§c§lKillReveal §8» §7";

	private static Map<FakeTeam, String> colors = new HashMap<FakeTeam, String>();
	private static List<FakeTeam> teams = new ArrayList<FakeTeam>();
	
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

		// register commands.
		getCommand("random").setExecutor(new RandomCommand());
		getCommand("killreveal").setExecutor(new KillRevealCommand());
	}
	
	/**
	 * Register a new fake team.
	 * 
	 * @return The fake team created.
	 */
	public static FakeTeam registerNewFakeTeam() {
		FakeTeam team = new FakeTeam(teams.size() + 1);
		teams.add(team);
		
		return team;
	}

	/**
	 * Get the fake team for the given player.
	 * 
	 * @param player The given player.
	 * @return The fake team, null if the player isn't in a team.
	 */
	public static FakeTeam getTeam(Player player) {
		for (FakeTeam team : teams) {
			if (team.hasPlayer(player)) {
				return team;
			}
		}
		
		return null;
	}
	
	/**
	 * Get the color of the given team.
	 * 
	 * @param team The team getting for.
	 * @return The color, null if none.
	 */
	public static String getColor(FakeTeam team) {
		return colors.get(team);
	}
	
	/**
	 * Set the color of the given team.
	 * 
	 * @param team The team setting for.
	 * @param color The new color.
	 */
	public static void setColor(FakeTeam team, String color) {
		colors.put(team, color);
	}
	
	/**
	 * Get a Set of all the fake teams.
	 * 
	 * @return A set of fake teams.
	 */
	public static Set<FakeTeam> getFakeTeams() {
		return ImmutableSet.copyOf(teams);
	}
}