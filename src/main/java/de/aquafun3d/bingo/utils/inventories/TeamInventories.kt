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

    override fun removeItem(player: Player, item: ItemStack){
        if(_settings.getMode() == Mode.LOCKOUT){
            removeForAll(player, item.type)
        }else{
            _inventories[_teams.getPlayerTeam(player)]!!.removeItem(item)
        }
    }

    override fun removeItem(player: Player, item: Material){
        if(_settings.getMode() == Mode.LOCKOUT){
            removeForAll(player, item)
        }else{
            _inventories[_teams.getPlayerTeam(player)]!!.removeItem(ItemStack(item))
        }
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
        if(_bingoTasks.getList().isEmpty()) _bingoTasks.fillList()
        val task = _bingoTasks.getList()[index - 1]
        val newTask = _bingoTasks.getNewTask(task.getTaskType())
        _bingoTasks.replace(index - 1, newTask)
        for(inv in _inventories.values){
            inv.setItem(index - 1, newTask.getItemStack())
        }
        _helper.atAll(task.getItemStack().displayName().color(NamedTextColor.LIGHT_PURPLE).append(Component.text(" replaced with ", NamedTextColor.GREEN)).append(newTask.getItemStack().displayName().color(NamedTextColor.LIGHT_PURPLE)))
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
            if(item == null) continue
            if(_settings.getMode() == Mode.LOCKOUT){
                if(_teams.getPlayerTeamName(player) == "red"){
                    if(item.itemMeta.displayName() == Component.text("Team #1", NamedTextColor.DARK_RED)) out++
                }
                if(_teams.getPlayerTeamName(player) == "blue"){
                    if(item.itemMeta.displayName() == Component.text("Team #2", NamedTextColor.BLUE)) out++
                }
            }else{
                out++
            }
        }
        return out
    }
}
