package fr.lemonadd.hubcore;

import fr.lemonadd.hubcore.managers.BarManager;
import fr.lemonadd.hubcore.managers.SBManager;
import fr.lemonadd.hubcore.managers.TabManager;
import fr.lemonadd.hubcore.sql.MySQL;
import fr.lemonadd.hubcore.sql.SQLGetter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public class Main extends JavaPlugin implements Listener {

    public MySQL SQL;
    public SQLGetter data;
    public TabManager tab;
    public BarManager bar;
    public SBManager score;

    @Override
    public void onEnable() {
        this.SQL = new MySQL();
        this.data = new SQLGetter(this);
        this.tab = new TabManager(this);
        this.score = new SBManager(this);

        /* CONNEXION DB */
        /*
        try {
            SQL.connect();
        } catch (ClassNotFoundException | SQLException e) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.BLUE + "[HUBCore] " + ChatColor.WHITE +"Database non connectée");
        }

        if (SQL.isConnected()) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.BLUE + "[HUBCore] " + ChatColor.WHITE + "Database connectée!");
            data.createTable();
            // events relevants de la DB
        }
        */
        /* CONNEXION DB */

        /* SETUP TAB */
        tab.addHeader("\n&7&l--------- &c&lNOM DU SERVEUR &7&l----------\n\n");
        tab.addHeader("\n&7&l--------- &9&lNOM DU SERVEUR &7&l----------\n\n");
        tab.addHeader("\n&7&l--------- &d&lNOM DU SERVEUR &7&l----------\n\n");

        tab.addFooter("\n&7Joueurs en ligne : &c&l" + Bukkit.getOnlinePlayers().size() +
                "\n\n&7Insérer un text random oui &csalut &rahah.\n");
        tab.addFooter("\n&7Joueurs en ligne : &c&l" + Bukkit.getOnlinePlayers().size() +
                "\n\n&7Je peux changer le message aussi mdr lol\n\n");

        tab.showTab();
        /* SETUP TAB */

        /* SETUP BAR */
        this.getServer().getPluginManager().registerEvents(this,this);
        bar = new BarManager(this);
        bar.createBar();

        if (Bukkit.getOnlinePlayers().size() > 0) // pour le reload
            for (Player on : Bukkit.getOnlinePlayers()) {
                bar.addPlayer(on);
                score.createBoard(on);
            }

        /* SETUP BAR */

        /* SETUP SCOREBOARD */
        this.getServer().getPluginManager().registerEvents(this,this);
        /* SETUP SCOREBOARD */
    }

    @Override
    public void onDisable() {
        SQL.disconnect();
        bar.getBar().removeAll();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if (!bar.getBar().getPlayers().contains(event.getPlayer()))
            bar.addPlayer(event.getPlayer());

        score.createBoard(event.getPlayer());
    }
}
