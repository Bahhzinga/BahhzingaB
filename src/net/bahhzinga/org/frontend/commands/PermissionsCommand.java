package net.bahhzinga.org.frontend.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.bahhzinga.org.backend.files.Settings;
import net.bahhzinga.org.frontend.formatting.JSON;
import net.bahhzinga.org.frontend.formatting.JSONType;
import net.bahhzinga.org.frontend.menus.ColorsMenu;

public class PermissionsCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player player = (Player) sender;
		if (label.equalsIgnoreCase("perms") || label.equalsIgnoreCase("permissions")) {
			
			if (args.length == 0) {
				
				JSON.send(player, JSONType.LINK, Settings.prefix() + "&7Viewing &9Permissions&7 help page:", "&7Click to view the BahhzingaBackend Wiki.", "");
				player.sendMessage("");
				JSON.send(player, JSONType.SUGGEST_COMMAND, " &8> &7/" + label + " user (player) add (permission)", "&7Grants a permission to a player.", "/perms user " + player.getName() + " add bahhzinga.perms.add");
				JSON.send(player, JSONType.SUGGEST_COMMAND, " &8> &7/" + label + " user (player) remove (permission)", "&7Remove a permission from a player.", "/perms user " + player.getName() + " remove bahhzinga.perms.remove");
				JSON.send(player, JSONType.SUGGEST_COMMAND, " &8> &7/" + label + " group (group) add (permission)", "&7Grants a permission to a group.", "/perms group " + player.getName() + " add bahhzinga.group.add");
				JSON.send(player, JSONType.SUGGEST_COMMAND, " &8> &7/" + label + " group (group) remove (permission)", "&7Remove a permission from a group.", "/perms group " + player.getName() + " remove bahhzinga.perms.remove");


			} else {
				
			}
			
			
			
		}
		
		
		return false;
	}
	
	

}
