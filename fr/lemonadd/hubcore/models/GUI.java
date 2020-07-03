package fr.lemonadd.hubcore.models;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GUI {

    private static Inventory INV;

    public void register() {
        Inventory inv = Bukkit.createInventory(null, 9, format("&6&lPARTICULES GUI"));

        ItemStack item = new ItemStack(Material.TOTEM_OF_UNDYING);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(format("&eTotem"));
        item.setItemMeta(meta);
        inv.setItem(3, item);

        item = new ItemStack(Material.FIRE_CHARGE);
        meta = item.getItemMeta();
        meta.setDisplayName(format("&cLE FEU AUX FESSES"));
        item.setItemMeta(meta);
        inv.setItem(5, item);

        setInventory(inv);
    }

    public Inventory getInventory() {
        return INV;
    }

    private void setInventory(Inventory inv) {
        INV = inv;
    }

    public void openInventory(Player player) {
        player.openInventory(INV);
    }

    private String format(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
}
