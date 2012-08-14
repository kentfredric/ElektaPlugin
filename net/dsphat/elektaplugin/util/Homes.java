package net.dsphat.elektaplugin.util;

import net.dsphat.elektaplugin.ElektaPluginMain;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Set;

public class Homes {
	public ElektaPluginMain plugin;
	HashMap<String, Location> homes;
	HashMap<String, String> homesStore;
	
	public Homes(ElektaPluginMain plugin) {
		this.plugin = plugin;
		
		homes = new HashMap<String, Location>();
		homesStore = new HashMap<String, String>();
		
		 try {
			 YamlConfiguration config = plugin.getMyConfiguration();
			 Set<String> nodes = config.getConfigurationSection("player.homes").getKeys(false);
			 if(nodes == null || nodes.isEmpty()) return;
			 
			 for(String key : nodes) {
				 String value = config.get("player.homes." + key).toString();
				 homes.put(key,  Utils.unserializeLocation(value));
				 homesStore.put(key, value);
			 }
		 } catch(Exception e) { e.printStackTrace(); }
	}
	
	private void save() {
		plugin.getMyConfiguration().set("player.homes", homesStore);
		plugin.saveMyConfiguration();
	}
	
	private String transformName(Player ply) {
		return ply.getName().toLowerCase();
	}
	
	public void removeHome(Player ply) {
		String name = transformName(ply);
		homes.remove(name);
		homesStore.remove(name);
		save();
	}
	
	public void setHome(Player ply, Location home) {
		String name = transformName(ply);
		homes.put(name, home);
		homesStore.put(name, Utils.serializeLocation(name));
		save();
	}
	
	public Location getHome(Player ply) {
		return homes.get(transformName(ply));
	}
}
