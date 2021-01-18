package com.tiebe.plugins.spawnersilktouch;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;

public class breakListener implements Listener {
    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent event) {
        Bukkit.getConsoleSender().sendMessage("BLOCK BROKEN");
        Player player = event.getPlayer();
        Block block = event.getBlock();
        Material blockType = block.getType();
        Location loc = block.getLocation();
        @SuppressWarnings("deprecation")
        byte blockData = block.getData();

        if (event.getBlock().getType().equals(Material.SPAWNER)) {
            Bukkit.getConsoleSender().sendMessage("SPAWNER");
            if (event.getPlayer().getInventory().getItemInMainHand().containsEnchantment(Enchantment.SILK_TOUCH)) {
                if (event.getPlayer().getInventory().firstEmpty() != -1) {
                    CreatureSpawner cs = (CreatureSpawner) block.getState();
                    ItemStack spawner = new ItemStack(blockType, 1, blockData);
                    BlockStateMeta blockMeta = (BlockStateMeta) spawner.getItemMeta();
                    blockMeta.setBlockState(cs);
                    spawner.setItemMeta(blockMeta);
                    event.getPlayer().getInventory().addItem(new ItemStack(spawner));
                    Bukkit.getConsoleSender().sendMessage("SPAWNER WITH SILK TOUCH");
                    event.setExpToDrop(0);
                }
                else {
                    event.getPlayer().sendMessage("Please check if you have an empty inventory slot before mining this spawner.");
                    event.setCancelled(true);
                }
            }
        }
    }
}
