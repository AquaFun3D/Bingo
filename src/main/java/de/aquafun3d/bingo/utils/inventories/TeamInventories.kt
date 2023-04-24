package de.aquafun3d.bingo.utils.inventories

import de.aquafun3d.bingo.utils.helpers.IHelpers
import de.aquafun3d.bingo.utils.helpers.ISettings
import de.aquafun3d.bingo.utils.tasks.IBingoTaskManager
import de.aquafun3d.bingo.utils.teams.ITeams
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.bukkit.scoreboard.Team

class TeamInventories(private val _teams: ITeams, private val _helper: IHelpers, private val _bingoTasks: IBingoTaskManager, private val _settings: ISettings) : ITeamInventories {
    private val _inventories = mutableMapOf<Team, Inventory>()
    private val _item: ItemStack

    init {
        _item = _helper.newItem(Material.BUNDLE, Component.text("Bingo", NamedTextColor.DARK_AQUA))
    }

    override fun fillInventories() {
        _bingoTasks.fillList()
        for (t in _teams.getTeams().values) {
            val inv = Bukkit.createInventory(null, 9 * _settings.getQuantity(), Component.text("Bingo", NamedTextColor.DARK_PURPLE))
            var i: Int = 0
            for(item in _bingoTasks.getList()){
                inv.setItem(i, item.getItemStack())
                i++
            }
            if(!_inventories.containsKey(t)) {
                _inventories[t] = inv
            }
        }
    }

    override fun removeItem(player: Player, item: ItemStack) {
        _inventories[_teams.getPlayerTeam(player)]!!.removeItem(item)
    }

    override fun removeItem(player: Player, item: Material) {
        _inventories[_teams.getPlayerTeam(player)]!!.removeItem(ItemStack(item))
    }

    override fun getInventorybyPlayer(player: Player): Inventory {
        return _inventories[_teams.getPlayerTeam(player)]!!
    }

    override fun getInventorybyTeam(team: Team): Inventory {
        return _inventories[team]!!
    }

    override fun getItem(): ItemStack{
        return _item
    }

    override fun itemCount(player: Player): Int{
        var out = 0
        for(item in getInventorybyPlayer(player)){
            if(item != null){
                out++
            }
        }
        return out
    }
}
