package net.dsphat.elektaplugin.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@BaseCommand.Name("summon")
public class SummonCommand extends TpCommand {
	@Override
	public boolean onCommandConsole(CommandSender commandSender, Command command, String s, String[] strings) throws Exception {
		teleport(strings[1], strings[0]);
		return true;
	}
	
	@Override
	public boolean onCommandPlayer(Player player, Command command, String s, String[] strings) throws Exception {
		Player other = getPlayerSingle(strings[0]);
		if(player.hasPermission(command.getPermission() + ".force")) {
			other.teleport(player);
			sendResponse(player, "Summoned " + ChatColor.GRAY + other.getDisplayName());
		} else {
			requestTeleport(player, other, other, player, ChatColor.GRAY + "%2$s" + ChatColor.WHITE + " wants to summon you!");
			sendResponse(player, "Requested summoning " + ChatColor.GRAY + other.getDisplayName());
		}
		return true;
		
	}
}
