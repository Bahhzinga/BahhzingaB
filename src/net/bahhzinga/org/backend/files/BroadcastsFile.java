package net.bahhzinga.org.backend.files;

import java.io.File;
import java.io.IOException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import net.bahhzinga.org.Main;
import net.bahhzinga.org.backend.enums.OutputType;
import net.bahhzinga.org.backend.utils.Console;

public class BroadcastsFile {
	
	private static final Plugin plugin = Main.getPlugin(Main.class);

	public static void save() {
		
		File file = new File(plugin.getDataFolder(), "broadcasts.yml");
		FileConfiguration conf = YamlConfiguration.loadConfiguration(file);
		
		if (!file.exists()) {
			
			try {
				file.createNewFile();
				conf.set("broadcast.tooltip", "&7For more information, visit our &9/website&7!");
				conf.set("broadcast.jsontype", "LINK");
				conf.set("broadcast.output", "www.google.com");
				conf.set("broadcast.sound", "ENTITY_FIREWORK_ROCKET_TWINKLE");
				conf.save(file);
				
			} catch (IOException e) {
				Console.print(OutputType.ERROR, "Failed to create 'broadcasts.yml' file.");
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
		
		try {
			conf.save(file);
			Console.print(OutputType.NORMAL, "Successfully saved file '" + file.getName() + "'.");
		} catch (IOException e) {
			Console.print(OutputType.ERROR, "Error occurred while attempting to save file '" + file.getName() + "'.");
			e.printStackTrace();
		}
	}
	
	public static FileConfiguration get() {
		File file = new File(plugin.getDataFolder(), "broadcasts.yml");

		return YamlConfiguration.loadConfiguration(file);
	}
	
}
