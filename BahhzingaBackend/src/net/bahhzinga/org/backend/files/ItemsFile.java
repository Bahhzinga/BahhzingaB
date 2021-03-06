package net.bahhzinga.org.backend.files;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import net.bahhzinga.org.Main;
import net.bahhzinga.org.backend.enums.OutputType;
import net.bahhzinga.org.backend.utils.Console;

public class ItemsFile {
	
	private static Plugin plugin = Main.getPlugin(Main.class);

	public static void save() {
		
		File file = new File(plugin.getDataFolder(), "items.yml");
		FileConfiguration conf = YamlConfiguration.loadConfiguration(file);
		
		if (!file.exists()) {
			
			try {
				file.createNewFile();
				conf.set("items.example.material", "DIAMOND_SWORD");
				conf.set("items.example.displayName", "&fEmerald Sword");
				conf.set("items.example.customModelData", 1);
				conf.set("items.example.lore", Arrays.asList("", "", "", ""));
				
				conf.set("items.example.recipe.a", "AIR");
				conf.set("items.example.recipe.b", "EMERALD");
				conf.set("items.example.recipe.c", "AIR");
				
				conf.set("items.example.recipe.d", "AIR");
				conf.set("items.example.recipe.e", "EMERALD");
				conf.set("items.example.recipe.f", "AIR");
				
				conf.set("items.example.recipe.g", "AIR");
				conf.set("items.example.recipe.h", "STICK");
				conf.set("items.example.recipe.i", "AIR");
				conf.save(file);
				
			} catch (IOException e) {
				Console.print(OutputType.ERROR, "Failed to create 'items.yml' file.");
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
		File file = new File(plugin.getDataFolder(), "items.yml");
		FileConfiguration conf = YamlConfiguration.loadConfiguration(file);
		
		return conf;
	}
	
}
