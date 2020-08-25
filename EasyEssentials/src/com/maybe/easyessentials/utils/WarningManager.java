package com.maybe.easyessentials.utils;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.maybe.easyessentials.Main;
import com.maybe.easyessentials.events.Events;

public class WarningManager extends BukkitRunnable {

	private Main plugin;
	Events Events;
	
	public WarningManager(Main plugin) {
		this.plugin = plugin;
	}
	
	public void SetupPlayer(Player p) {
		File f = new File("plugins/EasyEssentials/PlayerData/Warnings/" + p.getDisplayName() + ".yml");
		if(!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
		yml.addDefault("Name", p.getName());
		yml.addDefault("Warnings", 0);
		yml.options().copyDefaults(true);
		try {
			yml.save(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean addWarning(Player p, int warning) {
		File f = new File("plugins/EasyEssentials/PlayerData/Warnings" + p.getDisplayName() + ".yml");
		YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
		yml.set("Warnings", warning);
		try {
			yml.save(f);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public int getWarning(Player p) {
		File f = new File("plugins/EasyEssentials/PlayerData/Warnings" + p.getDisplayName() + ".yml");
		YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
		return yml.getInt("Warnings");
	}
	
	@Override
	public void run() {
		
	}

}
