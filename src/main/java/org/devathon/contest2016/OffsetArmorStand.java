package org.devathon.contest2016;

import net.minecraft.server.v1_10_R1.EntityArmorStand;
import net.minecraft.server.v1_10_R1.World;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_10_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftArmorStand;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.function.Supplier;

import static org.devathon.contest2016.VectorUtils.degToRadians;

/**
 * @author kevin
 * @since 05.11.2016
 */
public class OffsetArmorStand implements Runnable {

    public static OffsetArmorStand spawn(Location location, Supplier<Location> originSupplier, Vector offsetVector) {
        final World world = ((CraftWorld) location.getWorld()).getHandle();
        final OffsetArmorStand offsetArmorStand = new OffsetArmorStand(originSupplier, offsetVector);
        final ExEntityArmorStand entityArmorStand = new ExEntityArmorStand(world, location.getX(), location.getY(), location.getZ(), offsetArmorStand);
        offsetArmorStand.entityArmorStand = entityArmorStand;
        ((CraftArmorStand) entityArmorStand.getBukkitEntity()).setHelmet(new ItemStack(Material.IRON_BLOCK));
        world.addEntity(entityArmorStand);
        // do some stuff here
        return offsetArmorStand;
    }

    protected final Supplier<Location> originSupplier;
    protected final Vector offsetVector;
    protected EntityArmorStand entityArmorStand;

    public OffsetArmorStand(Supplier<Location> originSupplier, Vector offsetVector) {
        this.originSupplier = originSupplier;
        this.offsetVector = offsetVector;
    }

    @Override
    public void run() {
        final Location origin = originSupplier.get();
        final Vector tmpOffset = offsetVector.clone();
        VectorUtils.rotate2d(tmpOffset, degToRadians(origin.getYaw()));

        entityArmorStand.setLocation(
                origin.getX() + tmpOffset.getX(),
                origin.getY() + tmpOffset.getY(),
                origin.getZ() + tmpOffset.getZ(),
                origin.getYaw(), origin.getPitch()
        );
    }

}
