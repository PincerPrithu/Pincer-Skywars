package me.pincer.skywars.functions;

import org.bukkit.entity.ArmorStand;

import static me.pincer.skywars.listener.ChestOpen.lootChests;

public class RemoveLabels {
    public static void execute() {
        for (ArmorStand stand : lootChests.values()) {
            stand.remove();
        }
        lootChests.clear();
    }
}
