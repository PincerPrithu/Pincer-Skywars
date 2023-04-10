package me.pincer.skywars.Manager;

import me.pincer.skywars.Skywars;
import me.pincer.skywars.Timer.RefillTimer;
import me.pincer.skywars.functions.CreatePlayerCage;
import me.pincer.skywars.listener.DamageListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class GameManager {
    public static void setGameState (GameStates gameState) {
        switch (gameState) {
            case LOBBY:
                Skywars.CurrentGameState = GameStates.LOBBY;
                break;
            case STARTING:
                Skywars.CurrentGameState = GameStates.STARTING;
                break;
            case INPROGRESS:
                Bukkit.broadcastMessage(ChatColor.GREEN + "The game has started!");
                for (Player player : Bukkit.getOnlinePlayers()) {
                    CreatePlayerCage.breakCage(player);
                    player.setFoodLevel(20);
                    player.setHealth(20);
                    player.setGameMode(GameMode.SURVIVAL);
                }
                DamageListener.resetGame();
                RefillTimer timer = new RefillTimer();
                timer.startTimer();
                Skywars.CurrentGameState = GameStates.INPROGRESS;
                break;
            case ENDING:
        }
    }
}
