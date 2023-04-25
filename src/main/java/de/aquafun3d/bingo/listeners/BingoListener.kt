package de.aquafun3d.bingo.listeners

import de.aquafun3d.bingo.utils.helpers.IHelpers
import de.aquafun3d.bingo.utils.helpers.ISettings
import de.aquafun3d.bingo.utils.inventories.ITeamInventories
import de.aquafun3d.bingo.utils.tasks.IBingoTask
import de.aquafun3d.bingo.utils.teams.ITeams
import net.kyori.adventure.audience.Audience
import net.kyori.adventure.identity.Identity
import net.kyori.adventure.key.Key
import net.kyori.adventure.sound.Sound
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.title.Title
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityPickupItemEvent
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack

class BingoListener(private val _helper: IHelpers, private val _teaminv: ITeamInventories, private val _teams: ITeams, private val _settings: ISettings) : Listener {
    @EventHandler
    fun onInvClick(e: InventoryClickEvent) {
        val player = e.whoClicked as Player
        val item = e.currentItem
        if (e.view.title() == Component.text("Teamselect", NamedTextColor.DARK_PURPLE)) {
            return
        }
        if (e.view.title() == Component.text("Bingo Settings", NamedTextColor.DARK_PURPLE)) {
            return
        }
        if (e.view.title() == Component.text("Bingo", NamedTextColor.DARK_PURPLE)) {
            e.isCancelled = true
            return
        }
        if (item == null) {
            return
        }
        if (item.itemMeta == null) {
            return
        }
        checkItem(player, item)
    }

    @EventHandler
    fun onPickUp(e: EntityPickupItemEvent) {
        if (e.entity is Player){
            val player = (e.entity as Player)
            val item = e.item.itemStack
            checkItem(player, item)
        }
    }

    private fun checkItem(player: Player, item: ItemStack){
        if (item.amount > 1) {
            if(_teaminv.getInventorybyPlayer(player).contains(item.type)) {
                _teaminv.removeItem(player, item.type)
                _helper.atAll(Component.text("Team ", NamedTextColor.GOLD).append(_teams.getPlayerTeamPrefix(player)).append(Component.text("registered ", NamedTextColor.GREEN)).append(item.displayName().color(NamedTextColor.LIGHT_PURPLE)).append(Component.text(" (" + ((_settings.getQuantity() * 9) - _teaminv.itemCount(player)) + "/" + _settings.getQuantity() * 9 + ")", NamedTextColor.YELLOW)))
            }
        }else if(_teaminv.getInventorybyPlayer(player).contains(item)){
            _teaminv.removeItem(player, item)
            _helper.atAll(Component.text("Team ", NamedTextColor.GOLD).append(_teams.getPlayerTeamPrefix(player)).append(Component.text("registered ", NamedTextColor.GREEN)).append(item.displayName().color(NamedTextColor.LIGHT_PURPLE)).append(Component.text(" (" + ((_settings.getQuantity() * 9) - _teaminv.itemCount(player)) + "/" + _settings.getQuantity() * 9 + ")", NamedTextColor.YELLOW)))
            sendTitle(player, item.displayName().color(NamedTextColor.LIGHT_PURPLE), Component.text("registered", NamedTextColor.GREEN))
        }
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
}
