package fr.bafbi.myplugin.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.bafbi.myplugin.Main;

public class Test implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) { 
        
        if(sender instanceof Player) {
            Player player = (Player)sender;
            player.sendMessage(/*ChatColor.DARK_RED + "Test" + ChatColor.RESET + ": " + ChatColor.GREEN + "Complete !"*/ Main.getPlugin().getConfig().getString("message.testcommand"));
        
        }

        return true;
    }

}
