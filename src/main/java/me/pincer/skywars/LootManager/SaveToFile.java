package me.pincer.skywars.LootManager;

import me.pincer.pincerlib.ConfigManager.InventoryConfigManager;
import org.bukkit.block.Chest;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class SaveToFile {
    public static void saveChestToFile(Chest chest, String name) {
        InventoryConfigManager config = new InventoryConfigManager(name, "chests");
        Inventory inventory = chest.getBlockInventory();
        int i = 0;
        for (ItemStack item : inventory.getContents()) {
            if (item != null) {
                config.saveItemToYaml(item, "contents." + i);
                i++;
                config.saveConfig();
            }
        }
    }
}
