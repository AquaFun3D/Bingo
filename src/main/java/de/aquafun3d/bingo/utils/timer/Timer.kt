package de.aquafun3d.bingo.utils.timer

import de.aquafun3d.bingo.utils.helpers.IHelpers
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Bukkit
import org.bukkit.plugin.Plugin
import java.time.LocalDateTime

class Timer(plugin: Plugin, private val _helper: IHelpers) : ITimer {
    private var _lastSec = 0
    private var _sec = 0

    init {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, {
            //TODO eigener Thread?
            if (_helper.isBingoRunning() && _lastSec != LocalDateTime.now().second) {
                _lastSec = LocalDateTime.now().second
                _sec++
            }
            if (_helper.isBingoRunning()) {
                for (player in Bukkit.getOnlinePlayers()) {
                    player.sendActionBar(Component.text(timerString,NamedTextColor.GOLD))
                }
            } else {
                for (player in Bukkit.getOnlinePlayers()) {
                    player.sendActionBar(Component.text("Timer paused",NamedTextColor.GREEN).decoration(TextDecoration.ITALIC,true))
                }
            }
        }, 5L, 5L)
    }

    override fun reset() {
        _helper.changeBingoRunning(false)
        _sec = 0
    }

    private val timerString: String
        get() {
            val hms: String = if (_sec < 3600) {
                String.format("%02d:%02d", _sec / 60, _sec % 60)
            } else if (_sec < 3600 * 24) {
                String.format("%02d:%02d:%02d", _sec / 60 / 60, _sec / 60 % 60, _sec % 60)
            } else {
                String.format("%d Days, %02d:%02d:%02d", _sec / 60 / 60 / 24, _sec / 60 / 60, _sec / 60 % 60, _sec % 60)
            }
            return hms
        }
}
