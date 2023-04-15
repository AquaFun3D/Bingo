package de.aquafun3d.bingo.utils.inventories;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public interface ISettingsInventory {
    Inventory getInventory();
    ItemStack getItem();
    boolean isConfirmed();
}
