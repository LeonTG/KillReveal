package com.leontg77.killreveal;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.OfflinePlayer;

public class FakeTeam {
	private List<UUID> players = new ArrayList<UUID>();
	int id;
	
	public FakeTeam(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

	public List<UUID> getPlayers() {
		return players;
	}

	public int getSize() throws IllegalStateException {
		return players.size();
	}

	public void addPlayer(OfflinePlayer player) {
		players.add(player.getUniqueId());
	}

	public boolean removePlayer(OfflinePlayer player) {
		return players.remove(player.getUniqueId());
	}

	public boolean hasPlayer(OfflinePlayer player) {
		return players.contains(player.getUniqueId());
	}
}