package net.dsphat.elektaplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@BaseCommand.Name("setnick")
public class SetNickCommand extends BaseCommand {
	@Override
	public boolean onCommandAll(CommandSender, commandSender, Command command, String s, String[] strings) throws Exception {
		if(strings.length < 1 || strings.length > 2) return false;
		
		Player ply = getPlayerSingle(strings[0]);
		if(strings.length == 2) {
			plugin.nicknames.setNickname(ply, strings[1]);
			sendResponse(commandSender, "Set nickname!");
		} else {
			plugin.nicknames.removeNickname(ply);
			sendResponse(commandSender, "Removed nickname!");
		}
		return true;
	}
}
