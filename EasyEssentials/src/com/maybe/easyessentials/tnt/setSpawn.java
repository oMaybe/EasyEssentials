package com.maybe.easyessentials.tnt;

import org.bukkit.Location;

import com.maybe.easyessentials.Main;

public class setSpawn {
	
	private static int i = 0;

    public static int set(Location location, Main plugin){
        i++;
        plugin.getConfig().set("location.spawn." + i, location);
        plugin.saveConfig();
        return i;
    }
	
}
