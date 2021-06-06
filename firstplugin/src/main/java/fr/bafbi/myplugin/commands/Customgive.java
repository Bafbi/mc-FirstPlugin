package fr.bafbi.myplugin.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fr.bafbi.myplugin.custom.CustomItems;

public class Customgive implements TabExecutor {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> tabComplete = new ArrayList<>();

        if (args.length == 1) {
            tabComplete = null;
        }
        if(args.length == 2) {
            for (String CustomItem : CustomItems.getCustomItemsList()) {
                if (CustomItem.startsWith(args[1])) {
                    tabComplete.add(CustomItem);
                }
            }
        }
        return tabComplete;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        
        if (args.length < 2) {
            return false;
        } else {
            String targetName = args[0];
            Player target = Bukkit.getPlayer(targetName);

            if (target == null) {
                sender.sendMessage(ChatColor.YELLOW + "Invalid player name: " + ChatColor.RED + targetName);
            } else {
                String customItemName = args[1];
                ItemStack customItem = CustomItems.getCustomItem(customItemName);

                if (customItem == null) {
                    sender.sendMessage(ChatColor.YELLOW + "Invalid custom item name: " + ChatColor.RED + customItemName);
                } else {
                    int count;
                    try {
                        count = Integer.parseInt(args[2]);
                    } catch (Exception e) {
                        count = 1;
                    }

                    customItem.setAmount(count);

                    if (target.getInventory().firstEmpty() == -1) {
                        sender.sendMessage(ChatColor.YELLOW + "Item wasn't given, inventory full");
                        return true;
                    } else {
                        target.getInventory().addItem(customItem);
                        target.updateInventory();

                        sender.sendMessage(ChatColor.YELLOW + "Gave " + count + " [" + ChatColor.RESET + customItem.getItemMeta().getDisplayName() + ChatColor.YELLOW + "] to " + ChatColor.GRAY + ChatColor.BOLD + targetName);

                        return true; 
                    }
                }
            }
        }

        




        return false;
    }
    
}
