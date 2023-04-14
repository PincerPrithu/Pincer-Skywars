package me.pincer.skywars.LootManager;

import me.pincer.pincerlib.General.ItemWithProbablity;
import me.pincer.pincerlib.General.MathUtils;
import org.bukkit.block.Chest;

public class RandomChestGenerator {
    public static void execute(ItemWithProbablity itemWithProbablity, Chest chest) {

        short percent = (short) MathUtils.random(0, 100);
        int slot = MathUtils.random(0, 26);

        if (itemWithProbablity.getProbability() == 100 || MathUtils.random(1, 100) < itemWithProbablity.getProbability())
            if (chest.getInventory().getItem(slot) == null)
                chest.getInventory().setItem(slot, itemWithProbablity.getItem());
            else execute(itemWithProbablity, chest);
    }

}
