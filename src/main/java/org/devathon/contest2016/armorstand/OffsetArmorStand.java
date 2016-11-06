package org.devathon.contest2016.armorstand;

import net.minecraft.server.v1_10_R1.EntityArmorStand;
import net.minecraft.server.v1_10_R1.Vector3f;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftArmorStand;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import org.devathon.contest2016.VectorUtils;

import java.util.function.Supplier;

import static org.devathon.contest2016.VectorUtils.degToRadians;

/**
 * @author kevin
 * @since 05.11.2016
 */
public class OffsetArmorStand implements Runnable {

    public static OffsetArmorStand spawn(Location location, Supplier<Location> originSupplier, Vector offsetVector, ItemStack itemStack) {
        offsetVector.multiply(0.6);
        final OffsetArmorStand offsetArmorStand = new OffsetArmorStand(originSupplier, offsetVector);
        final ExEntityArmorStand entityArmorStand = new ExEntityArmorStand(location.getWorld(), offsetArmorStand);
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
    protected float pitchOffset;

    public OffsetArmorStand(Supplier<Location> originSupplier, Vector offsetVector) {
        this.originSupplier = originSupplier;
        this.offsetVector = offsetVector;
        this.enablePitch = false;
        this.pitchOffset = 0;
    }

    public OffsetArmorStand enablePitch() {
        this.enablePitch = true;
        return this;
    }

    public OffsetArmorStand baby() {
        entityArmorStand.setSmall(true);
        return this;
    }

    public float pitchOffset() {
        return pitchOffset;
    }

    public OffsetArmorStand pitchOffset(float pitchOffset) {
        this.pitchOffset = pitchOffset;
        return this;
    }

    public EntityArmorStand getEntityArmorStand() {
        return entityArmorStand;
    }

    public void despawn() {
        entityArmorStand.world.removeEntity(entityArmorStand);
    }

    @Override
    public void run() {
        final Location origin = originSupplier.get();
        Vector tmpOffset = offsetVector.clone();
        if (enablePitch) {
            VectorUtils.rotateX(tmpOffset, degToRadians(-(origin.getPitch() + pitchOffset)));
            entityArmorStand.setHeadPose(new Vector3f(origin.getPitch() + pitchOffset, 0, 0));
        }
        VectorUtils.rotateY(tmpOffset, degToRadians(-origin.getYaw()));
        entityArmorStand.setPositionRotation(
                origin.getX() + tmpOffset.getX(),
                origin.getY() + tmpOffset.getY() - 0.75,
                origin.getZ() + tmpOffset.getZ(),
                origin.getYaw(), 0
        );
    }

}
