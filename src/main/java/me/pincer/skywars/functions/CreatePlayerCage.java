package me.pincer.skywars.functions;

import me.pincer.pincerlib.General.PlayerSpawnLocation;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.io.IOException;

import static me.pincer.skywars.Skywars.arenaConfig;

public class CreatePlayerCage {
    public static World arenaWorld;
    public static void execute() {
        int i = 1;
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (arenaConfig.getConfig().isSet("arena-spawn." + i)) {
                Location location;
                try {
                    location = PlayerSpawnLocation.getPlayerSpawnLocation(arenaConfig.getConfig(), "arena-spawn." + i).location;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                makeCage(location, Material.GLASS);
                location.setPitch(0);
                player.setGameMode(GameMode.ADVENTURE);
                player.teleport(location.clone().add(0.5, -1, 0.5));
                i++;
                arenaWorld = location.getWorld();
            } else {
                break;
            }
        }
    }
    public static void makeCage (Location location, Material material) {
        Block top = location.clone().add(0, 1, 0).getBlock();
        Block bottom = location.clone().add(0, -2, 0).getBlock();
        Block side1 = location.clone().add(1, 0, 0).getBlock();
        Block side2 = location.clone().add(-1, 0, 0).getBlock();
        Block side3 = location.clone().add(0, 0, 1).getBlock();
        Block side4 = location.clone().add(0, 0, -1).getBlock();

        top.setType(material);
        bottom.setType(material);

        side1.setType(material);
        side2.setType(material);
        side3.setType(material);
        side4.setType(material);

        side1.getLocation().subtract(0, 1, 0).getBlock().setType(material);
        side2.getLocation().subtract(0, 1, 0).getBlock().setType(material);
        side3.getLocation().subtract(0, 1, 0).getBlock().setType(material);
        side4.getLocation().subtract(0, 1, 0).getBlock().setType(material);
    }
    public static void breakCage (Player player) {
        Material material = Material.AIR;
        Location location = player.getLocation().clone().add(0, 1, 0);
        Block top = location.clone().add(0, 1, 0).getBlock();
        Block bottom = location.clone().add(0, -2, 0).getBlock();
        Block side1 = location.clone().add(1, 0, 0).getBlock();
        Block side2 = location.clone().add(-1, 0, 0).getBlock();
        Block side3 = location.clone().add(0, 0, 1).getBlock();
        Block side4 = location.clone().add(0, 0, -1).getBlock();

        top.setType(material);
        bottom.setType(material);

        side1.setType(material);
        side2.setType(material);
        side3.setType(material);
        side4.setType(material);

        side1.getLocation().subtract(0, 1, 0).getBlock().setType(material);
        side2.getLocation().subtract(0, 1, 0).getBlock().setType(material);
        side3.getLocation().subtract(0, 1, 0).getBlock().setType(material);
        side4.getLocation().subtract(0, 1, 0).getBlock().setType(material);

    }
}
