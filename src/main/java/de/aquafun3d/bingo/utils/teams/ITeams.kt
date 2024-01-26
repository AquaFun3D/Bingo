package de.aquafun3d.bingo.utils.teams

import net.kyori.adventure.text.Component
import org.bukkit.entity.Player
import org.bukkit.scoreboard.Team
import java.util.*

interface ITeams {
    fun joinTeam(player: Player, teamname: String)
    fun getPlayerTeam(player: Player): Team?
    fun getPlayerTeamName(player: Player): String
    fun getPlayerTeamPrefix(player: Player): Component
    fun getTeamPrefix(name: String): Component
    fun getTeams(): Map<UUID, Team>
    fun updateTeamSuffix(player: Player, itemCount: Int)
    fun getSuffix(player: Player): Component
}
