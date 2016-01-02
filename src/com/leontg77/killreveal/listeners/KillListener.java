package com.leontg77.killreveal.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import com.leontg77.killreveal.FakeTeam;
import com.leontg77.killreveal.Main;

/**
 * Kill listener class.
 * 
 * @author LeonTG77
 */
public class KillListener implements Listener {
	
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
		
		Scoreboard board = Bukkit.getScoreboardManager().getMainScoreboard();
		Team newTeam = board.getTeam("KRTeam" + team.getId());
		
		if (newTeam == null) {
			newTeam = board.registerNewTeam("KRTeam" + team.getId());
		}
		
		newTeam.addEntry(killer.getName());
		newTeam.setPrefix(Main.getColor(team));
		newTeam.setSuffix("§f");
	}
}