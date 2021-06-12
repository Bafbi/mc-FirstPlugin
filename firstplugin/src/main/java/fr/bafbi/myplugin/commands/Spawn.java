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
            World spawnWorld;
            Location spawn;

            if(config.get("spawn.y") == null) {
            
            spawnWorld = player.getWorld();
            spawn = spawnWorld.getSpawnLocation();
            spawn.setDirection(player.getLocation().getDirection());

            } else {

                spawnWorld = Bukkit.getWorld(config.getString("spawn.world"));
                spawn = new Location(spawnWorld, config.getInt("spawn.x"), config.getInt("spawn.y"), config.getInt("spawn.z"));
                spawn.setDirection(config.getVector("spawn.direction"));

            }

            player.teleport(spawn);
            player.sendMessage(ChatColor.YELLOW + "Tu viens d'etre teleporte au spawn");
            
        } else {

            sender.sendMessage("Tes pas un joueur mec, je peux pas te tp");

        }

        return true;
    }

}
