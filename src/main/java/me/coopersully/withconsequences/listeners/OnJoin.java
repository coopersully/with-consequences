package me.coopersully.withconsequences.listeners;

import me.coopersully.withconsequences.CoreUtils;
import me.coopersully.withconsequences.WithConsequences;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;

public class OnJoin implements Listener {

    @EventHandler
    public void onJoin(@NotNull PlayerJoinEvent event) {
        Player player = event.getPlayer();
        CoreUtils.sendMessages(player, WithConsequences.joinMessages);
        CoreUtils.registerPlayer(player);
    }

}
