package fr.bafbi.myplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class Alert implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
                sender.sendMessage(ChatColor.YELLOW + "Usage: /alert <message>");
        } else {
            StringBuilder bcMessage = new StringBuilder();
            for(String arg : args){
                bcMessage.append(arg + " ");
            }
            Bukkit.broadcastMessage(ChatColor.DARK_PURPLE + "[" + ChatColor.DARK_AQUA + "ALERT" + ChatColor.DARK_PURPLE + "] " + ChatColor.RESET + bcMessage.toString());
            for(Player player : Bukkit.getOnlinePlayers()) {
                player.sendTitle(ChatColor.RED + bcMessage.toString(),"send by " + ChatColor.GRAY + ChatColor.BOLD + sender.getName(), 5, 30 ,10);
                player.playSound(player.getLocation(), Sound.ENTITY_GHAST_SCREAM, SoundCategory.VOICE, 1, 1);
            }
        }
        return true;
    }

}
