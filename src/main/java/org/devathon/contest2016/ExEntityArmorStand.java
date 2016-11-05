package org.devathon.contest2016;

import net.minecraft.server.v1_10_R1.EntityArmorStand;
import net.minecraft.server.v1_10_R1.World;

/**
 * @author kevin
 * @since 05.11.2016
 */
public class ExEntityArmorStand extends EntityArmorStand {

    private final Runnable runnable;

    public ExEntityArmorStand(World world, double d0, double d1, double d2, Runnable runnable) {
        super(world, d0, d1, d2);
        this.runnable = runnable;
    }

    @Override
    public void m() {
        super.m();
        runnable.run();
    }

}
