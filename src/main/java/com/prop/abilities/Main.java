package com.prop.abilities;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("Majithiyan Abilities Plugin Enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Majithiyan Abilities Plugin Disabled!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("ability")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;

                // Fireball shoot
                Fireball fireball = player.launchProjectile(Fireball.class);
                fireball.setYield(2F);

                // Chakra particle effect
                Bukkit.getScheduler().runTaskTimer(this, task -> {
                    if (!player.isOnline()) {
                        task.cancel();
                        return;
                    }
                    player.getWorld().spawnParticle(Particle.END_ROD, player.getLocation().add(0, 1, 0), 20,
                            0.5, 1, 0.5, 0.1);
                }, 0L, 10L);

                // Speed & Jump boost
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, 1));
                player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 200, 1));

                // Lightning strike
                Location target = player.getTargetBlockExact(50).getLocation();
                if (target != null) {
                    player.getWorld().strikeLightning(target);
                }

                player.sendMessage("§6🔥 Majithiyan 9KK Ability Activated! 🔥");
            }
            return true;
        }
        return false;
    }
                  }
