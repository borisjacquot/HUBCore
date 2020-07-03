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
        Objective obj = board.registerNewObjective("HubScoreboard-1", "dummy", format("&8&l>> &e&lNOM SERVEUR &8&l<<"));
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        Score score10 = obj.getScore(format("    "));
        score10.setScore(8);
        Score score9 = obj.getScore(format(" &8&l> &6") + player.getName());
        score9.setScore(7);
        Score score7 = obj.getScore(format(" &8&l> &7Rank : &cAdmin"));
        score7.setScore(6);
        Score score6 = obj.getScore(format("  "));
        score6.setScore(5);
        Score score5 = obj.getScore(format(" &8&l> &7Coins : &e51k"));
        score5.setScore(4);
        Score score1 = obj.getScore(format(" "));
        score1.setScore(3);
        Score score2 = obj.getScore(format(" &8&l> &7Connectés : &a") + Bukkit.getOnlinePlayers().size());
        score2.setScore(2);
        Score score3 = obj.getScore(format(""));
        score3.setScore(1);
        Score score4 = obj.getScore(format(" &8&l> &esuperserveur.com"));
        score4.setScore(0);
        player.setScoreboard(board);
    }

    private String format(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

}
