package me.pincer.skywars.Timer;

import me.pincer.skywars.Skywars;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitTask;

public class RefillTimer {
    private BukkitTask taskID;
    public static int refillTimer = 180;
    public void startTimer() {
        taskID = Bukkit.getScheduler().runTaskTimerAsynchronously(Skywars.getInstance(), () -> {
            if (refillTimer > 0) {
                if (refillTimer == 60) {
                    Bukkit.broadcastMessage(ChatColor.YELLOW + "Chests refill in " + refillTimer + " seconds!");
                }
                refillTimer--;
            } else {
                Bukkit.broadcastMessage(ChatColor.GREEN + "Chests have been refilled!");
                refillTimer = 180;
            }
        }, 0L, 20L); // 20 ticks = 1 second
    }
}
