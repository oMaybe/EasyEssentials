package com.maybe.easyessentials.tnt;

import java.util.UUID;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.maybe.easyessentials.Main;
import com.maybe.easyessentials.tag.PlayerManager;

public class TntTagCommand implements CommandExecutor {

	private Main plugin;
	
	public TntTagCommand(Main plugin) {
		this.plugin = plugin;
		
		plugin.getCommand("tt").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (cmd.getName().equalsIgnoreCase("tt")) {
			if (sender instanceof Player) {
				if (sender.hasPermission("tt.admin")) {
					if (args[0].equalsIgnoreCase("join")) {
						Player p = (Player) sender;
						plugin.gameManage.joined.add(p);
						if (plugin.gameManage.isStarted() == false) {
							UUID uuid = p.getUniqueId();
							if(plugin.playersLeftGame.contains(p)) {
								// TODO bungee push back
							}

							plugin.playermanager.put(uuid, new PlayerManager(uuid, false, 0, false, false));
							plugin.playersInGame.add(p);
							plugin.gameManage.lobbyWait(p);
						}
					}else if (args[0].equalsIgnoreCase("leave")) {
						Player p = (Player) sender;
						UUID uuid= p.getUniqueId();
						
						plugin.playermanager.remove(uuid);
						plugin.playersInGame.remove(p);
						plugin.playersLeftGame.add(p);
					}
				}
			}
		}
		
		return false;
	}
	
}
