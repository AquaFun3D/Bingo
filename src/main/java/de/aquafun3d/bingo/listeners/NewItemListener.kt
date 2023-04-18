package de.aquafun3d.bingo.listeners

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.*
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityPickupItemEvent
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack

class NewItemListener : Listener {
    @EventHandler
    fun onInvClick(e: InventoryClickEvent) {
        val player = e.whoClicked as Player
        val item = e.currentItem
        if (e.view.title() == Component.text("Teamselect", NamedTextColor.DARK_PURPLE)) {
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
        //checkItem(player, item)
    }

    @EventHandler
    fun onPickUp(e: EntityPickupItemEvent) {
        if (e.entity is Player){
            val player = (e.entity as Player)
            val item = e.item.itemStack
            //checkItem(player, item)
        }
    }
}
