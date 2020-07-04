package fr.lemonadd.hubcore.managers;

import fr.lemonadd.hubcore.Main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

public class BarManager {

    private int taskID = -1;
    private final Main plugin;
    private BossBar bar;

    public BarManager(Main plugin) {
        this.plugin = plugin;
    }

    public void addPlayer(Player player) {
        bar.addPlayer(player);
    }

    public BossBar getBar() {
        return bar;
    }

    public void createBar() {
        bar = Bukkit.createBossBar(format(plugin.getConfig().getString("messages.bossbar.msg1")), BarColor.RED, BarStyle.SOLID);
        bar.setVisible(true);
        cast();
    }

    public void cast() {
        setTaskID(Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {

            int count = -1;
            double progress = 1.0;
            double time = 1.0/10;

            @Override
            public void run() {
                bar.setProgress(progress);

                switch(count) {
                    case -1:
                        break;
                    case 0:
                        bar.setColor(BarColor.PINK);
                        bar.setTitle(format(plugin.getConfig().getString("messages.bossbar.msg2")));
                        break;
                    case 1:
                        bar.setColor(BarColor.BLUE);
                        bar.setTitle(format(plugin.getConfig().getString("messages.bossbar.msg3")));
                        break;
                    case 2:
                        bar.setColor(BarColor.GREEN);
                        bar.setTitle(format(plugin.getConfig().getString("messages.bossbar.msg4")));
                        break;
                    case 3:
                    default:
                        bar.setColor(BarColor.RED);
                        bar.setTitle(format(plugin.getConfig().getString("messages.bossbar.msg1")));
                        count = -1;
                        break;
                }

                progress = progress - time;
                if (progress <= 0) {
                    count++;
                    progress = 1.0;
                }

            }

        }, 0, 20));
    }

    private String format(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

}