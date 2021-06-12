package fr.bafbi.myplugin.custom;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.persistence.PersistentDataType;

import fr.bafbi.myplugin.Main;

public class CustomItems {

    public static List<String> getCustomItemsList() {

        FileConfiguration config = Main.getPlugin().getConfig();

        List<String> customItemsList = new ArrayList<>();

        for (String customItemId : config.getConfigurationSection("customs").getKeys(false)) {

            customItemsList.add(customItemId);

        }

        return customItemsList;

    }

    public static ItemStack getCustomItem(String customItemIdName) {

        FileConfiguration config = Main.getPlugin().getConfig();
        
        for (String customItemId : config.getConfigurationSection("customs").getKeys(false)) {

            if (customItemId.equalsIgnoreCase(customItemIdName)) {

                ItemStack customitem = new ItemStack(Material.getMaterial(config.getString("customs." + customItemId + ".material")), 1);
                ItemMeta customMeta = customitem.getItemMeta();

                customMeta.setCustomModelData(config.getInt("customs." + customItemId + ".custommodeldata"));

                customMeta.getPersistentDataContainer().set(new NamespacedKey(Main.getPlugin(), config.getString("customs." + customItemId + ".type")), PersistentDataType.INTEGER, 1);
                customMeta.getPersistentDataContainer().set(new NamespacedKey(Main.getPlugin(), customItemId), PersistentDataType.INTEGER, 1);
        
                customMeta.setDisplayName(config.getString("customs." + customItemId + ".displayname").replace("&", "§"));

                List<String> loreList = new ArrayList<>();
                List<String> configLore = config.getStringList("customs." + customItemId + ".lore");
                for (String unformatedLore : configLore) {
                    loreList.add(unformatedLore.replace("&", "§"));
                }

                if(config.contains("customs." + customItemId + ".set")) {

                    loreList.add(ChatColor.GRAY + capFirst(config.getString("customs." + customItemId + ".set")) + " Set");
                    customMeta.getPersistentDataContainer().set(new NamespacedKey(Main.getPlugin(), config.getString("customs." + customItemId + ".set") + "_set"), PersistentDataType.INTEGER, 1);

                }

                loreList.add(ChatColor.DARK_GRAY + typeFormat(config.getString("customs." + customItemId + ".type")));

                customMeta.setLore(loreList);

                customitem.setItemMeta(customMeta);

                return customitem;

            }
        }
        
        return null;
        
    }

    private static String capFirst(String string) {
    
        return Character.toUpperCase(string.charAt(0)) + string.substring(1);

    }

    private static String typeFormat(String string) {

        String formatedString = null;

        for (String word : string.split("_")) {
            if (word.equalsIgnoreCase(string.split("_")[0])) formatedString = capFirst(word);
            else formatedString = formatedString + " " + capFirst(word);
        }
    
        return formatedString;

    }



     
}
