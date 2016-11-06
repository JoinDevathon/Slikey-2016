package org.devathon.contest2016.armorstand;

import net.minecraft.server.v1_10_R1.EntityArmorStand;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_10_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftArmorStand;

/**
 * @author kevin
 * @since 05.11.2016
 */
public class ExEntityArmorStand extends EntityArmorStand {

    private final Runnable runnable;

    public ExEntityArmorStand(World world, Runnable runnable) {
        super(((CraftWorld) world).getHandle());
        this.runnable = runnable;
        setInvisible(true);
        setNoGravity(true);
        ((CraftArmorStand) getBukkitEntity()).setAI(false);
    }

    @Override
    public void m() {
        super.m();
        if (ticksLived > 1) {
            runnable.run();
        }
    }

}
