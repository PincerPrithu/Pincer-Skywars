package me.pincer.skywars.Commands;

import me.pincer.pincerlib.ConfigManager.MasterConfig;
import me.pincer.pincerlib.General.PlayerSpawnLocation;
import me.pincer.skywars.Skywars;
import me.pincer.skywars.Timer.StartTimer;
import me.pincer.skywars.functions.CreatePlayerCage;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static me.pincer.pincerlib.Utilities.LoggerUtils.c;

public class SkywarsCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(c("&cCommand must be executed by a player!"));
            return true;
        }

        if (args.length == 0) return false;

        Player player = (Player) sender;
        if ("start".equals(args[0])) {
            StartTimer timer = new StartTimer();
            timer.startTimer();
        } else if ("setSpectatorLocation".equals(args[0])) {
            PlayerSpawnLocation arenaSpawn = new PlayerSpawnLocation("Spectator-Location", player.getLocation());
            try {
                arenaSpawn.savePlayerSpawnLocation(Skywars.arenaConfig.file, Skywars.arenaConfig.getConfig(), "arena-spectator-location");
                player.sendMessage(c("&aSpectator Location set successfully!"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } else if ("test".equals(args[0])) {
                CreatePlayerCage.execute();
            ItemStack item = new ItemStack(Material.DIAMOND_SWORD);
            item.addEnchantment(Enchantment.DAMAGE_ALL, 1);

            MasterConfig test = new MasterConfig("test", "NONE");
            test.config.set("test", item);
            try {
                test.config.save(test.file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            player.sendMessage(c("&cInvaild Argument!"));
        }
        return true;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (args.length == 1) {
            List<String> completions = new ArrayList<>();
            completions.add("start");
            return completions;
        }
        return null;
    }

}
