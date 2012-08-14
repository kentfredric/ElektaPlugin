package net.dsphat.elektaplugin.commands;

import net.dsphat.elektaplugin.util.Utils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

@BaseCommand.Name("me")
public class MeCommand extends BaseCommand {
	@Override
	public boolean onCommandPlayer(Player player, Command command, String s, String[] strings) throws Exception {
		String msg = Utils.concatArray(strings, 0, null);
		if(msg == null) return false;
		msg = msg.trim();
		if(msg.isEmpty()) return false;
		
		plugin.getServer().broadcastMessage("* " + ChatColor.GRAY + player.getDisplayName() + ChatColor.WHITE + " " + msg);
		return true;
	}
}
