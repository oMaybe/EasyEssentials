package com.maybe.easyessentials.commands.admin;

import java.util.ArrayList;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.maybe.easyessentials.Main;

public class Admin implements CommandExecutor {

	private Main plugin;
	public ArrayList<Player> adminmode = new ArrayList<Player>();
	
	GameMode prevGameMode = null;
	
	public Admin(Main plugin) {
		this.plugin = plugin;
	
		plugin.getCommand("admin").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (!(sender instanceof Player)) {
			sender.sendMessage("§cError! You cannot do this!");
			return true;
		}
		
		Player p = (Player) sender;
		
		if (cmd.getName().equalsIgnoreCase("admin")) {
			if (p.hasPermission("ee.admin.toggle")) {
				if (adminmode.contains(p)) {
					adminmode.remove(p);
					
					p.getInventory().clear();
					p.setGameMode(prevGameMode);
					
					p.sendMessage("§5You have §0disabled§5 adminmode!");
					return true;
				}else {
					prevGameMode = p.getGameMode();
					
					p.setGameMode(GameMode.CREATIVE);
					
					p.getInventory().clear();
					p.getInventory().setArmorContents(null);
					
					ItemStack item = new ItemStack(Material.WOOD_AXE, 1);
					ItemMeta meta = item.getItemMeta();
					meta.setDisplayName("§cBan Hammer");
					item.setItemMeta(meta);
					
					p.getInventory().setItem(0, new ItemStack(item));
					
					p.performCommand("/vanish");
					p.sendMessage("§5You have §0enabled§5 adminmode!");
					return true;
				}
			}
		}
		
		return false;
	}

}
