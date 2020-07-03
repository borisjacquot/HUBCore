package fr.lemonadd.hubcore.models;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

import fr.lemonadd.hubcore.Main;

public class Effects {
    private int taskID;
    private final Player player;

    public Effects(Player player) {
        this.player = player;
    }

    public void startTotem() {
        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(Main.class), new Runnable() {

            double var = 0;
            Location loc, first, second;
            ParticulesData particule = new ParticulesData(player.getUniqueId());

            @Override
            public void run() {
                if (!particule.hasID()) {
                    particule.setID(taskID);
                }

                var += Math.PI / 16;
                loc = player.getLocation();
                first = loc.clone().add(Math.cos(var), Math.sin(var) +1, Math.sin(var));
                second = loc.clone().add(Math.cos(var + Math.PI), Math.sin(var) +1, Math.sin(var+Math.PI));

                player.getWorld().spawnParticle(Particle.TOTEM, first, 0);
                player.getWorld().spawnParticle(Particle.TOTEM, second, 0);
            }
        },0,1);
    }
}
