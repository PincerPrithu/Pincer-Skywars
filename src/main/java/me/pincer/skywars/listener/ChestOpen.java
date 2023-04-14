package me.pincer.skywars.listener;

import me.pincer.skywars.LootManager.RefillChest;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashMap;


public class ChestOpen implements Listener {
    public static HashMap<Location, ArmorStand> lootChests = new HashMap<>();
    @EventHandler
    public static void onSkywarsChestOpen (PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block block = event.getClickedBlock();

        if (block != null && block.getState() instanceof Chest) {
            if (!lootChests.containsKey(block.getLocation())) {
                Chest chest = (Chest) block.getState();
                World world = chest.getWorld();
                ArmorStand label = (ArmorStand) world.spawnEntity(chest.getLocation().add(0.5, -1, 0.5), EntityType.ARMOR_STAND);
                label.setVisible(false);
                label.setCustomNameVisible(true);
                // Perform actions when the player opens the chest
                RefillChest.execute(chest);
                lootChests.put(block.getLocation(), label);
            }
        }
    }
}
