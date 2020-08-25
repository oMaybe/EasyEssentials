package com.maybe.easyessentials.commands.admin;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.maybe.easyessentials.Main;
import com.maybe.easyessentials.utils.Util;

public class StaffChat implements CommandExecutor {
	
	@SuppressWarnings("unused")
	private Main plugin;
	
	//static boolean isStaffChat = false;
	
	public static ArrayList<Player> Insc = new ArrayList<Player>();
	
	public StaffChat(Main plugin) {
		this.plugin = plugin;
		
		plugin.getCommand("staffchat").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (cmd.getName().equalsIgnoreCase("staffchat")) {
			if(!(sender instanceof Player)) {
				sender.sendMessage(Util.chat("&cYou must be a player to execute this command!"));
				return true;
			}
			
			Player p = (Player) sender;
			
			if (args.length == 0) {
				if(!(p.hasPermission("stupid.staffchat.use"))) {
					p.sendMessage(Util.chat("&cYou do not have permission!"));
				}else {
					if (Insc.contains(p)) {
						Insc.remove(p);
						p.sendMessage(Util.chat("&5Staffchat has been &0Disabled!"));
						return true;
					}else {
						Insc.add(p);
						p.sendMessage(Util.chat("&5Staffchat has been &0Enabled!"));
						return true;
					}
				}
			}
		}	
		return false;
	}
	
}
