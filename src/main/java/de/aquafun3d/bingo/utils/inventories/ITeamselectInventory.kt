package de.aquafun3d.bingo.utils.inventories

import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

interface ITeamselectInventory {
    fun updateInventory(name: String, player: Player)
    val inventory: Inventory
    val item: ItemStack
}
