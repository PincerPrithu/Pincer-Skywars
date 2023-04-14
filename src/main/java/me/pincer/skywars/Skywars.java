package me.pincer.skywars;

import me.pincer.pincerlib.ConfigManager.MasterConfig;
import me.pincer.pincerlib.PincerLib;
import me.pincer.pincerlib.Utilities.LoggerUtils;
import me.pincer.skywars.Commands.ArenaCommand;
import me.pincer.skywars.Commands.ChestCommand;
import me.pincer.skywars.Commands.SkywarsCommand;
import me.pincer.skywars.Manager.GameStates;
import me.pincer.skywars.Manager.PlaceholderManager;
import me.pincer.skywars.Scoreboard.ScoreboardManager;
import me.pincer.skywars.Timer.LabelUpdater;
import me.pincer.skywars.functions.FormatC;
import me.pincer.skywars.functions.RemoveLabels;
import me.pincer.skywars.listener.ChestOpen;
import me.pincer.skywars.listener.DamageListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;



public final class Skywars extends JavaPlugin {
    public static GameStates CurrentGameState;
    public static MasterConfig arenaConfig;
    private static Skywars instance;
    public static List<String> chests = new ArrayList<String>();
    @Override
    public void onEnable() {
        // Plugin startup logic
        FormatC.pluginPlaceholder = PlaceholderManager.getPluginPlaceholders();
        instance = this;

        PincerLib.setPlugin(this);
        arenaConfig = new MasterConfig("arena", "data");
        getCommand("arena").setExecutor(new ArenaCommand());
        getCommand("skywars").setExecutor(new SkywarsCommand());
        getCommand("chest").setExecutor(new ChestCommand());
        getServer().getPluginManager().registerEvents(new DamageListener(), this);
        getServer().getPluginManager().registerEvents(new ChestOpen(), this);

        CurrentGameState = GameStates.LOBBY;
        ScoreboardManager.master();
        LabelUpdater updater = new LabelUpdater();
        updater.run();
    }

    public static Skywars getInstance() {
        return instance;
    }

    @Override
    public void onDisable() {
        RemoveLabels.execute();
    }
}
