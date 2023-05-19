package de.aquafun3d.bingo.commands

import de.aquafun3d.bingo.utils.helpers.IHelpers
import de.aquafun3d.bingo.utils.helpers.ISettings
import de.aquafun3d.bingo.utils.inventories.ITeamInventories
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class SkipCommand(private val _helper: IHelpers, private val _teamInv: ITeamInventories, private val _settings: ISettings): CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if(!sender.isOp){
            _helper.send(sender as Player, Component.text("you don't have permissions!", NamedTextColor.RED))
        }
        if(_helper.isBingoRunning() && sender is Player){
            if(args.size != 1){
                _helper.send(sender, Component.text("./skip index", NamedTextColor.RED))
            }else{
                if(args[0].toInt() < _settings.getQuantity() * 9 || args[0].toInt() < 0) {
                    _teamInv.removeItemIndex(args[0].toInt(), sender)
                }else{
                    _helper.send(sender, Component.text("Index out of bounds", NamedTextColor.RED))
                }
            }
        }else {
            _helper.send(sender as Player, Component.text("Bingo hasn't started yet!", NamedTextColor.RED))
        }
        return false
    }
}