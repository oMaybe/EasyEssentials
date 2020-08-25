package com.maybe.easyessentials.events;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import com.maybe.easyessentials.commands.admin.Admin;

public class InventoryItemsC implements Listener {
	
	Admin admin;
	
	@EventHandler
	public void onInven(PlayerInteractEntityEvent e) {
		Player p = e.getPlayer();
		if (admin.adminmode.contains(p)) {		
			Entity entity = e.getRightClicked();
			Material is = p.getInventory().getItemInHand().getType();
			if (is == Material.WOOD_AXE) {
				if (is.getData().getName().contains("§cBan Hammer")) {
					if (entity instanceof Player) {
						p.performCommand("/ban " + entity.getName() + " §cYou have been caught break rules!");
						p.sendMessage("You have banned " + entity.getName() + "!");
					}else {
						p.sendMessage("§cThat is not a player!");
					}
				}
			}
			
		}else {
			return;
		}
	}
}
