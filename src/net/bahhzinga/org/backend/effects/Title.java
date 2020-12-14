package net.bahhzinga.org.backend.effects;

import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class Title {
	
	public static void send(Player player, String title, String subtitle, Integer duration) {
		player.sendTitle(
				ChatColor.translateAlternateColorCodes('&', title),
				ChatColor.translateAlternateColorCodes('&', subtitle),
				1, duration, 1);
	}

}
