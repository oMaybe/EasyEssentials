package com.maybe.easyessentials.commands.admin;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.maybe.easyessentials.Main;

public class bcCommand implements CommandExecutor {

	private Main plugin;
	
	public bcCommand(Main plugin) {
		this.plugin = plugin;
		
		plugin.getCommand("bc").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (sender.hasPermission("bc")) {
			if (args.length >= 1) {
				Player p = (Player) sender;
				ArrayList<Player> player_list = new ArrayList<>(p.getServer().getOnlinePlayers());		
				String myString = "";
				for(int i = 0; i < args.length; i++){			
		            String arg = args[i] + " ";
		            myString = myString + arg;
		        }
				for (int i = 0; i < player_list.size(); i++) {	        
			        player_list.get(i).sendTitle(ChatColor.translateAlternateColorCodes('&', myString), p.getDisplayName());
				}
			}
		}
		
		return false;
	}

}
