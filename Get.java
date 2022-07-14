package page.pinniped.mjolnir;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Get implements CommandExecutor {

    public ItemStack getItem() {

        ItemStack item = new ItemStack(Material.GOLDEN_AXE);

        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Mjölnir");

        meta.addEnchant(Enchantment.DURABILITY, 3, true);
        meta.addEnchant(Enchantment.MENDING, 1, true);
        meta.addEnchant(Enchantment.DAMAGE_ALL, 5, true);
        meta.addEnchant(Enchantment.FIRE_ASPECT, 2, true);

        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        List<String> lore = new ArrayList<String>();

        lore.add(ChatColor.LIGHT_PURPLE + "" + ChatColor.ITALIC + "    ");
        lore.add(ChatColor.LIGHT_PURPLE + "" + ChatColor.ITALIC + "  Mjölnir bestows the wielder  ");
        lore.add(ChatColor.LIGHT_PURPLE + "" + ChatColor.ITALIC + "  with the gift of flight and the  ");
        lore.add(ChatColor.LIGHT_PURPLE + "" + ChatColor.ITALIC + "  ability to control lightning.  ");
        lore.add(ChatColor.LIGHT_PURPLE + "" + ChatColor.ITALIC + "    ");
        lore.add(ChatColor.GRAY + " (Left Click) " + ChatColor.DARK_AQUA + "Lightning Strike");
        lore.add(ChatColor.GRAY + " (Right Click) " + ChatColor.DARK_AQUA + "Burst of Flight");

        meta.setLore(lore);

        item.setItemMeta(meta);

        return item;

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String string, String[] args) {

        if (string.equalsIgnoreCase("mjolnir")) {

            if (sender instanceof Player) {

                Player player = (Player) sender;

                if (player.hasPermission("mjolnir.mjolnir")) {

                    if (args.length == 0) {

                        if (player.getInventory().firstEmpty() == -1) {

                            Location location = player.getLocation();

                            World world = player.getWorld();

                            world.dropItemNaturally(location, getItem());

                            return true;

                        }

                        player.getInventory().addItem(getItem());

                        return true;

                    }

                    try {

                        Player target = Bukkit.getPlayerExact(args[0]);

                        if (target.getInventory().firstEmpty() == -1) {

                            Location location = target.getLocation();

                            World world = target.getWorld();

                            world.dropItemNaturally(location, getItem());

                            player.sendMessage("Gave 1 [Mjölnir] to " + target.getName());

                            return true;

                        }

                        target.getInventory().addItem(getItem());

                        player.sendMessage("Gave 1 [Mjölnir] to " + target.getName());

                        return true;

                    } catch (Exception exception) {

                        player.sendMessage(ChatColor.DARK_PURPLE + "ERROR: " + ChatColor.DARK_RED + "Unknown player.");

                        return true;

                    }

                }

                player.sendMessage(ChatColor.DARK_PURPLE + "ERROR: " + ChatColor.DARK_RED + "Insufficient permissions!");

                return true;

            }

            if (args.length == 0) {

                sender.sendMessage(ChatColor.DARK_PURPLE + "ERROR: " + ChatColor.DARK_RED + "Specify a player.");

                return true;

            }

            try {

                Player target = Bukkit.getPlayerExact(args[0]);

                if (target.getInventory().firstEmpty() == -1) {

                    Location location = target.getLocation();

                    World world = target.getWorld();

                    world.dropItemNaturally(location, getItem());

                    sender.sendMessage("Gave 1 [Mjölnir] to " + target.getName());

                    return true;

                }

                target.getInventory().addItem(getItem());

                sender.sendMessage("Gave 1 [Mjölnir] to " + target.getName());

                return true;

            } catch (Exception exception) {

                sender.sendMessage(ChatColor.DARK_PURPLE + "ERROR: " + ChatColor.DARK_RED + "Unknown player.");

                return true;

            }

        }

        return false;

    }

}
