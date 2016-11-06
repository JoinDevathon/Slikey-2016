package org.devathon.contest2016;

import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.function.Supplier;

import static org.devathon.contest2016.TerminatorModel.TerminatorModelPart.*;

/**
 * @author kevin
 * @since 06.11.2016
 */
public class TerminatorSpawner {

    public static Terminator spawnTerminator(Player player, Terminator terminator) {
        final ItemStack ironBlock = new ItemStack(Material.IRON_BLOCK);

        // body layer 1
        terminator.getModel().spawn(player::getLocation, new Vector(1, 2, 0), ironBlock, BODY);
        terminator.getModel().spawn(player::getLocation, new Vector(0, 2, 0), ironBlock, BODY);
        terminator.getModel().spawn(player::getLocation, new Vector(-1, 2, 0), ironBlock, BODY);
        terminator.getModel().spawn(player::getLocation, new Vector(1, 2, 1), ironBlock, BODY);
        terminator.getModel().spawn(player::getLocation, new Vector(0, 2, 1), ironBlock, BODY);
        terminator.getModel().spawn(player::getLocation, new Vector(-1, 2, 1), ironBlock, BODY);

        // body layer 2
        terminator.getModel().spawn(player::getLocation, new Vector(1, 3, 0), ironBlock, BODY);
        terminator.getModel().spawn(player::getLocation, new Vector(0, 3, 0), ironBlock, BODY);
        terminator.getModel().spawn(player::getLocation, new Vector(-1, 3, 0), ironBlock, BODY);
        terminator.getModel().spawn(player::getLocation, new Vector(1, 3, 1), ironBlock, BODY);
        terminator.getModel().spawn(player::getLocation, new Vector(0, 3, 1), ironBlock, BODY);
        terminator.getModel().spawn(player::getLocation, new Vector(-1, 3, 1), ironBlock, BODY);

        // body layer 3
        terminator.getModel().spawn(player::getLocation, new Vector(1, 4, 0), ironBlock, BODY);
        terminator.getModel().spawn(player::getLocation, new Vector(0, 4, 0), ironBlock, BODY);
        terminator.getModel().spawn(player::getLocation, new Vector(-1, 4, 0), ironBlock, BODY);
        terminator.getModel().spawn(player::getLocation, new Vector(0, 4, 1), ironBlock, BODY);

        spawnLegs(terminator, player);
        spawnEnergySource(terminator, player);
        spawnHead(terminator, player);
        spawnArms(terminator, player);

        return terminator;
    }

    private static void spawnLegs(Terminator terminator, Player player) {
        final ItemStack blackWool = new ItemStack(Material.WOOL, 1, DyeColor.BLACK.getWoolData());
        final Supplier<Location> legAnchor = () -> {
            Location loc = player.getLocation().add(0, 1, 0);
            loc.setPitch(0);
            return loc;
        };

        // left leg
        terminator.getModel().spawn(legAnchor, new Vector(1, -2, 0), blackWool, LEFT_LEG).enablePitch().pitchOffset(-30);
        terminator.getModel().spawn(legAnchor, new Vector(1, -2, 1), blackWool, LEFT_LEG).enablePitch().pitchOffset(-30);
        terminator.getModel().spawn(legAnchor, new Vector(1, -1, 0), blackWool, LEFT_LEG).enablePitch().pitchOffset(-30);
        terminator.getModel().spawn(legAnchor, new Vector(1, 0, 0), blackWool, LEFT_LEG).enablePitch().pitchOffset(-30);

        // right leg
        terminator.getModel().spawn(legAnchor, new Vector(-1, -2, 0), blackWool, RIGHT_LEG).enablePitch().pitchOffset(30);
        terminator.getModel().spawn(legAnchor, new Vector(-1, -2, 1), blackWool, RIGHT_LEG).enablePitch().pitchOffset(30);
        terminator.getModel().spawn(legAnchor, new Vector(-1, -1, 0), blackWool, RIGHT_LEG).enablePitch().pitchOffset(30);
        terminator.getModel().spawn(legAnchor, new Vector(-1, 0, 0), blackWool, RIGHT_LEG).enablePitch().pitchOffset(30);
    }

