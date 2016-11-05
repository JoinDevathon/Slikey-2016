package org.devathon.contest2016;

import net.minecraft.server.v1_10_R1.EntityArmorStand;
import net.minecraft.server.v1_10_R1.World;

/**
 * @author kevin
 * @since 05.11.2016
 */
public class ExEntityArmorStand extends EntityArmorStand {

    private final Runnable runnable;

    public ExEntityArmorStand(World world, Runnable runnable) {
        super(world);
        this.runnable = runnable;
    }

    @Override
    public void m() {
        runnable.run();
        super.m();
    }

}
