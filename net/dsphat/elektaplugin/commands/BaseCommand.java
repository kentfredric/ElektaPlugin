package net.dsphat.elektaplugin.commands;

import net.dsphat.elektaplugin.ElektaPluginMain;
import net.dsphat.elektaplugin.util.Utils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Constructor;
import java.util.List;

public abstract class BaseCommand implements CommandExecutor {
		protected final ElektaPluginMain plugin;
		protected BaseCommand() {
			plugin = ElektaPluginMain.instance;
		}
		
		@Retention(RetentionPolicy.RUNTIME) protected @interface Name { String value(); }
		
		@Override
		public final boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
			try {
				return onCommandAll(commandSender, command, s, strings);
			} catch(Exception e) {
				sendResponse(commandSender, "Error: " + e.getMessage(), ChatColor.DARK_RED);
				return false;
			}
		}
		
		public boolean onCommandAll(CommandSender commandSender, Command command, String s, String[] strings) throws Exception {
			if(commandSender instanceof Player) {
				return onCommandPlayer((Player)commandSender, command, s, strings);
			} else {
				return onCommandConsole(commandSender, command, s, strings);
			}
		}
		
		public boolean onCommandConsole(CommandSender commandSender, Command command, String s, String[] strings) throws Exception {
			sendResponse(commandSender, "Sorry, this command can not be used from the console!", ChatColor.DARK_RED);
			return true;
		}
		
		public boolean onCommandPlayer(Player player, Command command, String s. String[] strings) throws Exception {
			sendResponse(player, "Sorry, this command can not be used by a player!", ChatColor.DARK_RED);
			return true;
		}
		
		public static void registerCommands() {
			List<Class<? extends BaseCommand>> commands = Utils.getSubClasses(BaseCommand.class);
			for(Class<? extends BaseCommand> command : commands) {
				registerCommand(command);
			}
		}
		
		private static void registerCommand(Class<? extends BaseCommand> commandClass) {
			try {
				Constructor<? extends BaseCommand> ctor = commandClass.getConstructor();
				BaseCommand command = ctor.newInstance();
				if(!commandClass.isAnnotationPresent(Name.class)) return;
				ElektaPluginMain.instance.getCommand(commandClass.getAnnotation(Name.class).value()).setExecutor(command);
			} catch(Exception e) { }
		}
		
		protected Player getPlayerSingle(String name) throws Exception {
			List<Player> ret = plugin.getServer().matchPlayer(name);
			if(ret == null || ret.isEmpty()) {
				throw new Exception("Sorry, no player found!");
			} else if(ret.size() > 1) {
				throw new Exception("Sorry, multiple players found!");
			}
			return ret.get(0);
		}
		
		protected void sendResponse(CommandSender cmd, String msg) {
			sendResponse(cmd, msg, ChatColor.DARK_PURPLE);
		}
		
		protected void sendResponse(CommandSender cmd, String msg, ChatColor color) {
			cmd.sendMessage(color + "[ELEKTA] " + ChatColor.WHITE + msg);
		}
}