    private static void spawnHead(Terminator terminator, Player player) {
        final Supplier<Location> headAnchor = () -> player.getLocation().add(0, 3.5, 0);

        final ItemStack redWool = new ItemStack(Material.WOOL, 1, (short) 0xE);
        final ItemStack eyeBlock = new ItemStack(Material.DRAGON_EGG);
        final ItemStack skinBlock = new ItemStack(Material.HARD_CLAY);

        // head layer 1
        terminator.getModel().spawn(headAnchor, new Vector(1, -1, -0.3), skinBlock, HEAD).enablePitch();
        terminator.getModel().spawn(headAnchor, new Vector(0, -1, -0.3), skinBlock, HEAD).enablePitch();
        terminator.getModel().spawn(headAnchor, new Vector(-1, -1, -0.3), skinBlock, HEAD).enablePitch();
        terminator.getModel().spawn(headAnchor, new Vector(1, -1, 0.7), skinBlock, HEAD).enablePitch();
        terminator.getModel().spawn(headAnchor, new Vector(0, -1, 0.7), skinBlock, HEAD).enablePitch();
        terminator.getModel().spawn(headAnchor, new Vector(-1, -1, 0.7), skinBlock, HEAD).enablePitch();

        // head layer 2
        terminator.getModel().spawn(headAnchor, new Vector(1, 0, -0.3), skinBlock, HEAD).enablePitch();
        terminator.getModel().spawn(headAnchor, new Vector(0, 0, -0.3), skinBlock, HEAD).enablePitch();
        terminator.getModel().spawn(headAnchor, new Vector(-1, 0, -0.3), skinBlock, HEAD).enablePitch();
        terminator.getModel().spawn(headAnchor, new Vector(1, 0, 0.7), skinBlock, HEAD).enablePitch();
        terminator.getModel().spawn(headAnchor, new Vector(0, 0, 0.7), skinBlock, HEAD).enablePitch();
        terminator.getModel().spawn(headAnchor, new Vector(-1, 0, 0.7), skinBlock, HEAD).enablePitch();

        // eyes
        terminator.getModel().spawn(headAnchor, new Vector(0.7, 0.25, 1), eyeBlock, HEAD).enablePitch();
        terminator.getModel().spawn(headAnchor, new Vector(-0.7, 0.25, 1), eyeBlock, HEAD).enablePitch();

        // hat
        terminator.getModel().spawn(headAnchor, new Vector(1, 1, -0.3), redWool, HEAD).enablePitch();
        terminator.getModel().spawn(headAnchor, new Vector(0, 1, -0.3), redWool, HEAD).enablePitch();
        terminator.getModel().spawn(headAnchor, new Vector(-1, 1, -0.3), redWool, HEAD).enablePitch();
        terminator.getModel().spawn(headAnchor, new Vector(1, 1, 0.7), redWool, HEAD).enablePitch();
        terminator.getModel().spawn(headAnchor, new Vector(0, 1, 0.7), redWool, HEAD).enablePitch();
        terminator.getModel().spawn(headAnchor, new Vector(-1, 1, 0.7), redWool, HEAD).enablePitch();
    }

    private static void spawnArms(Terminator terminator, Player player) {
        final Supplier<Location> armAnchor = () -> player.getLocation().add(0, 3, 0);

        final ItemStack ironBlock = new ItemStack(Material.IRON_BLOCK);

        // left arm
        terminator.getModel().spawn(armAnchor, new Vector(2, -1, 0), ironBlock, LEFT_ARM).enablePitch();
        terminator.getModel().spawn(armAnchor, new Vector(2.25, -1, 0.25), ironBlock, LEFT_ARM).enablePitch();
        terminator.getModel().spawn(armAnchor, new Vector(2.5, -1, 1), ironBlock, LEFT_ARM).enablePitch();
        terminator.getModel().spawn(armAnchor, new Vector(2.5, -1, 2), ironBlock, LEFT_ARM).enablePitch();

        // right arm
        terminator.getModel().spawn(armAnchor, new Vector(-2, -1, 0), ironBlock, RIGHT_ARM).enablePitch();
        terminator.getModel().spawn(armAnchor, new Vector(-2.25, -1, 0.25), ironBlock, RIGHT_ARM).enablePitch();
        terminator.getModel().spawn(armAnchor, new Vector(-2.5, -1, 1), ironBlock, RIGHT_ARM).enablePitch();
        terminator.getModel().spawn(armAnchor, new Vector(-2.5, -1, 2), ironBlock, RIGHT_ARM).enablePitch();
    }

    private static void spawnEnergySource(Terminator terminator, Player player) {
        final ItemStack energyBlock = new ItemStack(Material.COAL_BLOCK);

        // energy source
        terminator.getModel().spawn(player::getLocation, new Vector(0, 1.5, -0.7), energyBlock, ENERGY_SOURCE);
        terminator.getModel().spawn(player::getLocation, new Vector(0, 2.3, -0.7), energyBlock, ENERGY_SOURCE);
        terminator.getModel().spawn(player::getLocation, new Vector(0, 3.1, -0.7), energyBlock, ENERGY_SOURCE);
        terminator.getModel().spawn(player::getLocation, new Vector(0, 3.8, -0.7), energyBlock, ENERGY_SOURCE);
    }

}
