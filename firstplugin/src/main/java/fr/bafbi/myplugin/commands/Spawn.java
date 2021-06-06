package fr.bafbi.myplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import fr.bafbi.myplugin.Main;

public class Spawn implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        FileConfiguration config = Main.getPlugin().getConfig();
        
        if (sender instanceof Player) {
            Player player = (Player) sender;
            World overworld = Bukkit.getWorld(config.getString("spawn.world"));
            Location spawn = new Location(overworld, config.getInt("spawn.x"), config.getInt("spawn.y"), config.getInt("spawn.z"));
            player.teleport(spawn);
            player.sendMessage(ChatColor.YELLOW + "Tu viens d'etre teleporte au spawn");
        }

        return true;
    }

}
