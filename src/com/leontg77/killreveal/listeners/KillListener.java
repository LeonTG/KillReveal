package com.leontg77.killreveal.listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import com.leontg77.killreveal.FakeTeam;
import com.leontg77.killreveal.Main;

/**
 * Kill listener class.
 * 
 * @author LeonTG77
 */
public class KillListener implements Listener {
	private List<String> hasAKill = new ArrayList<String>();
	
	@EventHandler
	public void on(PlayerDeathEvent event) {
		Player player = event.getEntity();
		Player killer = player.getKiller();
		
		if (killer == null) {
			return;
		}
		
		FakeTeam team = Main.getTeam(killer);
		
		if (team == null) {
			return;
		}
		
		killer.setPlayerListName(Main.color.get(team) + killer.getName());
		hasAKill.add(killer.getName());
	}
	
	@EventHandler
	public void on(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		
		if (!hasAKill.contains(player.getName())) {
			return;
		}

		FakeTeam team = Main.getTeam(player);
		
		if (team == null) {
			return;
		}
		
		player.setPlayerListName(Main.color.get(team) + player.getName());
	}
}