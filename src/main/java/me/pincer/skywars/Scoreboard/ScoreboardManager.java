package me.pincer.skywars.Scoreboard;

import fr.mrmicky.fastboard.FastBoard;
import me.pincer.pincerlib.ConfigManager.MasterConfig;
import me.pincer.skywars.Skywars;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.Map;

import static me.pincer.skywars.Skywars.CurrentGameState;
import static org.bukkit.Bukkit.getServer;

public class ScoreboardManager {
    public static Map<Player, FastBoard> scoreboards;
    private static YamlConfiguration config;
    private static MasterConfig scoreboard;
    public static void master() {
        scoreboard = new MasterConfig("config", "scoreboard");
        config = scoreboard.config;
        Lobby.getScoreboard();
        InGame.getScoreboard();
        getServer().getScheduler().runTaskTimer(Skywars.getInstance(), () -> {
            switch (CurrentGameState) {
                case LOBBY:
                        Lobby.updateScoreboard();
                    break;
                case STARTING:
                        Lobby.updateScoreboard();
                    break;
                case INPROGRESS:
                        InGame.updateScoreboard();
                    break;
                case ENDING:
            }

        }, 0, 20);
    }
    public static String getTitle() {
        if (config.isSet("scoreboard-title"))
            return config.getString("scoreboard-title");
        config.set("scoreboard-title", "&e&lSkywars");
        try {
            config.save(scoreboard.file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "&e&lSkywars";
    }
}
