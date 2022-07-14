package page.pinniped.mjolnir;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class Mjolnir extends JavaPlugin {

    @Override
    public void onEnable() {

        Bukkit.addRecipe(recipe());

        getServer().getPluginManager().registerEvents(new Use(), this);

        this.getCommand("Mjolnir").setExecutor(new Get());

    }

    @Override
    public void onDisable() {

    }

    public ShapedRecipe recipe() {

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

        NamespacedKey key = new NamespacedKey(this, "Mjolnir");

        ShapedRecipe recipe = new ShapedRecipe(key, item);

        recipe.shape("APA", "PTP", "APA");

        recipe.setIngredient('A', Material.GOLDEN_AXE);
        recipe.setIngredient('P', Material.BLAZE_POWDER);
        recipe.setIngredient('T', Material.TOTEM_OF_UNDYING);

        return recipe;

    }

}
