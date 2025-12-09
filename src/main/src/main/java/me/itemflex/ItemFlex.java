package me.itemflex;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class ItemFlex extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("ItemFlex enabled!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return true;

        Player p = (Player) sender;
        ItemStack item = p.getInventory().getItemInMainHand();

        if (item.getType().isAir()) {
            p.sendMessage(ChatColor.RED + "You're not holding an item!");
            return true;
        }

        // Broadcast item info
        Bukkit.broadcastMessage(ChatColor.GOLD + p.getName() + " is showing off:");
        Bukkit.broadcastMessage(ChatColor.YELLOW + "Item: " + item.getType().toString());

        if (item.getItemMeta().hasDisplayName()) {
            Bukkit.broadcastMessage(ChatColor.AQUA + "Name: " + item.getItemMeta().getDisplayName());
        }

        if (item.getItemMeta().hasLore()) {
            Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "Lore: " + String.join(", ", item.getItemMeta().getLore()));
        }

        if (item.getItemMeta().hasEnchants()) {
            Bukkit.broadcastMessage(ChatColor.GREEN + "Enchants:");
            item.getItemMeta().getEnchants().forEach((ench, lvl) -> 
                Bukkit.broadcastMessage(ChatColor.GRAY + ench.getKey().getKey() + " " + lvl)
            );
        }

        return true;
    }
}
