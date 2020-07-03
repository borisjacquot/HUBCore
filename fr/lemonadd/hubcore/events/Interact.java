package fr.lemonadd.hubcore.events;

import fr.lemonadd.hubcore.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class Interact implements Listener {
    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!event.hasItem()) return;

        if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (player.getItemInHand().getType() == Material.GRAY_DYE) {
                event.setCancelled(true);

                ItemStack item = new ItemBuilder(Material.LIME_DYE, 1)
                        .setName("§a§lJoueurs affichés")
                        .setLore("§9Permet de cacher les joueurs", "§a§lJoueurs actuellement affichés")
                        .toItemStack();
                player.getInventory().setItem(7,item);

                for (Player online : Bukkit.getOnlinePlayers()) {
                    player.showPlayer(online);
                }
            }

            else if (player.getItemInHand().getType() == Material.LIME_DYE) {
                event.setCancelled(true);

                ItemStack item = new ItemBuilder(Material.GRAY_DYE, 1)
                        .setName("§8§lJoueurs cachés")
                        .setLore("§9Permet d'afficher les joueurs", "§8§lJoueurs actuellement cachés")
                        .toItemStack();
                player.getInventory().setItem(7,item);

                for (Player online : Bukkit.getOnlinePlayers()) {
                    player.hidePlayer(online);
                }
            }
        }
    }
}
