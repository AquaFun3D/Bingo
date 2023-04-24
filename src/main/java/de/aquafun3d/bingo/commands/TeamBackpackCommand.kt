package de.aquafun3d.bingo.commands

import de.aquafun3d.bingo.utils.config.IConfig
import de.aquafun3d.bingo.utils.helpers.IHelpers
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

class TeamBackpackCommand(private val _helpers: IHelpers, private val _config: IConfig, private val _teams: ITeams) :
    CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if (sender is Player) {
            val inv = Bukkit.createInventory(null, 9, Component.text("Team-Backpack", NamedTextColor.GREEN))
            if (_helpers.isBingoRunning()) {
                if (_config.contains("teambackpack")) {
                    for (i in 0..8) {
                        var item: ItemStack
                        if (_config["teambackpack." + _teams.getPlayerTeam(sender)!!.prefix().toString() + "." + i] == null) {
                            item = ItemStack(Material.AIR)
                            inv.setItem(i, item)
                        } else {
                            item = _config["teambackpack." + _teams.getPlayerTeam(sender)!!.prefix().toString() + "." + i] as ItemStack
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
