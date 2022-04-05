package de.aquafun3d.bingo

import de.aquafun3d.bingo.commands.TopCommand
import de.aquafun3d.bingo.utils.BingoConfig
import de.aquafun3d.bingo.utils.SpawnCage
import de.aquafun3d.bingo.utils.TimerService
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.plugin.PluginManager
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {

	override fun onEnable() {
		Bukkit.getLogger().fine("Plugin activated")

		bingoConfig = BingoConfig()
		timer = TimerService(this)
		spawnCage = SpawnCage()

		commandRegistration()
		listenerRegistration()
	}

	override fun onDisable() {
		Bukkit.getLogger().fine("Plugin deactivated")
		val spawn = Bukkit.getWorld("world")!!.spawnLocation
		val new = Location(Bukkit.getWorld("world"),spawn.x,spawn.y - 2.0, spawn.z)
		Bukkit.getWorld("world")?.spawnLocation = new
		//challengeConfig?.set("time", timerService!!.getSec())
	}

	private fun commandRegistration() {
		getCommand("top")!!.setExecutor(TopCommand())
	}

	private fun listenerRegistration() {
		//pluginManager.registerEvents(JoinListener(), this)
	}

	//Global objects (Static one time instances)
	companion object {
		val pluginManager: PluginManager = Bukkit.getPluginManager()
		var timer: TimerService? = null
		var bingoConfig: BingoConfig? = null
		var spawnCage: SpawnCage? = null
	}
}