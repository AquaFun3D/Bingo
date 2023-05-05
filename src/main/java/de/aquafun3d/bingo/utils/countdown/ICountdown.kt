package de.aquafun3d.bingo.utils.countdown

import org.bukkit.entity.Player

interface ICountdown {
    fun count(i: Int, player: Player)
}
