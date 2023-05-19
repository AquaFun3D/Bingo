package de.aquafun3d.bingo.listeners

import de.aquafun3d.bingo.utils.helpers.IHelpers
import de.aquafun3d.bingo.utils.helpers.ISettings
import de.aquafun3d.bingo.utils.inventories.ITeamInventories
import de.aquafun3d.bingo.utils.teams.ITeams
import de.aquafun3d.bingo.utils.timer.ITimer
import net.kyori.adventure.audience.Audience
import net.kyori.adventure.key.Key
import net.kyori.adventure.sound.Sound
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.title.Title
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityPickupItemEvent
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack

class BingoListener(private val _helper: IHelpers, private val _teamInv: ITeamInventories, private val _teams: ITeams, private val _settings: ISettings, private val _timer: ITimer) : Listener {
    @EventHandler
    fun onInvClick(e: InventoryClickEvent) {
        val player = e.whoClicked as Player
        val item = e.currentItem
        if (e.view.title() == Component.text("Teamselect", NamedTextColor.DARK_PURPLE)) {
            return
        }
        if (e.view.title() == Component.text("Settings", NamedTextColor.DARK_PURPLE)) {
            return
        }
        if (e.view.title() == Component.text("Bingo", NamedTextColor.DARK_PURPLE)) {
            e.isCancelled = true
            return
        }
        if (item == null) return
        if (item.itemMeta == null) return
        if(!_helper.isBingoRunning()) return
        checkItem(player, item)
    }

    @EventHandler
    fun onPickUp(e: EntityPickupItemEvent) {
        if(!_helper.isBingoRunning()) return
        if (e.entity is Player){
            val player = (e.entity as Player)
            val item = e.item.itemStack
            if(item == _teamInv.getItem()) e.isCancelled = true
            checkItem(player, item)
        }
    }

    private fun checkItem(player: Player, item: ItemStack){
        if(_teams.getPlayerTeamName(player) == "spec") return
            if(_teamInv.getInventorybyPlayer(player).contains(item.type)) {
                _teamInv.removeItem(player, item.type)
                _helper.atAll(Component.text("Team ", NamedTextColor.GOLD).append(_teams.getPlayerTeamPrefix(player)).append(Component.text(player.name, NamedTextColor.AQUA)).append(Component.text(" registered ", NamedTextColor.GREEN)).append(item.displayName().color(NamedTextColor.LIGHT_PURPLE)).append(Component.text(" (" + ((_settings.getQuantity() * 9) - _teamInv.itemCount(player)) + "/" + _settings.getQuantity() * 9 + ")", NamedTextColor.YELLOW)))
                sendTitle(player, item.displayName().color(NamedTextColor.LIGHT_PURPLE), Component.text("registered", NamedTextColor.GREEN))
                _teams.updateTeamSuffix(player, _teamInv.itemCount(player))
            }
        if(_teamInv.getInventorybyPlayer(player).isEmpty) winTask(player)
    }


    private fun sendTitle(player: Player, title: Component, subtitle: Component){
        Bukkit.getEntity(player.uniqueId)
        val list = mutableListOf<Player>()
        for(k in _teams.getTeams().keys){
            if(_teams.getTeams()[k] == _teams.getPlayerTeam(player)){
                list.add(Bukkit.getEntity(k) as Player )
            }
        }
        val audi = Audience.audience(list)
        audi.showTitle(Title.title(title,subtitle))
        audi.playSound(Sound.sound(Key.key("entity.player.levelup"), Sound.Source.PLAYER, 1f, 1f))
    }

    private fun winTask(player: Player){
        _timer.reset()
        val audi = Audience.audience(Bukkit.getOnlinePlayers())
        audi.showTitle(Title.title(Component.text("Team ",NamedTextColor.GOLD).append(_teams.getPlayerTeamPrefix(player)), Component.text("has won the Bingo!", NamedTextColor.GRAY)))
        audi.playSound(Sound.sound(Key.key("entity.player.levelup"), Sound.Source.PLAYER, 1f, 1f))
        for(p in Bukkit.getOnlinePlayers()){
            p.gameMode = GameMode.SPECTATOR
        }
        _helper.atAll(Component.text("Team ",NamedTextColor.GOLD).append(_teams.getPlayerTeamPrefix(player)).append(Component.text("won the Bingo!", NamedTextColor.GOLD)))
    }
}
