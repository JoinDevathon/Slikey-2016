package org.devathon.contest2016;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.Arrays;
import java.util.function.Supplier;

/**
 * @author kevin
 * @since 06.11.2016
 */
public class TerminatorSpawner {

    public static Terminator spawnTerminator(Player player, Terminator terminator) {
        final Location loc = player.getLocation();

        final ItemStack blackWool = new ItemStack(Material.WOOL, 1, (short) 0xF);
        final ItemStack ironBlock = new ItemStack(Material.IRON_BLOCK);

        // left leg
        terminator.block(loc, player::getLocation, new Vector(1, -1, 0), blackWool);
        terminator.block(loc, player::getLocation, new Vector(1, -1, 1), blackWool);
        terminator.block(loc, player::getLocation, new Vector(1, 0, 0), blackWool);

        // right leg
        terminator.block(loc, player::getLocation, new Vector(-1, -1, 0), blackWool);
        terminator.block(loc, player::getLocation, new Vector(-1, -1, 1), blackWool);
        terminator.block(loc, player::getLocation, new Vector(-1, 0, 0), blackWool);

        // body layer 1
        terminator.block(loc, player::getLocation, new Vector(1, 1, 0), ironBlock);
        terminator.block(loc, player::getLocation, new Vector(-1, 1, 0), ironBlock);

        // body layer 2
        terminator.block(loc, player::getLocation, new Vector(1, 2, 0), ironBlock);
        terminator.block(loc, player::getLocation, new Vector(0, 2, 0), ironBlock);
        terminator.block(loc, player::getLocation, new Vector(-1, 2, 0), ironBlock);
        terminator.block(loc, player::getLocation, new Vector(1, 2, 1), ironBlock);
        terminator.block(loc, player::getLocation, new Vector(0, 2, 1), ironBlock);
        terminator.block(loc, player::getLocation, new Vector(-1, 2, 1), ironBlock);

        // body layer 3
        terminator.block(loc, player::getLocation, new Vector(1, 3, 0), ironBlock);
        terminator.block(loc, player::getLocation, new Vector(0, 3, 0), ironBlock);
        terminator.block(loc, player::getLocation, new Vector(-1, 3, 0), ironBlock);
        terminator.block(loc, player::getLocation, new Vector(1, 3, 1), ironBlock);
        terminator.block(loc, player::getLocation, new Vector(0, 3, 1), ironBlock);
        terminator.block(loc, player::getLocation, new Vector(-1, 3, 1), ironBlock);

        // body layer 4
        terminator.block(loc, player::getLocation, new Vector(1, 4, 0), ironBlock);
        terminator.block(loc, player::getLocation, new Vector(0, 4, 0), ironBlock);
        terminator.block(loc, player::getLocation, new Vector(-1, 4, 0), ironBlock);
        terminator.block(loc, player::getLocation, new Vector(0, 4, 1), ironBlock);

        spawnEnergySource(terminator, player);
        spawnHead(terminator, player);
        spawnArms(terminator, player);

        return terminator;
    }

    private static void spawnHead(Terminator terminator, Player player) {
        final Location loc = player.getLocation();

        final Supplier<Location> headAnchor = () -> player.getLocation().add(0, 3.5, 0);

        final ItemStack redWool = new ItemStack(Material.WOOL, 1, (short) 0xE);
        final ItemStack eyeBlock = new ItemStack(Material.DRAGON_EGG);
        final ItemStack skinBlock = new ItemStack(Material.HARD_CLAY);

        // head layer 1
        terminator.block(loc, headAnchor, new Vector(1, -1, -0.3), skinBlock).enablePitch();
        terminator.block(loc, headAnchor, new Vector(0, -1, -0.3), skinBlock).enablePitch();
        terminator.block(loc, headAnchor, new Vector(-1, -1, -0.3), skinBlock).enablePitch();
        terminator.block(loc, headAnchor, new Vector(1, -1, 0.7), skinBlock).enablePitch();
        terminator.block(loc, headAnchor, new Vector(0, -1, 0.7), skinBlock).enablePitch();
        terminator.block(loc, headAnchor, new Vector(-1, -1, 0.7), skinBlock).enablePitch();

        // head layer 2
        terminator.block(loc, headAnchor, new Vector(1, 0, -0.3), skinBlock).enablePitch();
        terminator.block(loc, headAnchor, new Vector(0, 0, -0.3), skinBlock).enablePitch();
        terminator.block(loc, headAnchor, new Vector(-1, 0, -0.3), skinBlock).enablePitch();
        terminator.block(loc, headAnchor, new Vector(1, 0, 0.7), skinBlock).enablePitch();
        terminator.block(loc, headAnchor, new Vector(0, 0, 0.7), skinBlock).enablePitch();
        terminator.block(loc, headAnchor, new Vector(-1, 0, 0.7), skinBlock).enablePitch();

        // eyes
        terminator.block(loc, headAnchor, new Vector(0.7, 0.25, 1), eyeBlock).enablePitch();
        terminator.block(loc, headAnchor, new Vector(-0.7, 0.25, 1), eyeBlock).enablePitch();

        // hat
        terminator.block(loc, headAnchor, new Vector(1, 1, -0.3), redWool).enablePitch();
        terminator.block(loc, headAnchor, new Vector(0, 1, -0.3), redWool).enablePitch();
        terminator.block(loc, headAnchor, new Vector(-1, 1, -0.3), redWool).enablePitch();
        terminator.block(loc, headAnchor, new Vector(1, 1, 0.7), redWool).enablePitch();
        terminator.block(loc, headAnchor, new Vector(0, 1, 0.7), redWool).enablePitch();
        terminator.block(loc, headAnchor, new Vector(-1, 1, 0.7), redWool).enablePitch();
    }

    private static void spawnArms(Terminator terminator, Player player) {
        final Location loc = player.getLocation();

        final Supplier<Location> armAnchor = () -> player.getLocation().add(0, 3, 0);

        final ItemStack ironBlock = new ItemStack(Material.IRON_BLOCK);

        // left arm
        terminator.block(loc, armAnchor, new Vector(2, -1, 0), ironBlock).enablePitch();
        terminator.block(loc, armAnchor, new Vector(2.5, -1, 0), ironBlock).enablePitch();
        terminator.block(loc, armAnchor, new Vector(2.5, -1, 1), ironBlock).enablePitch();
        terminator.block(loc, armAnchor, new Vector(2.5, -1, 2), ironBlock).enablePitch();

        // right arm
        terminator.block(loc, armAnchor, new Vector(-2, -1, 0), ironBlock).enablePitch();
        terminator.block(loc, armAnchor, new Vector(-2.5, -1, 0), ironBlock).enablePitch();
        terminator.block(loc, armAnchor, new Vector(-2.5, -1, 1), ironBlock).enablePitch();
        terminator.block(loc, armAnchor, new Vector(-2.5, -1, 2), ironBlock).enablePitch();
    }

    private static void spawnEnergySource(Terminator terminator, Player player) {
        final Location loc = player.getLocation();
        final ItemStack energyBlock = new ItemStack(Material.COAL_BLOCK);

        // energy source
        terminator.getEnergySource().addAll(Arrays.asList(
                terminator.block(loc, player::getLocation, new Vector(0, 1.5, -0.7), energyBlock),
                terminator.block(loc, player::getLocation, new Vector(0, 2.3, -0.7), energyBlock),
                terminator.block(loc, player::getLocation, new Vector(0, 3.1, -0.7), energyBlock),
                terminator.block(loc, player::getLocation, new Vector(0, 3.8, -0.7), energyBlock)
        ));
    }

}
