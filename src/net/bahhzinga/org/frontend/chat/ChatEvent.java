package net.bahhzinga.org.frontend.chat;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import net.bahhzinga.org.backend.files.PlayerDataFile;
import net.bahhzinga.org.frontend.formatting.ColorSystem;
import net.bahhzinga.org.frontend.menus.ColorsMenu;
import net.md_5.bungee.api.ChatColor;

public class ChatEvent implements Listener {
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		
		// Get player
		Player player = event.getPlayer();
		
		// Get player's chat colour
		event.setMessage(ChatColor.of(ColorSystem.getChatColor(player)) + event.getMessage());
		
	}

}
