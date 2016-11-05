package org.devathon.contest2016;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class DevathonPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("blocky").setExecutor((sender, command, label, args) -> {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                player.addPotionEffect(PotionEffectType.INVISIBILITY.createEffect(Integer.MAX_VALUE, 1));
                spawnTerminator(player);
            }
            return true;
        });
        getServer().getPluginManager().registerEvents(new GunListener(), this);
        // put your enable code here
    }

    @Override
    public void onDisable() {
        // put your disable code here
    }

    public void spawnTerminator(Player player) {
        final Location loc = player.getLocation();

        final ItemStack blackWool = new ItemStack(Material.WOOL, 1, (short) 0xF);
        final ItemStack ironBlock = new ItemStack(Material.IRON_BLOCK);
        final ItemStack skinBlock = new ItemStack(Material.HARD_CLAY);
        final ItemStack energyBlock = new ItemStack(Material.DIAMOND_BLOCK);
        final ItemStack redWool = new ItemStack(Material.WOOL, 1, (short) 0xE);

        // left leg
        OffsetArmorStand.spawn(loc, player::getLocation, new Vector(1, -1, 0), blackWool);
        OffsetArmorStand.spawn(loc, player::getLocation, new Vector(1, -1, 1), blackWool);
        OffsetArmorStand.spawn(loc, player::getLocation, new Vector(1, 0, 0), blackWool);

        // right leg
        OffsetArmorStand.spawn(loc, player::getLocation, new Vector(-1, -1, 0), blackWool);
        OffsetArmorStand.spawn(loc, player::getLocation, new Vector(-1, -1, 1), blackWool);
        OffsetArmorStand.spawn(loc, player::getLocation, new Vector(-1, 0, 0), blackWool);

        // body layer 1
        OffsetArmorStand.spawn(loc, player::getLocation, new Vector(1, 1, 0), ironBlock);
        OffsetArmorStand.spawn(loc, player::getLocation, new Vector(0, 1, 0), ironBlock);
        OffsetArmorStand.spawn(loc, player::getLocation, new Vector(-1, 1, 0), ironBlock);
        OffsetArmorStand.spawn(loc, player::getLocation, new Vector(0, 1, 1), ironBlock);

        // body layer 2
        OffsetArmorStand.spawn(loc, player::getLocation, new Vector(1, 2, 0), ironBlock);
        OffsetArmorStand.spawn(loc, player::getLocation, new Vector(0, 2, 0), ironBlock);
        OffsetArmorStand.spawn(loc, player::getLocation, new Vector(-1, 2, 0), ironBlock);
        OffsetArmorStand.spawn(loc, player::getLocation, new Vector(1, 2, 1), ironBlock);
        OffsetArmorStand.spawn(loc, player::getLocation, new Vector(0, 2, 1), ironBlock);
        OffsetArmorStand.spawn(loc, player::getLocation, new Vector(-1, 2, 1), ironBlock);

        // body layer 3
        OffsetArmorStand.spawn(loc, player::getLocation, new Vector(1, 3, 0), ironBlock);
        OffsetArmorStand.spawn(loc, player::getLocation, new Vector(0, 3, 0), ironBlock);
        OffsetArmorStand.spawn(loc, player::getLocation, new Vector(-1, 3, 0), ironBlock);
        OffsetArmorStand.spawn(loc, player::getLocation, new Vector(1, 3, 1), ironBlock);
        OffsetArmorStand.spawn(loc, player::getLocation, new Vector(0, 3, 1), ironBlock);
        OffsetArmorStand.spawn(loc, player::getLocation, new Vector(-1, 3, 1), ironBlock);

        // body layer 4
        OffsetArmorStand.spawn(loc, player::getLocation, new Vector(1, 4, 0), ironBlock);
        OffsetArmorStand.spawn(loc, player::getLocation, new Vector(0, 4, 0), ironBlock);
        OffsetArmorStand.spawn(loc, player::getLocation, new Vector(-1, 4, 0), ironBlock);
        OffsetArmorStand.spawn(loc, player::getLocation, new Vector(0, 4, 1), ironBlock);

        // head layer 1 TODO
        OffsetArmorStand.spawn(loc, player::getLocation, new Vector(1, 5, 0), skinBlock);
        OffsetArmorStand.spawn(loc, player::getLocation, new Vector(0, 5, 0), skinBlock);
        OffsetArmorStand.spawn(loc, player::getLocation, new Vector(-1, 5, 0), skinBlock);
        OffsetArmorStand.spawn(loc, player::getLocation, new Vector(1, 5, 1), skinBlock);
        OffsetArmorStand.spawn(loc, player::getLocation, new Vector(0, 5, 1), skinBlock);
        OffsetArmorStand.spawn(loc, player::getLocation, new Vector(-1, 5, 1), skinBlock);

        // head layer 1 TODO
        OffsetArmorStand.spawn(loc, player::getLocation, new Vector(1, 6, 0), skinBlock);
        OffsetArmorStand.spawn(loc, player::getLocation, new Vector(0, 6, 0), skinBlock);
        OffsetArmorStand.spawn(loc, player::getLocation, new Vector(-1, 6, 0), skinBlock);
        OffsetArmorStand.spawn(loc, player::getLocation, new Vector(1, 6, 1), skinBlock);
        OffsetArmorStand.spawn(loc, player::getLocation, new Vector(0, 6, 1), skinBlock);
        OffsetArmorStand.spawn(loc, player::getLocation, new Vector(-1, 6, 1), skinBlock);

        // hat
        OffsetArmorStand.spawn(loc, player::getLocation, new Vector(1, 7, 0), redWool);
        OffsetArmorStand.spawn(loc, player::getLocation, new Vector(0, 7, 0), redWool);
        OffsetArmorStand.spawn(loc, player::getLocation, new Vector(-1, 7, 0), redWool);
        OffsetArmorStand.spawn(loc, player::getLocation, new Vector(1, 7, 1), redWool);
        OffsetArmorStand.spawn(loc, player::getLocation, new Vector(0, 7, 1), redWool);
        OffsetArmorStand.spawn(loc, player::getLocation, new Vector(-1, 7, 1), redWool);

        // energy source
        OffsetArmorStand.spawn(loc, player::getLocation, new Vector(0, 1.5, -0.7), energyBlock);
        OffsetArmorStand.spawn(loc, player::getLocation, new Vector(0, 2.3, -0.7), energyBlock);
        OffsetArmorStand.spawn(loc, player::getLocation, new Vector(0, 3.1, -0.7), energyBlock);
        OffsetArmorStand.spawn(loc, player::getLocation, new Vector(0, 3.8, -0.7), energyBlock);

        // left arm
        OffsetArmorStand.spawn(loc, player::getLocation, new Vector(2, 4, 0), ironBlock);
        OffsetArmorStand.spawn(loc, player::getLocation, new Vector(2.5, 4, 0), ironBlock);
        OffsetArmorStand.spawn(loc, player::getLocation, new Vector(2.5, 4, 1), ironBlock);
        OffsetArmorStand.spawn(loc, player::getLocation, new Vector(2.5, 4, 2), ironBlock);

        // right arm
        OffsetArmorStand.spawn(loc, player::getLocation, new Vector(-2, 4, 0), ironBlock);
        OffsetArmorStand.spawn(loc, player::getLocation, new Vector(-2.5, 4, 0), ironBlock);
        OffsetArmorStand.spawn(loc, player::getLocation, new Vector(-2.5, 4, 1), ironBlock);
        OffsetArmorStand.spawn(loc, player::getLocation, new Vector(-2.5, 4, 2), ironBlock);
    }

}

