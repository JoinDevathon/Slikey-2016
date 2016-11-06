package org.devathon.contest2016;

import org.bukkit.entity.Player;

/**
 * @author kevin
 * @since 06.11.2016
 */
public class TerminatorEnergyExplosion {

    private final Terminator terminator;

    public TerminatorEnergyExplosion(Terminator terminator) {
        this.terminator = terminator;
    }

    public void execute() {
        int energy = terminator.getEnergy();
        if (terminator.getEnergy() > 0) {
            terminator.setEnergy(0);

            Player player = terminator.getPlayer();
            player.getWorld().createExplosion(player.getLocation(), energy * 3, true);
        }
    }


}
