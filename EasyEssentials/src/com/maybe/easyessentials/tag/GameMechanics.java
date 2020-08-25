package com.maybe.easyessentials.tag;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import com.maybe.easyessentials.Main;

public class GameMechanics implements Listener {
	
	private Main plugin = Main.getPlugin(Main.class);
	
	private int taggersSelected;
	
	//TODO:
	// MAKE THIS SO PEOPLE CAN JOIN WITH COMMAND/COMPASS
	// MAKE THIS SO PEOPLE CAN JOIN AND PLAY (ON PLAYER JOIN EVENT)
	
	
	/*@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent e) {
		
		if (plugin.gameManage.isStarted() == false) {
			Player p = e.getPlayer();
			UUID uuid = p.getUniqueId();
			if(plugin.playersLeftGame.contains(p)) {
				// TODO bungee push back
			}
			
			//  e.setJoinMessage("");
			
			String[] args = e.getMessage().split(" ");
			String cmd = args[0].replace("/", "").toLowerCase();
			if(cmd.equalsIgnoreCase("tt")) {
				if (args[1].equalsIgnoreCase("join")) {
					plugin.playermanager.put(uuid, new PlayerManager(uuid, false, 0, false, false));
					plugin.playersInGame.add(p);
					plugin.gameManage.lobbyWait(p);
				}
			}	
		}else {
			// TODO bungee push back
		}
		
		
	}*/
	
	/*@EventHandler
	public void onJoin(PlayerJoinEvent e) {

		if (plugin.gameManage.isStarted() == false) {
			Player p = e.getPlayer();
			UUID uuid = p.getUniqueId();
			if(plugin.playersLeftGame.contains(p)) {
				// TODO bungee push back
			}
			e.setJoinMessage("");
			plugin.playermanager.put(uuid, new PlayerManager(uuid, false, 0, false, false));
			plugin.playersInGame.add(p);
			plugin.gameManage.lobbyWait(p);
		}else {
			// TODO BUNGEE PUSH BACK
		}
	}
	
	@EventHandler
	public void onLeave(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		UUID uuid= p.getUniqueId();
		
		e.setQuitMessage("");
		plugin.playermanager.remove(uuid);
		plugin.playersInGame.remove(p);
		plugin.playersLeftGame.add(p);
	}*/

	@EventHandler
	public void playerTNTTag(EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
			e.setDamage(0);
			
			Player tagger = (Player) e.getDamager();
			UUID taggerUUID = tagger.getUniqueId();
			PlayerManager taggerPlayerManager = plugin.playermanager.get(taggerUUID);
			
			Player tagged = (Player) e.getEntity();
			UUID taggedUUID = tagged.getUniqueId();
			PlayerManager taggedPlayerManager = plugin.playermanager.get(taggerUUID);
		
			if (taggerPlayerManager.isHasTNT() == true && taggedPlayerManager.isHasTNT() == true) {
				e.setCancelled(true);
				tagger.sendMessage("§cThis player is already tagged!");
				return;
			}
			
			if (taggerPlayerManager.isHasTNT() == true) {
				taggerPlayerManager.setHasTNT(false);
				tagger.getInventory().setHelmet(null);
				tagger.getInventory().clear();
				
				taggerPlayerManager.setHasTNT(true);
				tagger.getInventory().setHelmet(new ItemStack(Material.TNT));
				tagged.playSound(tagger.getLocation(), Sound.ENTITY_TNT_PRIMED, 1, 1);
				tagged.sendMessage("§cYou are now it!");
				tagged.getInventory().addItem(new ItemStack(Material.TNT, 576));
			}
		}
	}
	
	public void tntCheck() {
		
		for (PlayerManager playerManager : plugin.playermanager.values()) {
			if (playerManager.isHasTNT() == true) {
				
				playerManager.setHasTNT(false);
				playerManager.setIsdead(true);		
								
				Player player = Bukkit.getPlayer(playerManager.getUuid());
				plugin.playersInGame.remove(player);
				
				Location playerLocation = player.getLocation();
				playerLocation.getWorld().createExplosion(playerLocation.getX(), playerLocation.getY(),
						playerLocation.getZ(), 1, false, false);			
				player.setPlayerListName("§7" + player.getName());			
				player.getInventory().setHelmet(null);			
				player.setGameMode(GameMode.SPECTATOR);
				
				
			}
		}
		
	}

	public void tntPlacer() {
		// TODO Auto-generated method stub
		
	}
	
}
