package fr.lemonadd.hubcore.events;

import fr.lemonadd.hubcore.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import fr.lemonadd.hubcore.Main;

public class Join implements Listener {
    private Main plugin;
    public Join(Main plugin) { this.plugin = plugin; }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        spawn(player);

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
        player.getInventory().setItem(4,item);

        item = new ItemBuilder(Material.PLAYER_HEAD, 1)
                .setName("§3§lProfil & options")
                .setLore("§9Affiche votre profil")
                .setSkullOwner(player.getName())
                .toItemStack();
        player.getInventory().setItem(0,item);
    }

    private void spawn(Player player) {
        Location loc = new Location(Bukkit.getWorld(plugin.getConfig().getString("spawn.world")),
                plugin.getConfig().getDouble("spawn.x"),
                plugin.getConfig().getDouble("spawn.y"),
                plugin.getConfig().getDouble("spawn.z"),
                (float) plugin.getConfig().getDouble("spawn.yaw"),
                (float) plugin.getConfig().getDouble("spawn.p"));
        player.teleport(loc);
    }
}
