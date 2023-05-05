package de.aquafun3d.bingo.utils.countdown

import de.aquafun3d.bingo.utils.helpers.IHelpers
import de.aquafun3d.bingo.utils.inventories.ITeamInventories
import de.aquafun3d.bingo.utils.spawncage.ISpawnCage
import net.kyori.adventure.audience.Audience
import net.kyori.adventure.key.Key
import net.kyori.adventure.sound.Sound
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.title.Title
import org.bukkit.Bukkit
import org.bukkit.Difficulty
import org.bukkit.plugin.Plugin

class Countdown(private val _plugin: Plugin, private val _cage: ISpawnCage, private val _helper: IHelpers, private val _teamInv: ITeamInventories) : ICountdown {
    private var _taskid = 0
    val audi = Audience.audience(Bukkit.getOnlinePlayers())
    override fun count(i: Int) {
        _taskid = Bukkit.getScheduler().scheduleSyncRepeatingTask(_plugin, object : Runnable {
            var time = i + 1
            override fun run() {
                time--
                val mainTitle: Component = Component.text(time.toString(), NamedTextColor.GREEN)
                val subTitle: Component = Component.text("seconds until Bingo starts", NamedTextColor.GRAY)
                if (time != 0) {
                    audi.showTitle(Title.title(mainTitle, subTitle))
                } else {
                    audi.showTitle(Title.title(mainTitle, subTitle))
                    audi.showTitle(Title.title(Component.text("Bingo", NamedTextColor.GREEN), Component.text("has started",NamedTextColor.GRAY)))
                    val sound = Sound.sound(Key.key("entity.player.levelup"), Sound.Source.PLAYER, 1f, 1f)
                    audi.playSound(sound)
                    _helper.changeBingoRunning(true)
                    _teamInv.fillInventories()
                    Bukkit.getWorld("world")!!.difficulty = Difficulty.EASY
                    _cage.removeCage()
                    Bukkit.getScheduler().cancelTask(_taskid)
                }
            }
        }, 0, 20)
    }
}
