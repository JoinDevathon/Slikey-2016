package org.devathon.contest2016;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftArmorStand;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author kevin
 * @since 06.11.2016
 */
public class TerminatorLaserGun {

    private final Terminator terminator;

    public TerminatorLaserGun(Terminator terminator) {
        this.terminator = terminator;
    }

    public ExEntityArmorStand spawnProjectile(Location loc) {
        if (terminator.getEnergy() > 0) {
            terminator.setEnergy(terminator.getEnergy() - 1);

            final Vector dir = loc.getDirection();
            final AtomicReference<ExEntityArmorStand> ref = new AtomicReference<>();
            final ExEntityArmorStand eas = new ExEntityArmorStand(loc.getWorld(), new Runnable() {

                private int i = 0;

                @Override
                public void run() {
                    final ExEntityArmorStand eas = ref.get();
                    final Location impact = loc.clone().add(dir.clone().multiply(i));
                    eas.setPositionRotation(impact.getX(), impact.getY() - 1, impact.getZ(), impact.getYaw(), 0);
                    for (Entity entity : loc.getWorld().getNearbyEntities(impact, 1, 1, 1)) {
                        hitEntity(entity);
                    }
                    if (i++ > 40) {
                        eas.die();
                    }
                }

            });
            eas.setPositionRotation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), 0);
            eas.setInvisible(true);
            eas.setNoGravity(true);
            eas.setSmall(true);
            ((CraftArmorStand) eas.getBukkitEntity()).setHelmet(new ItemStack(Material.DIAMOND_BLOCK));
            eas.world.addEntity(eas);
            ref.set(eas);
            return eas;
        }
        return null;
    }

    private void hitEntity(Entity entity) {
        if (entity instanceof Monster || entity instanceof Animals) {
            entity.remove();
            final Random random = new Random();
            for (int i = 0; i < 20; i++) {
                entity.getWorld().playEffect(entity.getLocation().add(
                        random.nextDouble() * 2 - 1,
                        random.nextDouble(),
                        random.nextDouble() * 2 - 1
                ), Effect.HEART, 1);
            }
        }
    }

}
