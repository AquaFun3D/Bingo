package de.aquafun3d.bingo.utils.inventories

import net.kyori.adventure.text.Component
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

interface ITeamselectInventory {
    fun updateInventory(player: Player, name: String, itemName: Component)
    fun getItem(): ItemStack
    fun getInventory(): Inventory
}
