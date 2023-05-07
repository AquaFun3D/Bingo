package de.aquafun3d.bingo

import de.aquafun3d.bingo.commands.*
import de.aquafun3d.bingo.listeners.BingoListener
import de.aquafun3d.bingo.listeners.DefaultListener
import de.aquafun3d.bingo.listeners.InventoryListener
import de.aquafun3d.bingo.listeners.TeamBackpackListener
import de.aquafun3d.bingo.utils.config.BingoConfig
import de.aquafun3d.bingo.utils.config.IConfig
import de.aquafun3d.bingo.utils.countdown.Countdown
import de.aquafun3d.bingo.utils.countdown.ICountdown
import de.aquafun3d.bingo.utils.helpers.Helpers
import de.aquafun3d.bingo.utils.helpers.IHelpers
import de.aquafun3d.bingo.utils.helpers.ISettings
import de.aquafun3d.bingo.utils.helpers.Settings
import de.aquafun3d.bingo.utils.inventories.*
import de.aquafun3d.bingo.utils.scoreboards.IScoreboards
import de.aquafun3d.bingo.utils.scoreboards.Scoreboards
import de.aquafun3d.bingo.utils.spawncage.SpawnCage
import de.aquafun3d.bingo.utils.tasks.BingoTaskManager
import de.aquafun3d.bingo.utils.tasks.ItemTaskManager
import de.aquafun3d.bingo.utils.teams.BingoTeams
import de.aquafun3d.bingo.utils.teams.ITeams
import de.aquafun3d.bingo.utils.timer.ITimer
import de.aquafun3d.bingo.utils.timer.Timer
import org.bukkit.Bukkit
import org.bukkit.Difficulty
import org.bukkit.Location
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {
    override fun onEnable() {
        Bukkit.getLogger().fine("Plugin activated")
        Bukkit.getWorld("world")!!.difficulty = Difficulty.PEACEFUL
        val helpers = Helpers()
        val settings = Settings()
        val config = BingoConfig("BingoConfig")
        val scoreboards = Scoreboards()
        val teams = BingoTeams(scoreboards, helpers, settings)
        val teamSelectInv = TeamselectTeamselectInventory(helpers)
        val settingsInv = SettingsInventory(helpers,settings)
        val itemTaskManager = ItemTaskManager(settings)
        val bingoTaskManager = BingoTaskManager(itemTaskManager, settings)
        val teamInventories = TeamInventories(teams, helpers, bingoTaskManager, settings)
        val cage = SpawnCage()
        val countdown = Countdown(this, cage, helpers, teamInventories, teams)
        val timer = Timer(this, helpers)
        commandRegistration(helpers, config, teams, teamInventories, countdown, settings)
        listenerRegistration(helpers, scoreboards, teams, teamSelectInv, config, teamSelectInv, settingsInv, teamInventories, settings, timer)
    }

    override fun onDisable() {
        Bukkit.getLogger().fine("Plugin deactivated")
        val spawn = Bukkit.getWorld("world")!!.spawnLocation
        val location = Location(Bukkit.getWorld("world"), spawn.x, spawn.y - 2.0, spawn.z)
        Bukkit.getWorld("world")!!.setSpawnLocation(location)
    }

    private fun commandRegistration(helpers: IHelpers, config: IConfig, teams: ITeams, teaminv: ITeamInventories, countdown: ICountdown, settings: ISettings) {
        getCommand("top")!!.setExecutor(TopCommand(helpers))
        getCommand("teambackpack")!!.setExecutor(TeamBackpackCommand(helpers, config, teams, settings))
        getCommand("bingo")!!.setExecutor(BingoCommand(helpers, teams, teaminv))
        getCommand("start")!!.setExecutor(StartCommand(helpers, settings, teaminv, teams, countdown))
        getCommand("spawn")!!.setExecutor(SpawnCommand(helpers))
    }

    private fun listenerRegistration(helpers: IHelpers, scoreboards: IScoreboards, teams: ITeams, teamSelectInv: ITeamselectInventory, config: IConfig, teamselectInv: ITeamselectInventory, settingsInv: ISettingsInventory, teamInv: ITeamInventories, settings: ISettings, timer: ITimer) {
        val pluginManager = Bukkit.getPluginManager()
        pluginManager.registerEvents(DefaultListener(helpers, scoreboards, settingsInv, teamselectInv, teams, teamInv, settings), this)
        pluginManager.registerEvents(TeamBackpackListener(config, teams, settings), this)
        pluginManager.registerEvents(InventoryListener(teams,helpers, teamSelectInv, settings, settingsInv, teamselectInv), this)
        pluginManager.registerEvents(BingoListener(helpers, teamInv, teams, settings, timer), this)
    }
}
