package net.dsphat.elektaplugin.commands;

import net.dsphat.elektaplugin.request.Request;
import net.dsphat.elektaplugin.request.RequestRunnable;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@BaseCommand.Name("tp")
public class TpCommand extends BaseCommand {
	@Override
	public boolean onCommandConsole(CommandSender commandSender, Command command, String s, String[] strings) throws Exception {
		Player other = getPlayerSingle(strings[0]);
		if(player.hasPermission(command.getPermission() + ".force")) {
			player.teleport(other);
			sendResponse(player, "Teleported to " + ChatColor.GRAY + other.getDisplayName());
		} else {
			requestTeleport(player, other, player, other, ChatColor.GRAY + "%2$s" + ChatColor.WHITE + " wants to teleport to you.");
			sendResponse(player, "Requested teleporting to " + ChatColor.GRAY + other.getDisplayName());
		}
		return true;
	}
	
	protected void teleport(String toTeleport, String target) {
		plugin.getServer().getPlayerExact(toTeleport).teleport(plugin.getServer().getPlayerExact(target));
	}
	
	protected void requestTeleport(final Player byPlayer, final Player forPlayer, final Player toTeleport, final Player target, final String msg) {
		new Request(forPlayer, byPlayer, new RequestRunnable() {
			@Override
			public void accept() {
				toTeleport.teleport(target);
				sendResponse(byPlayer, "Your teleportatikon request was accepted!", ChatColor.DARK_GREEN);
			}
			
			@Override
			public void decline() {
				sendResponse(byPlayer, "Your teleportation request was declined!", ChatColor.DARK_RED);
			}
		}).add(msg); 
	}
}
