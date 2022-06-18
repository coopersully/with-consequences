package me.coopersully.withconsequences;

import me.coopersully.withconsequences.commands.Stats;
import me.coopersully.withconsequences.listeners.OnJoin;
import me.coopersully.withconsequences.listeners.OnPurification;
import me.coopersully.withconsequences.listeners.OnDeath;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class WithConsequences extends JavaPlugin {

    private static WithConsequences plugin;
    public static List<String> deathMessages;
    public static List<String> joinMessages;
    public static List<String> purifyMessages;
    public static List<String> superPurifyMessages;

    @Override
    public void onEnable() {
        plugin = this;

        // Load plugin configuration
        saveDefaultConfig();
        reloadDefaultConfig();

        // Register all commands
        getCommand("withconsequences").setExecutor(new me.coopersully.withconsequences.commands.WithConsequences());
        getCommand("stats").setExecutor(new Stats());

        // Register all listeners
        getServer().getPluginManager().registerEvents(new OnJoin(), this);
        getServer().getPluginManager().registerEvents(new OnDeath(), this);
        getServer().getPluginManager().registerEvents(new OnPurification(), this);
    }

    @Override
    public void onDisable() {
    }

    public static WithConsequences getPlugin() {
        return plugin;
    }

    public static void reloadDefaultConfig() {
        getPlugin().reloadConfig();
        deathMessages = getPlugin().getConfig().getStringList("death.messages");
        joinMessages = getPlugin().getConfig().getStringList("join.messages");
        purifyMessages = getPlugin().getConfig().getStringList("purification.normal.messages");
        superPurifyMessages = getPlugin().getConfig().getStringList("purification.super.messages");
    }
}
