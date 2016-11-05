package org.devathon.contest2016;

import net.minecraft.server.v1_10_R1.EntityArmorStand;
import net.minecraft.server.v1_10_R1.World;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftArmorStand;

/**
 * @author kevin
 * @since 05.11.2016
 */
public class ExEntityArmorStand extends EntityArmorStand {

    private final Runnable runnable;

    public ExEntityArmorStand(World world, Runnable runnable) {
        super(world);
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
