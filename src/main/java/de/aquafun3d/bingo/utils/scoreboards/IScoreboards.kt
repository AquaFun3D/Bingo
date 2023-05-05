package de.aquafun3d.bingo.utils.scoreboards

import org.bukkit.entity.Player
import org.bukkit.scoreboard.Scoreboard
import java.util.*

interface IScoreboards {
    fun initPlayerScorebaord(player: Player)
    fun getPlayerboards(): Map<UUID, Scoreboard>
}
