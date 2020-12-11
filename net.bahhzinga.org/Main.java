public class Main extends JavaPlugin {
	
	public void onEnable(){
		
		// Save player data
		Data.save(DataType.PLAYER);
		
		// Save configurations
		Files.save("plugins/BahhzingaB/settings.yml");
		Files.save("plugins/BahhzingaB/chat.yml");
		Files.save("plugins/BahhzingaB/tags.yml");
		Files.save("plugins/BahhzingaB/permissions.yml");
		Files.save("plugins/BahhzingaB/groups.yml");
		Files.save("plugins/BahhzingaB/menus.yml");
		Files.save("plugins/BahhzingaB/databases.yml");
		Files.save("plugins/BahhzingaB/items.yml");
		Files.save("plugins/BahhzingaB/logging.yml");
		
		// Establish SQL Connections
		SQL.establishConnections();
		
		// Notify console
		Console.print("[BahhzingaB] Successfully saved 9 plugin configuration files.");
		Console.print("[BahhzingaB] Established connections to databases.");
		
		// Check for updates
		if (Updater.check() == true){
			
			// Notify console
			Console.print("[BahhzingaB] You are running an outdated version of BahhzingaBackend, please update to " + Updater.latestVersion() + " to receive new features and bug fixes.");
			Console.print("[BahhzingaB] Older versions of this plugin are considered legacy and are not supported by the developer");
			
			// Notify online server admins
			for (Player player : Bukkit.getOnlinePlayers(){
				
				JSON.send(player, Settings.prefix() + " &7You are running an &coutdated&7 version of this plugin, you will not receive any support while you use this version.", "&7Click to update the plugin", "/bb update");
				
			}
			
		} else if (Updater.check() == false){
			
			// Notify console
			Console.print("[BahhzingaB] You are running the latest version of BahhzingaB.");
		
		} else if (Updater.check() == null){
			
			// Notify console
			Console.print("[BahhzingaB] An Error has occurred: unable to check for updates. Please contact the plugin developer on SpigotMC.")
			
		}
		
		// Credit developer <3
		Console.print("[BahhzingaB] You are running BahhzingaBackend, developed by Bahhzinga.");
		Console.print("[BahhzingaB] Contact me on Discord: bazinga#3579.");
	}
