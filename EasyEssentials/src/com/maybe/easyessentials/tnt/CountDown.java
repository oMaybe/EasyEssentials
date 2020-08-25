package com.maybe.easyessentials.tnt;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.maybe.easyessentials.Main;

public class CountDown {

    static boolean isStarted = false;
    static int count = 60;
    static int sched;

    static ArrayList<Player> joined = new ArrayList<Player>();
    
    public static void start(boolean force){
        if(GameState.getGameState() != GameState.LOBBY){
            return;
        }
        if(force || joined.size() >= 2){
            if(!isStarted) {
                if (force) {
                    count = 5;
                }
                sched = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(Main.class), new Runnable() {
                    @Override
                    public void run() {
                        joined.forEach(p -> p.setLevel(count));
                        if (count == 60 || count == 45 || count == 30 || count == 15 || count == 10 || count <= 5 && count != 0) {
                            joined.forEach(p -> p.sendMessage(Main.getPlugin(Main.class).prefix
                                    + "§aThe game will start in §8" + count + " Seconds§a!"));
                        } else if (count == 0) {
                            joined.forEach(p -> p.sendMessage(Main.getPlugin(Main.class).prefix
                                    + "§aThe Game has started!"));
                            Bukkit.getScheduler().cancelTask(sched);
                            GameManager.start();                     
                            Bukkit.getScheduler().cancelTask(sched);
                            return;
                        }
                        count--;
                    }
                }, 20, 20);
            }
        }
    }
    
    public static void playerJoin(Player player) {
    	joined.forEach(p -> p.sendMessage(Main.getPlugin(Main.class).prefix + player.getDisplayName() + " §bhas joined the game!"));
    }
    
    public static void playerLeave(Player player) {
    	joined.forEach(p -> p.sendMessage(Main.getPlugin(Main.class).prefix + player.getDisplayName() + " §bhas left the game!"));
    }
}
