package me.pincer.skywars.functions;

import me.pincer.pincerlib.ConfigManager.MasterConfig;
import me.pincer.pincerlib.General.PlayerSpawnLocation;
import me.pincer.skywars.Skywars;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.io.IOException;
import static me.pincer.skywars.functions.FormatC.c;



public class setPlayerSpawn {
    public static void setArenaSpawn (Player player) throws IOException {
        Location location = player.getLocation();
        PlayerSpawnLocation arenaSpawn = new PlayerSpawnLocation("ArenaSpawnLoc", location);
        MasterConfig config = Skywars.arenaConfig;
        int i = 1;
        while (true) {
            if (config.getConfig().isSet("arena-spawn." + i)) {
                i++;
            } else {
                arenaSpawn.savePlayerSpawnLocation(config.file, config.getConfig(), "arena-spawn." + i);
                config.saveConfig();
                player.sendMessage(c("&aArena Spawn Location " + i + " has been set successfully!"));
                break;
            }

        }
    }
}
