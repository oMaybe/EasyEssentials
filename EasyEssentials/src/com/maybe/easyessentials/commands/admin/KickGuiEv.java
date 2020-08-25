package com.maybe.easyessentials.commands.admin;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.maybe.easyessentials.Main;
import com.maybe.easyessentials.utils.Util;

public class KickGuiEv {
	
	private Main plugin;
	public Inventory inv;
	
	public KickGuiEv(Main plugin) {
		this.plugin = plugin;
		
		inv = Bukkit.createInventory(null, 27, Util.chat("&6Player Selector"));
	}
	
	public void initializeItems(Player p) {
		for (Player pl : Bukkit.getOnlinePlayers()) {
			inv.addItem(createGuiItem(Material.SKULL, pl.getDisplayName(), pl.getUniqueId().toString()));
		}
	}
	
	protected ItemStack createGuiItem(final Material material, final String name, final String lore) {
		final ItemStack item = new ItemStack(material, 1);
		final ItemMeta meta = item.getItemMeta();
		
		meta.setDisplayName(name);
		meta.setLore(Arrays.asList(lore));
		item.setItemMeta(meta);
		return item;
	}
	
	private ItemStack nameItem(ItemStack item, String name, String lore) {
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        ArrayList<String> l = new ArrayList<>();
        l.add(lore);
        meta.setLore(l);
        item.setItemMeta(meta);
        return item;
    }
}
