package fr.bafbi.myplugin;

import org.bukkit.command.PluginCommand;

import org.bukkit.plugin.java.JavaPlugin;

import fr.bafbi.myplugin.commands.Alert;
import fr.bafbi.myplugin.commands.Customgive;
import fr.bafbi.myplugin.commands.Customhelp;
import fr.bafbi.myplugin.commands.Farmrun;
import fr.bafbi.myplugin.commands.Spawn;
import fr.bafbi.myplugin.commands.Test;

public class Main extends JavaPlugin {

    private static Main plugin;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        plugin = this;

            // Start //
        this.getLogger().info("Hello, SpigotMC!");

            // Simple commands //
        this.getCommand("test").setExecutor(new Test());
        this.getCommand("alert").setExecutor(new Alert());
        this.getCommand("spawn").setExecutor(new Spawn());

            // Tab complete commands //
        //customgive
        PluginCommand commandCustomGive = this.getCommand("customgive");
        Customgive customGive = new Customgive();
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
        this.getServer().getPluginManager().registerEvents(new MonPluginListeners(), this);
    }
    @Override
    public void onDisable() {
        this.getLogger().info("See you again, SpigotMC!");
    }

    public static Main getPlugin() {
        return plugin;
    }

}
