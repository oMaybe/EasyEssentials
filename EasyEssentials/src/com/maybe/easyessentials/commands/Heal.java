package com.maybe.easyessentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.maybe.easyessentials.Main;

public class Heal implements CommandExecutor {

	private Main plugin;
	
	public Heal(Main plugin) {
		this.plugin = plugin;
		
		plugin.getCommand("heal").setExecutor(this);
	}
	
	public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
		Player player = (Player) sender;
		if (alias.equalsIgnoreCase("heal")) {
			if (args.length == 0) {
				//Player only typed '/heal' so lets heal them back!
				player.setHealth(20.0);
			} else {
				//Player typed something more
				Player target = Bukkit.getPlayerExact(args[0]);
				if (target == null) {
					//Target is not online
					player.sendMessage("Your target " + args[0] + " is not online!");
				} else {
					//Targets online
					player.sendMessage("You've healed " + args[0]);
					target.setHealth(20.0);
				}
			}
		}
		return true;
	}
}