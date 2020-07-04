package fr.lemonadd.hubcore.events;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;
import fr.lemonadd.hubcore.Main;

public class Ping implements Listener {

    private Main plugin;
    public Ping(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPing(ServerListPingEvent event) {
        event.setMotd(format(plugin.getConfig().getString("messages.motd")));
    }

    private String format(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
}
