package fr.bafbi.myplugin.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import fr.bafbi.myplugin.Main;

public class Farmrun implements TabExecutor {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        
        if(sender instanceof Player) {
            Player player = (Player) sender;
            Inventory inventory = Bukkit.createInventory(null, 27, "§8§nFarmrun GUI");

            ItemStack createFarmPlotSeed = this.getFarmrunGuiItem(Material.WHEAT_SEEDS, "Create FarmPlot", "aa§bb", 1 , true);

            inventory.setItem(13, createFarmPlotSeed);

            player.openInventory(inventory);

        }

        



        return false;
    }

    public ItemStack getFarmrunGuiItem(Material material, String name, String lore, int customModeldata, boolean glow) {

        ItemStack farmrunGuiItem = new ItemStack(material, 1);
        ItemMeta farmrunGuiItemMeta = farmrunGuiItem.getItemMeta();

        farmrunGuiItemMeta.setCustomModelData(customModeldata);

        farmrunGuiItemMeta.getPersistentDataContainer().set(new NamespacedKey(Main.getPlugin(), "farmrungui"), PersistentDataType.INTEGER, 1);
        farmrunGuiItemMeta.getPersistentDataContainer().set(new NamespacedKey(Main.getPlugin(), name.toLowerCase().replace(" ", "_")), PersistentDataType.INTEGER, 1);

        farmrunGuiItemMeta.setDisplayName(name);

        List<String> loreList = new ArrayList<>();
        String[] lores = lore.split("§");
        for (String loreLine : lores) {
            loreList.add(loreLine);
        }
        farmrunGuiItemMeta.setLore(loreList);

        

        if (glow) {
            farmrunGuiItemMeta.addEnchant(Enchantment.LOYALTY, 1, false);
            farmrunGuiItemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }

        farmrunGuiItem.setItemMeta(farmrunGuiItemMeta);  
              
        return farmrunGuiItem;
    }
    
}
