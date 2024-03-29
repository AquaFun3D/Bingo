package de.aquafun3d.bingo.listeners

import de.aquafun3d.bingo.utils.helpers.IHelpers
import de.aquafun3d.bingo.utils.helpers.ISettings
import de.aquafun3d.bingo.utils.helpers.Mode
import de.aquafun3d.bingo.utils.inventories.ISettingsInventory
import de.aquafun3d.bingo.utils.inventories.ITeamselectInventory
import de.aquafun3d.bingo.utils.tasks.TaskDifficulty
import de.aquafun3d.bingo.utils.teams.ITeams
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.Difficulty
import org.bukkit.Material
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
        if (item == null) return

        if (item.itemMeta == null) return

        val itemName = item.itemMeta.displayName()

        if(((e.cursor.type == Material.BUNDLE) || item.type == Material.BUNDLE) && e.isRightClick) e.isCancelled = true


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
            _teamSelectInv.newInventory()
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
            updateInv(player)
        }
        if (itemName == Component.text("Nether ON", NamedTextColor.GREEN)) {
            if(e.isLeftClick){
                _settings.setNether(false)
            }
            updateInv(player)
        }
        if (itemName == Component.text("Nether OFF", NamedTextColor.RED)) {
            if(e.isLeftClick){
                _settings.setNether(true)
            }
            updateInv(player)
        }
        if (itemName == Component.text("End ON", NamedTextColor.GREEN)) {
            if(e.isLeftClick){
                _settings.setEnd(false)
            }
            updateInv(player)
        }
        if (itemName == Component.text("End OFF", NamedTextColor.RED)) {
            if(e.isLeftClick){
                _settings.setEnd(true)
            }
            updateInv(player)
        }
        if (itemName == Component.text("Silktouch ON", NamedTextColor.GREEN)) {
            if(e.isLeftClick){
                _settings.setSilktouch(false)
            }
            updateInv(player)
        }
        if (itemName == Component.text("Silktouch OFF", NamedTextColor.RED)) {
            if(e.isLeftClick){
                _settings.setSilktouch(true)
            }
            updateInv(player)
        }
        if (itemName == Component.text("Items", NamedTextColor.AQUA)) {
            if(e.isLeftClick){
                _settings.setQuantity(_settings.getQuantity()+1)
            }
            updateInv(player)
        }
        if (itemName == Component.text("Teamsize", NamedTextColor.DARK_AQUA)) {
            if(e.isLeftClick){
                if(_settings.getMode() != Mode.LOCKOUT) {
                    _settings.setTeamsize(_settings.getTeamsize() + 1)
                }
            }
            updateInv(player)
        }
        if (itemName == Component.text("PVP ON", NamedTextColor.GREEN)) {
            if(e.isLeftClick){
                _settings.setPvP(false)
            }
            updateInv(player)
        }
        if (itemName == Component.text("PVP OFF", NamedTextColor.RED)) {
            if(e.isLeftClick){
                _settings.setPvP(true)
            }
            updateInv(player)
        }
        if (itemName == Component.text("KeepInventory ON", NamedTextColor.GREEN)) {
            if(e.isLeftClick){
                _settings.setKeepInventory(false)
            }
            updateInv(player)
        }
        if (itemName == Component.text("KeepInventory OFF", NamedTextColor.RED)) {
            if(e.isLeftClick){
                _settings.setKeepInventory(true)
            }
            updateInv(player)
        }
        if (itemName == Component.text("Enchanting ON", NamedTextColor.GREEN)) {
            if(e.isLeftClick){
                _settings.setEnchanting(false)
            }
            updateInv(player)
        }
        if (itemName == Component.text("Enchanting OFF", NamedTextColor.RED)) {
            if(e.isLeftClick){
                _settings.setEnchanting(true)
            }
            updateInv(player)
        }
        if (itemName == Component.text("Mobs ON", NamedTextColor.GREEN)) {
            if(e.isLeftClick){
                _settings.setMobs(false)
            }
            updateInv(player)
        }
        if (itemName == Component.text("Mobs OFF", NamedTextColor.RED)) {
            if(e.isLeftClick){
                _settings.setMobs(true)
            }
            updateInv(player)
        }
        if (itemName == Component.text("Bioms ON", NamedTextColor.GREEN)) {
            if(e.isLeftClick){
                _settings.setBioms(false)
            }
            updateInv(player)
        }
        if (itemName == Component.text("Bioms OFF", NamedTextColor.RED)) {
            if(e.isLeftClick){
                _settings.setBioms(true)
            }
            updateInv(player)
        }
        if (itemName == Component.text("Achievements ON", NamedTextColor.GREEN)) {
            if(e.isLeftClick){
                _settings.setAchievements(false)
            }
            updateInv(player)
        }
        if (itemName == Component.text("Achievements OFF", NamedTextColor.RED)) {
            if(e.isLeftClick){
                _settings.setAchievements(true)
            }
            updateInv(player)
        }
        if (itemName == Component.text("Hunger ON", NamedTextColor.GREEN)) {
            if(e.isLeftClick){
                _settings.setHunger(false)
            }
            updateInv(player)
        }
        if (itemName == Component.text("Hunger OFF", NamedTextColor.RED)) {
            if(e.isLeftClick){
                _settings.setHunger(true)
            }
            updateInv(player)
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
            updateInv(player)
        }

        if (itemName == Component.text(_settings.getMode().toString(),NamedTextColor.AQUA).append(Component.text(" Bingo", NamedTextColor.GOLD))) {
            if(e.isLeftClick){
                when(_settings.getMode()){
                    Mode.NORMAL ->{
                        _settings.setMode(Mode.ROWS)
                        _settings.setTeamsize(1)
                    }
                    Mode.ROWS ->{
                        _settings.setMode(Mode.LOCKOUT)
                        _settings.setTeamsize(10)
                    }
                    Mode.LOCKOUT ->{
                        _settings.setMode(Mode.NORMAL)
                        _settings.setTeamsize(1)
                    }
                }
            }else if(e.isRightClick){
                when(_settings.getMode()){
                    Mode.NORMAL ->{
                        _settings.setMode(Mode.LOCKOUT)
                        _settings.setTeamsize(10)
                    }
                    Mode.ROWS ->{
                        _settings.setMode(Mode.NORMAL)
                        _settings.setTeamsize(1)
                    }
                    Mode.LOCKOUT ->{
                        _settings.setMode(Mode.ROWS)
                        _settings.setTeamsize(1)
                    }
                }
            }
            updateInv(player)
        }

        //TEAMSELECT
        if (itemName == Component.text("Team #1", NamedTextColor.DARK_RED)) {
            join("red",player)
        }
        if (itemName == Component.text("Team #2", NamedTextColor.BLUE)) {
            join("blue",player)
        }
        if (itemName == Component.text("Team #3", NamedTextColor.WHITE)) {
            join("white",player)
        }
        if (itemName == Component.text("Team #4", NamedTextColor.GOLD)) {
            join("orange",player)
        }
        if (itemName == Component.text("Team #5", NamedTextColor.LIGHT_PURPLE)) {
            join("magenta",player)
        }
        if (itemName == Component.text("Team #6", NamedTextColor.AQUA)) {
            join("lightblue",player)
        }
        if (itemName == Component.text("Team #7", NamedTextColor.YELLOW)) {
            join("yellow",player)
        }
        if (itemName == Component.text("Team #8", NamedTextColor.GREEN)) {
            join("lime",player)
        }
        if (itemName == Component.text("Team #9", NamedTextColor.RED)) {
            join("pink",player)
        }
        if (itemName == Component.text("Team #10", NamedTextColor.DARK_GRAY)) {
            join("gray",player)
        }
        if (itemName == Component.text("Team #11", NamedTextColor.GRAY)) {
            join("lightgray",player)
        }
        if (itemName == Component.text("Team #12", NamedTextColor.DARK_AQUA)) {
            join("cyan",player)
        }
        if (itemName == Component.text("Team #13", NamedTextColor.DARK_PURPLE)) {
            join("purple",player)
        }
        if (itemName == Component.text("Team #14", NamedTextColor.GOLD)) {
            join("brown",player)
        }
        if (itemName == Component.text("Team #15", NamedTextColor.DARK_GREEN)) {
            join("green",player)
        }
        if (itemName == Component.text("Team #16", NamedTextColor.DARK_GRAY)) {
            join("black",player)
        }
        if (itemName == Component.text("Spectator", NamedTextColor.GRAY)) {
            join("spec",player)
        }
    }

    private fun join(teamName: String, player: Player){
        _teams.joinTeam(player, teamName)
        _teamSelectInv.updateInventory(player)
    }

    private fun updateInv(player: Player){
        _settingsInv.newInventory()
        player.openInventory(_settingsInv.getInventory())
    }
}


