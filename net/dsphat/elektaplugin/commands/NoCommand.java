package net.dsphat.elektaplugin.commands;

import net.dsphat.elektaplugin.request.Request;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

@BaseCommand.Name("no")
public class NoCommand extends BaseCommand {
	public boolean onCommandPlayer(Player player, Command command, String s, String[] strings) throws Exception {
		Player byPlayer = null;
		if(strings.length > 0) {
			byPlayer = getPlayerSingle(strings[0]);
		}
		Request req = Request.getRequest(player, byPlayer);
		if(req == null) {
			throw new Exception("Sorry, no pending request found!");
		} else {
			sendResponse(player, "Request declined!");
			req.decline();
		}
		return true;
	}
}
