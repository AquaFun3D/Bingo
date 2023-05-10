package de.aquafun3d.bingo.listeners

import de.aquafun3d.bingo.utils.helpers.IHelpers
import de.aquafun3d.bingo.utils.helpers.ISettings
import de.aquafun3d.bingo.utils.inventories.ISettingsInventory
import de.aquafun3d.bingo.utils.inventories.ITeamInventories
import de.aquafun3d.bingo.utils.inventories.ITeamselectInventory
import de.aquafun3d.bingo.utils.scoreboards.IScoreboards
import de.aquafun3d.bingo.utils.teams.ITeams
import io.papermc.paper.event.player.AsyncChatEvent
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.FoodLevelChangeEvent
import org.bukkit.event.player.PlayerDropItemEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.event.player.PlayerRespawnEvent

class DefaultListener(private val _helpers: IHelpers, private val _scoreboard: IScoreboards, private val _settingsInv: ISettingsInventory, private val _teamselect: ITeamselectInventory, private val _teams: ITeams, private val _teamInv: ITeamInventories, private val _settings: ISettings) : Listener {

    @EventHandler
    fun onJoin(e: PlayerJoinEvent) {
        val player = e.player
        e.joinMessage(_helpers.getPrefix().append(player.name().color(NamedTextColor.AQUA)).append(Component.text(" has joined").color(NamedTextColor.LIGHT_PURPLE)))
        _scoreboard.initPlayerScorebaord(player)
        if(!_helpers.isBingoRunning()){
            player.inventory.clear()
        }
        if(player.isOp && !_helpers.isBingoRunning()){
            player.inventory.setItem(8, _settingsInv.getItem())
        }
        if(_teams.getPlayerTeam(player) == null){
            _teams.joinTeam(player,"spec")
            _teamselect.updateInventory(player)
        }
        if(_settings.isConfirmed() && !_helpers.isBingoRunning()){
            player.inventory.setItem(4, _teamselect.getItem())
        }
    }

    @EventHandler
    fun onQuit(e: PlayerQuitEvent) {
        val player = e.player
        e.quitMessage(_helpers.getPrefix().append(player.name().color(NamedTextColor.AQUA)).append(Component.text(" has left").color(NamedTextColor.LIGHT_PURPLE)))
    }

    @EventHandler
    fun onChat(e: AsyncChatEvent) {
        val player = e.player
        val name: Component = if (player.isOp) {
            _teams.getPlayerTeamPrefix(player).append(player.name().color(NamedTextColor.RED))
        } else {
            _teams.getPlayerTeamPrefix(player).append(player.name().color(NamedTextColor.WHITE))
        }
        val message = Component.text(" | ").color(NamedTextColor.DARK_GRAY).append(e.message().color(NamedTextColor.WHITE))
        Bukkit.broadcast(name.append(message))
        e.isCancelled = true
    }

    @EventHandler
    fun onItemDrop(e: PlayerDropItemEvent) {
        val item = e.itemDrop.itemStack
        if (item == _settingsInv.getItem() || item == _teamselect.getItem() || item == _teamInv.getItem()){
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
    fun onPlayerDeath(e: PlayerRespawnEvent) {
        val player = e.player
        player.inventory.setItem(8, _teamInv.getItem())
    }

    @EventHandler
    fun onPlayerDamage(e: EntityDamageByEntityEvent){
        if(!_helpers.isBingoRunning() && e.damager is Player){
            e.isCancelled = true
        }
    }

    @EventHandler
    fun onRightClick(e: PlayerInteractEvent) {
        val player = e.player
        if (e.action == Action.RIGHT_CLICK_AIR || e.action == Action.RIGHT_CLICK_BLOCK) {
            if (e.hasItem()) {
                if (e.item == _teamselect.getItem()) {
                    player.openInventory(_teamselect.getInventory())
                }
            }
        }
        if (e.action == Action.RIGHT_CLICK_AIR || e.action == Action.RIGHT_CLICK_BLOCK) {
            if (e.hasItem()) {
                if (e.item == _settingsInv.getItem()) {
                    for(p in Bukkit.getOnlinePlayers()){
                        if (p.openInventory.title() == Component.text("Settings", NamedTextColor.DARK_PURPLE)) {
                            _helpers.send(player, Component.text("Someone else is setting up right now!",NamedTextColor.RED))
                            return
                        }
                    }
                    _settings.setConfirmed(false)
                    player.openInventory(_settingsInv.getInventory())
                }
            }
        }
        if (e.action == Action.RIGHT_CLICK_AIR || e.action == Action.RIGHT_CLICK_BLOCK) {
            if (e.hasItem()) {
                if (e.item == _teamInv.getItem()) {
                    player.openInventory(_teamInv.getInventorybyPlayer(player))
                }
            }
        }
    }
}
