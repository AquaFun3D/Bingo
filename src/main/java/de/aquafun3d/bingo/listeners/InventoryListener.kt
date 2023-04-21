package de.aquafun3d.bingo.listeners

import de.aquafun3d.bingo.utils.inventories.ITeamselectInventory
import de.aquafun3d.bingo.utils.teams.ITeams
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent

class InventoryListener(private val _teams: ITeams, private val _teamInv: ITeamselectInventory) : Listener {
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

        //SETTINGS


        //TEAMSELCT
        if (itemName == Component.text("Team #1", NamedTextColor.WHITE)) {
            _teams.joinTeam(player, "white")
            _teamInv.updateInventory(player, "white", itemName)
        }
        if (itemName == Component.text("Team #2", NamedTextColor.GOLD)) {
            _teams.joinTeam(player, "orange")
            _teamInv.updateInventory(player, "orange", itemName)
        }
        if (itemName == Component.text("Team #3", NamedTextColor.LIGHT_PURPLE)) {
            _teams.joinTeam(player, "magenta")
            _teamInv.updateInventory(player, "magenta", itemName)
        }
        if (itemName == Component.text("Team #4", NamedTextColor.AQUA)) {
            _teams.joinTeam(player, "lightblue")
            _teamInv.updateInventory(player, "lightblue", itemName)
        }
        if (itemName == Component.text("Team #5", NamedTextColor.YELLOW)) {
            _teams.joinTeam(player, "yellow")
            _teamInv.updateInventory(player, "yellow", itemName)
        }
        if (itemName == Component.text("Team #6", NamedTextColor.GREEN)) {
            _teams.joinTeam(player, "lime")
            _teamInv.updateInventory(player, "lime", itemName)
        }
        if (itemName == Component.text("Team #7", NamedTextColor.RED)) {
            _teams.joinTeam(player, "pink")
            _teamInv.updateInventory(player, "pink", itemName)
        }
        if (itemName == Component.text("Team #8", NamedTextColor.DARK_GRAY)) {
            _teams.joinTeam(player, "gray")
            _teamInv.updateInventory(player, "gray", itemName)
        }
        if (itemName == Component.text("Team #9", NamedTextColor.GRAY)) {
            _teams.joinTeam(player, "lightgray")
            _teamInv.updateInventory(player, "lightgray", itemName)
        }
        if (itemName == Component.text("Team #10", NamedTextColor.DARK_AQUA)) {
            _teams.joinTeam(player, "cyan")
            _teamInv.updateInventory(player, "cyan", itemName)
        }
        if (itemName == Component.text("Team #11", NamedTextColor.DARK_PURPLE)) {
            _teams.joinTeam(player, "purple")
            _teamInv.updateInventory(player, "purple", itemName)
        }
        if (itemName == Component.text("Team #12", NamedTextColor.BLUE)) {
            _teams.joinTeam(player, "blue")
            _teamInv.updateInventory(player, "blue", itemName)
        }
        if (itemName == Component.text("Team #13", NamedTextColor.GOLD)) {
            _teams.joinTeam(player, "brown")
            _teamInv.updateInventory(player, "brown", itemName)
        }
        if (itemName == Component.text("Team #14", NamedTextColor.DARK_GREEN)) {
            _teams.joinTeam(player, "green")
            _teamInv.updateInventory(player, "green", itemName)
        }
        if (itemName == Component.text("Team #15", NamedTextColor.DARK_RED)) {
            _teams.joinTeam(player, "red")
            _teamInv.updateInventory(player, "red", itemName)
        }
        if (itemName == Component.text("Team #16", NamedTextColor.DARK_GRAY)) {
            _teams.joinTeam(player, "black")
            _teamInv.updateInventory(player, "black", itemName)
        }
        if (itemName == Component.text("Spectator", NamedTextColor.GRAY)) {
            _teams.joinTeam(player, "spec")
            _teamInv.updateInventory(player, "spec", itemName)
        }
    }
}
