package de.aquafun3d.bingo.commands

import de.aquafun3d.bingo.utils.helpers.IHelpers
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.util.*

class TopCommand(private val _helpers: IHelpers) : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if (sender is Player) {
            if (_helpers.isBingoRunning()) {
                val world: String = sender.world.name
                var x: Double = sender.location.x
                var z: Double = sender.location.z
                val yaw: Float = sender.location.yaw
                val pitch: Float = sender.location.pitch
                val y = Objects.requireNonNull(Bukkit.getWorld(world))!!.getHighestBlockYAt(x.toInt(), z.toInt()) + 1
                if (world == "world_nether") {
                    x *= 8.0
                    z *= 8.0
                }
                val location = Location(Bukkit.getWorld("world"), x, y.toDouble(), z, yaw, pitch)
                sender.teleport(location)
                _helpers.send(sender, Component.text("Wooooosh!", NamedTextColor.GREEN))
            } else {
                _helpers.send(sender, Component.text("Bingo hasn't started yet!", NamedTextColor.RED))
            }
        }
        return false
    }
}