package fr.lemonadd.hubcore.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import fr.lemonadd.hubcore.Main;

public class SetHub implements CommandExecutor {
    private Main plugin;
    public SetHub(Main plugin) { this.plugin = plugin; }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("sethub")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("Vous n'êtes pas un joueur");
                return true;
            }
            Player player = (Player) sender;
            if (player.hasPermission("hubcore.sethub")) {
                setSpawn(player.getLocation());
                player.sendMessage("§eLe spawn du Hub a été assigné à votre position!");
            }
        }
        return false;
    }

    private void setSpawn(Location loc) {
        plugin.getConfig().set("spawn.x", loc.getX());
        plugin.getConfig().set("spawn.y", loc.getY());
        plugin.getConfig().set("spawn.z", loc.getZ());
        plugin.getConfig().set("spawn.p", loc.getPitch());
        plugin.getConfig().set("spawn.yaw", loc.getYaw());
        plugin.getConfig().set("spawn.world", loc.getWorld().getName());
        plugin.saveConfig();
    }
}
