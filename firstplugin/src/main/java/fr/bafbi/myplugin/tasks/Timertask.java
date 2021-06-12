package fr.bafbi.myplugin.tasks;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class Timertask extends BukkitRunnable {

    private int timer = 10;

    @Override
    public void run() {

        Bukkit.broadcastMessage("Il reste " + timer + " seconde");

        if (timer == 0) {
            Bukkit.broadcastMessage("Boom!");
            cancel();
        }

        timer --;

        
        
    }

}
