package org.devathon.contest2016;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static org.devathon.contest2016.VectorUtils.degToRadians;

/**
 * @author kevin
 * @since 06.11.2016
 */
public class Terminator {

    private final Player player;
    private final TerminatorLaserGun laserGun;
    private final List<OffsetArmorStand> blocks;
    private final List<OffsetArmorStand> energySource;
    private int energy;

    public Terminator(Player player) {
        this.player = player;
        laserGun = new TerminatorLaserGun(this);
        blocks = new ArrayList<>();
        energySource = new ArrayList<>();
    }

    public List<OffsetArmorStand> getEnergySource() {
        return energySource;
    }

    public void despawn() {
        for (OffsetArmorStand oas : blocks) {
            oas.despawn();
        }
        blocks.clear();
        energySource.clear();
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
        final Location loc = player.getLocation();
        final Vector tmpOffset = new Vector(1.75, 0, 0);
        VectorUtils.rotateY(tmpOffset, degToRadians(-loc.getYaw()));
        loc.add(tmpOffset);
        laserGun.spawnProjectile(loc);
    }

}
