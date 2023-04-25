package de.aquafun3d.bingo.listeners

import de.aquafun3d.bingo.utils.helpers.ISettings
import de.aquafun3d.bingo.utils.inventories.ISettingsInventory
import de.aquafun3d.bingo.utils.inventories.ITeamselectInventory
import de.aquafun3d.bingo.utils.tasks.TaskDifficulty
import de.aquafun3d.bingo.utils.teams.ITeams
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent

class InventoryListener(private val _teams: ITeams, private val _teamInv: ITeamselectInventory, private val _settings: ISettings, private val _settingsInv: ISettingsInventory, private val _teamselect: ITeamselectInventory) : Listener {
    @EventHandler
    fun onInvClick(e: InventoryClickEvent) {
        val player = e.whoClicked as Player
        val item = e.currentItem
        if (e.view.title() == Component.text("Bingo", NamedTextColor.DARK_PURPLE)) {
            e.isCancelled = true
        }
        if (e.view.title() == Component.text("Bingo Settings", NamedTextColor.DARK_PURPLE)) {
            e.isCancelled = true
        }
        if (e.view.title() == Component.text("Teamselect", NamedTextColor.DARK_PURPLE)) {
            e.isCancelled = true
        }
        if (item == null) {
            return
        }
        if (item.itemMeta == null) {
            return
        }

        val itemName = item.itemMeta.displayName()

        //SETTINGS
        if (itemName == Component.text("Confirm", NamedTextColor.GREEN)) {
            if(e.isLeftClick){
                _settings.setConfirmed(true)
            }
            for(p in Bukkit.getOnlinePlayers()){
                p.inventory.setItem(4, _teamselect.getItem())
            }
            player.closeInventory()
        }
        if (itemName == Component.text("Difficulty ", NamedTextColor.GOLD).append(Component.text(_settings.getDifficulty().toString()).color(NamedTextColor.AQUA))) {
            if(e.isLeftClick){
                when(_settings.getDifficulty()){
                    TaskDifficulty.EASY ->{
                        _settings.setDifficulty(TaskDifficulty.NORMAL)
                    }
                    TaskDifficulty.NORMAL ->{
                        _settings.setDifficulty(TaskDifficulty.HARD)
                    }
                    TaskDifficulty.HARD ->{
                        _settings.setDifficulty(TaskDifficulty.EXTREME)

                    }
                    TaskDifficulty.EXTREME ->{
                        _settings.setDifficulty(TaskDifficulty.EASY)

                    }
                }
            }else if(e.isRightClick){
                when(_settings.getDifficulty()){
                    TaskDifficulty.EASY ->{
                        _settings.setDifficulty(TaskDifficulty.EXTREME)
                    }
                    TaskDifficulty.NORMAL ->{
                        _settings.setDifficulty(TaskDifficulty.EASY)
                    }
                    TaskDifficulty.HARD ->{
                        _settings.setDifficulty(TaskDifficulty.NORMAL)

                    }
                    TaskDifficulty.EXTREME ->{
                        _settings.setDifficulty(TaskDifficulty.HARD)

                    }
                }
            }
            _settingsInv.newInventory()
            player.openInventory(_settingsInv.getInventory())
        }
        if (itemName == Component.text("Nether ON", NamedTextColor.GREEN)) {
            if(e.isLeftClick){
                _settings.setNether(false)
            }
            _settingsInv.newInventory()
            player.openInventory(_settingsInv.getInventory())
        }
        if (itemName == Component.text("Nether OFF", NamedTextColor.RED)) {
            if(e.isLeftClick){
                _settings.setNether(true)
            }
            _settingsInv.newInventory()
            player.openInventory(_settingsInv.getInventory())
        }
        if (itemName == Component.text("End ON", NamedTextColor.GREEN)) {
            if(e.isLeftClick){
                _settings.setEnd(false)
            }
            _settingsInv.newInventory()
            player.openInventory(_settingsInv.getInventory())
        }
        if (itemName == Component.text("End OFF", NamedTextColor.RED)) {
            if(e.isLeftClick){
                _settings.setEnd(true)
            }
            _settingsInv.newInventory()
            player.openInventory(_settingsInv.getInventory())
        }
        if (itemName == Component.text("Silktouch ON", NamedTextColor.GREEN)) {
            if(e.isLeftClick){
                _settings.setSilktouch(false)
            }
            _settingsInv.newInventory()
            player.openInventory(_settingsInv.getInventory())
        }
        if (itemName == Component.text("Silktouch OFF", NamedTextColor.RED)) {
            if(e.isLeftClick){
                _settings.setSilktouch(true)
            }
            _settingsInv.newInventory()
            player.openInventory(_settingsInv.getInventory())
        }
        if (itemName == Component.text("Items", NamedTextColor.AQUA)) {
            if(e.isLeftClick){
                _settings.setQuantity(_settings.getQuantity()+1)
            }
            _settingsInv.newInventory()
            player.openInventory(_settingsInv.getInventory())
        }
        if (itemName == Component.text("Teamsize", NamedTextColor.DARK_AQUA)) {
            if(e.isLeftClick){
                _settings.setTeamsize(_settings.getTeamsize()+1)
            }
            _settingsInv.newInventory()
            player.openInventory(_settingsInv.getInventory())
        }

        //TEAMSELCT
        if (itemName == Component.text("Team #1", NamedTextColor.WHITE)) {
            _teams.joinTeam(player, "white")
            _teamInv.updateInventory(player)
        }
        if (itemName == Component.text("Team #2", NamedTextColor.GOLD)) {
            _teams.joinTeam(player, "orange")
            _teamInv.updateInventory(player)
        }
        if (itemName == Component.text("Team #3", NamedTextColor.LIGHT_PURPLE)) {
            _teams.joinTeam(player, "magenta")
            _teamInv.updateInventory(player)
        }
        if (itemName == Component.text("Team #4", NamedTextColor.AQUA)) {
            _teams.joinTeam(player, "lightblue")
            _teamInv.updateInventory(player)
        }
        if (itemName == Component.text("Team #5", NamedTextColor.YELLOW)) {
            _teams.joinTeam(player, "yellow")
            _teamInv.updateInventory(player)
        }
        if (itemName == Component.text("Team #6", NamedTextColor.GREEN)) {
            _teams.joinTeam(player, "lime")
            _teamInv.updateInventory(player)
        }
        if (itemName == Component.text("Team #7", NamedTextColor.RED)) {
            _teams.joinTeam(player, "pink")
            _teamInv.updateInventory(player)
        }
        if (itemName == Component.text("Team #8", NamedTextColor.DARK_GRAY)) {
            _teams.joinTeam(player, "gray")
            _teamInv.updateInventory(player)
        }
        if (itemName == Component.text("Team #9", NamedTextColor.GRAY)) {
            _teams.joinTeam(player, "lightgray")
            _teamInv.updateInventory(player)
        }
        if (itemName == Component.text("Team #10", NamedTextColor.DARK_AQUA)) {
            _teams.joinTeam(player, "cyan")
            _teamInv.updateInventory(player)
        }
        if (itemName == Component.text("Team #11", NamedTextColor.DARK_PURPLE)) {
            _teams.joinTeam(player, "purple")
            _teamInv.updateInventory(player)
        }
        if (itemName == Component.text("Team #12", NamedTextColor.BLUE)) {
            _teams.joinTeam(player, "blue")
            _teamInv.updateInventory(player)
        }
        if (itemName == Component.text("Team #13", NamedTextColor.GOLD)) {
            _teams.joinTeam(player, "brown")
            _teamInv.updateInventory(player)
        }
        if (itemName == Component.text("Team #14", NamedTextColor.DARK_GREEN)) {
            _teams.joinTeam(player, "green")
            _teamInv.updateInventory(player)
        }
        if (itemName == Component.text("Team #15", NamedTextColor.DARK_RED)) {
            _teams.joinTeam(player, "red")
            _teamInv.updateInventory(player)
        }
        if (itemName == Component.text("Team #16", NamedTextColor.DARK_GRAY)) {
            _teams.joinTeam(player, "black")
            _teamInv.updateInventory(player)
        }
        if (itemName == Component.text("Spectator", NamedTextColor.GRAY)) {
            _teams.joinTeam(player, "spec")
            _teamInv.updateInventory(player)
        }
    }
}
