package com.maybe.easyessentials.events;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.maybe.easyessentials.Main;
import com.maybe.easyessentials.commands.HelpCommand;
import com.maybe.easyessentials.commands.MessageCommand;
import com.maybe.easyessentials.commands.admin.MuteCommand;
import com.maybe.easyessentials.commands.admin.ScreenShareCommand;
import com.maybe.easyessentials.commands.admin.StaffChat;
import com.maybe.easyessentials.commands.admin.Vanish;
import com.maybe.easyessentials.utils.PlayerHandler;
import com.maybe.easyessentials.utils.Util;
import com.maybe.easyessentials.utils.WarningManager;

public class Events implements Listener {
	
	private Main plugin;
	
    PlayerHandler PlayerHandler;
	Main main;
	MuteCommand MuteCommand;
	MessageCommand MessageCommand;
	WarningManager WarningManager;
	AsyncChat AC;
	Vanish V;
	
    private ArrayList<Player> cooldown = new ArrayList<Player>(), nofall = new ArrayList<Player>();
    
	public Events(PlayerHandler _PlayerHandler) {
		this.plugin = plugin;
		PlayerHandler = _PlayerHandler;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		PlayerHandler.SetupPlayer(p);
		PlayerHandler.refreshRanks();		
		e.setJoinMessage(Util.chat("&3>&6>&4>&f" + PlayerHandler.getRankPrefix(PlayerHandler.getRank(p)) + "&f" + p.getDisplayName() + " has hopped in! <&6<&3<"));
		if (V.vanishe.isEmpty()) {
			return;
		}else {
			for (Player g : V.vanishe){
				e.getPlayer().showPlayer(g);
			}
		}
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		e.setQuitMessage(Util.chat("&3>&6>&4>&f" + PlayerHandler.getRankPrefix(PlayerHandler.getRank(p)) + "&f" + p.getDisplayName() + " has hopped out :( <&6<&3<"));
		if (MessageCommand.lastMessageSender.containsValue(p)) {
			MessageCommand.lastMessageSender.remove(e.getPlayer());
		}
		V.vanishe.remove(e.getPlayer());
	}
	
