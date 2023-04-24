package de.aquafun3d.bingo.commands

import de.aquafun3d.bingo.utils.countdown.ICountdown
import de.aquafun3d.bingo.utils.helpers.IHelpers
import de.aquafun3d.bingo.utils.inventories.ITeamInventories
import de.aquafun3d.bingo.utils.spawncage.ISpawnCage
import de.aquafun3d.bingo.utils.teams.ITeams
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.GameRule
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class StartCommand(private val _helper: IHelpers, private val _teamInventories: ITeamInventories, private val _teams: ITeams, private val _countdown: ICountdown, private val _cage: ISpawnCage) : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        val player = sender as Player
        if (!player.isOp) {
            _helper.send(player, Component.text("You don't have permissions to do that!", NamedTextColor.RED))
            return false
        }
        if (_helper.isBingoRunning()) {
            _helper.send(player,  Component.text("Bingo is already running", NamedTextColor.RED))
        }
        if (_helper.isConfirmed()) {
            _countdown.count(5)
            _helper.changeBingoRunning(true)
            _teamInventories.fillInventories()
            //TODO Scoreboard laden (aus Countdown)
            for (world in Bukkit.getWorlds()) {
                world.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false)
                world.setGameRule(GameRule.KEEP_INVENTORY, true)
            }
            for (p in Bukkit.getOnlinePlayers()) {
                p.inventory.clear()
                p.saturation = 20f
                p.health = 20.0
                if (_teams.getPlayerTeamName(player) == "spec") {
                    p.gameMode = GameMode.SPECTATOR
                } else {
                    p.gameMode = GameMode.SURVIVAL
                }
            }
            _cage.removeCage()
            for(p in Bukkit.getOnlinePlayers()){
                p.inventory.setItem(8, _teamInventories.getItem())
            }
        }
        return false
    }
}
