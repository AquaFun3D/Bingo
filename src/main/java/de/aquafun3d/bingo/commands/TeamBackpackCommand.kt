package de.aquafun3d.bingo.commands

import de.aquafun3d.bingo.utils.config.IConfig
import de.aquafun3d.bingo.utils.helpers.IHelpers
import de.aquafun3d.bingo.utils.helpers.ISettings
import de.aquafun3d.bingo.utils.teams.ITeams
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class TeamBackpackCommand(private val _helpers: IHelpers, private val _config: IConfig, private val _teams: ITeams, private val _settings: ISettings) :
    CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if (sender is Player) {
            val size = if (_settings.getTeamsize() > 2){
                9 * (_settings.getTeamsize() - 1)
            }else{
                9
            }
            val inv = Bukkit.createInventory(null, size, Component.text("Team-Backpack ", NamedTextColor.GREEN).append(_teams.getPlayerTeamPrefix(sender)))
            if (_helpers.isBingoRunning()) {
                for(p in Bukkit.getOnlinePlayers()){
                    if (p.openInventory.title() == Component.text("Team-Backpack ", NamedTextColor.GREEN).append(_teams.getPlayerTeamPrefix(sender))) {
                        _helpers.send(sender, Component.text("Someone else is using the backpack right now!", NamedTextColor.RED))
                        return false
                    }
                }
                if (_config.contains("teambackpack")) {
                    for (i in 0 until size) {
                        var item: ItemStack
                        if (!_config.contains("teambackpack." + _teams.getPlayerTeam(sender)!!.name + "." + i)) {
                            item = ItemStack(Material.AIR)
                            inv.setItem(i, item)
                        } else {
                            item = _config.getAny("teambackpack." + _teams.getPlayerTeam(sender)!!.name + "." + i) as ItemStack
                            inv.setItem(i, item)
                        }
                    }
                }
                sender.openInventory(inv)
            } else {
                _helpers.send(sender, Component.text("Bingo hasn't started yet", NamedTextColor.RED))
            }
        }
        return false
    }
}
