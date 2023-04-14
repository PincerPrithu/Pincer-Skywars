package me.pincer.skywars.LootManager;

import me.pincer.pincerlib.ConfigManager.InventoryConfigManager;
import me.pincer.pincerlib.General.ItemWithProbablity;
import org.bukkit.block.Chest;
import org.bukkit.configuration.file.YamlConfiguration;

public class LoadFromFile {
    public static void loadChestFromFile(Chest chest, String name) {
        InventoryConfigManager configFile = new InventoryConfigManager(name, "chests");
        YamlConfiguration config = configFile.config;

        int i = 0;
        while (true) {
            ItemWithProbablity itemWithProbablity = configFile.loadItemFromYaml("contents." + i);
            if (!config.isSet("contents." + i)) break;
            RandomChestGenerator.execute(itemWithProbablity, chest);
            i++;
        }
    }
}
