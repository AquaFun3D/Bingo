package de.aquafun3d.bingo.utils.inventories

import de.aquafun3d.bingo.utils.helpers.IHelpers
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

class TeamselectInventory(private val _helper: IHelpers) : ITeamselectInventory {
    private val _inventory: Inventory = Bukkit.createInventory(null, 18, Component.text("Teamselect", NamedTextColor.DARK_PURPLE))
    private val _item: ItemStack = _helper.newItem(Material.EMERALD, Component.text("Select Team", NamedTextColor.DARK_AQUA))

    init {
        newInventory()
    }

    private fun newInventory() {
        val empty = _helper.newItem(Material.BLACK_STAINED_GLASS_PANE, Component.text(" "))
        val white = _helper.newItem(Material.WHITE_WOOL, Component.text("Team #1", NamedTextColor.WHITE))
        val orange = _helper.newItem(Material.ORANGE_WOOL, Component.text("Team #2", NamedTextColor.GOLD))
        val magenta = _helper.newItem(Material.MAGENTA_WOOL, Component.text("Team #3", NamedTextColor.LIGHT_PURPLE))
        val lightblue = _helper.newItem(Material.LIGHT_BLUE_WOOL, Component.text("Team #4", NamedTextColor.AQUA))
        val yellow = _helper.newItem(Material.YELLOW_WOOL, Component.text("Team #5", NamedTextColor.YELLOW))
        val lime = _helper.newItem(Material.LIME_WOOL, Component.text("Team #6", NamedTextColor.GREEN))
        val pink = _helper.newItem(Material.PINK_WOOL, Component.text("Team #7", NamedTextColor.RED))
        val gray = _helper.newItem(Material.GRAY_WOOL, Component.text("Team #8", NamedTextColor.DARK_GRAY))
        val lightgray = _helper.newItem(Material.LIGHT_GRAY_WOOL, Component.text("Team #9", NamedTextColor.GRAY))
        val cyan = _helper.newItem(Material.CYAN_WOOL, Component.text("Team #10", NamedTextColor.DARK_AQUA))
        val purple = _helper.newItem(Material.PURPLE_WOOL, Component.text("Team #11", NamedTextColor.DARK_PURPLE))
        val blue = _helper.newItem(Material.BLUE_WOOL, Component.text("Team #12", NamedTextColor.BLUE))
        val brown = _helper.newItem(Material.BROWN_WOOL, Component.text("Team #13", NamedTextColor.GOLD))
        val green = _helper.newItem(Material.GREEN_WOOL, Component.text("Team #14", NamedTextColor.DARK_GREEN))
        val red = _helper.newItem(Material.RED_WOOL, Component.text("Team #15", NamedTextColor.DARK_RED))
        val black = _helper.newItem(Material.BLACK_WOOL, Component.text("Team #16", NamedTextColor.DARK_GRAY))
        val spec = _helper.newItem(Material.GLASS, Component.text("Spectator", NamedTextColor.GRAY))
        _inventory.setItem(0, white)
        _inventory.setItem(1, orange)
        _inventory.setItem(2, magenta)
        _inventory.setItem(3, lightblue)
        _inventory.setItem(4, yellow)
        _inventory.setItem(5, lime)
        _inventory.setItem(6, pink)
        _inventory.setItem(7, gray)
        _inventory.setItem(8, lightgray)
        _inventory.setItem(9, cyan)
        _inventory.setItem(10, purple)
        _inventory.setItem(11, blue)
        _inventory.setItem(12, brown)
        _inventory.setItem(13, green)
        _inventory.setItem(14, red)
        _inventory.setItem(15, black)
        _inventory.setItem(16, spec)
        _inventory.setItem(17, empty)
    }

    override fun updateInventory(player: Player, name: String, itemName: Component) {
        for (item in _inventory) {
            val meta = item.itemMeta
            val list = ArrayList<Component>()
            if(item.itemMeta.displayName() == itemName){
                for (p in player.scoreboard.getTeam(name)!!.entries) {
                    list.add(Component.text(p!!))
                }
            }
            meta.lore(list)
            item.setItemMeta(meta)
        }
        for (p in Bukkit.getOnlinePlayers()) {
            if (p.openInventory.title() == Component.text("Teamselect", NamedTextColor.DARK_PURPLE)) {
                p.openInventory(_inventory)
            }
        }
    }

    override fun getItem(): ItemStack{
        return _item
    }

    override fun getIventory(): Inventory{
        return _inventory
    }
}
