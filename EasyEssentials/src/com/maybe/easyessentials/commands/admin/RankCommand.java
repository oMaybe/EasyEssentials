package com.maybe.easyessentials.commands.admin;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.maybe.easyessentials.Main;
import com.maybe.easyessentials.utils.Util;
import com.maybe.easyessentials.utils.PlayerHandler;

public class RankCommand implements CommandExecutor {

	private Main plugin;
	
	PlayerHandler PlayerHandler;
	
	public RankCommand(Main plugin) {
		this.plugin = plugin;
		
		plugin.getCommand("rank").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		Player a = (Player) Bukkit.getPlayer(args[1]);
		String rank = args[1];
		if (!(sender instanceof Player)) {
			sender.sendMessage("You cannot do this u Idot");
		}
		if (cmd.getName().equalsIgnoreCase("rank")) {
			if (sender.hasPermission("easyessentials.rank")) {
				if (!(args.length == 0)) {
					if (args[0].length() != 0) {
						if (args[1].length() !=0) {
							if (args[1].equalsIgnoreCase("owner")) {
								PlayerHandler.setRank(a, 100);
							}else if (args[1].equalsIgnoreCase("coowner")) {
								PlayerHandler.setRank(a, 95);
							}else if (args[1].equalsIgnoreCase("staffmanager")) {
								PlayerHandler.setRank(a, 70);
							}else if (args[1].equalsIgnoreCase("admin")) {
								PlayerHandler.setRank(a, 65);
							}else if (args[1].equalsIgnoreCase("yt")) {
								PlayerHandler.setRank(a, 60);
							}else if (args[1].equalsIgnoreCase("mod")) {
								PlayerHandler.setRank(a, 50);
							}else if (args[1].equalsIgnoreCase("build")) {
								PlayerHandler.setRank(a, 40);
							}else if (args[1].equalsIgnoreCase("helper")) {
								PlayerHandler.setRank(a, 30);
							}else if (args[1].equalsIgnoreCase("warrior")) {
								PlayerHandler.setRank(a, 20);
							}else if (args[1].equalsIgnoreCase("vip")) {
								PlayerHandler.setRank(a, 10);
							}else if (args[1].equalsIgnoreCase("member")) {
								PlayerHandler.setRank(a, 0);
							}else {
								p.sendMessage(Util.chat("&cError! Rank cannot be found!"));
							}
						}else {
							p.sendMessage(Util.chat("&cPlease enter a rank!"));
						}
					}else {
						p.sendMessage(Util.chat("&cPlease enter a players name!"));
					}
				}else {
					p.sendMessage(Util.chat("&cPlease enter a players name!"));
				}
			}else {
				p.sendMessage(Util.chat("&cYou do not have enough permissions!"));
			}
		}
		
		return false;
	}

}
