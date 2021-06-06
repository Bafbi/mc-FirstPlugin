package fr.bafbi.myplugin.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import fr.bafbi.myplugin.custom.CustomItems;

public class Customhelp implements TabExecutor {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> tabComplete = new ArrayList<>();

        if(args.length == 1) {
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
        
        if(args.length < 1) {
            if(sender instanceof Player) {
            Player player = (Player)sender;
            player.sendMessage(ChatColor.DARK_RED + "Test" + ChatColor.RESET + ": " + ChatColor.GREEN + "Complete !");
        
            }
        } else {
            String customItemName = args[0];

            for (String Description : CustomItems.getCustomItemDescription(customItemName)) {
                sender.sendMessage(Description);
            }
        }

        
        return true;
    }
    
}
