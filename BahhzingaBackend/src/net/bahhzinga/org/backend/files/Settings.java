package net.bahhzinga.org.backend.files;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import net.bahhzinga.org.Main;
import net.bahhzinga.org.backend.enums.OutputType;
import net.bahhzinga.org.backend.utils.Console;
import net.md_5.bungee.api.ChatColor;

public class Settings {
	
	private static Plugin plugin = Main.getPlugin(Main.class);
	
	public static String prefix() {
		return ChatColor.translateAlternateColorCodes('&', get().getString("settings.prefix"));
	}
	
	public static void save() {
		
		File file = new File(plugin.getDataFolder(), "settings.yml");
		FileConfiguration conf = YamlConfiguration.loadConfiguration(file);
		
		if (!file.exists()) {
			
			try {
				file.createNewFile();
				conf.set("settings.prefix", "&f&lBahhzinga&4&lBackend &8| ");
				conf.save(file);
				
			} catch (IOException e) {
				Console.print(OutputType.ERROR, "Failed to create 'settings.yml' file.");
				e.printStackTrace();
			}
			
		}
		try {
			conf.save(file);
			Console.print(OutputType.NORMAL, "Successfully saved file '" + file.getName() + "'.");
		} catch (IOException e) {
			Console.print(OutputType.ERROR, "Error occurred while attempting to save file '" + file.getName() + "'.");
			e.printStackTrace();
		}
	}
	
	public static FileConfiguration get() {
		File file = new File(plugin.getDataFolder(), "settings.yml");
		FileConfiguration conf = YamlConfiguration.loadConfiguration(file);
		
		return conf;
	}

}
