package com.maybe.easyessentials.commands.admin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.maybe.easyessentials.Main;
import com.maybe.easyessentials.utils.Util;

public class FlyCommand implements CommandExecutor {

	private Main plugin;
	
	public FlyCommand(Main plugin) {
		this.plugin = plugin;
		
		plugin.getCommand("fly").setExecutor(this);
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(!(sender instanceof Player)) {
			sender.sendMessage(Util.chat("&cOnly players are allowed to execute this command!"));
			return true;
		}
		
		Player p = (Player) sender;
		
		if(p.hasPermission("stupid.fly")) {
			if(p.getAllowFlight() == true) {
				p.setAllowFlight(false);
				p.setFlying(false);
				p.sendMessage(Util.chat("&5Flight is now &0disabled!"));
				return true;
			}else if(p.getAllowFlight() == false) {		
				p.setAllowFlight(true);
				p.setFlying(true);
				p.sendMessage(Util.chat("&5Flight is now &0enabled!"));
			}
		}else {
			p.sendMessage(Util.chat("&cError! You have no permission!"));
		}
		
		return false;
	}

}
