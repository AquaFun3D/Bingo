package de.aquafun3d.bingo.utils.inventories

import de.aquafun3d.bingo.utils.helpers.IHelpers
import de.aquafun3d.bingo.utils.teams.ITeams
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.bukkit.scoreboard.Team

class TeamInventories(private val _teams: ITeams, private val _helper: IHelpers) : ITeamInventories {
    private val _inventories = mutableMapOf<Team, Inventory>()
    private val _item: ItemStack

    init {
        _item = _helper.newItem(Material.BUNDLE, Component.text("Bingo",NamedTextColor.DARK_AQUA))
    }

    override fun fillInventories() {
        for (t in _teams.getTeams().values) {
            val inv = Bukkit.createInventory(null, 9 * 1, Component.text("Bingo",NamedTextColor.DARK_AQUA)) //TODO * Quatity
            //TODO generate Bingo Items
            //inv.setContents(null); TODO
            if (!_inventories.containsKey(t)) {
                _inventories[t] = inv
            }
        }
    }

    override fun removeItem(player: Player, item: ItemStack) {
        _inventories[_teams.getPlayerTeam(player)]!!.removeItem(item)
    }

    override fun getIventorybyPlayer(player: Player): Inventory {
        return _inventories[_teams.getPlayerTeam(player)]!!
    }

    override fun getInventorybyTeam(team: Team): Inventory {
        return _inventories[team]!!
    }

    override fun getItem(): ItemStack{
        return _item
    }
}
