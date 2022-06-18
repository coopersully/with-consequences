package me.coopersully.withconsequences.listeners;

import me.coopersully.withconsequences.CoreUtils;
import me.coopersully.withconsequences.WithConsequences;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

public class OnDeath implements Listener {

    @EventHandler
    public void onDeath(@NotNull PlayerDeathEvent event) {

        Player player = event.getPlayer();
        AttributeInstance healthAttribute = player.getAttribute(Attribute.GENERIC_MAX_HEALTH);

        assert healthAttribute != null;
        double health = healthAttribute.getValue();

        if (health <= 2.0) {
            healthAttribute.setBaseValue(healthAttribute.getDefaultValue());
            for (AttributeModifier modifier : healthAttribute.getModifiers()) {
                healthAttribute.removeModifier(modifier);
            }
            // TODO: Temporarily ban the player for time specified in config
            player.banPlayer(
                    ChatColor.YELLOW + "You ran out of health completely! You will be able to rejoin and resume playing in 24h.",
                    new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24))
            );
            return;
        }

        // TODO: Make decrement value configurable
        healthAttribute.setBaseValue(healthAttribute.getBaseValue() - 2.0);
        CoreUtils.sendMessages(player, WithConsequences.deathMessages);

    }

}
