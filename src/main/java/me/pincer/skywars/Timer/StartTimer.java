package me.pincer.skywars.Timer;

import me.pincer.skywars.Manager.GameManager;
import me.pincer.skywars.Manager.GameStates;
import me.pincer.skywars.Skywars;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class StartTimer {
    private int taskID;
    public static int timer = 15;
    public void startTimer() {
        run();
        timer = 15;
    }
    public void run() {
        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Skywars.getInstance(), new Runnable() {
            @Override
            public void run() {
                if (timer > 0) {
                    if (timer == 15 || timer == 10 || timer == 5 || timer == 4 || timer == 3 || timer == 2 || timer == 1) {
                        Bukkit.broadcastMessage(ChatColor.YELLOW + "The game will start in " + timer + " seconds!");
                    }
                    timer--;
                } else {
                    try {
                        GameManager.setGameState(GameStates.INPROGRESS);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    Bukkit.getScheduler().cancelTask(taskID);
                }
            }
        }, 0L, 20L); // 20 ticks = 1 second
    }
}







