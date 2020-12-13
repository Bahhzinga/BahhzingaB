package net.bahhzinga.org.frontend.menus;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import net.bahhzinga.org.Main;
import net.bahhzinga.org.backend.enums.OutputType;
import net.bahhzinga.org.backend.files.ColorsFile;
import net.bahhzinga.org.backend.files.MessagesFile;
import net.bahhzinga.org.backend.files.PlayerDataFile;
import net.bahhzinga.org.backend.utils.Console;
import net.bahhzinga.org.frontend.formatting.ColorSystem;
import net.bahhzinga.org.frontend.formatting.JSON;
import net.bahhzinga.org.frontend.formatting.JSONType;
import net.md_5.bungee.api.ChatColor;

public class ColorsMenu implements Listener {
	
	private static Plugin plugin = Main.getPlugin(Main.class);

	public static void open(Player player) {
		
		if (player.hasPermission("bahhzingabackend.chatcolor")) {
		
			Inventory inv = Bukkit.createInventory(null, ColorsFile.get().getInt("menu.size"), ChatColor.translateAlternateColorCodes('&', ColorsFile.get().getString("menu.title")));
			
			for (String color : ColorsFile.get().getConfigurationSection("colors").getKeys(false)) {
				
				ItemStack item = new ItemStack(Material.valueOf(ColorsFile.get().getString("colors." + color + ".iconMaterial")));
				ItemMeta meta = item.getItemMeta();		meta.setCustomModelData(ColorsFile.get().getInt("colors." + color + ".customModelData"));
				meta.setDisplayName(ChatColor.of(ColorsFile.get().getString("colors." + color + ".hexColor")) + ColorsFile.get().getString("colors." + color + ".displayName"));
				item.setItemMeta(meta);
				
				inv.setItem(ColorsFile.get().getInt("colors." + color + ".slot"), item);
				
			}
			player.playSound(player.getLocation(), Sound.valueOf(ColorsFile.get().getString("menu.sounds.openmenu.success")), 3, 3);
			player.openInventory(inv);
			
		} else {
			
			player.playSound(player.getLocation(), Sound.valueOf(ColorsFile.get().getString("menu.sounds.openmenu.noperm")), 3, 3);
			JSON.send(player, JSONType.valueOf(MessagesFile.get().getString("messages.nopermission.jsontype")),
					MessagesFile.get().getString("messages.nopermission.text"), 
					MessagesFile.get().getString("messages.nopermission.tooltip"), 
					MessagesFile.get().getString("messages.nopermission.output"));
		}
		
	}
	
	@EventHandler
	public void onInvClick(InventoryClickEvent event) {
		
		Player player = (Player) event.getWhoClicked();
		Inventory inv = event.getClickedInventory();
		
		String title = event.getView().getTitle();
		
		if (title.equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', ColorsFile.get().getString("menu.title")))) {
			
			for (String color : ColorsFile.get().getConfigurationSection("colors").getKeys(false)) {
				
				if (event.getSlot() == ColorsFile.get().getInt("colors." + color + ".slot")) {
					
					if (player.hasPermission(ColorsFile.get().getString("colors." + color + ".permission"))  || player.isOp()) {
						
						if (!PlayerDataFile.get().getString(player.getUniqueId().toString() + ".color").equalsIgnoreCase(color)) {
							
							
							File file = new File(plugin.getDataFolder(), "playerdata.yml");
							FileConfiguration conf = YamlConfiguration.loadConfiguration(file);
							
							conf.set(player.getUniqueId().toString() + ".color", color);
							try {
								conf.save(file);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							Console.print(OutputType.DEBUG, color);
							player.playSound(player.getLocation(), Sound.valueOf(ColorsFile.get().getString("menu.sounds.selectcolor.success")), 3, 3);
							player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Your chat color is now " + ChatColor.of(ColorsFile.get().getString("colors." + color + ".hexColor")) + ColorsFile.get().getString("colors." + color + ".displayName") + "&7!"));
						} else {
							player.playSound(player.getLocation(), Sound.valueOf(ColorsFile.get().getString("menu.sounds.selectcolor.failure")), 3, 3);
							JSON.send(player, JSONType.valueOf(ColorsFile.get().getString("messages.nopermission.jsontype")),
									MessagesFile.get().getString("messages.nopermission.text"), 
									MessagesFile.get().getString("messages.nopermission.tooltip"), 
									MessagesFile.get().getString("messages.nopermission.output"));
						}
						
					} else {
						player.playSound(player.getLocation(), Sound.valueOf(ColorsFile.get().getString("menu.sounds.selectcolor.failure")), 3, 3);
						JSON.send(player, JSONType.RUN_COMMAND,
								"&7You already have that color selected!", 
								"&7Click to pick another color!", 
								"/chatcolor");
					}
					
				}
				
			}
			event.setCancelled(true);
			
		}
		
	}
	
}