	@EventHandler
	public void shootFireball(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (p.getItemInHand().getType() == Material.FIREBALL) {
			Location loc = p.getLocation();
			Entity f = loc.getWorld().spawnEntity(loc.add(loc.getDirection()), EntityType.FIREBALL);
			//f.setVelocity(loc.getDirection().multiply(0.0001));
			//p.setVelocity(p.getLocation().getDirection().multiply(-1));
		}
	}
	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent e) {
		e.setCancelled(true);
		Player p = e.getPlayer();
		String name = p.getName();
		String sc = Util.chat("&f[&3&lStaffChat&f]");
		String prefix = PlayerHandler.getRankPrefix(PlayerHandler.getRank(p));
		String message = e.getMessage();
		String[] words = message.split(" ");
		
			if (StaffChat.Insc.contains(p)) {
				e.setCancelled(true);
				for (Player staff : Bukkit.getServer().getOnlinePlayers()) {
					if(staff.hasPermission("stupid.staffchat.see")) {
						staff.sendMessage(sc + " " + prefix + name + ": " + ChatColor.AQUA + message);
					}
				}
			}else {
				if (PlayerHandler.getRank(p) == PlayerHandler.MEMBER) {
					Bukkit.broadcastMessage(prefix + name + ": " + ChatColor.GRAY + message);
				}else if (PlayerHandler.getRank(p) >= PlayerHandler.HELPER) {
					if (p.hasPermission("ee.expressions.use")){
						message = message.replace("<3", "§c\u2764§r");
			            message = message.replace(":star:", "§6\u272e§r");
			            message = message.replace(":shrug:", "§e¯\\_(\u30c4)_/¯§r"     );
			            message = message.replace(":tableflip:", "§c(\u256f°\u25a1°\uff09\u256f§f\ufe35 §7\u253b\u2501\u253b§r"     );
			            message = message.replace("o/", "§d( \uff9f\u25e1\uff9f)/§r"     );
			            message = message.replace(":123:", "§a1§e2§c3§r"     );
			            message = message.replace(":totem:", "§b\u2609§e_§b\u2609§r"     );
			            message = message.replace(":yes:", "§a\u2714§r"     );
			            message = message.replace(":no:", "§c\u2716§r"     );
			            message = message.replace(":arrow:", "§9\u279c§r"     );
				        message = message.replace(":totem:", "§b\u2609_\u2609§r"     );
			            message = message.replace(":typing:", "§e\u270e§6...§r"     );				            message = message.replace(":maths:", "§b\u221a§e(§b\u03c0§e+§bx§e)=§bL§r"     );
					    message = message.replace(":snail:", "§e@§a'§e-§a'§r"     );
					    message = message.replace(":gimme:", "§e\u0f3c\u3064\u25d5_\u25d5\u0f3d\u3064§r"     );
					    message = message.replace(":wizard:", "§b('-')\u2283\u2501§e\u2606§b\uff9f.§c*§b\uff65\uff61\uff9f§r"     );
					    message = message.replace(":pvp:", "§b\u2694§r"     );
					    message = message.replace(":oof:", "§c§lOOF§r"     );
						e.setMessage(message);
					}
					Bukkit.broadcastMessage(prefix + name + ": " + form(message));
				}	
			}
		}
	
	public String form(String msg) {
		return ChatColor.translateAlternateColorCodes('&', msg);
	}
	
	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent e) {
		Player p = e.getPlayer();
		String[] args = e.getMessage().split(" ");
		String cmd = args[0].replace("/", "").toLowerCase();
		int rank = PlayerHandler.getRank(p);
		if(cmd.equals("rank")) {
			if (rank >= PlayerHandler.ADMIN) {
				e.setCancelled(true);
				if(args.length == 3) {
					String targetName = args[1];
					Player target = Bukkit.getPlayer(targetName);
					if(target != null) {
						int rankValue = 0;
						String rankName = args[2].toLowerCase();
						if(rankName.equals("owner")) {
							rankValue = PlayerHandler.OWNER;
						}else if (rankName.equals("coowner")) {
							rankValue = PlayerHandler.CO_OWNER;
						}else if (rankName.equals("manager")) {
							rankValue = PlayerHandler.STAFF_MANAGER;
						}else if (rankName.equals("admin")) {
							rankValue = PlayerHandler.ADMIN;
						}else if (rankName.equals("yt")) {
							rankValue = PlayerHandler.YOUTUBE;
						}else if (rankName.equals("moderator")) {
							rankValue = PlayerHandler.MODERATOR;
						}else if (rankName.equals("builder")) {
							rankValue = PlayerHandler.BUILDER;
						}else if (rankName.equals("helper")) {
							rankValue = PlayerHandler.HELPER;
						}else if (rankName.equals("warrior")) {
							rankValue = PlayerHandler.WARRIOR;
						}else if (rankName.equals("vip")) {
							rankValue = PlayerHandler.VIP;
						}else if (rankName.equals("member")) {
							rankValue = PlayerHandler.MEMBER;
						}else {
							rankValue = -1;
						}
						if(rankValue >= 0) {
							if(PlayerHandler.setRank(target, rankValue)) {
								p.sendMessage("§aSuccessfully set §f" + target.getName() + "'s rank to " + rankName);
								target.sendMessage("§aYour rank has been changed to " + PlayerHandler.getRankPrefix(PlayerHandler.getRank(target)));
								PlayerHandler.refreshRanks();
							}
						}else {
							p.sendMessage("§cError: " + rankName + " is not a rank!");
						}
					}else {
						p.sendMessage("§cError: " + PlayerHandler.getRankPrefix(PlayerHandler.getRank(p)) + targetName + " is not online!");
					}
				}else {
					p.sendMessage("§cUsage: /rank <player> <rank>");
				}
			}
		}								
	}
	
	@EventHandler
	public void onCommandUse(PlayerCommandPreprocessEvent e) {
		Player p = e.getPlayer();
		List<String> commands = Arrays.asList("help", "seen", "version", "mail", "back", "tpa", "tpaccept", "sudo", "bal", "balance", "eco", "suicide");
		commands.forEach(all ->{
			if(e.getMessage().toLowerCase().equalsIgnoreCase("/" + all.toLowerCase())) {
				if (!p.hasPermission("ee.help")) {
					commands.add("plugins");
					commands.add("pl");
					e.setCancelled(true);
					HelpCommand.DisplayHelp(p, 1);
				}			
			}
		});
	}
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		if (ScreenShareCommand.Froz.contains(e.getPlayer())) {		
			e.setTo(e.getFrom());
		}else {
			if (e.getPlayer().getLocation().getY() <= 0) {
				e.getPlayer().setHealth(0);
				e.getPlayer().spigot().respawn();
			}
			e.setCancelled(false);
		}
	}
	
	@EventHandler
	public void onDie(PlayerDeathEvent e) {
		e.setDeathMessage(null);
	}

	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		if (!e.getPlayer().hasPermission("easyessentials.break")) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onBreak(BlockPlaceEvent e) {
		if (!e.getPlayer().hasPermission("easyessentials.place")) {
			e.setCancelled(true);
		}
	}
	

}