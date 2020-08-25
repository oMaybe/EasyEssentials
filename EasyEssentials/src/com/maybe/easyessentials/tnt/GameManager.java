package com.maybe.easyessentials.tnt;

import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.maybe.easyessentials.Main;

public class GameManager {
	
	public static Main plugin;
	
	public GameManager(Main plugin) {
		this.plugin = plugin;
	}
	
	//TODO: 
	// ADD ITEMS LIKE DOUBLE JUMP, POTS,
	// MAKE /SETSPAWN
	// REMOVE WEAHTER/DIFFICULTY
	
	public static void start() {
		GameState.setGameState(GameState.INGAME);	
		for (Player p : Bukkit.getOnlinePlayers()) {
			p.setGameMode(GameMode.ADVENTURE);
			p.setHealth(20);
			p.setFoodLevel(20);
			p.getWorld().setThunderDuration(0);
			p.getWorld().setStorm(false);
			p.getWorld().setStorm(false);
			p.getInventory().clear();
			p.getWorld().setDifficulty(Difficulty.PEACEFUL);
			Location loc = new Location(p.getWorld(), 287, 100, 279);
			
			// give power items
			
			/*ItemStack dj = new ItemStack(Material.FEATHER);
			ItemMeta dj1 = dj.getItemMeta();
			dj1.setDisplayName("§7Double Jump");
			dj.setItemMeta(dj1);
			
			p.getInventory().addItem(new ItemStack(dj));
			p.getInventory().addItem(new ItemStack(dj));
			p.getInventory().addItem(new ItemStack(dj));*/
			
			//p.teleport(loc);
		}
	}
	
	public static void stop() {
		Bukkit.getScheduler().cancelTask(CountDown.sched);
		Bukkit.getScheduler().cancelTask(CountDown.sched);
		GameState.setGameState(GameState.LOBBY);
		Bukkit.getScheduler().cancelTask(CountDown.sched);
	}
	
}
