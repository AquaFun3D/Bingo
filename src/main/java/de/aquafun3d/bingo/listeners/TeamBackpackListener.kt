package de.aquafun3d.bingo.listeners

import de.aquafun3d.bingo.utils.config.IConfig
import de.aquafun3d.bingo.utils.helpers.ISettings
import de.aquafun3d.bingo.utils.teams.ITeams
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.*
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryCloseEvent
import java.io.IOException

class TeamBackpackListener(private val _config: IConfig, private val _teams: ITeams, private val _settings: ISettings) : Listener {
    @EventHandler
    @Throws(IOException::class)
    fun onInvClose(e: InventoryCloseEvent) {
        val title = e.view.title()
        val inv = e.inventory
        val player = e.player as Player
        val size = if (_settings.getTeamsize() > 2){
            9 * (_settings.getTeamsize() - 1)
        }else{
            9
        }
        if (title == Component.text("Team-Backpack", NamedTextColor.GREEN)) {
            _config.set("teambackpack." + _teams.getPlayerTeam(player)!!.name, null)
            for (i in 0 until size) {
                val item = inv.getItem(i) ?: continue
                if (item.type == Material.AIR) continue
                _config.set("teambackpack." + _teams.getPlayerTeam(player)!!.name + "." + i, item)
            }
        }
    }
}
