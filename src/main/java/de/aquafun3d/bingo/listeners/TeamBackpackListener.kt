package de.aquafun3d.bingo.listeners

import de.aquafun3d.bingo.utils.config.IConfig
import de.aquafun3d.bingo.utils.teams.ITeams
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.*
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryCloseEvent
import java.io.IOException

class TeamBackpackListener(private val _config: IConfig, private val _teams: ITeams) : Listener {
    @EventHandler
    @Throws(IOException::class)
    fun onInvClose(e: InventoryCloseEvent) {
        val title = e.view.title()
        val inv = e.inventory
        val player = e.player as Player
        if (title == Component.text("Team-Backpack", NamedTextColor.GREEN)) {
            _config["teambackpack." + _teams.getPlayerTeam(player)!!.prefix().toString()] = null
            for (i in 0..8) {
                val item = inv.getItem(i) ?: continue
                if (item.type == Material.AIR) continue
                _config["teambackpack." + _teams.getPlayerTeam(player)!!.prefix().toString() + "." + i] = item
            }
        }
    }
}
