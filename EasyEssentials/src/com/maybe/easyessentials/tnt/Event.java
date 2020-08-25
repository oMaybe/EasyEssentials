package com.maybe.easyessentials.tnt;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import com.maybe.easyessentials.Main;

public class Event implements Listener {
	
	String prefix = "§7[§c§lTNT RUN§7] §b";
	
	public static List<Block> BLOK_SAND = new LinkedList<Block>();
	public static List<Block> BLOK_GRAVEL = new LinkedList<Block>();
	public static List<Block> BLOK_TNT = new LinkedList<Block>();
	
	@EventHandler
	public void onStep(PlayerMoveEvent e) {
		if (GameState.getGameState() == GameState.INGAME) {
			ArrayList<Player> player_list = new ArrayList<Player>(Bukkit.getOnlinePlayers());					
			Player player = e.getPlayer();
			Block block1 = player.getLocation().subtract(0, 1, 0).getBlock();
			Block block2 = player.getLocation().subtract(0, 2, 0).getBlock();		
			
			if (e.getTo().getY() <= 88) {
				player.setHealth(0);
				player.sendMessage(prefix + "§c§lYou have lost!");
				player_list.remove(player);
				GameManager.stop();
				for (int i = 0; i < player_list.size(); i++) {
					for (Player g : Bukkit.getOnlinePlayers()) {
						g.sendTitle("§6§lWinner!", "§b" + player_list.get(i).getDisplayName() + " Has won the game!");
						g.sendMessage(prefix + player_list.get(i).getDisplayName() + " §bhas won!");
					}
				}
			}
			if (block1.getType() == Material.SAND || block1.getType() == Material.GRAVEL) {
				Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
		            public void run(){
				      	
				      	if (block1.getType() == Material.SAND) {
				      		BLOK_SAND.add(block1);
				      	}else if (block1.getType() == Material.GRAVEL) {
				      		BLOK_GRAVEL.add(block1);
				      	}else if (block2.getType() == Material.TNT) {
				      		BLOK_TNT.add(block2);
				      	}
				      	
				      	
		              block1.setType(Material.AIR); 
		              block2.setType(Material.AIR);
		              
		      		 
		            }
		          },3L);
			}
		}
	}
	
	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		if (GameState.getGameState() == GameState.INGAME || GameState.getGameState() == GameState.LOBBY) {
			if  (e.getEntity() instanceof Player) {
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onBlock(BlockBreakEvent e) {
		Block b = e.getBlock();
		String block = b.getTypeId() + ":" + b.getData() + ":" + b.getWorld().getName() + 
				":" + b.getX() + ":" + b.getY() + ":" + b.getZ();
		CHANGES.add(block);
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		Block b = e.getBlock();
		String block = b.getTypeId() + ":" + b.getData() + ":" + b.getWorld().getName() + 
				":" + b.getX() + ":" + b.getY() + ":" + b.getZ();
		CHANGES.add(block);
	}
	
	@EventHandler 
	public void onPoop(EntityChangeBlockEvent e) {
		Block b = e.getBlock();
		String block = b.getTypeId() + ":" + b.getData() + ":" + b.getWorld().getName() + 
				":" + b.getX() + ":" + b.getY() + ":" + b.getZ();
		CHANGES.add(block);
	}
	
	
}
