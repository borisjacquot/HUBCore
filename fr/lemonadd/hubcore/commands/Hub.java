package fr.lemonadd.hubcore.commands;

import fr.lemonadd.hubcore.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Hub implements CommandExecutor {
    private Main plugin;
    public Hub(Main plugin) { this.plugin = plugin; }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("hub")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("Vous n'êtes pas un joueur");
                return true;
            }
            Player player = (Player) sender;
            spawn(player);
        }
        return false;
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
