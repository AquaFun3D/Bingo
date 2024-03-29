package de.aquafun3d.bingo.utils.helpers

import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.advancement.Advancement
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

interface IHelpers {
    fun send(player: Player, message: Component)
    fun atAll(message: Component)
    fun isBingoRunning(): Boolean
    fun changeBingoRunning(bool: Boolean)
    fun newItem(mat: Material, name: Component): ItemStack
    fun newItem(mat: Material, amount: Int, name: Component): ItemStack
    fun getPrefix(): Component
    fun parse(name: String): Advancement
}
