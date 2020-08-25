package com.maybe.easyessentials.commands.admin;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.maybe.easyessentials.Main;
import com.maybe.easyessentials.utils.Util;

public class SpeedCommand implements CommandExecutor {

	private Main plugin;
	
	public SpeedCommand(Main plugin) {
		this.plugin = plugin;
		
		plugin.getCommand("speed").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player p = (Player) sender;
		
		int speed;
		
		if (sender instanceof Player) {
			if(cmd.getName().equalsIgnoreCase("speed")) {
				if(sender instanceof Player) {
					if(p.hasPermission("speed.use")) {
						if(args.length == 1) {
							speed = Integer.parseInt(args[0]);
							if (!(speed < 1 || speed > 10)) {	
								try {		
									if (p.isFlying()) {
										p.setFlySpeed((float) speed / 10);
										p.sendMessage(Util.chat("&5You have set your flying speed to &0" + speed));
									}else {
										p.setWalkSpeed((float) speed / 10);
										p.sendMessage(Util.chat("&5You have set your walking speed to &0" + speed));
									}
								}catch (NullPointerException d) {
									d.printStackTrace();
								}
							}else {
								p.sendMessage(Util.chat("Please enter a number 1-10"));
							}
						}
						if(args.length == 2) {
							speed = Integer.parseInt(args[0]);
							Player a = Bukkit.getPlayer(args[1]);
							if (!(speed < 1 || speed > 10)) {
								try {
									if(a.isFlying()) {
										a.setFlySpeed((float) speed / 10);
										a.sendMessage(Util.chat("&0" + p.getDisplayName() + "&5has set your fly speed to &0" + speed));
										p.sendMessage(Util.chat("&5You have set &0" + a + " &5 fly's speed to &0" + speed));	
									}else {		
										a.setWalkSpeed(speed);
										a.sendMessage(Util.chat("&0" + p.getDisplayName() + "&5has set your walking speed to &0" + speed));
										p.sendMessage(Util.chat("&5You have set &0" + a.getDisplayName() + " &5 walking speed to &0" + speed));					
									}
								}catch(NullPointerException d) {
									d.printStackTrace();
								}
							}else {
								p.sendMessage(Util.chat("&cPlease enter a number 1-10"));
							}
						}
					}
				}
			}
		}else {
			Util.chat("&cYou cannot use this command!");
		}	
		return false;
	}
	
	
}
