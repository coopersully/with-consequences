package me.coopersully.withconsequences.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class WithConsequences implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (args.length < 1) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3With Consequences&r v" + me.coopersully.withconsequences.WithConsequences.getPlugin().getDescription().getVersion()));
            sender.sendMessage("A semi-hardcore gamemode for multiplayer servers.");
            sender.sendMessage("");
            sender.sendMessage("/withconsequences [reload]");
            return true;
        }

        var operation = args[0];
        switch (operation) {
            case "reload", "rel", "r":
                me.coopersully.withconsequences.WithConsequences.reloadDefaultConfig();
                sender.sendMessage(ChatColor.GREEN + "Reloaded all configuration files!");
                return true;
            default:
                return false;
        }

    }
}
