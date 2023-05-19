package de.aquafun3d.bingo.commands

import de.aquafun3d.bingo.utils.countdown.ICountdown
import de.aquafun3d.bingo.utils.helpers.IHelpers
import de.aquafun3d.bingo.utils.helpers.ISettings
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class StartCommand(private val _helper: IHelpers, private val _settings: ISettings, private val _countdown: ICountdown) : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        val player = sender as Player
        if (!player.isOp) {
            _helper.send(player, Component.text("You don't have permissions to do that!", NamedTextColor.RED))
            return false
        }
        if (_helper.isBingoRunning()) {
            _helper.send(player, Component.text("Bingo is already running!", NamedTextColor.RED))
        }else if (_settings.isConfirmed()) {
            for(p in Bukkit.getOnlinePlayers()){
                p.inventory.clear()
            }
            _countdown.count(5,player)
        }else{
            _helper.send(player, Component.text("Please confirm!", NamedTextColor.RED))
        }
        return false
    }
}
