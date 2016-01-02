package com.leontg77.killreveal;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.bukkit.OfflinePlayer;

import com.google.common.collect.ImmutableSet;

/**
 * FakeTeam class.
 * <p>
 * Class used to store teammates for kill reveal since 
 * vanilla teams sorts the tab list.
 * 
 * @author LeonTG77
 */
public class FakeTeam {
	private Set<UUID> players = new HashSet<UUID>();
	private int id;
	
	/**
	 * FakeTeam constructor.
	 * 
	 * @param id the id of the team.
	 */
	public FakeTeam(int id) {
		this.id = id;
	}
	
	/**
	 * Get the ID of this team.
	 * 
	 * @return The ID.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Get a set of all UUID's on this team.
	 * 
	 * @return A set of UUID's.
	 */
	public Set<UUID> getPlayers() {
		return ImmutableSet.copyOf(players);
	}

	/**
	 * Get the size of the team.
	 * 
	 * @return The size.
	 */
	public int getSize() {
		return players.size();
	}

	/**
	 * Add the given player to the team.
	 * 
	 * @param player The player adding.
	 */
	public void addPlayer(OfflinePlayer player) {
		for (FakeTeam team : Main.getFakeTeams()) {
			if (team.hasPlayer(player)) {
				team.removePlayer(player);
			}
		}
		
		players.add(player.getUniqueId());
	}
	
	/**
	 * Remove the given player to the team.
	 * 
	 * @param player The player removing.
	 * @return True if successful, false otherwise.
	 */
	public boolean removePlayer(OfflinePlayer player) {
		return players.remove(player.getUniqueId());
	}

	/**
	 * Check if the given player is on this team.
	 * 
	 * @param player The player checking.
	 * @return True if he is, false otherwise.
	 */
	public boolean hasPlayer(OfflinePlayer player) {
		return players.contains(player.getUniqueId());
	}
}