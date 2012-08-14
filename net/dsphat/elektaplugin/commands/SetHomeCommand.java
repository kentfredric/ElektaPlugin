package net.dsphat.elektaplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

@BaseCommand.Name("sethome")
public class SetHomeCommand extends BaseCommand {
	@Override
	public boolean onCommandPlayer(Player player, Command command, String s, String[] strings) throws Exception {
		plugin.homes.setHome(player, player.getLocation());
		sendResponse(player, "Home set!");
	}
}
