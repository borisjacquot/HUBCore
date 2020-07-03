package fr.lemonadd.hubcore.events;

import fr.lemonadd.hubcore.models.ParticulesData;
import org.bukkit.Particle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.Random;

public class Movement implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if (!ParticulesData.hasFakeID(event.getPlayer().getUniqueId())) return;

        Random r = new Random();
        for (int i = 0; i<5; i++)
            event.getPlayer().getWorld().spawnParticle(Particle.CRIT_MAGIC, event.getPlayer().getLocation().add(
                    -1*(r.nextDouble() * 0.5), r.nextDouble() *0.5,r.nextDouble() * .5), 0);
        for (int i = 0; i<5; i++)
            event.getPlayer().getWorld().spawnParticle(Particle.CRIT_MAGIC, event.getPlayer().getLocation().add(
                    r.nextDouble() * 0.5, r.nextDouble() *0.5,(r.nextDouble() * .5) *-1), 0);
    }
}
