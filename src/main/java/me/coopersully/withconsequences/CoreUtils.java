package me.coopersully.withconsequences;

import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CoreUtils {

    public static @Nullable Player checkPlayer(CommandSender sender) {
        if (sender instanceof Player player) return player;
        sender.sendMessage(ChatColor.RED + "This command can only be run via a player.");
        return null;
    }

    private static final double maxHealth = 20;

    public static void addHearts(@NotNull Player player, double amount) {

        var health = player.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        assert health != null;

        health.setBaseValue(health.getBaseValue() + amount);
        var health_amt = health.getBaseValue();
        if (health_amt > maxHealth) {
            health.setBaseValue(maxHealth);
        }
    }

    private static final NamespacedKey rituals = new NamespacedKey(WithConsequences.getPlugin(), "rituals");
    private static final NamespacedKey super_rituals = new NamespacedKey(WithConsequences.getPlugin(), "super_rituals");

    public static void incrementRituals(@NotNull Player player, @NotNull RitualType type) {

        NamespacedKey stat;
        switch (type) {
            case REGULAR -> stat = rituals;
            case SUPER -> stat = super_rituals;
            default -> {
                throw new RuntimeException("Ritual type provided is not accounted for; contact developer.");
            }
        }

        var playerPDC = player.getPersistentDataContainer();
        if (playerPDC.has(stat)) {
            var amount = getRituals(player, type);
            playerPDC.set(stat, PersistentDataType.INTEGER, amount + 1);
            return;
        }

        registerPlayer(player);
        incrementRituals(player, type);
    }

    public static void registerPlayer(@NotNull Player player) {
        player.getPersistentDataContainer().set(rituals, PersistentDataType.INTEGER, 0);
        player.getPersistentDataContainer().set(super_rituals, PersistentDataType.INTEGER, 0);
    }

    public static int getRituals(@NotNull Player player, @NotNull RitualType type) {
        return player.getPersistentDataContainer().get(getKeyFromType(type), PersistentDataType.INTEGER);
    }

    private static NamespacedKey getKeyFromType(@NotNull RitualType type) {
        switch (type) {
            case REGULAR -> {
                return rituals;
            }
            case SUPER -> {
                return super_rituals;
            }
            default -> throw new RuntimeException("Ritual type provided is not accounted for; contact developer.");
        }

    }

    public static void sendMessages(@NotNull Player player, @NotNull List<String> messages) {
        if (messages.isEmpty()) return;
        for (var message : messages) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
        }
    }
}
