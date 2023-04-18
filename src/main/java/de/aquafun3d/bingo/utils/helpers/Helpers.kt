package de.aquafun3d.bingo.utils.helpers

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class Helpers : IHelpers {
    private var _isBingoRunning = false
    private var _isConfirmed = false
    private val _prefix = Component.text("[",NamedTextColor.DARK_GRAY).append(Component.text("Bingo",NamedTextColor.DARK_AQUA,)).append(Component.text("]",NamedTextColor.DARK_GRAY))

    override fun send(player: Player, message: Component) {
        player.sendMessage(_prefix.append(message))
    }

    override fun atAll(message: Component,) {
        Bukkit.broadcast(_prefix.append(message))
    }

    override fun changeBingoRunning(bool: Boolean) {
        _isBingoRunning = bool
    }

    override fun changeConfirm(bool: Boolean) {
        _isConfirmed = bool
    }

    override fun isBingoRunning(): Boolean {
        return _isBingoRunning
    }

    override fun isConfirmed(): Boolean {
        return _isConfirmed
    }

    override fun getPrefix(): Component{
        return _prefix
    }

    override fun newItem(mat: Material, name: Component): ItemStack {
        val item = ItemStack(mat)
        val meta = item.itemMeta
        meta.displayName(name)
        item.setItemMeta(meta)
        return item
    }
}
