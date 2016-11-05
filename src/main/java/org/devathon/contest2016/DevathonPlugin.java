package org.devathon.contest2016;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class DevathonPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("blocky").setExecutor((sender, command, label, args) -> {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                for (int i = 0; i < 7; i++) {
                    OffsetArmorStand.spawn(player.getLocation(), player::getLocation, new Vector(i - 3, 0.5, 0));
                }
            }
            return true;
        });
        // put your enable code here
    }

    @Override
    public void onDisable() {
        // put your disable code here
    }


}

