package fr.lemonadd.hubcore.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class Security  implements Listener {
    @EventHandler
    public void preventPlayerThrow(PlayerDropItemEvent event) {
        if (!event.getPlayer().hasPermission("hubcore.edit")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void lockInventory(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getClickedInventory() == player.getInventory() && !player.hasPermission("hubcore.edit")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void preventPlaceBlock(BlockPlaceEvent event) {
        if (!event.getPlayer().hasPermission("hubcore.edit")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void preventBreakBlock(BlockBreakEvent event) {
        if (!event.getPlayer().hasPermission("hubcore.edit")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void preventItemLoot(PlayerPickupItemEvent event) {
        if (!event.getPlayer().hasPermission("hubcore.edit")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void godMod(EntityDamageEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void infiniteFood(FoodLevelChangeEvent event) {
        event.setCancelled(true);
    }
}
