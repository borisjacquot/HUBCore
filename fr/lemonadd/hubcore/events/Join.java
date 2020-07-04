package fr.lemonadd.hubcore.events;

import fr.lemonadd.hubcore.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class Join implements Listener {
    
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        event.setJoinMessage("[Préfixe] " + player.getName() + " fait son entrée !");

        // set items dans l'inventaire
        player.getInventory().clear();
        ItemStack item = new ItemBuilder(Material.LIME_DYE, 1)
                .setName("§a§lJoueurs affichés")
                .setLore("§9Permet de cacher les joueurs", "§a§lJoueurs actuellement affichés")
                .toItemStack();
        player.getInventory().setItem(7,item);

        item = new ItemBuilder(Material.CHEST, 1)
                .setName("§6§lInventaire")
                .setLore("§9Affiche son inventaire de gadgets")
                .toItemStack();
        player.getInventory().setItem(8,item);

        item = new ItemBuilder(Material.COMPASS, 1)
                .setName("§d§lJOUER")
                .setLore("§9Affiche la liste des jeux")
                .toItemStack();
        player.getInventory().setItem(0,item);
    }
}
