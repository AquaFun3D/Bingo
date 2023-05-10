package de.aquafun3d.bingo.commands

import de.aquafun3d.bingo.utils.helpers.IHelpers
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class SpawnCommand(private val _helper: IHelpers): CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if(_helper.isBingoRunning() && sender is Player){
            sender.teleport(Bukkit.getWorld("world")!!.spawnLocation)
            _helper.send(sender, Component.text("Wooooosh!", NamedTextColor.GREEN))
        }else {
            _helper.send(sender as Player, Component.text("Bingo hasn't started yet!", NamedTextColor.RED))
        }
        return false
    }
}