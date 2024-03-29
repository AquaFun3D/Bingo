package de.aquafun3d.bingo.utils.inventories

import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

interface ITeamselectInventory {
    fun newInventory()
    fun updateInventory(player: Player)
    fun getItem(): ItemStack
    fun getInventory(): Inventory
}
