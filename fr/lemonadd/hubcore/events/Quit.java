package fr.lemonadd.hubcore.events;

import fr.lemonadd.hubcore.models.ParticulesData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class Quit implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        ParticulesData p = new ParticulesData(event.getPlayer().getUniqueId());
        if (p.hasID()) {
            p.endTask();
            p.removeID();
        }
    }
}
