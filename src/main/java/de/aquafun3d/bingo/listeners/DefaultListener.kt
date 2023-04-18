package de.aquafun3d.bingo.listeners

import de.aquafun3d.bingo.utils.helpers.IHelpers
import de.aquafun3d.bingo.utils.inventories.ISettingsInventory
import de.aquafun3d.bingo.utils.inventories.ITeamInventories
import de.aquafun3d.bingo.utils.inventories.ITeamselectInventory
import de.aquafun3d.bingo.utils.scoreboards.IScoreboards
import de.aquafun3d.bingo.utils.teams.ITeams
import io.papermc.paper.event.player.AsyncChatEvent
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.entity.FoodLevelChangeEvent
import org.bukkit.event.player.PlayerDropItemEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

class DefaultListener(private val _helpers: IHelpers, private val _scoreboard: IScoreboards, private val _settings: ISettingsInventory, private val _teamselect: ITeamselectInventory, private val _teams: ITeams, private val _teaminv: ITeamInventories) : Listener {

    @EventHandler
    fun onJoin(e: PlayerJoinEvent) {
        val player = e.player
        e.joinMessage(_helpers.getPrefix().append(player.name()).color(NamedTextColor.AQUA).append(Component.text(" has joined").color(NamedTextColor.LIGHT_PURPLE)))
        _scoreboard.initPlayerScorebaord(player)
        //TODO
    }

    @EventHandler
    fun onQuit(e: PlayerQuitEvent) {
        val player = e.player
        e.quitMessage(_helpers.getPrefix().append(player.name()).color(NamedTextColor.AQUA).append(Component.text(" has left").color(NamedTextColor.LIGHT_PURPLE)))
    }

    @EventHandler
    fun onChat(e: AsyncChatEvent) {
        val player = e.player
        val prefix = if (player.isOp) {
            player.displayName().color(NamedTextColor.RED).append(Component.text(" | ")).color(NamedTextColor.DARK_GRAY).append(e.message()).color(NamedTextColor.WHITE)
        } else {
            player.displayName().color(NamedTextColor.GRAY).append(Component.text(" | ")).color(NamedTextColor.DARK_GRAY).append(e.message()).color(NamedTextColor.WHITE)
        }
        e.message(_teams.getPlayerTeamPrefix(player).append(prefix))
    }

    @EventHandler
    fun onItemDrop(e: PlayerDropItemEvent) {
        val item = e.itemDrop.itemStack
        if (item == _settings.getItem() || item == _teamselect.item) {
            e.isCancelled = true
        }
    }

    @EventHandler
    fun onHunger(e: FoodLevelChangeEvent) {
        val player = e.entity as Player
        player.saturation = 21f
        e.isCancelled = true
    }

    @EventHandler
    fun onRightClick(e: PlayerInteractEvent) {
        val player = e.player
        if (e.action == Action.RIGHT_CLICK_AIR || e.action == Action.RIGHT_CLICK_BLOCK) {
            if (e.hasItem()) {
                if (e.item == _teamselect.item) {
                    _teamselect.updateInventory(_teams.getPlayerTeamName(player), player)
                }
            }
        }
        if (e.action == Action.RIGHT_CLICK_AIR || e.action == Action.RIGHT_CLICK_BLOCK) {
            if (e.hasItem()) {
                if (e.item == _settings.getItem()) {
                    player.openInventory(_settings.getInventory())
                }
            }
        }
        if (e.action == Action.RIGHT_CLICK_AIR || e.action == Action.RIGHT_CLICK_BLOCK) {
            if (e.hasItem()) {
                if (e.item == _teaminv.getItem()) {
                    player.openInventory(_teaminv.getIventorybyPlayer(player))
                }
            }
        }
    }
}
