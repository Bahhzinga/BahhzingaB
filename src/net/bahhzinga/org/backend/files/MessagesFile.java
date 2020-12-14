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

@SuppressWarnings("ALL")
public class MessagesFile {
	
	private static final Plugin plugin = Main.getPlugin(Main.class);

	public static void save() {
		
		File file = new File(plugin.getDataFolder(), "messages.yml");
		FileConfiguration conf = YamlConfiguration.loadConfiguration(file);
		
		if (!file.exists()) {
			
			try {
				file.createNewFile();
				conf.set("messages.nopermission.text", "&7You aren't permitted to run that &ccommand&7!");
				conf.set("messages.nopermission.tooltip", "&7Click to view a list of commands.");
				conf.set("messages.nopermission.jsontype", "RUN_COMMAND");
				conf.set("messages.nopermission.output", "/help");
				conf.set("messages.nopermission.sound", "BLOCK_ANVIL_BREAK");
				conf.save(file);
				
			} catch (IOException e) {
				Console.print(OutputType.ERROR, "Failed to create 'messages.yml' file.");
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
		File file = new File(plugin.getDataFolder(), "messages.yml");

		return YamlConfiguration.loadConfiguration(file);
	}
	
}
