package de.aquafun3d.bingo.commands

import de.aquafun3d.bingo.utils.helpers.IHelpers
import de.aquafun3d.bingo.utils.inventories.ITeamInventories
import de.aquafun3d.bingo.utils.teams.ITeams
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class BingoCommand(private val _helper: IHelpers, private val _teams: ITeams, private val _teaminv: ITeamInventories) :
    CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if (sender is Player) {
            if (_helper.isBingoRunning()) {
                if (_teams.getPlayerTeamName(sender) != "spec") {
                    sender.openInventory(_teaminv.getIventorybyPlayer(sender))
                } else {
                    _helper.send(sender, Component.text("You are spectator",NamedTextColor.RED))
                }
            } else {
                _helper.send(sender, Component.text("Bingo hasn't started yet",NamedTextColor.RED))
            }
        }
        return false
    }
}
