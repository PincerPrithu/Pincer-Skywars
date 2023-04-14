package me.pincer.skywars.Commands;

import me.pincer.skywars.LootManager.LoadFromFile;
import me.pincer.skywars.LootManager.SaveToFile;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


import static me.pincer.skywars.Skywars.arenaConfig;
import static me.pincer.skywars.functions.FormatC.c;

public class ChestCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(c("&cCommand must be executed by a player!"));
            return true;
        }

        if (args.length == 0) return false;

        Player player = (Player) sender;
        if ("createchest".equalsIgnoreCase(args[0])) {
            if (!((player.getTargetBlock((Set<Material>) null, 5).getType() == Material.CHEST)) || args[1] == null) {
                player.sendMessage(c("&cYou must be looking at a chest while executing that command!"));
                return true;
            }
            SaveToFile.saveChestToFile((Chest) player.getTargetBlock((Set<Material>) null, 5).getState(), args[1]);
            player.sendMessage(c("&aSuccessfully saved chest " + args[1] + "!"));
        } else if ("setchest".equalsIgnoreCase(args[0])) {
            Block block = (player.getTargetBlock((Set<Material>) null, 5));
            if (!(block.getType() == Material.CHEST) || args[1] == null) {
                Chest chest = (Chest) block.getState();
                if (chest.getInventory().getContents().length == 0) {
                    player.sendMessage(c("&cChest must have items!"));
                    return true;
                }
                player.sendMessage(c("&cYou must be looking at a chest while executing that command!"));
                return true;
            }
            try {
                if (!arenaConfig.getConfig().isSet("chests")) {
                    arenaConfig.getConfig().set("chests.1.name", args[1]);
                    Location loc = player.getTargetBlock((Set<Material>) null, 5).getLocation();
                    arenaConfig.getConfig().set("chests.1.chest", loc);
                    arenaConfig.saveConfig();
                } else {
                    int i = 1;
                    while (true) {
                        if (arenaConfig.getConfig().isSet("chests." + i))
                            i++;
                        else {
                            arenaConfig.getConfig().set("chests." + i +".name", args[1]);
                            Location loc = player.getTargetBlock((Set<Material>) null, 5).getLocation();
                            arenaConfig.getConfig().set("chests." + i + ".chest", loc);
                            arenaConfig.saveConfig();
                            return true;
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            LoadFromFile.loadChestFromFile((Chest) player.getTargetBlock((Set<Material>) null, 5).getState(), args[1]);
        } else {
            player.sendMessage(c("&cInvalid Argument!"));
        }
        return true;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (args.length == 1) {
            List<String> completions = new ArrayList<>();
            completions.add("createchest");
            completions.add("setchest");
            return completions;
        }
        return null;
    }
}
