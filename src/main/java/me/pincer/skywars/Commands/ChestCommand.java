package me.pincer.skywars.Commands;

import me.pincer.skywars.LootManager.SaveToFile;
import org.bukkit.Material;
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

import static me.pincer.pincerlib.Utilities.LoggerUtils.c;

public class ChestCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(c("&cCommand must be executed by a player!"));
            return true;
        }

        if (args.length == 0) return false;

        Player player = (Player) sender;
        if ("createChest".equalsIgnoreCase(args[0])) {
            if (!((player.getTargetBlock((Set<Material>) null, 5).getType() == Material.CHEST)) || args[1] == null) {
                player.sendMessage(c("&cYou must be looking at a chest while executing that command!"));
                return true;
            }
            SaveToFile.saveChestToFile((Chest) player.getTargetBlock((Set<Material>) null, 5).getState(), args[1]);
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
            completions.add("createChest");
            return completions;
        }
        return null;
    }
}
