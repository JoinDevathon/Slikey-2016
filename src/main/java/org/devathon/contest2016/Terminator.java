package org.devathon.contest2016;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;
import org.devathon.contest2016.armorstand.OffsetArmorStand;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static java.lang.Math.min;
import static org.devathon.contest2016.VectorUtils.degToRadians;

/**
 * @author kevin
 * @since 06.11.2016
 */
public class Terminator implements Runnable {

    private final DevathonPlugin plugin;
    private final Player player;
    private final TerminatorLaserGun laserGun;
    private final TerminatorEnergyExplosion energyExplosion;
    private final List<OffsetArmorStand> blocks;
    private final List<OffsetArmorStand> energySource;
    private BukkitTask bukkitTask;
    private long ticksLived;
    private int energy;

    public Terminator(DevathonPlugin plugin, Player player) {
        this.plugin = plugin;
        this.player = player;
        laserGun = new TerminatorLaserGun(this);
        energyExplosion = new TerminatorEnergyExplosion(this);
        blocks = new ArrayList<>();
        energySource = new ArrayList<>();
        ticksLived = 0;
    }

    public List<OffsetArmorStand> getEnergySource() {
        return energySource;
    }

    public void spawn() {
        TerminatorSpawner.spawnTerminator(player, this);
        bukkitTask = player.getServer().getScheduler().runTaskTimer(plugin, this, 0, 1);
    }

    public void despawn() {
        bukkitTask.cancel();
        for (OffsetArmorStand oas : blocks) {
            oas.despawn();
        }
        blocks.clear();
        energySource.clear();
    }

    public Player getPlayer() {
        return player;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getMaxEnergy() {
        return energySource.size();
    }

    public OffsetArmorStand block(Location location, Supplier<Location> originSupplier, Vector offsetVector, ItemStack itemStack) {
        final OffsetArmorStand oas = OffsetArmorStand.spawn(location, originSupplier, offsetVector, itemStack);
        blocks.add(oas);
        return oas;
    }

    public void immaFirinMaLazor() {
        final Location loc = player.getLocation().add(0, 3, 0);
        final Vector tmpOffset = new Vector(1.75, 0, 0);
        VectorUtils.rotateY(tmpOffset, degToRadians(-loc.getYaw()));
        loc.add(tmpOffset);
        laserGun.spawnProjectile(loc);
    }

    public void energyExplosion() {
        energyExplosion.execute();
    }

    @Override
    public void run() {
        if (ticksLived++ % (5 * 20) == 0) {
            energy = min(energy + 1, getMaxEnergy());
        }
        for (int i = 0; i < energySource.size(); i++) {
            OffsetArmorStand oas = energySource.get(i);
            if (energy > i) {
                ((CraftArmorStand) oas.getEntityArmorStand().getBukkitEntity()).setHelmet(new ItemStack(Material.DIAMOND_BLOCK));
            } else {
                ((CraftArmorStand) oas.getEntityArmorStand().getBukkitEntity()).setHelmet(new ItemStack(Material.COAL_BLOCK));
            }
        }
    }
}
