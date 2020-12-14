package net.bahhzinga.org.frontend.formatting;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import net.bahhzinga.org.backend.files.ColorsFile;
import net.bahhzinga.org.backend.files.PlayerDataFile;
import net.md_5.bungee.api.ChatColor;

public class ColorSystem {
	
	
	public static void createCustomColor(String name, String permission, String hexCode, Integer customModelData, Material icon) {
		
		
		
	}
	
	public static void setColor(Player player, String color) {
		
		if (player.hasPermission(ColorsFile.get().getString("colors." + color + ".permission")) || player.isOp()) {
			PlayerDataFile.get().set(player.getUniqueId().toString() + ".color", color);
			PlayerDataFile.save();
		} else {
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7You haven't unlocked " + ChatColor.of(ColorsFile.get().getString("colors." + color + ".hexColor")) + color + "&7!"));
		}
		
	}
	
	public static String getChatColor(Player player) {
		if (PlayerDataFile.get().getString(player.getUniqueId().toString() + ".color") == null) {
			return "&f";
		} else {
			String color = PlayerDataFile.get().getString(player.getUniqueId().toString() + ".color");
			return ColorsFile.get().getString("colors." + color + ".hexColor");
		}
	}

}
