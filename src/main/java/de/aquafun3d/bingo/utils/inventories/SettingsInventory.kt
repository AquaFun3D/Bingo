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
    private val _inventory: Inventory = Bukkit.createInventory(null, 27, Component.text("Settings", NamedTextColor.DARK_PURPLE))
    private val _item: ItemStack = _helper.newItem(Material.NETHER_STAR, Component.text("Settings", NamedTextColor.DARK_AQUA))

    init {
        newInventory()
    }

    override fun newInventory() {
        _settings.setConfirmed(false)
        val empty = _helper.newItem(Material.BLACK_STAINED_GLASS_PANE, Component.text(" "))
        val confirm = _helper.newItem(Material.LIME_DYE, Component.text("Confirm", NamedTextColor.GREEN))
        val difficulty = _helper.newItem(Material.TOTEM_OF_UNDYING, Component.text("Difficulty ", NamedTextColor.GOLD).append(Component.text(_settings.getDifficulty().toString()).color(NamedTextColor.AQUA)))
        val nether: ItemStack = if(_settings.getNether()){
            _helper.newItem(Material.NETHERRACK, Component.text("Nether ON", NamedTextColor.GREEN))
        }else{
            _helper.newItem(Material.NETHERRACK, Component.text("Nether OFF", NamedTextColor.RED))
        }
        val end:ItemStack = if(_settings.getEnd()){
            _helper.newItem(Material.END_STONE, Component.text("End ON", NamedTextColor.GREEN))
        }else{
            _helper.newItem(Material.END_STONE, Component.text("End OFF", NamedTextColor.RED))
        }
        val silktouch:ItemStack = if(_settings.getSilktouch()){
            _helper.newItem(Material.GOLDEN_PICKAXE, Component.text("Silktouch ON", NamedTextColor.GREEN))
        }else{
            _helper.newItem(Material.GOLDEN_PICKAXE, Component.text("Silktouch OFF", NamedTextColor.RED))
        }
        val quantity = _helper.newItem(Material.FIRE_CHARGE, _settings.getQuantity() * 9, Component.text("Items", NamedTextColor.AQUA))
        val teamsize = _helper.newItem(Material.ARMOR_STAND, _settings.getTeamsize(), Component.text("Teamsize", NamedTextColor.DARK_AQUA))

        for (j in 0..26) {
            _inventory.setItem(j, empty)
        }

        _inventory.setItem(26, confirm)
        _inventory.setItem(11, difficulty)
        _inventory.setItem(13, nether)
        _inventory.setItem(14, end)
        _inventory.setItem(15, silktouch)
        _inventory.setItem(12, quantity)
        _inventory.setItem(10, teamsize)
    }

    override fun getInventory(): Inventory {
        return _inventory
    }

    override fun getItem(): ItemStack{
        return _item
    }
}
