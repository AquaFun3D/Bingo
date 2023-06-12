package de.aquafun3d.bingo.listeners

import de.aquafun3d.bingo.utils.helpers.IHelpers
import de.aquafun3d.bingo.utils.helpers.ISettings
import de.aquafun3d.bingo.utils.inventories.ISettingsInventory
import de.aquafun3d.bingo.utils.inventories.ITeamselectInventory
import de.aquafun3d.bingo.utils.tasks.TaskDifficulty
import de.aquafun3d.bingo.utils.teams.ITeams
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.Difficulty
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent

class InventoryListener(private val _teams: ITeams, private val _helper: IHelpers, private val _settings: ISettings, private val _settingsInv: ISettingsInventory, private val _teamSelectInv: ITeamselectInventory) : Listener {
    @EventHandler
    fun onInvClick(e: InventoryClickEvent) {
        val player = e.whoClicked as Player
        val item = e.currentItem
        if (e.view.title() == Component.text("Bingo", NamedTextColor.DARK_PURPLE)) {
            e.isCancelled = true
        }
        if (e.view.title() == Component.text("Settings", NamedTextColor.DARK_PURPLE)) {
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

        if(itemName == Component.text("Bingo", NamedTextColor.DARK_AQUA) && e.isRightClick) e.isCancelled = true

        //SETTINGS
        if (itemName == Component.text("Confirm", NamedTextColor.GREEN)) {
            if(e.isLeftClick){
                _settings.setConfirmed(true)
                _helper.send(player, Component.text("Confirmed", NamedTextColor.GREEN))
            }
            for(p in Bukkit.getOnlinePlayers()){
                p.inventory.clear()
                if(p.isOp) p.inventory.setItem(8, _settingsInv.getItem())
                p.inventory.setItem(4, _teamSelectInv.getItem())
            }
            player.closeInventory()
        }
        if (itemName == Component.text("Bingo Difficulty ", NamedTextColor.GOLD).append(Component.text(_settings.getBingoDifficulty().toString()).color(NamedTextColor.AQUA))) {
            if(e.isLeftClick){
                when(_settings.getBingoDifficulty()){
                    TaskDifficulty.EASY ->{
                        _settings.setBingoDifficulty(TaskDifficulty.NORMAL)
                    }
                    TaskDifficulty.NORMAL ->{
                        _settings.setBingoDifficulty(TaskDifficulty.HARD)
                    }
                    TaskDifficulty.HARD ->{
                        _settings.setBingoDifficulty(TaskDifficulty.EXTREME)

                    }
                    TaskDifficulty.EXTREME ->{
                        _settings.setBingoDifficulty(TaskDifficulty.EASY)

                    }
                }
            }else if(e.isRightClick){
                when(_settings.getBingoDifficulty()){
                    TaskDifficulty.EASY ->{
                        _settings.setBingoDifficulty(TaskDifficulty.EXTREME)
                    }
                    TaskDifficulty.NORMAL ->{
                        _settings.setBingoDifficulty(TaskDifficulty.EASY)
                    }
                    TaskDifficulty.HARD ->{
                        _settings.setBingoDifficulty(TaskDifficulty.NORMAL)

                    }
                    TaskDifficulty.EXTREME ->{
                        _settings.setBingoDifficulty(TaskDifficulty.HARD)

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
        if (itemName == Component.text("PVP ON", NamedTextColor.GREEN)) {
            if(e.isLeftClick){
                _settings.setPvP(false)
            }
            _settingsInv.newInventory()
            player.openInventory(_settingsInv.getInventory())
        }
        if (itemName == Component.text("PVP OFF", NamedTextColor.RED)) {
            if(e.isLeftClick){
                _settings.setPvP(true)
            }
            _settingsInv.newInventory()
            player.openInventory(_settingsInv.getInventory())
        }
        if (itemName == Component.text("KeepInventory ON", NamedTextColor.GREEN)) {
            if(e.isLeftClick){
                _settings.setKeepInventory(false)
            }
            _settingsInv.newInventory()
            player.openInventory(_settingsInv.getInventory())
        }
        if (itemName == Component.text("KeepInventory OFF", NamedTextColor.RED)) {
            if(e.isLeftClick){
                _settings.setKeepInventory(true)
            }
            _settingsInv.newInventory()
            player.openInventory(_settingsInv.getInventory())
        }
        if (itemName == Component.text("Enchanting ON", NamedTextColor.GREEN)) {
            if(e.isLeftClick){
                _settings.setEnchanting(false)
            }
            _settingsInv.newInventory()
            player.openInventory(_settingsInv.getInventory())
        }
        if (itemName == Component.text("Enchanting OFF", NamedTextColor.RED)) {
            if(e.isLeftClick){
                _settings.setEnchanting(true)
            }
            _settingsInv.newInventory()
            player.openInventory(_settingsInv.getInventory())
        }
        if (itemName == Component.text("Mobs ON", NamedTextColor.GREEN)) {
            if(e.isLeftClick){
                _settings.setMobs(false)
            }
            _settingsInv.newInventory()
            player.openInventory(_settingsInv.getInventory())
        }
        if (itemName == Component.text("Mobs OFF", NamedTextColor.RED)) {
            if(e.isLeftClick){
                _settings.setMobs(true)
            }
            _settingsInv.newInventory()
            player.openInventory(_settingsInv.getInventory())
        }
        if (itemName == Component.text("Bioms ON", NamedTextColor.GREEN)) {
            if(e.isLeftClick){
                _settings.setBioms(false)
            }
            _settingsInv.newInventory()
            player.openInventory(_settingsInv.getInventory())
        }
        if (itemName == Component.text("Bioms OFF", NamedTextColor.RED)) {
            if(e.isLeftClick){
                _settings.setBioms(true)
            }
            _settingsInv.newInventory()
            player.openInventory(_settingsInv.getInventory())
        }
        if (itemName == Component.text("Achievements ON", NamedTextColor.GREEN)) {
            if(e.isLeftClick){
                _settings.setAchievements(false)
            }
            _settingsInv.newInventory()
            player.openInventory(_settingsInv.getInventory())
        }
        if (itemName == Component.text("Achievements OFF", NamedTextColor.RED)) {
            if(e.isLeftClick){
                _settings.setAchievements(true)
            }
            _settingsInv.newInventory()
            player.openInventory(_settingsInv.getInventory())
        }
        if (itemName == Component.text("Hunger ON", NamedTextColor.GREEN)) {
            if(e.isLeftClick){
                _settings.setHunger(false)
            }
            _settingsInv.newInventory()
            player.openInventory(_settingsInv.getInventory())
        }
        if (itemName == Component.text("Hunger OFF", NamedTextColor.RED)) {
            if(e.isLeftClick){
                _settings.setHunger(true)
            }
            _settingsInv.newInventory()
            player.openInventory(_settingsInv.getInventory())
        }
        if (itemName == Component.text("Difficulty ", NamedTextColor.GOLD).append(Component.text(_settings.getDifficulty().toString()).color(NamedTextColor.AQUA))) {
            if(e.isLeftClick){
                when(_settings.getDifficulty()){
                    Difficulty.EASY ->{
                        _settings.setDifficulty(Difficulty.NORMAL)
                    }
                    Difficulty.NORMAL ->{
                        _settings.setDifficulty(Difficulty.HARD)
                    }
                    Difficulty.HARD ->{
                        _settings.setDifficulty(Difficulty.PEACEFUL)

                    }
                    Difficulty.PEACEFUL ->{
                        _settings.setDifficulty(Difficulty.EASY)

                    }
                }
            }else if(e.isRightClick){
                when(_settings.getDifficulty()){
                    Difficulty.EASY ->{
                        _settings.setDifficulty(Difficulty.PEACEFUL)
                    }
                    Difficulty.NORMAL ->{
                        _settings.setDifficulty(Difficulty.EASY)
                    }
                    Difficulty.HARD ->{
                        _settings.setDifficulty(Difficulty.NORMAL)

                    }
                    Difficulty.PEACEFUL ->{
                        _settings.setDifficulty(Difficulty.HARD)

                    }
                }
            }
            _settingsInv.newInventory()
            player.openInventory(_settingsInv.getInventory())
        }

        //TEAMSELECT
        if (itemName == Component.text("Team #1", NamedTextColor.WHITE)) {
            _teams.joinTeam(player, "white")
            _teamSelectInv.updateInventory(player)
        }
        if (itemName == Component.text("Team #2", NamedTextColor.GOLD)) {
            _teams.joinTeam(player, "orange")
            _teamSelectInv.updateInventory(player)
        }
        if (itemName == Component.text("Team #3", NamedTextColor.LIGHT_PURPLE)) {
            _teams.joinTeam(player, "magenta")
            _teamSelectInv.updateInventory(player)
        }
        if (itemName == Component.text("Team #4", NamedTextColor.AQUA)) {
            _teams.joinTeam(player, "lightblue")
            _teamSelectInv.updateInventory(player)
        }
        if (itemName == Component.text("Team #5", NamedTextColor.YELLOW)) {
            _teams.joinTeam(player, "yellow")
            _teamSelectInv.updateInventory(player)
        }
        if (itemName == Component.text("Team #6", NamedTextColor.GREEN)) {
            _teams.joinTeam(player, "lime")
            _teamSelectInv.updateInventory(player)
        }
        if (itemName == Component.text("Team #7", NamedTextColor.RED)) {
            _teams.joinTeam(player, "pink")
            _teamSelectInv.updateInventory(player)
        }
        if (itemName == Component.text("Team #8", NamedTextColor.DARK_GRAY)) {
            _teams.joinTeam(player, "gray")
            _teamSelectInv.updateInventory(player)
        }
        if (itemName == Component.text("Team #9", NamedTextColor.GRAY)) {
            _teams.joinTeam(player, "lightgray")
            _teamSelectInv.updateInventory(player)
        }
        if (itemName == Component.text("Team #10", NamedTextColor.DARK_AQUA)) {
            _teams.joinTeam(player, "cyan")
            _teamSelectInv.updateInventory(player)
        }
        if (itemName == Component.text("Team #11", NamedTextColor.DARK_PURPLE)) {
            _teams.joinTeam(player, "purple")
            _teamSelectInv.updateInventory(player)
        }
        if (itemName == Component.text("Team #12", NamedTextColor.BLUE)) {
            _teams.joinTeam(player, "blue")
            _teamSelectInv.updateInventory(player)
        }
        if (itemName == Component.text("Team #13", NamedTextColor.GOLD)) {
            _teams.joinTeam(player, "brown")
            _teamSelectInv.updateInventory(player)
        }
        if (itemName == Component.text("Team #14", NamedTextColor.DARK_GREEN)) {
            _teams.joinTeam(player, "green")
            _teamSelectInv.updateInventory(player)
        }
        if (itemName == Component.text("Team #15", NamedTextColor.DARK_RED)) {
            _teams.joinTeam(player, "red")
            _teamSelectInv.updateInventory(player)
        }
        if (itemName == Component.text("Team #16", NamedTextColor.DARK_GRAY)) {
            _teams.joinTeam(player, "black")
            _teamSelectInv.updateInventory(player)
        }
        if (itemName == Component.text("Spectator", NamedTextColor.GRAY)) {
            _teams.joinTeam(player, "spec")
            _teamSelectInv.updateInventory(player)
        }
    }
}
