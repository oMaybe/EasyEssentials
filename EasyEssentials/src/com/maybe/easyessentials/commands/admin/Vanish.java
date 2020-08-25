package com.maybe.easyessentials.commands.admin;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.maybe.easyessentials.Main;

public class Vanish implements CommandExecutor {

	private Main plugin;
	
	public ArrayList<Player> vanishe = new ArrayList<Player>();
	
	public Vanish(Main plugin) {
		this.plugin = plugin;
		
		plugin.getCommand("vanish").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player p = (Player) sender;
		
		if (!(sender instanceof Player) || !sender.hasPermission("vanish")) {
			sender.sendMessage("§cYou cannot vanish");
			return true;
		}
		
		if (cmd.getName().equalsIgnoreCase("vanish")) {
			if (!vanishe.contains(p)) {
				for (Player pl : Bukkit.getServer().getOnlinePlayers()) {
					pl.hidePlayer(p);
				}
				vanishe.add(p);
				p.sendMessage("§5You have toggled §0vanish §5to §0on");
				return true;
			}else {
				for (Player pl : Bukkit.getServer().getOnlinePlayers()) {
					pl.showPlayer(p);
				}
				vanishe.remove(p);
				p.sendMessage("§5You have toggled §0vanish §5to §0off");
				return true;
			}
		}	
		return true;
	}

}
