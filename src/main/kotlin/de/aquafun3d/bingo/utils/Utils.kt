package de.aquafun3d.bingo.utils

import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.Player

object Utils {

	//Challenge Prefix
	val PREFIX: String = "" + ChatColor.DARK_GRAY + "[" + ChatColor.DARK_AQUA + "Bingo" + ChatColor.DARK_GRAY + "] " + ChatColor.DARK_PURPLE

	//Sends message to player with Challenge prefix
	fun send(player: Player, message: String){
		player.sendMessage("$PREFIX$message")
	}

	//Sends message to all players with Challenge prefix
	fun atAll(message: String){
		Bukkit.broadcast(Component.text("$PREFIX$message"))
	}
}