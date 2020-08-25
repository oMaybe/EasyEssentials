package com.maybe.easyessentials.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerEventon implements Listener {
	
	@EventHandler
	public void onDie(PlayerDeathEvent e) {
		Player p = e.getEntity();
		
		p.spigot().respawn();
	}

}
