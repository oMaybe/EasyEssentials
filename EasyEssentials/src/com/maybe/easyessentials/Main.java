package com.maybe.easyessentials;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.maybe.easyessentials.commands.EmojiCommand;
import com.maybe.easyessentials.commands.Heal;
import com.maybe.easyessentials.commands.HelpCommand;
import com.maybe.easyessentials.commands.MessageCommand;
import com.maybe.easyessentials.commands.admin.Admin;
import com.maybe.easyessentials.commands.admin.FlyCommand;
import com.maybe.easyessentials.commands.admin.GamemodeCommand;
import com.maybe.easyessentials.commands.admin.KaboomCommand;
import com.maybe.easyessentials.commands.admin.RankCommand;
import com.maybe.easyessentials.commands.admin.ScreenShareCommand;
import com.maybe.easyessentials.commands.admin.SpeedCommand;
import com.maybe.easyessentials.commands.admin.StaffChat;
import com.maybe.easyessentials.commands.admin.Vanish;
import com.maybe.easyessentials.events.Events;
import com.maybe.easyessentials.events.InventoryItemsC;
import com.maybe.easyessentials.events.PlayerEventon;
import com.maybe.easyessentials.tag.GameManage;
import com.maybe.easyessentials.tag.GameMechanics;
import com.maybe.easyessentials.tag.PlayerManager;
import com.maybe.easyessentials.tnt.Event;
import com.maybe.easyessentials.tnt.StartCommand;
import com.maybe.easyessentials.tnt.TntTagCommand;
import com.maybe.easyessentials.utils.PlayerHandler;
import com.maybe.easyessentials.utils.Util;
import com.maybe.easyessentials.utils.WarningManager;

public class Main extends JavaPlugin {
	
	private static Main instance;
	
	PlayerHandler PlayerHandler = new PlayerHandler();
	Events Events = new Events(PlayerHandler);
	
	String Path = "plugins/EasyEssentials";
	
	WarningManager warningManager;
	
	public static Main plugin;
	public File BannedWords = new File(Path + "bannedWords.yml");
	
	public String prefix = "§7[§c§lTNT RUN§7] §b";
	
	public HashMap<UUID, PlayerManager> playermanager = new HashMap<UUID, PlayerManager>();
	public ArrayList<Player> playersInGame = new ArrayList<Player>();
	public ArrayList<Player> playersLeftGame = new ArrayList<Player>();
	
	public GameMechanics gameMechanics;
	public GameManage gameManage;
	
	public Main() {
		instance = this;
	}
	
	public static Main getInstance() {
		return instance;
	}
	
	@Override
	public void onEnable() {
		plugin = this;
		
		loadConfig();
		instanceClasses();
		
		File MainDirectory = new File(Path);
		if(!MainDirectory.exists()) {
			MainDirectory.mkdir();
		}
		File PlayerData = new File(Path + "/PlayerData");
		if(!PlayerData.exists()) {
			PlayerData.mkdir();
		}			
		
		InitCommands();	
		InitEvents();
		
		warningManager = new WarningManager(this);
		
		warningManager.runTaskTimer(this, 0, 15 * 20);
		
		getServer().getLogger().info(Util.chat("&b=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-="));
		getServer().getLogger().info(Util.chat("&b=-=- &3EasyEssentials &b is now &aENABLED!"));
		getServer().getLogger().info(Util.chat("&b=-=- &1Author &b oMaybe"));
		getServer().getLogger().info(Util.chat("&b=-=- &2Version &b 1.5"));
		getServer().getLogger().info(Util.chat("&b=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-="));		
	}
	
	@Override
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage(Util.chat("&b=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-="));
		Bukkit.getConsoleSender().sendMessage(Util.chat("&b=-=- &3EasyEssentials &b is now &cDISABLED!"));
		Bukkit.getConsoleSender().sendMessage(Util.chat("&b=-=- &1Author &b oMaybe"));
		Bukkit.getConsoleSender().sendMessage(Util.chat("&b=-=- &2Version &b 1.5"));
		Bukkit.getConsoleSender().sendMessage(Util.chat("&b=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-="));	
	}
	
	public void loadConfig() {
		getConfig().options().copyDefaults(true);
		saveConfig();
	}
	
	public void InitCommands() {
		//new MuteCommand(this);
		//new BroadcastCommand(this);
	
		new GamemodeCommand(this);
		new SpeedCommand(this);
		new FlyCommand(this);		
		new ScreenShareCommand(this);
		new StaffChat(this);
		new RankCommand(this);
		new MessageCommand(this);		
		new KaboomCommand(this);
		new HelpCommand(this);
		new Vanish(this);
		new Admin(this);
		new EmojiCommand(this);
		new StartCommand(this);
		new Heal(this);
		new TntTagCommand(this);
	}
	
	public void InitEvents() {
		Bukkit.getServer().getPluginManager().registerEvents(new PlayerEventon(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new InventoryItemsC(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new Event(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new GameMechanics(), this);
		Bukkit.getServer().getPluginManager().registerEvents(Events, this);
	}
	
	public void instanceClasses() {
		gameMechanics = new GameMechanics();
		gameManage = new GameManage();
	}
}
