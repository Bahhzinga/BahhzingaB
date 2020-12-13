package net.bahhzinga.org.frontend.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.bahhzinga.org.frontend.menus.ColorsMenu;

public class ChatColorCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player player = (Player) sender;
		if (label.equalsIgnoreCase("chatcolor") | label.equalsIgnoreCase("chatcolour")) {
			ColorsMenu.open(player);
		}
		
		
		return true;
	}
	
	

}
