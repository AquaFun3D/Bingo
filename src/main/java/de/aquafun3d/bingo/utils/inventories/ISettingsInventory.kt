package de.aquafun3d.bingo.utils.inventories

import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

interface ISettingsInventory {
    fun newInventory()
    fun getInventory(): Inventory
    fun getItem(): ItemStack
}