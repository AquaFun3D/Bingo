package de.aquafun3d.bingo.utils.inventories

import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.bukkit.scoreboard.Team

interface ITeamInventories {
    fun fillInventories()
    fun removeItem(player: Player, item: ItemStack)
    fun removeForAll(player: Player, item: Material)
    fun removeItem(player: Player, item: Material)
    fun removeItemIndex(index: Int, player: Player)
    fun getInventorybyPlayer(player: Player): Inventory
    fun getInventorybyTeam(team: Team): Inventory
    fun getItem(): ItemStack
    fun itemCount(player: Player): Int
}
