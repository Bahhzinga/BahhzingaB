package net.bahhzinga.org.frontend.motd;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import net.bahhzinga.org.frontend.formatting.ColorSystem;
import net.bahhzinga.org.frontend.menus.ColorsMenu;

public class Join implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		
		Player player = event.getPlayer();
		ColorsMenu.open(player);
	}

}
