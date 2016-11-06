package org.devathon.contest2016;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;
import org.devathon.contest2016.armorstand.OffsetArmorStand;
import org.devathon.contest2016.gun.TerminatorEnergyExplosion;
import org.devathon.contest2016.gun.TerminatorLaserGun;

import java.util.List;

import static java.lang.Math.min;

/**
 * @author kevin
 * @since 06.11.2016
 */
public class Terminator implements Runnable {

    private final DevathonPlugin plugin;
    private final Player player;
    private final TerminatorModel model;
    private final TerminatorLaserGun laserGun;
    private final TerminatorEnergyExplosion energyExplosion;
    private Location previous;
    private BukkitTask bukkitTask;
    private long ticksLived;
    private int energy;

    public Terminator(DevathonPlugin plugin, Player player) {
        this.plugin = plugin;
        this.player = player;
        this.model = new TerminatorModel();
        laserGun = new TerminatorLaserGun(this);
        energyExplosion = new TerminatorEnergyExplosion(this);
        previous = player.getLocation();
        ticksLived = 0;
    }

    public void spawn() {
        TerminatorSpawner.spawnTerminator(player, this);
        bukkitTask = player.getServer().getScheduler().runTaskTimer(plugin, this, 0, 1);
    }

    public void despawn() {
        bukkitTask.cancel();
        model.destroy();
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
        return model.getParts(TerminatorModel.TerminatorModelPart.ENERGY_SOURCE).size();
    }

    public TerminatorModel getModel() {
        return model;
    }

    public void immaFirinMaLazor() {
        laserGun.execute();
    }

    public void energyExplosion() {
        energyExplosion.execute();
    }

    @Override
    public void run() {
        if (ticksLived++ % (5 * 20) == 0) {
            energy = min(energy + 1, getMaxEnergy());
        }
        final List<OffsetArmorStand> energySource = model.getParts(TerminatorModel.TerminatorModelPart.ENERGY_SOURCE);
        for (int i = 0; i < energySource.size(); i++) {
            OffsetArmorStand oas = energySource.get(i);
            if (energy > i) {
                ((CraftArmorStand) oas.getEntityArmorStand().getBukkitEntity()).setHelmet(new ItemStack(Material.DIAMOND_BLOCK));
            } else {
                ((CraftArmorStand) oas.getEntityArmorStand().getBukkitEntity()).setHelmet(new ItemStack(Material.COAL_BLOCK));
            }
        }

        { // Leg movement
            final double length = previous.distance(player.getLocation());
            previous = player.getLocation();

            final int ticks = 15; // ticks to move leg back and forth
            final int direction = ticksLived % (ticks * 2) >= ticks ? 1 : -1;
            float pitchChange = 60f / ticks;
            for (OffsetArmorStand oas : model.getParts(TerminatorModel.TerminatorModelPart.LEFT_LEG)) {
                float pitch = oas.pitchOffset();
                pitch += pitchChange * 4 * length * direction;
                if (pitch > 30) {
                    pitch = 30;
                } else if (pitch < -30) {
                    pitch = -30;
                }
                oas.pitchOffset(pitch);
            }
            for (OffsetArmorStand oas : model.getParts(TerminatorModel.TerminatorModelPart.RIGHT_LEG)) {
                float pitch = oas.pitchOffset();
                pitch += pitchChange * 4 * length * -direction;
                if (pitch > 30) {
                    pitch = 30;
                } else if (pitch < -30) {
                    pitch = -30;
                }
                oas.pitchOffset(pitch);
            }
        }
    }
}
