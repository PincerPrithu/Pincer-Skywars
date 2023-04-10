package me.pincer.skywars.Scoreboard;

import fr.mrmicky.fastboard.FastBoard;
import me.pincer.pincerlib.ConfigManager.ScoreboardConfig;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

import static me.pincer.pincerlib.Utilities.LoggerUtils.c;

public class InGame {
    public static ScoreboardConfig scoreboard;
    public static YamlConfiguration config;
    public static List<String> lines = new ArrayList<String>();

    public static void getScoreboard() {
        scoreboard = new ScoreboardConfig("ingame");
        config = scoreboard.config;

        lines.clear();
        lines.add("&7%date%");
        lines.add("");
        lines.add("&fRefill: &a%refill_timer%");
        lines.add("");
        lines.add("&7Kills: &a%player_kills%");

        lines = scoreboard.getLines("player", lines);

        List<String> temp = new ArrayList<String>();
        for (String line : lines) {
            temp.add(c(line));
        }
        lines = temp;
    }

    public static void updateScoreboard() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            FastBoard board = new FastBoard(player);
            board.updateTitle(c(ScoreboardManager.getTitle()));
            board.updateLines(lines);
        }
    }
}
