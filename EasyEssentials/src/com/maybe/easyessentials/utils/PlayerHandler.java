package com.maybe.easyessentials.utils;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import org.bukkit.ChatColor;

public class PlayerHandler {
	
	public int OWNER = 100;
	public int CO_OWNER = 95;
	public int STAFF_MANAGER = 70;
	public int ADMIN = 65;
	public int YOUTUBE = 60;
	public int MODERATOR = 50;
	public int BUILDER = 40;
	public int HELPER = 30;
	public int WARRIOR = 20;
	public int VIP = 10;
	public int MEMBER = 0;
	
	public void SetupPlayer(Player p) {
		File f = new File("plugins/EasyEssentials/PlayerData/" + p.getDisplayName() + ".yml");
		if(!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
		yml.addDefault("Name", p.getName());
		yml.addDefault("Rank", MEMBER);
		yml.options().copyDefaults(true);
		try {
			yml.save(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean setRank(Player p, int rank) {
		File f = new File("plugins/EasyEssentials/PlayerData/" + p.getDisplayName() + ".yml");
		YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
		yml.set("Rank", rank);
		try {
			yml.save(f);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public int getRank(Player p) {
		File f = new File("plugins/EasyEssentials/PlayerData/" + p.getDisplayName() + ".yml");
		YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
		return yml.getInt("Rank");
	}
	
	public String getRankPrefix(int Rank) {
		if(Rank == OWNER) {
			return Util.chat("[&4Owner&f] ");
		}else if (Rank == CO_OWNER) {
			return Util.chat("[&5Co-Owner&f] ");
		}else if (Rank == STAFF_MANAGER) {
			return Util.chat("[&3Staff&f] ");
		}else if (Rank == ADMIN) {
			return Util.chat("[&6Admin&f] ");
		}else if (Rank == YOUTUBE) {
			return Util.chat("[&cYou&ftube] ");
		}else if (Rank == MODERATOR) {
			return Util.chat("[&2Moderator&f] ");
		}else if (Rank == BUILDER){
			return Util.chat("[&3Build&f] ");
		}else if (Rank == HELPER){
			return Util.chat("[&1Helper&f] ");
		}else if (Rank == WARRIOR){
			return Util.chat("[&8War&7r&fior] ");
		}else if (Rank == VIP){
			return Util.chat("[&bV&3I&bP&f] ");
		}else if (Rank == MEMBER){
			return Util.chat("[&eMember&f] ");
		}else {
			return "";
		}
	}
	
		public void refreshRanks() {
			for (Player p : Bukkit.getOnlinePlayers()) {
				Scoreboard board = Bukkit.getServer().getScoreboardManager().getNewScoreboard();
				for (Player pl : Bukkit.getOnlinePlayers()) {
					String prefix = getRankPrefix(getRank(pl));
					Team team = board.registerNewTeam(pl.getName());
					team.setPrefix(prefix);
					//team.addPlayer(pl);
					team.addEntry(pl.getName());
			}
			
			Objective objective = board.registerNewObjective("Stats", "dummy");
			
			objective.setDisplaySlot(DisplaySlot.SIDEBAR);
			objective.setDisplayName("§c§lTest§f§lServer");
			
			String prefix = getRankPrefix(getRank(p));
			
			Score rankScore = objective.getScore("§fRank: " + prefix);
			rankScore.setScore(0);
			
			p.setScoreboard(board);
		}
	}
	
}
