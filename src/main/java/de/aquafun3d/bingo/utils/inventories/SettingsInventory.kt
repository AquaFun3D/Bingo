package de.aquafun3d.bingo.utils.inventories

import de.aquafun3d.bingo.utils.helpers.IHelpers
import de.aquafun3d.bingo.utils.helpers.ISettings
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

class SettingsInventory(private val _helper: IHelpers, private val _settings: ISettings) : ISettingsInventory {
    private val _inventory: Inventory = Bukkit.createInventory(null, 27, Component.text("Bingo Settings",NamedTextColor.DARK_PURPLE))
    private val _item: ItemStack = _helper.newItem(Material.NETHER_STAR, Component.text("Settings", NamedTextColor.DARK_AQUA))

    init {
        newInventory()
    }

    private fun newInventory() {
        val empty = _helper.newItem(Material.BLACK_STAINED_GLASS_PANE, Component.text(" "))
        val confirm = _helper.newItem(Material.LIME_DYE, Component.text("Confirm", NamedTextColor.GREEN))
        val difficulty = _helper.newItem(Material.TOTEM_OF_UNDYING, Component.text("Confirm", NamedTextColor.GREEN))
        val nether = _helper.newItem(Material.NETHERRACK, Component.text("Confirm", NamedTextColor.GREEN))
        val end = _helper.newItem(Material.END_STONE, Component.text("Confirm", NamedTextColor.GREEN))
        val silktouch = _helper.newItem(Material.DIAMOND_PICKAXE, Component.text("Confirm", NamedTextColor.GREEN))
        val quantity = _helper.newItem(Material.FIRE_CHARGE, Component.text("Confirm", NamedTextColor.GREEN))
        val teamsize = _helper.newItem(Material.ARMOR_STAND, Component.text("Confirm", NamedTextColor.GREEN))

        for (j in 0..26) {
            _inventory.setItem(j, empty)
        }

        _inventory.setItem(0, confirm)
        _inventory.setItem(1, difficulty)
        _inventory.setItem(2, nether)
        _inventory.setItem(3, end)
        _inventory.setItem(4, silktouch)
        _inventory.setItem(5, quantity)
        _inventory.setItem(6, teamsize)
    }

    override fun getInventory(): Inventory{
        return _inventory
    }

    override fun getItem(): ItemStack{
        return _item
    }
}
