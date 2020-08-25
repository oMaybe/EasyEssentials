package com.maybe.easyessentials.tag;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import com.maybe.easyessentials.Main;

public class playerScoreboard {
	
	public static ScoreboardManager m;
	public static Scoreboard b;
	public static Objective o;
	public static Score gameMode;
	public static Score time;
	public static Score coins;
	public static Score alive;
	public static Score dead;
	
	private static Main plugin = Main.getPlugin(Main.class);
	
	public static void scoreGame(Player player, int timeLeft) {
		m = Bukkit.getScoreboardManager();
		b = m.getNewScoreboard();
		o = b.registerNewObjective("TntTag", "");
		o.setDisplaySlot(DisplaySlot.SIDEBAR);
		o.setDisplayName("§6OMAYBE");
		
		if (plugin.playersInGame.size() == 1) {
			time = o.getScore("§fTime: §aComplete");
			time.setScore(4);
			return;
		}else {
			time = o.getScore("§fTime: §a" + timeLeft);
			time.setScore(4);
		}
		
		coins = o.getScore("Coins: §a100");
		coins.setScore(3);
		
		gameMode = o.getScore("Game: §aTNT TAG");
		gameMode.setScore(2);
		
		alive = o.getScore("§fAlive: §a" + plugin.playersInGame.size());
		alive.setScore(1);
		
		dead = o.getScore("§fDead: §a" + (plugin.gameManage.joined.size() - plugin.playersInGame.size()));
		dead.setScore(0);
		
		player.setScoreboard(b);
	}
	
	public static void scoreLobby(Player player) {
		m = Bukkit.getScoreboardManager();
		b = m.getNewScoreboard();
		o = b.registerNewObjective("TntTag", "");
		o.setDisplaySlot(DisplaySlot.SIDEBAR);
		o.setDisplayName("§6OMAYBE");
		
		time = o.getScore("§fTime: §7Not Started");
		time.setScore(4);
		
		gameMode = o.getScore("Game: §aTNT TAG");
		gameMode.setScore(3);
		
		coins = o.getScore("Coins: §a100");
		coins.setScore(2);		
		
		alive = o.getScore("§fPlayers: §a" + plugin.playersInGame.size() + " §f/§a" + plugin.gameManage.playerNeeded);
		alive.setScore(1);
		
		player.setScoreboard(b);
	}
}
