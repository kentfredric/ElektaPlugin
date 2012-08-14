package net.dsphat.elektaplugin.commands;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;

@BaseCommand.Name("home")
public class HomeCommand extends BaseCommand {
	@Override
	public boolean onCommandPlayer(Player player, Command command, String s, String[] strings) throws Exception {
		Location location = plugin.homes.getHome(player);
		if(location == null) {
			throw new Exception("Sorry, you don't have a home set!");
		}
		player.teleport(location);
		sendResponse(player, "Teleported home!");
		return true;
	}
}
