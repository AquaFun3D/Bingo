package de.aquafun3d.bingo

import de.aquafun3d.bingo.utils.TimerService
import org.bukkit.Bukkit
import org.bukkit.plugin.PluginManager
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {
	override fun onEnable() {
		Bukkit.getLogger().fine("Plugin activated")

		timer = TimerService(this)

		commandRegistration()
		listenerRegistration()
	}

	override fun onDisable() {
		Bukkit.getLogger().fine("Plugin deactivated")
		//challengeConfig?.set("time", timerService!!.getSec())
	}

	private fun commandRegistration() {
		//getCommand("timer")!!.setExecutor(TimerCommand())
	}

	private fun listenerRegistration() {
		//pluginManager.registerEvents(JoinListener(), this)
	}

	//Global objects (Static one time instances)
	companion object {
		val pluginManager: PluginManager = Bukkit.getPluginManager()
		var timer: TimerService? = null
	}
}