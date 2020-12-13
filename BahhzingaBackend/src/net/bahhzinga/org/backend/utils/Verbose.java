package net.bahhzinga.org.backend.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.bahhzinga.org.backend.files.PlayerDataFile;
import net.bahhzinga.org.frontend.formatting.JSON;
import net.bahhzinga.org.frontend.formatting.JSONType;

public class Verbose {
	
	public static void toggleVerbose(Player player) {
		PlayerDataFile.get().set(player.getUniqueId().toString() + ".verbose", !PlayerDataFile.get().getBoolean(player.getUniqueId().toString() + ".verbose"));
	}
	
	public static Boolean isVerbose(Player player) {
		return PlayerDataFile.get().getBoolean(player.getUniqueId().toString() + ".verbose");
	}
	
	public static void send(String message, String info, String output, JSONType type) {
		
		for (Player players : Bukkit.getOnlinePlayers()) {
			
			if (PlayerDataFile.get().getBoolean(players.getUniqueId().toString() + ".verbose")) {
				JSON.send(players, type, "&a[Verbose] " + message,
						info, output);
			}
			
		}
		
	}

}
