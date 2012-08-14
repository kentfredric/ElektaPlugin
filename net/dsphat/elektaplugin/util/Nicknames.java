package net.dsphat.elektaplugin.util;

import net.dsphat.elektaplugin.ElektaPluginMain;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Set;

public class Nicknames {
	public ElektaPluginMain plugin;
	HashMap<String, String> nicknames;
	
	public Nicknames(ElektaPluginMain plugin) {
		this.plugin = plugin;
		
		nicknames = new HashMap<String, String>();
		
		try {
			YamlConfiguration config = plugin.getMyConfiguration();
			Set<String> nodes = config.getConfigurationSection("player.nicks").getKeys(false);
			if(nodes == null || nodes.isEmpty()) return;
			
			for(String key : nodes) {
				nicknames.put(key, config.get("player.nicks." + key).toString());
			}
		} catch(Exception e) { }
	}
	
	private void save() {
		plugin.getMyConfiguration().get("player.nicks", nicknames);
		plugin.saveMyConfiguration();
	}
	
	private String transformName(Player ply) {
		return ply.getName().toLowerCase();
	}
	
	public void removeNickname(Player ply) {
		ply.setDisplayName(ply.getName());
		nicknames.remove(transformName(ply));
		save();
	}
	
	public void setNickname(Player ply) {
		String name = getNickname(ply);
		if(name != null) {
			ply.setDisplayName(name);
		} else {
			ply.setDisplayName(ply.getName());
		}
	}
	
	public void setNickname(Player ply, String nick) {
		ply.setDisplayName(nick);
		nicknames.put(transformName(ply), nick);
		save();
	}
	
	public String getNickname(Player ply) {
		String name = transformName(ply);
		if(nicknames.containsKey(name)) {
			return nicknames.get(name);
		}
		return null;
	}
}
