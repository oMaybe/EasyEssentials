package com.maybe.easyessentials.commands.admin;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.maybe.easyessentials.Main;

public class BroadcastCommand implements CommandExecutor {
	
	private Main plugin;
	
	public BroadcastCommand(Main plugin) {
		this.plugin = plugin;
		
		plugin.getCommand("broadcast").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		
			if (cmd.getName().equalsIgnoreCase("broadcast")) {
				if (args.length == 1) {
					if (args[0].length() != 0) {
						String myString = "";
						for(int i = 0; i < args.length; i++){			
				            String arg = args[i] + " ";
				            myString = myString + arg;
				        }
						Bukkit.getServer().broadcastMessage(myString);
				    }
				}
			}
		
		return false;
	}
}
