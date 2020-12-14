package net.bahhzinga.org.backend.menu;

import net.bahhzinga.org.Main;
import net.bahhzinga.org.backend.enums.OutputType;
import net.bahhzinga.org.backend.utils.Console;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class MenuAPI {

    private static Plugin plugin = Main.getPlugin(Main.class);

    public static void createMenu(String name, String title, Integer slots) {

        // Create new file
        File file = new File(plugin.getDataFolder(), "menus.yml");

        // Instantiate Configuration
        FileConfiguration yml = YamlConfiguration.loadConfiguration(file);

        // Set values
        yml.set("menu." + name + ".title", title);
        yml.set("menu." + name + ".slots", slots);

        yml.set("menu." + name + ".contents,exampleItem.displayName", "&7Example Item");
        yml.set("menu." + name + ".contents,exampleItem.slot", 0);
        yml.set("menu." + name + ".contents,exampleItem.lore", Arrays.asList("&7This is an Example Item with a", "&7custom model and an item lore!", "&7Cool, right?"));
        yml.set("menu." + name + ".contents.exampleItem.customModelData", 1);
        yml.set("menu." + name + ".contents.exampleItem.material", "DIAMOND_SWORD");
        yml.set("menu." + name + ".contents.exampleItem.actions.leftclick.command.permission", "/help");
        yml.set("menu." + name + ".contents.exampleItem.actions.rightclick.command.permission", "Bazinga!");

        // Save file
        try {
            yml.save(file);
        } catch (IOException e) {
            Console.print(OutputType.ERROR, "Error occurred while attempting to save file '" + file.getName() + "'.");
            e.printStackTrace();
        }

    }

    public static void openMenu(Player player, String name) {
        player.openInventory(getMenu(name));
    }

    public static Inventory getMenu(String name) {
        Inventory inv = Bukkit.createInventory(null, 5, ChatColor.translateAlternateColorCodes('&', getFile().getString("menu." + name + ".title")));

        for (String item : getFile().getConfigurationSection("menu." + name + ".contents").getKeys(false)){

            ItemStack itemStack = new ItemStack(Material.valueOf(getFile().getString("menu." + name + ".contents." + item + ".material")));
            ItemMeta meta = itemStack.getItemMeta();

            meta.setDisplayName("");
            meta.setCustomModelData(getFile().getInt("menu." + name + ".contents." + item + ".customModelData"));
            for (int i = 0;i<getFile().getStringList("menu." + name + ".contents." + item + ".lore").size();i++){
                meta.getLore().add(ChatColor.translateAlternateColorCodes('&',
                        getFile().getStringList("menu." + name + ".contents." + item + ".lore").get(i)));
            }

            itemStack.setItemMeta(meta);
            inv.setItem(getFile().getInt("menu." + name + ".contents." + item + ".slot"), itemStack);
        }
        return inv;
    }

    public static String getTitle(String name) {
        return getFile().getString("menu." + name + ".title");
    }

    public static Integer getSlots(String name) {
        return getFile().getInt("menu." + name + ".slots");
    }

    public static FileConfiguration getFile(){
        // Instantiate new file
        File file = new File(plugin.getDataFolder(), "menus.yml");

        // Instantiate Configuration
        FileConfiguration yml = YamlConfiguration.loadConfiguration(file);
        return yml;
    }

    public static void saveMenus(){

    }

}
