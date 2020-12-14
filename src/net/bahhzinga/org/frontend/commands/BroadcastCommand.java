package net.bahhzinga.org.frontend.commands;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.bahhzinga.org.backend.files.BroadcastsFile;
import net.bahhzinga.org.backend.files.MessagesFile;
import net.bahhzinga.org.frontend.formatting.JSON;
import net.bahhzinga.org.frontend.formatting.JSONType;

public class BroadcastCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player player = (Player) sender;
		
		if (label.equalsIgnoreCase("bc") || label.equalsIgnoreCase("broadcast")) {
			
			// If player doesn't have permission
			if (!player.hasPermission("bahhzinga.broadcast")) {
				JSON.send(player, 
						JSONType.valueOf(MessagesFile.get().getString("messages.nopermission.jsontype")), 
						MessagesFile.get().getString("messages.nopermission.text"), 
						MessagesFile.get().getString("messages.nopermission.tooltip"), 
						MessagesFile.get().getString("messages.nopermission.output"));
				player.playSound(player.getLocation(), Sound.valueOf(MessagesFile.get().getString("messages.nopermission.sound")), 3, 3);
			} else {	// If player does have permission
				
				for (Player players : Bukkit.getOnlinePlayers()) {
					
					String preset = args[1];
					
					players.playSound(players.getLocation(), Sound.valueOf(BroadcastsFile.get().getString("broadcast.sound")), 3, 3);
					JSON.send(players, 
							JSONType.valueOf(MessagesFile.get().getString("broadcast.jsontype")),
							args.toString().replaceFirst(args[1], ""), 
							BroadcastsFile.get().getString("broadcast.tooltip"),
							BroadcastsFile.get().getString("broadcast.output"));
					
				}
				
				
				
				
			}
		}
		
		
		return false;
	}

}
