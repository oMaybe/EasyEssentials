package com.maybe.easyessentials.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.maybe.easyessentials.Main;
import com.maybe.easyessentials.utils.PlayerHandler;

public class EmojiCommand implements CommandExecutor {

	private Main plugin;
	
	PlayerHandler PlayerHandler;
	
	public EmojiCommand(Main plugin) {
		this.plugin = plugin;
		
		plugin.getCommand("emoji").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player p = (Player) sender;
		
		if (cmd.getName().equalsIgnoreCase("emoji")) {
			if (p.hasPermission("ee.use")) {
				p.sendMessage("§aHere's a list: ");
                sender.sendMessage(ChatColor.GOLD + "<3 " + ChatColor.WHITE + " -  " + ChatColor.RED + "\u2764");
                sender.sendMessage(ChatColor.GOLD + ":star: " + ChatColor.WHITE + " -  " + ChatColor.GOLD + "\u272e");
                sender.sendMessage(ChatColor.GOLD + ":shrug: " + ChatColor.WHITE + " -  " + ChatColor.YELLOW + "¯\\_(\u30c4)_/¯");
                sender.sendMessage(ChatColor.GOLD + ":tableflip: " + ChatColor.WHITE + " -  " + ChatColor.RED + "§c(\u256f°\u25a1°\uff09\u256f§f\ufe35 §7\u253b\u2501\u253b");
                sender.sendMessage(ChatColor.GOLD + "o/ " + ChatColor.WHITE + " -  " + ChatColor.LIGHT_PURPLE + "( \uff9f\u25e1\uff9f)/");
                sender.sendMessage(ChatColor.GOLD + ":123: " + ChatColor.WHITE + " -  " + ChatColor.GREEN + "§a1§e2§c3");
                sender.sendMessage(ChatColor.GOLD + ":totem: " + ChatColor.WHITE + " -  " + ChatColor.AQUA + "§b\u2609§e_§b\u2609");
                sender.sendMessage(ChatColor.GOLD + ":yes: " + ChatColor.WHITE + " -  " + ChatColor.RED + "§a\u2714");
                sender.sendMessage(ChatColor.GOLD + ":no: " + ChatColor.WHITE + " -  " + ChatColor.GOLD + "§c\u2716");
                sender.sendMessage(ChatColor.GOLD + ":arrow: " + ChatColor.WHITE + " -  " + ChatColor.YELLOW + "§9\u279c");
                sender.sendMessage(ChatColor.GOLD + ":totem: " + ChatColor.WHITE + " -  " + ChatColor.RED + "§b\u2609_\u2609");
                sender.sendMessage(ChatColor.GOLD + ":typing: " + ChatColor.WHITE + " -  " + ChatColor.LIGHT_PURPLE + "§e\u270e§6...");
                sender.sendMessage(ChatColor.GOLD + ":maths: " + ChatColor.WHITE + " -  " + ChatColor.GREEN + "§b\u221a§e(§b\u03c0§e+§bx§e)=§bL");
                sender.sendMessage(ChatColor.GOLD + ":snail: " + ChatColor.WHITE + " -  " + ChatColor.AQUA + "§e@§a'§e-§a'");
                sender.sendMessage(ChatColor.GOLD + ":gimme: " + ChatColor.WHITE + " -  " + ChatColor.RED + "§e\u0f3c\u3064\u25d5_\u25d5\u0f3d\u3064");
                sender.sendMessage(ChatColor.GOLD + ":wizard: " + ChatColor.WHITE + " -  " + ChatColor.LIGHT_PURPLE + "§b('-')\u2283\u2501§e\u2606§b\uff9f.§c*§b\uff65\uff61");
                sender.sendMessage(ChatColor.GOLD + ":pvp: " + ChatColor.WHITE + " -  " + ChatColor.GREEN + "§b\u2694");
                sender.sendMessage(ChatColor.GOLD + ":oof: " + ChatColor.WHITE + " -  " + ChatColor.AQUA + "§c§lOOF");
			}
		}
		
		return false;
	}

}
