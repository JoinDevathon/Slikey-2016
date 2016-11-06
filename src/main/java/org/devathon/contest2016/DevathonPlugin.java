package org.devathon.contest2016;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.Map;

public class DevathonPlugin extends JavaPlugin {

    private Map<Player, Terminator> terminators;

    @Override
    public void onEnable() {
        terminators = new HashMap<>();
        getCommand("blocky").setExecutor((sender, command, label, args) -> {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (!terminators.containsKey(player)) {
                    player.addPotionEffect(PotionEffectType.INVISIBILITY.createEffect(Integer.MAX_VALUE, 1));
                    Terminator terminator = new Terminator(this, player);
                    terminator.spawn();
                    terminators.put(player, terminator);
                }
            }
            return true;
        });
        getServer().getPluginManager().registerEvents(new GunListener(this), this);
        // put your enable code here
    }

    @Override
    public void onDisable() {
        for (Terminator terminator : terminators.values()) {
            terminator.despawn();
        }
        terminators.clear();
    }

    public Terminator getTerminator(Player player) {
        return terminators.get(player);
    }

}

