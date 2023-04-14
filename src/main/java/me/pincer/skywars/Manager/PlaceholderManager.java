package me.pincer.skywars.Manager;

import me.pincer.skywars.Timer.RefillTimer;
import me.pincer.skywars.Timer.StartTimer;
import org.bukkit.Bukkit;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class PlaceholderManager {
    public static HashMap<String, String> placeHolders = new HashMap<>();
    public static HashMap<String, String> getPluginPlaceholders() {

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDateTime now = LocalDateTime.now();

            placeHolders.put("%online_players%", String.valueOf(Bukkit.getOnlinePlayers().size()));
            placeHolders.put("%max_players%", "20");
            placeHolders.put("%start_timer%", String.valueOf(StartTimer.timer));
            placeHolders.put("%date%", dtf.format(now));
            placeHolders.put("%map%", "MAPNAME");
            placeHolders.put("%refill_timer%", String.valueOf(RefillTimer.refillTimer));

        return placeHolders;
    }

}
