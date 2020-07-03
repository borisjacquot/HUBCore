package fr.lemonadd.hubcore.events;

import fr.lemonadd.hubcore.models.Effects;
import fr.lemonadd.hubcore.models.GUI;
import fr.lemonadd.hubcore.models.ParticulesData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

public class ClickEvent implements Listener {

    private GUI menu;
    public ClickEvent() {
        menu = new GUI();
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (!event.getInventory().equals(menu.getInventory())) return;

        Player player = (Player) event.getWhoClicked();
        event.setCancelled(true);

        if (event.getView().getType() == InventoryType.PLAYER) return;

        ParticulesData particule = new ParticulesData(player.getUniqueId());

        if (particule.hasID()) {
            particule.endTask();
            particule.removeID();
        }

        Effects trails = new Effects(player);

        switch (event.getSlot()) {
            case 3:
                trails.startTotem();
                player.closeInventory();
                player.closeInventory();
                break;
            case 5:
                particule.setID(1);
                player.closeInventory();
                player.closeInventory();
                break;
            default:
                break;
        }
    }
}
