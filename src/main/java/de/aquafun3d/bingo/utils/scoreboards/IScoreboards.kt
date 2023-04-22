package de.aquafun3d.bingo.utils.scoreboards

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.TextComponent
import org.bukkit.entity.Player
import org.bukkit.scoreboard.Scoreboard
import java.util.*

interface IScoreboards {
    fun initPlayerScorebaord(player: Player)
    fun updateScore(player: Player, teamname: String, value: TextComponent, score: Int, remove: Boolean)
    fun getPlayerboards(): Map<UUID, Scoreboard>
}
