package fr.lemonadd.hubcore.managers;

import fr.lemonadd.hubcore.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class SBManager {
    private Main plugin;
    public SBManager(Main plugin) {
        this.plugin = plugin;
    }

    public void createBoard(Player player) {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();
        Objective obj = board.registerNewObjective("HubScoreboard-1", "dummy", format(plugin.getConfig().getString("messages.scoreboard.titre")));
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        int i = 0;
        Score score;
        for (String msg : plugin.getConfig().getStringList("messages.scoreboard.lignes")) {
            if (i == 2) {
                score = obj.getScore(format(msg) + Bukkit.getOnlinePlayers().size());
            } else if (i == 7) {
                score = obj.getScore(format(msg) + player.getName());
            } else {
                score = obj.getScore(format(msg));
            }
            score.setScore(i);
            i++;
        }
        player.setScoreboard(board);

    }

    private String format(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

}
