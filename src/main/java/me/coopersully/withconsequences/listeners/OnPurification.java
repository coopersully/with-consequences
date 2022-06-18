package me.coopersully.withconsequences.listeners;

import me.coopersully.withconsequences.CoreUtils;
import me.coopersully.withconsequences.WithConsequences;
import me.coopersully.withconsequences.RitualType;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

public class OnPurification implements Listener {

    @EventHandler
    public void onConsumeItem(@NotNull PlayerItemConsumeEvent event) {

        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        var hasWeakness = false;
        for (var effect : player.getActivePotionEffects()) {
            if (effect.getType().toString().toLowerCase().contains("weakness")) hasWeakness = true;
        }

        if (!hasWeakness) return;

        if (item.getType() == Material.GOLDEN_APPLE) {

            // Upon eating a normal golden apple
            CoreUtils.addHearts(player, 2.0);
            CoreUtils.incrementRituals(player, RitualType.REGULAR);
            player.removePotionEffect(PotionEffectType.WEAKNESS);

            player.playSound(player, Sound.ENTITY_ZOMBIE_VILLAGER_CURE, SoundCategory.PLAYERS, 2 ,1);
            CoreUtils.sendMessages(player, WithConsequences.purifyMessages);

        } else if (item.getType() == Material.ENCHANTED_GOLDEN_APPLE) {

            // Upon eating an enchanted golden apple
            CoreUtils.addHearts(player, 20.0);
            CoreUtils.incrementRituals(player, RitualType.SUPER);
            player.removePotionEffect(PotionEffectType.WEAKNESS);

            player.playSound(player, Sound.ENTITY_ZOMBIE_VILLAGER_CURE, SoundCategory.PLAYERS, 2 ,1);
            CoreUtils.sendMessages(player, WithConsequences.superPurifyMessages);

        }

    }

}
