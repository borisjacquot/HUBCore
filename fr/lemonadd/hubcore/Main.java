package fr.lemonadd.hubcore;

import fr.lemonadd.hubcore.commands.Trails;
import fr.lemonadd.hubcore.events.*;
import fr.lemonadd.hubcore.managers.BarManager;
import fr.lemonadd.hubcore.managers.SBManager;
import fr.lemonadd.hubcore.managers.TabManager;
import fr.lemonadd.hubcore.models.GUI;
import fr.lemonadd.hubcore.sql.MySQL;
import fr.lemonadd.hubcore.sql.SQLGetter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

    public MySQL SQL;
    public SQLGetter data;
    public TabManager tab;
    public BarManager bar;
    public SBManager score;

    @Override
    public void onEnable() {

        /* perms
        hubcore.edit        -> pouvoir construire
        hubcore.doublejump  -> double jump
         */

        PluginManager pm = this.getServer().getPluginManager();
        this.SQL = new MySQL();
        this.data = new SQLGetter(this);
        this.tab = new TabManager(this);
        this.score = new SBManager(this);
        GUI menu = new GUI();

        this.saveDefaultConfig();

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

        /* SETUP EVENTS */
        pm.registerEvents(new Interact(), this);
        pm.registerEvents(new Join(), this);
        pm.registerEvents(new PlayerMoves(), this);
        pm.registerEvents(new Security(), this);
        pm.registerEvents(new Ping(this), this);
        /* SETUP EVENTS */

        /* SETUP COMMANDS */

        /* SETUP COMMANDS */

        /* SETUP TAB */
        for (String msg : this.getConfig().getStringList("messages.tablist.header")) {
            tab.addHeader(msg);
        }
        for (String msg : this.getConfig().getStringList("messages.tablist.footer")) {
            tab.addFooter("\n&7Joueurs en ligne : &c&l" + Bukkit.getOnlinePlayers().size() + msg);
        }
        tab.showTab();
        /* SETUP TAB */

        /* SETUP BAR + SCOREBOARD */
        pm.registerEvents(this,this);
        bar = new BarManager(this);
        bar.createBar();

        if (Bukkit.getOnlinePlayers().size() > 0) // pour le reload
            for (Player on : Bukkit.getOnlinePlayers()) {
                bar.addPlayer(on);
                score.createBoard(on);
            }

        /* SETUP BAR + SCOREBOARD */

        /* SETUP PARTICULES */
        menu.register();
        pm.registerEvents(new ClickEvent(), this);
        pm.registerEvents(new Quit(), this);
        pm.registerEvents(new Movement(), this);
        this.getCommand("trails").setExecutor(new Trails());
        /* SETUP PARTICULES */


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
