package com.maybe.easyessentials.commands.admin;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.maybe.easyessentials.Main;

public class KaboomCommand implements CommandExecutor {

	private Main plugin;
	
	public KaboomCommand(Main plugin) {
		this.plugin = plugin;
		
		plugin.getCommand("kaboom").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player p = (Player) sender;
		ArrayList<Player> player_list = new ArrayList<>(p.getServer().getOnlinePlayers());		
		
		if (p.hasPermission("ee.kaboom")) {
			for (int i = 0; i < player_list.size(); i++) {
				Random r = new Random();
				Location loc = player_list.get(i).getLocation();
				loc.setY(loc.getY() + r.nextInt(80));
				player_list.get(i).teleport(loc);
				player_list.get(i).sendTitle("§c§lKABOOM!", "§eYou have been launched by " + p.getDisplayName());
				if (player_list.get(i).getInventory().contains(new ItemStack(Material.WATER_BUCKET))) {
				}else {
					player_list.get(i).getInventory().addItem(new ItemStack(Material.WATER_BUCKET));
				}
			}
			return true;
		}
		
		return false;
	}

}
