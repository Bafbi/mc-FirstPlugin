package fr.bafbi.myplugin.commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import fr.bafbi.myplugin.Main;

public class Setspawn implements TabExecutor{

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        FileConfiguration config = Main.getPlugin().getConfig();
        
        if (args.length <= 0) {

            sender.sendMessage("aa");

            if (sender instanceof Player) {
                
                Player player = (Player) sender;

                config.set("spawn.world", player.getWorld().getName());
                config.set("spawn.x", Math.round(player.getLocation().getX() * 100) / 100);
                config.set("spawn.y", Math.round(player.getLocation().getY() * 100) / 100);
                config.set("spawn.z", Math.round(player.getLocation().getZ() * 100) / 100);
                config.set("spawn.direction", player.getLocation().getDirection());

            }

        }

        Main.getPlugin().saveConfig();
        return false;
    }
    
}
