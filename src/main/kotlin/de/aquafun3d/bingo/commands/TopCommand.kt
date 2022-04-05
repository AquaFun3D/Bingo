package de.aquafun3d.bingo.commands

import de.aquafun3d.bingo.utils.Utils
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Location
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class TopCommand:CommandExecutor {
	override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
		if(sender is Player) {
			var player: Player = sender
			if(Utils.isBingoRunning){
				val world: String = player.world.name
				var x: Double = player.location.x
				var z: Double = player.location.y
				val yaw: Float = player.location.yaw
				val pitch: Float = player.location.pitch
				val y: Double = Bukkit.getWorld(world)?.getHighestBlockAt(x.toInt(),z.toInt())?.location!!.y + 1.0
				if(world == "world_nether"){
					x *= 8
					z *= 8
				}
				val location: Location = Location(Bukkit.getWorld("world"),x,y,z,yaw,pitch)
				player.teleport(location)
				Utils.send(player,ChatColor.GREEN.toString() + "Wooooosh!")
			}else{
				Utils.send(player,ChatColor.RED.toString() + "Bingo hasn't started yet!")
			}
		}
		return false
	}
}
