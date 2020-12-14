package net.bahhzinga.org.frontend.items;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import net.bahhzinga.org.Main;
import net.bahhzinga.org.backend.enums.OutputType;
import net.bahhzinga.org.backend.files.ItemsFile;
import net.bahhzinga.org.backend.utils.Console;
import net.md_5.bungee.api.ChatColor;

@SuppressWarnings("ALL")
public class ItemsAPI {
	
	private static final Plugin plugin = Main.getPlugin(Main.class);
	
	public static void createItem(String name, String displayName, List<String> lore, Integer customModelData, Material baseMaterial, 
			Material a1, Material a2, Material a3,
			Material b1, Material b2, Material b3,
			Material c1, Material c2, Material c3) {
		
		if (ItemsFile.get().getString("items." + name) == null) {
		
			ItemStack item = new ItemStack(Material.valueOf(ItemsFile.get().getString("items." + name + ".material")));
			ItemMeta meta = item.getItemMeta();		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', ItemsFile.get().getString("items." + name + ".displayName")));
			meta.setCustomModelData(ItemsFile.get().getInt("items." + name + ".customModelData"));
			
			for (int i = 0;i<lore.size();i++) {
				meta.getLore().add(ChatColor.translateAlternateColorCodes('&', lore.get(i)));
			}
			
			item.setItemMeta(meta);
			
			NamespacedKey key = new NamespacedKey(plugin, name);
			ShapedRecipe r = new ShapedRecipe(key, item);
			r.shape("ABC", "DEF", "GHI");
			r.setIngredient('A', Material.valueOf(ItemsFile.get().getString("items." + name + ".recipe.a")));
			r.setIngredient('B', Material.valueOf(ItemsFile.get().getString("items." + name + ".recipe.b")));
			r.setIngredient('C', Material.valueOf(ItemsFile.get().getString("items." + name + ".recipe.c")));
			
			r.setIngredient('D', Material.valueOf(ItemsFile.get().getString("items." + name + ".recipe.d")));
			r.setIngredient('E', Material.valueOf(ItemsFile.get().getString("items." + name + ".recipe.e")));
			r.setIngredient('F', Material.valueOf(ItemsFile.get().getString("items." + name + ".recipe.f")));
			
			r.setIngredient('G', Material.valueOf(ItemsFile.get().getString("items." + name + ".recipe.g")));
			r.setIngredient('H', Material.valueOf(ItemsFile.get().getString("items." + name + ".recipe.h")));
			r.setIngredient('I', Material.valueOf(ItemsFile.get().getString("items." + name + ".recipe.i")));
			
			Bukkit.addRecipe(r);
			
			// Notify console
			Console.print(OutputType.NORMAL, "Created new item by the name of '" + name + "'.");
			
		} else {
			// Notify console
			Console.print(OutputType.ERROR, "This item already exists.");
		}
	}
	
	
	public static ItemStack getItemStack(String name) {
		
		ItemStack item = new ItemStack(Material.valueOf(ItemsFile.get().getString("items." + name + ".material")));
		ItemMeta meta = item.getItemMeta();		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', ItemsFile.get().getString("items." + name + ".displayName")));
		meta.setCustomModelData(ItemsFile.get().getInt("items." + name + ".customModelData"));
		
		List<String> lore = ItemsFile.get().getStringList("items." + name + ".lore");
		
		for (int i = 0;i<lore.size();i++) {
			meta.getLore().add(ChatColor.translateAlternateColorCodes('&', lore.get(i)));
		}
		
		item.setItemMeta(meta);
		return item;
		
	}
	
	public static void registerRecipe(String name) {
		
		NamespacedKey key = new NamespacedKey(plugin, name);
		ShapedRecipe r = new ShapedRecipe(key, getItemStack(name));
		r.shape("ABC", "DEF", "GHI");
		r.setIngredient('A', Material.valueOf(ItemsFile.get().getString("items." + name + ".recipe.a")));
		r.setIngredient('B', Material.valueOf(ItemsFile.get().getString("items." + name + ".recipe.b")));
		r.setIngredient('C', Material.valueOf(ItemsFile.get().getString("items." + name + ".recipe.c")));
		
		r.setIngredient('D', Material.valueOf(ItemsFile.get().getString("items." + name + ".recipe.d")));
		r.setIngredient('E', Material.valueOf(ItemsFile.get().getString("items." + name + ".recipe.e")));
		r.setIngredient('F', Material.valueOf(ItemsFile.get().getString("items." + name + ".recipe.f")));
		
		r.setIngredient('G', Material.valueOf(ItemsFile.get().getString("items." + name + ".recipe.g")));
		r.setIngredient('H', Material.valueOf(ItemsFile.get().getString("items." + name + ".recipe.h")));
		r.setIngredient('I', Material.valueOf(ItemsFile.get().getString("items." + name + ".recipe.i")));
		
		Bukkit.addRecipe(r);
		
	}
}
