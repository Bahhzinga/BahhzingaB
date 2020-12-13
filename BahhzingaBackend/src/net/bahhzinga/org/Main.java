package net.bahhzinga.org;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import net.bahhzinga.org.backend.enums.OutputType;
import net.bahhzinga.org.backend.files.ColorsFile;
import net.bahhzinga.org.backend.files.ItemsFile;
import net.bahhzinga.org.backend.files.MessagesFile;
import net.bahhzinga.org.backend.files.PermissionsFile;
import net.bahhzinga.org.backend.files.PlayerDataFile;
import net.bahhzinga.org.backend.files.Settings;
import net.bahhzinga.org.backend.services.Updater;
import net.bahhzinga.org.backend.utils.Console;
import net.bahhzinga.org.frontend.chat.ChatEvent;
import net.bahhzinga.org.frontend.commands.ChatColorCommand;
import net.bahhzinga.org.frontend.commands.PermissionsCommand;
import net.bahhzinga.org.frontend.formatting.JSON;
import net.bahhzinga.org.frontend.formatting.JSONType;
import net.bahhzinga.org.frontend.menus.ColorsMenu;
import net.bahhzinga.org.frontend.motd.Join;

public class Main extends JavaPlugin {
	
	@Override
	public void onEnable(){
		
		// Register Events
		Bukkit.getPluginManager().registerEvents(new ChatEvent(), this);
		Bukkit.getPluginManager().registerEvents(new Join(), this);
		Bukkit.getPluginManager().registerEvents(new ColorsMenu(), this);
		
		// Register Commands
		this.getCommand("chatcolor").setExecutor(new ChatColorCommand());
//		this.getCommand("chatcolour").setExecutor(new ChatColorCommand());
		this.getCommand("perms").setExecutor(new PermissionsCommand());
//		this.getCommand("permissions").setExecutor(new PermissionsCommand());
	
//		// Save player data
//		Data.save(DataType.PLAYER);
		
		// Save configurations
		ItemsFile.save();
		ColorsFile.save();
		PlayerDataFile.save();
		Settings.save();
		PermissionsFile.save();
		MessagesFile.save();
		
//		// Establish SQL Connections
//		SQL.establishConnections();
		
		// Check for updates
		if (Updater.check() == true){
			
			// Notify console
			System.out.println();
			Console.print(OutputType.WARN, "You are running an outdated version of BahhzingaBackend, please update to  to receive new features and bug fixes.");
			Console.print(OutputType.WARN, "Older versions of this plugin are considered legacy and are not supported by the developer");
			
			// Notify online server admins
			for (Player player : Bukkit.getOnlinePlayers()){
				
				JSON.send(player, JSONType.RUN_COMMAND, Settings.prefix() + "&7You are running an &coutdated&7 version of this plugin, you will not receive any support while you use this version.", "&7Click to update the plugin", "/bb update");
				
			}
			
		} else if (Updater.check() == false){
			
			// Notify console
			System.out.println();
			Console.print(OutputType.NORMAL, "You are running the latest version of BahhzingaB.");
		
		} else if (Updater.check() == null){
			
			// Notify console
			System.out.println();
			Console.print(OutputType.ERROR, "An Error has occurred: unable to check for updates. Please contact the plugin developer on SpigotMC.");
			
		}
		
		// Credit developer <3
		System.out.println();
		Console.print(OutputType.NORMAL, "You are running BahhzingaBackend, developed by Bahhzinga.");
		Console.print(OutputType.NORMAL, "Contact me on Discord: bazinga#3579.");
	}
}
