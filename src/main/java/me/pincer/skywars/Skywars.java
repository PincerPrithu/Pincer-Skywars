package me.pincer.skywars;

import me.pincer.pincerlib.ConfigManager.MasterConfig;
import me.pincer.pincerlib.PincerLib;
import me.pincer.skywars.Commands.ArenaCommand;
import me.pincer.skywars.Commands.ChestCommand;
import me.pincer.skywars.Commands.SkywarsCommand;
import me.pincer.skywars.Manager.GameStates;
import me.pincer.skywars.Scoreboard.ScoreboardManager;
import me.pincer.skywars.listener.DamageListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;


public final class Skywars extends JavaPlugin {
    public static GameStates CurrentGameState;
    public static MasterConfig arenaConfig;
    private static Skywars instance;
    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.broadcastMessage("SEX");
        instance = this;
        PincerLib.setPlugin(this);
        arenaConfig = new MasterConfig("arena", "data");
        getCommand("arena").setExecutor(new ArenaCommand());
        getCommand("skywars").setExecutor(new SkywarsCommand());
        getCommand("chest").setExecutor(new ChestCommand());
        getServer().getPluginManager().registerEvents(new DamageListener(), this);
        CurrentGameState = GameStates.LOBBY;
        ScoreboardManager.master();

    }

    public static Skywars getInstance() {
        return instance;
    }

    @Override
    public void onDisable() {

    }
}
