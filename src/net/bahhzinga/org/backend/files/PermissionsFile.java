package net.bahhzinga.org.backend.files;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import net.bahhzinga.org.Main;
import net.bahhzinga.org.backend.enums.OutputType;
import net.bahhzinga.org.backend.utils.Console;
import net.md_5.bungee.api.ChatColor;

@SuppressWarnings("ALL")
public class PermissionsFile {
	
	private static final Plugin plugin = Main.getPlugin(Main.class);
	
	public static void save() {
		
		
		File file = new File(plugin.getDataFolder(), "permissions.yml");
		FileConfiguration conf = YamlConfiguration.loadConfiguration(file);
		
		if (!file.exists()) {
			
			try {
				file.createNewFile();

				// Create permissions for each player
				Integer counter = 0;
				for (Player player : Bukkit.getOnlinePlayers()) {
					conf.set("players." + player.getUniqueId().toString() + ".isOpped", player.isOp());
					conf.set("players." + player.getUniqueId().toString() + ".permissions",
							Arrays.asList(
									"bahhzingabackend.chatcolor",
									"bahhzingabackend.kits",
									"bahhzingabackend.warp",
									"bahhzingabackend.tags",
									"bahhzingabackend.report.player",
									"bahhzingabackend.report.bug",
									"bahhzingabackend.vote",
									"bahhzingabackend.vote.rewards",
									"bahhzingabackend.vote.links",
									"bahhzingabackend.quests",
									"bahhzingabackend.sethome"));
					counter++;
				}
				
				Console.print(OutputType.NORMAL, "Added " + counter + " players to the permissions file.");
				
				conf.save(file);
				
				
			} catch (IOException e) {
				Console.print(OutputType.ERROR, "Failed to create 'permissions.yml' file.");
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
		File file = new File(plugin.getDataFolder(), "permissions.yml");

		return YamlConfiguration.loadConfiguration(file);
	}

}
