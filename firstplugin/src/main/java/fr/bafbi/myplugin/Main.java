package fr.bafbi.myplugin;

import java.io.File;

import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import org.bukkit.plugin.java.JavaPlugin;

import fr.bafbi.myplugin.commands.Alert;
import fr.bafbi.myplugin.commands.Customgive;
import fr.bafbi.myplugin.commands.Customhelp;
import fr.bafbi.myplugin.commands.Farmrun;
import fr.bafbi.myplugin.commands.Setspawn;
import fr.bafbi.myplugin.commands.Spawn;
import fr.bafbi.myplugin.commands.Test;
import fr.bafbi.myplugin.tasks.Timertask;

public class Main extends JavaPlugin {

    private static Main plugin;
    private  File customItemsFile = new File(getDataFolder(), "customitems.ylm");
    private  FileConfiguration customItemsConfig = YamlConfiguration.loadConfiguration(customItemsFile);

    @Override
    public void onEnable() {
        saveDefaultConfig();


        /*if(!customItemsFile.exists()) {
            saveResource("customitems.ylm", false);
            this.getLogger().info("customitems.ylm file is created");
        }*/

        
        plugin = this;

            // Start //
        this.getLogger().info("Hello, SpigotMC!");

            // Simple commands //
        this.getCommand("test").setExecutor(new Test());
        this.getCommand("alert").setExecutor(new Alert());
        this.getCommand("spawn").setExecutor(new Spawn());

            // Tab complete commands //
        //setspawn
        PluginCommand commandSetSpawn = this.getCommand("setspawn");
        Setspawn setspawn = new Setspawn();
        commandSetSpawn.setExecutor(setspawn);
        commandSetSpawn.setTabCompleter(setspawn);
        //customgive
        PluginCommand commandCustomGive = this.getCommand("customgive");
        Customgive customGive = new Customgive(this);
        commandCustomGive.setExecutor(customGive);
        commandCustomGive.setTabCompleter(customGive);
        //customhelp
        PluginCommand commandCustomHelp = this.getCommand("customhelp");
        Customhelp customHelp = new Customhelp();
        commandCustomHelp.setExecutor(customHelp);
        commandCustomHelp.setTabCompleter(customHelp);
        //farmrungui
        PluginCommand commandFarmrun = this.getCommand("farmrun");
        Farmrun farmrun = new Farmrun();
        commandFarmrun.setExecutor(farmrun);
        commandFarmrun.setTabCompleter(farmrun);

            // Listenners //
        this.getServer().getPluginManager().registerEvents(new MonPluginListeners(this), this);

            // Schedulers //
        Timertask task = new Timertask();
        task.runTaskTimer(this, 0, 20);
    }
    @Override
    public void onDisable() {
        this.getLogger().info("See you again, SpigotMC!");
    }

    public static Main getPlugin() {
        return plugin;
    }

    public FileConfiguration getCustomItemsConfig() {
        return customItemsConfig;
    }

    public File getCustomItemsFile() {
        return customItemsFile;
    }


}
