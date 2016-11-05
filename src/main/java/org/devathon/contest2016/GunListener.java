package org.devathon.contest2016;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * @author kevin
 * @since 05.11.2016
 */
public class GunListener implements Listener {

    @EventHandler
    public void onLeftClick(PlayerInteractEvent event) {
        System.out.println(event.getAction());
    }

}
