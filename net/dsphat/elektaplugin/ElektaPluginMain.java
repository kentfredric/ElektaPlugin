package net.dsphat.elektaplugin;

import net.dsphat.elektaplugin.commands.BaseCommand;
import net.dsphat.elektaplugin.util.Homes;
import net.dsphat.elektaplugin.util.Nicknames;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.logging.Level;

public class ElektaPluginMain extends JavaPlugin {
		public static ElektaPluginMain instance;
	
		private ElektaPluginListener listener;
		public Nicknames nicknames;
		public Homes homes;
	
		private YamlConfiguration config = null;
		private File mainFile = null;
		
			public ElektaPluginMain() {
				super();
				instance = this;
			}
		
		public void saveMyConfiguration() {
			try {
				config.save(mainFile);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		public YamlConfiguration getMyConfiguration() {
			return config;
		}
		
			@Override
			public void onDisable() {
				log("Plugin disabled!");
			}
			
			@Override
			public void onEnable() {
				try {
					getDataFolder().mkdirs();
					mainFile = new File(getDataFolder(), "config.yml");
					config = new YamlConfiguration();
					config.log(mainFile);
				} catch(Exception e) {
					e.printStackTrace();
				}
				
				saveMyConfiguration();
				
						listener = new ElektaPluginListener(this);
						nicknames = new Nicknames(this);
						homes = new Homes(this);
						BaseCommand.registerCommands();
						log("Plugin enabled!");
			}
			
			public void log(String msg) {
				log(Level.INFO, msg);
			}
			
			public void log(Level level, String msg) {
				getLogger().log(level, msg);
			}
}
