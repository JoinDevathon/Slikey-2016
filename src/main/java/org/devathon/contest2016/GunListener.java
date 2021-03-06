package org.devathon.contest2016;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * @author kevin
 * @since 05.11.2016
 */
public class GunListener implements Listener {

    private final DevathonPlugin plugin;

    public GunListener(DevathonPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onLeftClick(PlayerInteractEvent event) {
        if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
            final Terminator terminator = plugin.getTerminator(event.getPlayer());
            if (terminator != null) {
                final Player player = event.getPlayer();
                if (player.getInventory().getHeldItemSlot() == 0) {
                    terminator.immaFirinMaLazor();
                } else if (player.getInventory().getHeldItemSlot() == 1) {
                    terminator.energyExplosion();
                }
            }
        }
    }

}
