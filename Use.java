package page.pinniped.mjolnir;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Use implements Listener {

    @EventHandler
    public void onClick(PlayerInteractEvent event) {

        if (event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.GOLDEN_AXE)) {

            if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasLore()) {

                Player player = (Player) event.getPlayer();

                if (player.hasPermission("mjolnir.use")) {

                    if (event.getAction() == Action.LEFT_CLICK_AIR) {

                        Location playerLocation = player.getLocation();

                        for (Entity entity : player.getWorld().getChunkAt(playerLocation).getEntities()) {

                            if (entity instanceof LivingEntity && !(entity instanceof Player)) {

                                Location entityLocation = entity.getLocation();

                                player.getWorld().strikeLightningEffect(entityLocation);

                                ((LivingEntity) entity).damage(40);

                            }

                        }

                    }

                    if (event.getAction() == Action.RIGHT_CLICK_AIR) {

                        player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 600, 10));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 600, 10));

                    }

                    return;

                }

                if (!player.hasPermission("mjolnir.have")) {

                    player.sendMessage(ChatColor.DARK_PURPLE + "ERROR: " + ChatColor.DARK_RED + "Insufficient permissions!");

                    event.getItem().setAmount(0);

                    event.setCancelled(true);

                }

                player.sendMessage(ChatColor.DARK_PURPLE + "ERROR: " + ChatColor.DARK_RED + "Insufficient permissions!");

            }

        }

    }

}