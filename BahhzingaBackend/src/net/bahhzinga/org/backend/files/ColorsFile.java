package net.bahhzinga.org.backend.files;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import net.bahhzinga.org.Main;
import net.bahhzinga.org.backend.enums.OutputType;
import net.bahhzinga.org.backend.utils.Console;

public class ColorsFile {
	
	private static Plugin plugin = Main.getPlugin(Main.class);

	public static void save() {
		
		File file = new File(plugin.getDataFolder(), "colors.yml");
		FileConfiguration conf = YamlConfiguration.loadConfiguration(file);
		
		if (!file.exists()) {
			
			try {
				file.createNewFile();
				
				conf.set("menu.title", "&bSelect Your Chat Color");
				conf.set("menu.size", 27);
				
				conf.set("menu.sounds.openmenu.success", "ENTITY_FIREWORK_ROCKET_TWINKLE");
				conf.set("menu.sounds.openmenu.failure", "BLOCK_ANVIl_BREAK");
				conf.set("menu.sounds.selectcolor.success", "UI_BUTTON_CLICK");
				conf.set("menu.sounds.selectcolor.failure", "BLOCK_ANVIl_BREAK");
				
				conf.set("colors.lavender.hexColor", "#e066ff");
				conf.set("colors.lavender.displayName", "Lavender");
				conf.set("colors.lavender.iconMaterial", "MAGENTA_DYE");
				conf.set("colors.lavender.customModelData", 1);
				conf.set("colors.lavender.permission", "chatcolor.lavender");
				conf.set("colors.lavender.slot", 0);
				
				conf.set("colors.turquoise.hexColor", "#48d1cc");
				conf.set("colors.turquoise.displayName", "Turquoise");
				conf.set("colors.turquoise.iconMaterial", "LIME_DYE");
				conf.set("colors.turquoise.customModelData", 1);
				conf.set("colors.turquoise.permission", "chatcolor.turquoise");
				conf.set("colors.turquoise.slot", 1);
				
				conf.set("colors.candyred.hexColor", "#ff1493");
				conf.set("colors.candyred.displayName", "Candy Red");
				conf.set("colors.candyred.iconMaterial", "RED_DYE");
				conf.set("colors.candyred.customModelData", 1);
				conf.set("colors.candyred.permission", "chatcolor.candyred");
				conf.set("colors.candyred.slot", 2);
				
				conf.set("colors.discordblue.hexColor", "#7289da");
				conf.set("colors.discordblue.displayName", "Discord Blue");
				conf.set("colors.discordblue.iconMaterial", "BLUE_DYE");
				conf.set("colors.discordblue.customModelData", 1);
				conf.set("colors.discordblue.permission", "chatcolor.discordblue");
				conf.set("colors.discordblue.slot", 3);
				
				conf.set("colors.lightpurple.hexColor", "#b890ee");
				conf.set("colors.lightpurple.displayName", "Light Purple");
				conf.set("colors.lightpurple.iconMaterial", "PURPLE_DYE");
				conf.set("colors.lightpurple.customModelData", 1);
				conf.set("colors.lightpurple.permission", "chatcolor.lightpurple");
				conf.set("colors.lightpurple.slot", 4);
				
				conf.set("colors.orange.hexColor", "#eb7400");
				conf.set("colors.orange.displayName", "Orange");
				conf.set("colors.orange.iconMaterial", "ORANGE_DYE");
				conf.set("colors.orange.customModelData", 1);
				conf.set("colors.orange.permission", "chatcolor.orange");
				conf.set("colors.orange.slot", 5);

				conf.save(file);
				
			} catch (IOException e) {
				Console.print(OutputType.ERROR, "Failed to create 'colors.yml' file.");
				e.printStackTrace();
			}
			
		}
	}
	
	public static FileConfiguration get() {
		File file = new File(plugin.getDataFolder(), "colors.yml");
		FileConfiguration conf = YamlConfiguration.loadConfiguration(file);
		
		return conf;
	}
	
}
