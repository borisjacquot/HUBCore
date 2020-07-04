package fr.lemonadd.hubcore.events;

import fr.lemonadd.hubcore.models.ParticulesData;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;

import java.util.Random;

public class PlayerMoves implements Listener {
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        // double jump
        if ((player.getGameMode() != GameMode.CREATIVE
                && player.hasPermission("hubcore.doublejump"))
                && (player.getLocation().subtract(0,1,0).getBlock().getType() != Material.AIR)
                && (!player.isFlying())) {
            player.setAllowFlight(true);
        }
    }

    @EventHandler
    public void onPlayerToggleFlight(PlayerToggleFlightEvent event) {
        Player player = event.getPlayer();
        if (player.getGameMode() == GameMode.CREATIVE) return;
        event.setCancelled(true);
        player.setAllowFlight(false);
        player.setFlying(false);
        player.setVelocity(player.getLocation().getDirection().multiply(1.5).setY(1));
        player.playSound(player.getLocation(), Sound.ENTITY_LLAMA_SPIT, 10, 1);
    }
}
