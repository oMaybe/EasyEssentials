package com.maybe.easyessentials.commands.admin;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.maybe.easyessentials.Main;
import com.maybe.easyessentials.utils.Util;

public class MuteCommand implements CommandExecutor {

	private Main plugin;
	
	public MuteCommand(Main plugin) {
		this.plugin = plugin;
	}
	
	public ArrayList<Player> mute = new ArrayList<Player>();
	public ArrayList<String> mute_reason = new ArrayList<String>();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		Player a = Bukkit.getPlayer(args[0]);
		String reason = args[1];
		if (cmd.getName().equalsIgnoreCase("mute")) {
			if (sender.hasPermission("easyessentials.mute")) {
				if (!(args.length == 0)) {
					if (args[0].length() != 0) {
						if (args[1].length() !=0) {
							if (mute.contains(a)) {
								mute.remove(a);
								p.sendMessage(Util.chat("&5You have unmuted &0 " + a.getDisplayName() + " &5for the reason of &0" + reason));
								a.sendMessage(Util.chat("&5You have been unmuted for " + reason));
							}else {					
								mute.add(a);
								p.sendMessage(Util.chat("&5You have muted &0 " + a.getDisplayName() + " &5for the reason of " + reason));
								a.sendMessage(Util.chat("&5You have been muted for &0" + reason + "!"));
							}
						}else {
							p.sendMessage(Util.chat("&cPlease enter a r!"));
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
