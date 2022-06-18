package me.coopersully.withconsequences.commands;

import me.coopersully.withconsequences.CoreUtils;
import me.coopersully.withconsequences.RitualType;
import org.bukkit.ChatColor;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Stats implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        Player player = CoreUtils.checkPlayer(sender);
        if (player == null) return false;

        player.sendMessage(ChatColor.AQUA + "Your current statistics:");
        player.sendMessage(ChatColor.AQUA + "  \u2022 Kills: " + ChatColor.YELLOW + player.getStatistic(Statistic.PLAYER_KILLS));
        player.sendMessage(ChatColor.AQUA + "  \u2022 Deaths: " + ChatColor.YELLOW + player.getStatistic(Statistic.DEATHS));
        player.sendMessage(ChatColor.AQUA + "  \u2022 Regular Purifications Performed: " + ChatColor.YELLOW + CoreUtils.getRituals(player, RitualType.REGULAR));
        player.sendMessage(ChatColor.AQUA + "  \u2022 Super Purifications Performed: " + ChatColor.YELLOW + CoreUtils.getRituals(player, RitualType.SUPER));

        return true;

    }
}
