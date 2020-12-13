package net.bahhzinga.org.frontend.permissions;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.bahhzinga.org.backend.files.PermissionsFile;
import net.bahhzinga.org.backend.files.PlayerDataFile;
import net.bahhzinga.org.backend.utils.Verbose;
import net.bahhzinga.org.frontend.formatting.JSONType;

public class PermissionsAPI {
	
	public static void addPermissionToPlayer(Player player, String permissionNode) {
		// Add the permissions node to the player
		if (!PermissionsFile.get().getStringList("players." + player.getUniqueId().toString() + ".permissions").contains(permissionNode)) {
			PermissionsFile.get().getStringList("players." + player.getUniqueId().toString() + ".permissions").add(permissionNode);
			PermissionsFile.save();
		} else {
			for (Player players : Bukkit.getOnlinePlayers()) {	
				if (PlayerDataFile.get().getBoolean(players.getUniqueId().toString() + ".verbose")) {
					Verbose.send("&7Error occurred while adding permission &9'" + permissionNode + "' &7to " + player.getDisplayName() + "&7, as they already had it.", 
							"&7Error occurred at: &9" + LocalDate.now().toString(), 
							"/bahhzingabackend logs", 
							JSONType.RUN_COMMAND);
				}
			}
		}
	}
	
	public static void removePermissionFromPlayer(Player player, String permissionNode) {
		// Remove the permissions node from the player
		if (PermissionsFile.get().getStringList("players." + player.getUniqueId().toString() + ".permissions").contains(permissionNode)) {
			PermissionsFile.get().getStringList("players." + player.getUniqueId().toString() + ".permissions").remove(permissionNode);
			PermissionsFile.save();
		} else {
			Verbose.send("&7Error occurred while removing permission &9'" + permissionNode + "' &7from " + player.getDisplayName() + "&7, as they never had it in the first place.", 
					"&7Error occurred at: &9" + LocalDate.now().toString(), 
					"/bahhzingabackend logs", 
					JSONType.RUN_COMMAND);
		}
	}
	
	public static void addPlayerToGroup(Player player, String group) {
		if (!PermissionsFile.get().getStringList("groups." + player.getUniqueId().toString() + ".members").contains(player.getUniqueId().toString())) {
			PermissionsFile.get().getStringList("groups." + player.getUniqueId().toString() + ".members").add(player.getUniqueId().toString());
			PermissionsFile.save();
		} else {
			Verbose.send("&7Error occurred while adding player &9'" + player.getDisplayName() + "' &7to " + group + "&7, as they were already a member.", 
					"&7Error occurred at: &9" + LocalDate.now().toString(), 
					"/bahhzingabackend logs", 
					JSONType.RUN_COMMAND);
		}
	}

	public static void removePlayerToGroup(Player player, String group) {
		if (PermissionsFile.get().getStringList("groups." + player.getUniqueId().toString() + ".members").contains(player.getUniqueId().toString())) {
			PermissionsFile.get().getStringList("groups." + player.getUniqueId().toString() + ".members").remove(player.getUniqueId().toString());
			PermissionsFile.save();
		} else {
			Verbose.send("&7Error occurred while removing player &9'" + player.getDisplayName() + "' &7from " + group + "&7, as they weren't a member.", 
					"&7Error occurred at: &9" + LocalDate.now().toString(), 
					"/bahhzingabackend logs", 
					JSONType.RUN_COMMAND);
		}
	}
	
	public static void createGroup(String group, String prefix, List<String> perms) {
		if (PermissionsFile.get().getString("groups." + group) == null) {
			PermissionsFile.get().set("groups." + group + ".prefix", prefix);
			PermissionsFile.get().set("groups." + group + ".permissions", perms);
			PermissionsFile.get().set("groups." + group + ".members", Arrays.asList("ExamplePlayer"));
			PermissionsFile.save();
		} else {
			Verbose.send("&7Error occurred while creating group &9" + group + "&7, as it already existed.", 
					"&7Error occurred at: &9" + LocalDate.now().toString(), 
					"/bahhzingabackend logs", 
					JSONType.RUN_COMMAND);
		}
	}
	
	public static void deleteGroup(String group) {
		if (PermissionsFile.get().getString("groups." + group) == null) {
			PermissionsFile.get().set("groups." + group, null);
			PermissionsFile.save();
		} else {
			Verbose.send("&7Error occurred while deleting group &9" + group + "&7, as it never existed.", 
					"&7Error occurred at: &9" + LocalDate.now().toString(), 
					"/bahhzingabackend logs", 
					JSONType.RUN_COMMAND);
		}
	}
	
	public static void setPrefix(String group, String prefix) {
		PermissionsFile.get().set("groups." + group + ".prefix", prefix);
	}
}
