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

        //SETTINGS


        //TEAMSELCT
        if (item.itemMeta.displayName() == Component.text("Team #1", NamedTextColor.WHITE)) {
            _teams.joinTeam(player, "white")
            _teamInv.updateInventory("white", player)
        }
        if (item.itemMeta.displayName() == Component.text("Team #2", NamedTextColor.GOLD)) {
            _teams.joinTeam(player, "orange")
            _teamInv.updateInventory("orange", player)
        }
        if (item.itemMeta.displayName() == Component.text("Team #3", NamedTextColor.LIGHT_PURPLE)) {
            _teams.joinTeam(player, "magenta")
            _teamInv.updateInventory("magenta", player)
        }
        if (item.itemMeta.displayName() == Component.text("Team #4", NamedTextColor.AQUA)) {
            _teams.joinTeam(player, "lightblue")
            _teamInv.updateInventory("lightblue", player)
        }
        if (item.itemMeta.displayName() == Component.text("Team #5", NamedTextColor.YELLOW)) {
            _teams.joinTeam(player, "yellow")
            _teamInv.updateInventory("yellow", player)
        }
        if (item.itemMeta.displayName() == Component.text("Team #6", NamedTextColor.GREEN)) {
            _teams.joinTeam(player, "lime")
            _teamInv.updateInventory("lime", player)
        }
        if (item.itemMeta.displayName() == Component.text("Team #7", NamedTextColor.RED)) {
            _teams.joinTeam(player, "pink")
            _teamInv.updateInventory("pink", player)
        }
        if (item.itemMeta.displayName() == Component.text("Team #8", NamedTextColor.DARK_GRAY)) {
            _teams.joinTeam(player, "gray")
            _teamInv.updateInventory("gray", player)
        }
        if (item.itemMeta.displayName() == Component.text("Team #9", NamedTextColor.GRAY)) {
            _teams.joinTeam(player, "lightgray")
            _teamInv.updateInventory("lightgray", player)
        }
        if (item.itemMeta.displayName() == Component.text("Team #10", NamedTextColor.DARK_AQUA)) {
            _teams.joinTeam(player, "cyan")
            _teamInv.updateInventory("cyan", player)
        }
        if (item.itemMeta.displayName() == Component.text("Team #11", NamedTextColor.DARK_PURPLE)) {
            _teams.joinTeam(player, "purple")
            _teamInv.updateInventory("purple", player)
        }
        if (item.itemMeta.displayName() == Component.text("Team #12", NamedTextColor.BLUE)) {
            _teams.joinTeam(player, "blue")
            _teamInv.updateInventory("blue", player)
        }
        if (item.itemMeta.displayName() == Component.text("Team #13", NamedTextColor.GOLD)) {
            _teams.joinTeam(player, "brown")
            _teamInv.updateInventory("brown", player)
        }
        if (item.itemMeta.displayName() == Component.text("Team #14", NamedTextColor.DARK_GREEN)) {
            _teams.joinTeam(player, "green")
            _teamInv.updateInventory("green", player)
        }
        if (item.itemMeta.displayName() == Component.text("Team #15", NamedTextColor.DARK_RED)) {
            _teams.joinTeam(player, "red")
            _teamInv.updateInventory("red", player)
        }
        if (item.itemMeta.displayName() == Component.text("Team #16", NamedTextColor.DARK_GRAY)) {
            _teams.joinTeam(player, "black")
            _teamInv.updateInventory("black", player)
        }
        if (item.itemMeta.displayName() == Component.text("Spectator", NamedTextColor.GRAY)) {
            _teams.joinTeam(player, "spec")
            _teamInv.updateInventory("spec", player)
        }
    }
}
