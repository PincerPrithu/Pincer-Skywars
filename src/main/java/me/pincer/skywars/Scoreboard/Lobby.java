package me.pincer.skywars.Scoreboard;

import fr.mrmicky.fastboard.FastBoard;
import me.pincer.pincerlib.ConfigManager.ScoreboardConfig;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

import static me.pincer.skywars.functions.FormatC.c;


public class Lobby {
    public static ScoreboardConfig scoreboard;
    public static YamlConfiguration config;
    public static List<String> lines = new ArrayList<String>();

    public static void getScoreboard() {
        scoreboard = new ScoreboardConfig("lobby");
        config = scoreboard.config;

        lines.clear();
        lines.add("&7%date%");
        lines.add("");
        lines.add("&fPlayers: &a%online_players%/%max_players%");
        lines.add("&fStarting: &7%start_timer%");
        lines.add("&7Map: &a%map%");

        lines = scoreboard.getLines("content", lines);

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