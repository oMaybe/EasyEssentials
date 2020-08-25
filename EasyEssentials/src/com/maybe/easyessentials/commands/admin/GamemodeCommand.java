package com.maybe.easyessentials.commands.admin;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.maybe.easyessentials.Main;
import com.maybe.easyessentials.utils.Util;

public class GamemodeCommand implements CommandExecutor {
	
	private Main plugin;
	
	public GamemodeCommand(Main plugin) {
		this.plugin = plugin;
		
		plugin.getCommand("gm").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("gm")) {
			if(sender instanceof Player) {
				if(p.hasPermission("gm.use")) {
					if(args.length == 1) {
						if(args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("s")) {
							p.setGameMode(GameMode.SURVIVAL);
							p.sendMessage(Util.chat("&5Your gamemode has been set to &0Survival"));
						}else if (args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("c")) {
							p.setGameMode(GameMode.CREATIVE);
							p.sendMessage(Util.chat("&5Your gamemode has been set to &0Creative"));
						}else if (args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("a")) {
							p.setGameMode(GameMode.ADVENTURE);
							p.sendMessage(Util.chat("&5Your gamemode has been set to &0Adventure"));
						}else if (args[0].equalsIgnoreCase("3") || args[0].equalsIgnoreCase("sp")) {
							p.setGameMode(GameMode.SPECTATOR);
							p.sendMessage(Util.chat("&5Your gamemode has been set to &0Spectator"));
						}else {
							p.sendMessage(Util.chat("&cError! correct usage: /gm <s,c,a,sp,0,1,2,3> <player>"));
						}
					}
					if(args.length == 2) {
						try {
							Player a = Bukkit.getPlayer(args[1]);
							if(args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("s")) {
								a.setGameMode(GameMode.SURVIVAL);
								p.sendMessage(Util.chat("&5You set &a" + a.getDisplayName() + "'s&5 gamemode to &0Survival"));
								a.sendMessage(Util.chat("&5Your gamemode has been set to &6Survival"));
							}else if (args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("c")) {
								a.setGameMode(GameMode.CREATIVE);
								p.sendMessage(Util.chat("&5You set &a" + a.getDisplayName() + "'s&5 gamemode to &0Creative"));
								a.sendMessage(Util.chat("&5Your gamemode has been set to &0Creative"));
							}else if (args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("a")) {
								a.setGameMode(GameMode.ADVENTURE);
								p.sendMessage(Util.chat("&5You set &a" + a.getDisplayName() + "'s&5 gamemode to &0Adventure"));
								a.sendMessage(Util.chat("&5Your gamemode has been set to &6Adventure"));
							}else if (args[0].equalsIgnoreCase("3") || args[0].equalsIgnoreCase("sp")) {
								a.setGameMode(GameMode.SPECTATOR);
								p.sendMessage(Util.chat("&5You set &a" + a.getDisplayName() + "'s&5 gamemode to &0Spectator"));
								a.sendMessage(Util.chat("&5Your gamemode has been set to &0Spectator"));
							}
						}catch(NullPointerException d) {
							d.printStackTrace();
						}
					}
				}else {
					sender.sendMessage(Util.chat("&cError! You don't have enough permission!"));
				}
			}else {
				sender.sendMessage(Util.chat("&cOnly players can execute this command!"));
			}
		}
		return false;
	}
	
}
