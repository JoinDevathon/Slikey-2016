package org.devathon.contest2016;

import net.minecraft.server.v1_10_R1.EntityArmorStand;
import net.minecraft.server.v1_10_R1.Vector3f;
import net.minecraft.server.v1_10_R1.World;
import org.bukkit.Location;
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

    public static OffsetArmorStand spawn(Location location, Supplier<Location> originSupplier, Vector offsetVector, ItemStack itemStack) {
        offsetVector.multiply(0.6);
        final World world = ((CraftWorld) location.getWorld()).getHandle();
        final OffsetArmorStand offsetArmorStand = new OffsetArmorStand(originSupplier, offsetVector);
        final ExEntityArmorStand entityArmorStand = new ExEntityArmorStand(world, offsetArmorStand);
        entityArmorStand.setPosition(location.getX(), location.getY(), location.getZ());
        ((CraftArmorStand) entityArmorStand.getBukkitEntity()).setHelmet(itemStack);
        entityArmorStand.world.addEntity(entityArmorStand);
        offsetArmorStand.entityArmorStand = entityArmorStand;
        return offsetArmorStand;
    }

    protected final Supplier<Location> originSupplier;
    protected final Vector offsetVector;
    protected EntityArmorStand entityArmorStand;
    protected boolean enablePitch;

    public OffsetArmorStand(Supplier<Location> originSupplier, Vector offsetVector) {
        this.originSupplier = originSupplier;
        this.offsetVector = offsetVector;
        this.enablePitch = false;
    }

    public OffsetArmorStand enablePitch() {
        this.enablePitch = true;
        return this;
    }

    @Override
    public void run() {
        final Location origin = originSupplier.get();
        final Vector tmpOffset = offsetVector.clone();
        VectorUtils.rotate2d(tmpOffset, degToRadians(-origin.getYaw()));

        if (enablePitch) {
            entityArmorStand.setHeadPose(new Vector3f(origin.getPitch(), 0, 0));
        }

        entityArmorStand.setPositionRotation(
                origin.getX() + tmpOffset.getX(),
                origin.getY() + tmpOffset.getY() - 3.5,
                origin.getZ() + tmpOffset.getZ(),
                origin.getYaw(), 0
        );
    }

}
