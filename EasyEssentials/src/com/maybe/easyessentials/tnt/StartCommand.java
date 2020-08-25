package com.maybe.easyessentials.tnt;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.omg.CORBA.VM_ABSTRACT;

import com.maybe.easyessentials.Main;
import com.maybe.easyessentials.events.Events;

public class StartCommand implements CommandExecutor {

	private static Main plugin;
	
	String prefix = "§7[§c§lTNT RUN§7] §b";
	
	public  StartCommand(Main plugin) {
		this.plugin = plugin;
		
		plugin.getCommand("tr").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player p = (Player) sender; 
		
		if (sender instanceof Player) {
			if (cmd.getName().equalsIgnoreCase("tr")) {
				if (p.hasPermission("tr.admin")) {			
					if (args.length >= 1) {
						if (args[0].equalsIgnoreCase("start")) {
								CountDown.start(false);
							p.sendMessage(prefix + "§bYou §astarted §bthe countdown");
						}else if (args[0].equalsIgnoreCase("gs")) {
							if (args.length >= 1) {
								if (args[1].equalsIgnoreCase("LOBBY")) {
									GameState.setGameState(GameState.LOBBY);
									p.sendMessage(prefix + "§bYou have set the gamestate to §aLOBBY");
								}else if (args[1].equalsIgnoreCase("INGAME")) {
									GameState.setGameState(GameState.INGAME);
									p.sendMessage(prefix + "§bYou have set the gamestate to §aINGAME");
								}else {
									p.sendMessage(prefix + "§cError! You can only use INGAME or LOBBY!");
								}
							}else {
								p.sendMessage(prefix + "§cError! Please enter a gamestate!");
							}
						}else if (args[0].equalsIgnoreCase("stop")) {
							GameManager.stop();
							p.sendMessage(prefix + "§bYou have §cstopped §bthe countdown!");
						}else if (args[0].equalsIgnoreCase("force")) {
							CountDown.start(true);
							p.sendMessage(prefix + "§bYou have §cFORCE §astarted §bthe countdown");
						}else if (args[0].equalsIgnoreCase("build")) {
							if (args.length >= 1) {
								Material bl = Material.matchMaterial(args[1]);
								for (int i = 0; i < 31; i++) {
									p.getLocation().subtract(0, 0, -i).getBlock().setType(bl);
									p.getLocation().subtract(-i, 0, 0).getBlock().setType(bl);
									for (int j = 0; j < 31; j++) {
										p.getLocation().subtract(-j, 0, -i).getBlock().setType(bl);
									}
								}
								p.sendMessage(prefix + "§bYou have built an arena!");
							}else {
								p.sendMessage(prefix + "§cPlease enter a block type!");
							}
						}else if (args[0].equalsIgnoreCase("join")){
							p.sendMessage(prefix + "§bYou have §ajoined §bthe game!");
							CountDown.playerJoin(p);
							CountDown.joined.add(p);
						}else if (args[0].equalsIgnoreCase("leave")) {
							p.sendMessage(prefix + "§bYou have §cleft §bthe game!");
							CountDown.playerLeave(p);
							CountDown.joined.remove(p);
						}else if (args[0].equalsIgnoreCase("reload")){
							//TODO: MAKE A MAP RESET
							restore(p);
						}else {
							p.sendMessage(prefix + "§cError! That is not a command!");
						}
					}else {
						p.sendMessage(prefix + "§cPlease enter a aurgument!");
					}
				}else {
					p.sendMessage(prefix + "§cNo perm!");
				}
			}
		}
	
		return false;
	}

	@SuppressWarnings("deprecation")
	public static void restore(Player player) {
		/*int blocks = 0;
		
		for (String b : Event.CHANGES) {
				String[] blockdata = b.split(":");
				int id = Integer.parseInt(blockdata[0]);
				byte data = Byte.parseByte(blockdata[1]);
				World world = Bukkit.getWorld(blockdata[2]);
				int x = Integer.parseInt(blockdata[3]);
				int y = Integer.parseInt(blockdata[4]);
				int z = Integer.parseInt(blockdata[5]);
				
				world.getBlockAt(x, y, z).setTypeId(id);
				world.getBlockAt(x, y, z).setData(data);
				blocks++;
		}
		player.sendMessage("§7MapReset: §e" + blocks + " §aBlocks have been reset!");*/
		
		int blo = 0;
		
		for (Block bl : Event.BLOK_TNT) {
			bl.setType(Material.TNT);
			blo++;
		}
		
		for (Block bl : Event.BLOK_SAND) {
				bl.setType(Material.SAND);
				blo++;
		}
		
		for (Block bl : Event.BLOK_GRAVEL) {
			bl.setType(Material.GRAVEL);
			blo++;
		}
		
		
		
		player.sendMessage("§7MapReset: §e" + blo + " §aBlocks have been reset!");
	}

		
}
