package de.aquafun3d.bingo.utils.inventories;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public interface IInventory{
    void updateInventory(String name, Player player);
    Inventory getInventory();
    ItemStack getItem();
}
