package com.maybe.easyessentials.commands.admin;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.maybe.easyessentials.Main;
import com.maybe.easyessentials.utils.Util;

public class ScreenShareCommand implements CommandExecutor {
	
	@SuppressWarnings("unused")
	private Main plugin;
	
	public static ArrayList<Player> Froz = new ArrayList<Player>();
	
	public ScreenShareCommand(Main plugin) {
		this.plugin = plugin;
		
		plugin.getCommand("ss").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("ss") || cmd.getName().equalsIgnoreCase("screenshare")) {
			if(sender instanceof Player) {
				if(p.hasPermission("stupid.ss")) {
					if(args.length == 0) {
						p.sendMessage(Util.chat("&cError! please enter a players name!"));
					}else if (args[0].length() >= 1) {
						Player a = Bukkit.getPlayer(args[0]);
						
						if (Froz.contains(a)) {
							Froz.remove(a);
							p.sendMessage(Util.chat("&bYou have unfrozen " + a.getDisplayName() + "!"));
							a.sendMessage(Util.chat("&bYou have been unfrozen!"));
						}else {
							Froz.add(a);
							p.sendMessage(Util.chat("&bYou have frozen " + a.getDisplayName() + "!"));
							a.sendMessage(Util.chat("&c&lYou have been frozen by a staff member! you have 5 minutes to get on [???] on teamspeak!"));						
							a.sendMessage(Util.chat("&4DO NOT LOG OUT OR YOU'LL BE BANNED!!"));
						}
					}
				}else {
					p.sendMessage(Util.chat("&cError! You are not allowed to use this command!"));
				}
			}else {
				p.sendMessage(Util.chat("&cError! Only players are allowed to use this command!"));
			}
		}
		return false;
	}
	
	
}
