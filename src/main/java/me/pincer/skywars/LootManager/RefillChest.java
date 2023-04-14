package me.pincer.skywars.LootManager;

import org.bukkit.Location;
import org.bukkit.block.Chest;

import java.util.ArrayList;
import java.util.List;

import static me.pincer.skywars.Skywars.arenaConfig;

public class RefillChest {


    private static List<Chest> listOfChests = new ArrayList<Chest>();

    public static void execute (Chest chest) {
        int i = 1;
        try {
            if (!arenaConfig.getConfig().isSet("chests")) {
                return;
            }
            Location loc = chest.getLocation();
            while (true) {
                if (!arenaConfig.getConfig().isSet("chests." + i + ".chest")) {
                    return;
                }
                Location x = (Location) arenaConfig.getConfig().get("chests." + i + ".chest");

                if (x.equals(loc)) {
                    LoadFromFile.loadChestFromFile(chest, arenaConfig.getConfig().getString("chests." + i + ".name"));
                    listOfChests.add(chest);
                    break;
                }
                i++;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
