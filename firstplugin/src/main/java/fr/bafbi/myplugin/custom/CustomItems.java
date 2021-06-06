package fr.bafbi.myplugin.custom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataType;

import fr.bafbi.myplugin.Main;

public class CustomItems {

    private static ItemStack VICTORY_SWORD() {

        ItemStack customsword = new ItemStack(Material.IRON_SWORD, 1);
        ItemMeta customMeta = customsword.getItemMeta();

        customMeta.setCustomModelData(1);

        customMeta.getPersistentDataContainer().set(new NamespacedKey(Main.getPlugin(), "customItem"), PersistentDataType.INTEGER, 1);
        customMeta.getPersistentDataContainer().set(new NamespacedKey(Main.getPlugin(), "victory_sword"), PersistentDataType.INTEGER, 1);

        customMeta.setDisplayName(ChatColor.RED + "Épée de la victoire");
        customMeta.setLore(Arrays.asList(ChatColor.DARK_PURPLE + "Victory Set",ChatColor.GRAY + "Custom Item"));

        customsword.setItemMeta(customMeta);

        return customsword;

    } 
    private static List<String> VICTORY_SWORD_DESCRIPTION() {
        List<String> description = new ArrayList<>();

        description.add(ChatColor.GRAY + "Custom Item : Victory Sword");
        description.add(ChatColor.DARK_PURPLE + "Set : Victory Set");
        description.add(ChatColor.YELLOW + "You gain 1 absorption heart every time you kill an entity, limited to 2");
        description.add(ChatColor.YELLOW + "Every piece of the set augment the limit of 2 hearts");

        return description;
    }

    private static ItemStack VICTORY_CHESTPLATE() {

        ItemStack customsword = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemMeta customMeta = customsword.getItemMeta();

        customMeta.setCustomModelData(1);

        customMeta.getPersistentDataContainer().set(new NamespacedKey(Main.getPlugin(), "customItem"), PersistentDataType.INTEGER, 1);
        customMeta.getPersistentDataContainer().set(new NamespacedKey(Main.getPlugin(), "victory_set"), PersistentDataType.INTEGER, 1);

        customMeta.setDisplayName(ChatColor.RED + "Plastron de la victoire");
        customMeta.setLore(Arrays.asList(ChatColor.DARK_PURPLE + "Victory Set",ChatColor.GRAY + "Custom Item"));

        customsword.setItemMeta(customMeta);

        return customsword;

    } 
    

    public static List<String> getCustomItemsList() {
        List<String> customItemsList = new ArrayList<>();

        customItemsList.add("victory_sword");
        customItemsList.add("victory_chestplate");

        return customItemsList;

    }

    public static ItemStack getCustomItem(String name) {

        ItemStack customItem = null;

        switch (name.toLowerCase()) {
            case "victory_sword":
                customItem = VICTORY_SWORD();
                break;
            case "victory_chestplate":
                customItem = VICTORY_CHESTPLATE();
                break;
        
            default:
                break;
        }

        if(name.equalsIgnoreCase("victory_sword")) {
            customItem = VICTORY_SWORD();
        }

        return customItem;

    }

    public static List<String> getCustomItemDescription(String name) {

        List<String> description = null;

        switch (name.toLowerCase()) {
            case "victory_sword":
                description = VICTORY_SWORD_DESCRIPTION();
                break;
        
            default:
                break;
        }

        return description;

    }




     
}
