package org.devathon.contest2016;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Random;

import static org.devathon.contest2016.VectorUtils.degToRadians;

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
        if (event.getAction() == Action.LEFT_CLICK_AIR) {
            final Location loc = event.getPlayer().getLocation();
            final Vector tmpOffset = new Vector(1.75, 1, 1);
            VectorUtils.rotateY(tmpOffset, degToRadians(-loc.getYaw()));
            loc.add(tmpOffset);
            final Vector dir = loc.getDirection();
            new BukkitRunnable() {

                private int i = 0;

                @Override
                public void run() {
                    final Location impact = loc.clone().add(dir.clone().multiply(i));
                    event.getPlayer().getWorld().playEffect(impact, Effect.HEART, 1);
                    for (Entity entity : loc.getWorld().getNearbyEntities(impact, 1, 1, 1)) {
                        if (entity instanceof Monster || entity instanceof Animals) {
                            entity.remove();
                            final Random random = new Random();
                            for (int i = 0; i < 20; i++) {
                                event.getPlayer().getWorld().playEffect(entity.getLocation().add(
                                        random.nextDouble() * 2 - 1,
                                        random.nextDouble(),
                                        random.nextDouble() * 2 - 1
                                ), Effect.HEART, 1);
                            }
                        }
                    }
                    if (++i > 40) {
                        cancel();
                    }
                }

            }.runTaskTimer(plugin, 0, 1);
        }
    }

}
