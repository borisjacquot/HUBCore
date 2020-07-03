package fr.lemonadd.hubcore.commands;

import fr.lemonadd.hubcore.models.GUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Trails implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("trails")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("Vous n'êtes pas un joueur");
                return true;
            }
            Player player = (Player) sender;
            GUI menu = new GUI();
            menu.openInventory(player);
            return true;
        }
        return false;
    }
}
