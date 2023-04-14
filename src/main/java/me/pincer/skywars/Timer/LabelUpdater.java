package me.pincer.skywars.Timer;

import me.pincer.pincerlib.General.MathUtils;
import me.pincer.skywars.Manager.PlaceholderManager;
import me.pincer.skywars.Scoreboard.ScoreboardManager;
import me.pincer.skywars.Skywars;
import me.pincer.skywars.functions.FormatC;
import me.pincer.skywars.listener.ChestOpen;
import org.bukkit.Bukkit;
import org.bukkit.entity.ArmorStand;
import org.bukkit.scheduler.BukkitTask;

import static me.pincer.skywars.Timer.RefillTimer.refillTimer;
import static me.pincer.skywars.functions.FormatC.c;

public class LabelUpdater {
    private BukkitTask taskID;
    public void run() {
        taskID = Bukkit.getScheduler().runTaskTimer(Skywars.getInstance(), new Runnable() {
            @Override
            public void run() {
                FormatC.pluginPlaceholder = PlaceholderManager.getPluginPlaceholders();
                for (ArmorStand stand : ChestOpen.lootChests.values()) {
                        stand.setCustomName(c("&a" + MathUtils.formatDurationMin(refillTimer)));
                    }
                }
        }, 0L, 10L); // 20 ticks = 1 second
    }
}
