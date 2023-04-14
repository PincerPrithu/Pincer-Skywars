package me.pincer.skywars.functions;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.util.HashMap;

public class FormatC {
    public static HashMap<String, String> pluginPlaceholder;
    public static String c(String msg) {
            HashMap<String, String> temp = pluginPlaceholder;
            for (String str : temp.keySet()) {
                if (msg.contains(str)) {
                    msg = msg.replace(str, pluginPlaceholder.get(str));
                }
            }
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
}
