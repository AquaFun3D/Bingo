package de.aquafun3d.bingo.utils.inventories

import de.aquafun3d.bingo.utils.helpers.IHelpers
import de.aquafun3d.bingo.utils.helpers.ISettings
import de.aquafun3d.bingo.utils.helpers.Mode
import de.aquafun3d.bingo.utils.tasks.IBingoTaskManager
import de.aquafun3d.bingo.utils.tasks.IItemTaskManager
import de.aquafun3d.bingo.utils.teams.ITeams
import de.aquafun3d.bingo.utils.timer.ITimer
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.bukkit.scoreboard.Team

class TeamInventories(private val _teams: ITeams,private val _helper: IHelpers, private val _bingoTasks: IBingoTaskManager, private val _settings: ISettings,private  val _timer: ITimer, private val _itemManager: IItemTaskManager) : ITeamInventories {
    private val _inventories = mutableMapOf<Team, Inventory>()
    private val _item: ItemStack = _helper.newItem(Material.BUNDLE, Component.text("Bingo", NamedTextColor.DARK_AQUA))

    override fun fillInventories() {
        _bingoTasks.fillList()
        for (t in _teams.getTeams().values){
            val inv = Bukkit.createInventory(null, 9 * _settings.getQuantity(), Component.text("Bingo", NamedTextColor.DARK_PURPLE))
            for((i, item) in _bingoTasks.getList().withIndex()){
                inv.setItem(i, item.getItemStack())
            }
            if(!_inventories.containsKey(t)) {
                _inventories[t] = inv
            }
        }
    }

    override fun removeItem(player: Player, item: ItemStack) {
        _inventories[_teams.getPlayerTeam(player)]!!.removeItem(item)
    }

    override fun removeForAll(player: Player, item: Material){
        val replace = if(_teams.getPlayerTeamName(player) == "red"){
            _helper.newItem(Material.RED_STAINED_GLASS_PANE, Component.text("Team #1", NamedTextColor.DARK_RED))
        }else{
            _helper.newItem(Material.BLUE_STAINED_GLASS_PANE, Component.text("Team #2", NamedTextColor.BLUE))
        }
        for(inv in _inventories){
            inv.value.setItem(inv.value.first(item), replace)
        }
    }

    override fun removeItemIndex(index: Int, player: Player){
        val item = _inventories[_teams.getPlayerTeam(player)]!!.getItem(index - 1) ?: return
        val newItem = _itemManager.getItems(1)[0].getItemStack()
        for(inv in _inventories.values){
            inv.setItem(index - 1, newItem)
        }
        _helper.atAll(item.displayName().color(NamedTextColor.LIGHT_PURPLE).append(Component.text(" replaced with ", NamedTextColor.GREEN)).append(newItem.displayName().color(NamedTextColor.LIGHT_PURPLE)))
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
            if(_settings.getMode() == Mode.LOCKOUT){
                if(_teams.getPlayerTeamName(player) == "red"){
                    if(item.itemMeta.displayName() == Component.text("Team #1", NamedTextColor.DARK_RED)) out++
                }
                if(_teams.getPlayerTeamName(player) == "blue"){
                    if(item.itemMeta.displayName() == Component.text("Team #2", NamedTextColor.BLUE)) out++
                }
            }else{
                if(item != null){
                    out++
                }
            }
        }
        return out
    }
}
