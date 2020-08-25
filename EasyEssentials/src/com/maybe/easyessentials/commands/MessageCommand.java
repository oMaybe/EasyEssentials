package com.maybe.easyessentials.commands;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.maybe.easyessentials.Main;
import com.maybe.easyessentials.utils.PlayerHandler;
import com.maybe.easyessentials.utils.Util;

public class MessageCommand implements CommandExecutor {

	private Main plugin;
	
	public Map<Player, Player> lastMessageSender = new HashMap<>();
	
	public MessageCommand(Main plugin) {
		this.plugin = plugin;
		
		plugin.getCommand("msg").setExecutor(this);
		plugin.getCommand("reply").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender commandSender, Command cmd, String label, String[] args) {
		if (commandSender instanceof Player) {
			final Player sender = (Player) commandSender;
			if (cmd.getName().equalsIgnoreCase("msg")) {
				if (args.length >= 2) {
					String targetName = args[0];
					Player target = Bukkit.getPlayer(targetName);
					String message = getMessage(args, 1);
					if (target != null) {
						target.sendMessage("§5[From " + sender.getName() + "§5]§f " + message);
						sender.sendMessage("§5[To " + target.getName() + "§5]§f " + message);
						lastMessageSender.put(target, sender);
					}else {
						sender.sendMessage(targetName + " has disconnected.");
					}
				}
			}else if (cmd.getName().equalsIgnoreCase("reply")) {
				if (args.length >= 1) {
					Player target = lastMessageSender.get(sender);
					String message = getMessage(args, 0);			
					if (target != null) {
						if (target.isOnline()) {
							target.sendMessage(Util.chat("&5[From " + sender.getName() + "&5]&f " + message));
							sender.sendMessage(Util.chat("&5[To " + target.getName() + "&5]&f " + message));
							lastMessageSender.put(target, sender);
						}else {
							sender.sendMessage(Util.chat(target + " has disconnected."));
						}
					}else {
						sender.sendMessage(Util.chat("Nobody sent you a message!"));
					}
				}
			}
		}	
		return true;
	}
	
	public static String emoji(String msg ) {
		return null;
	}
	
	private static String getMessage(String[] args, int index) {
		StringBuilder sb = new StringBuilder();
		for (int i = index; i < args.length; i++) {
			sb.append(args[i]);
		}
	//	sb.setLength(sb.length() - 1);
		return sb.toString();
	}

}
