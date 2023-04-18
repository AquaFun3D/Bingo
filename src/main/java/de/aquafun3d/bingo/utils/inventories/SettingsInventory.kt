package de.aquafun3d.bingo.utils.inventories

import de.aquafun3d.bingo.utils.helpers.IHelpers
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

class SettingsInventory(private val _helper: IHelpers) : ISettingsInventory {
    private val _inventory: Inventory = Bukkit.createInventory(null, 27, Component.text("Bingo Settings",NamedTextColor.DARK_PURPLE))
    private val _item: ItemStack = _helper.newItem(Material.NETHER_STAR, Component.text("Settings", NamedTextColor.DARK_AQUA))
    private val _isConfirmed = false

    init {
        newInventory()
    }

    private fun newInventory() {
        //val empty = _helper.newItem(Material.BLACK_STAINED_GLASS_PANE, Component.text(" "))
        //TODO
    }

    override fun getInventory(): Inventory{
        return _inventory
    }

    override fun getItem(): ItemStack{
        return _item
    }

    override fun isConfirmed(): Boolean{
        return _isConfirmed
    }
}
