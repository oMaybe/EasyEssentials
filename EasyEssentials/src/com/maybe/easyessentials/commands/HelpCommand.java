package com.maybe.easyessentials.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.maybe.easyessentials.Main;
import com.maybe.easyessentials.utils.Util;

public class HelpCommand implements CommandExecutor {
	
	private Main plugin;
	
	public HelpCommand(Main plugin) {
		this.plugin = plugin;
		
		plugin.getCommand("help").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("help")) {
			if (args.length == 0) {
				if (args[0].equalsIgnoreCase("1")) {
					DisplayHelp(p, 1);
				}else if (args[0].equalsIgnoreCase("2")) {
					DisplayHelp(p, 2);
				}else if (args[0].equalsIgnoreCase("3")) {
					DisplayHelp(p, 3);
				}else {
					p.sendMessage(Util.chat("&cError! Invalid page number! Enter a page number 1-3!"));
				}
			}
		}
		return false;
	}
	
	public static void DisplayHelp(Player p, int page) {
		if (page == 1) {
			p.sendMessage(Util.chat("&b=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-="));
			p.sendMessage(Util.chat("&b=-=- &3EasyEssentials &b Plugin!"));
			p.sendMessage(Util.chat("&b=-=- &1Author &b oMaybe"));
			p.sendMessage(Util.chat("&b=-=- &2Version &b 1.4"));
			p.sendMessage(Util.chat("&b=-=- &5Commands: &b /help 2 for page 2"));
			p.sendMessage(Util.chat("&b=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-="));	
		}else if (page == 2) {
			p.sendMessage(Util.chat("&b=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-="));
			p.sendMessage(Util.chat("&b=-=- &4NOTE: &b Values that have [] around"));
			p.sendMessage(Util.chat("&b=-=- &bare optional. <> are required!"));
			p.sendMessage(Util.chat("&b=-=- "));
			p.sendMessage(Util.chat("&b=-=- &5/gm <0-3;s,c,a,sp> [player]"));
			p.sendMessage(Util.chat("&b=-=- &3/speed <1-10> [player]"));
			p.sendMessage(Util.chat("&b=-=- &2/ss <player>"));
			p.sendMessage(Util.chat("&b=-=- &1/fly|flight <player>"));
			p.sendMessage(Util.chat("&b=-=- "));
			p.sendMessage(Util.chat("&b=-=- &5Commands: &b /help 3 for page 3"));
			p.sendMessage(Util.chat("&b=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-="));	
		}else if (page == 3) {
			p.sendMessage(Util.chat("&b=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-="));
			p.sendMessage(Util.chat("&b=-=- &7/msg <player> <message> | /reply <message>"));
			p.sendMessage(Util.chat("&b=-=-"));
		}else {
			p.sendMessage(Util.chat("&cError! Invalid page number! Enter a page number 1-3!"));
		}
		
	}
	
}
