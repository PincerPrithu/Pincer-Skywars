package me.pincer.skywars.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.ArrayList;

public class DamageListener implements Listener {
    private static ArrayList<Player> fallDamageList = new ArrayList<Player>();

    @EventHandler
    public static void damageListener (EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player)) return;
        if (!(event.getCause() == EntityDamageEvent.DamageCause.FALL)) return;
        Player player = (Player) event.getEntity();
        if (fallDamageList != null && fallDamageList.contains(player)) return;

        event.setCancelled(true);
        fallDamageList.add(player);
    }
    public static void resetGame() {
        fallDamageList.clear();
    }
}
