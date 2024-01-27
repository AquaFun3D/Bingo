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
import de.aquafun3d.bingo.utils.tasks.IBingoTaskManager
import de.aquafun3d.bingo.utils.tasks.ItemTaskManager
import de.aquafun3d.bingo.utils.tasks.MobTaskManager
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
        Bukkit.getWorld("world")!!.difficulty = Difficulty.PEACEFUL
        val helpers = Helpers()
        val settings = Settings()
        val config = BingoConfig()
        val scoreboards = Scoreboards()
        val cage = SpawnCage()
        val timer = Timer(this, helpers)
        val teams = BingoTeams(scoreboards, helpers, settings)
        val teamSelectInv = TeamselectTeamselectInventory(helpers,settings)
        val settingsInv = SettingsInventory(helpers,settings)
        val itemTaskManager = ItemTaskManager(settings)
        val mobTaskManager = MobTaskManager(settings)
        val bingoTaskManager = BingoTaskManager(itemTaskManager, settings, mobTaskManager)
        val teamInventories = TeamInventories(teams, helpers, bingoTaskManager, settings, timer, itemTaskManager)
        val countdown = Countdown(this, cage, helpers, teamInventories, teams, settings)
        commandRegistration(helpers, config, teams, teamInventories, countdown, settings)
        listenerRegistration(helpers, scoreboards, teams, config, teamSelectInv, settingsInv, teamInventories, settings, timer, bingoTaskManager)
    }

    override fun onDisable() {
        val spawn = Bukkit.getWorld("world")!!.spawnLocation
        val location = Location(Bukkit.getWorld("world"), spawn.x, spawn.y - 2.0, spawn.z)
        Bukkit.getWorld("world")!!.setSpawnLocation(location)
    }

    private fun commandRegistration(helpers: IHelpers, config: IConfig, teams: ITeams, teamInv: ITeamInventories, countdown: ICountdown, settings: ISettings) {
        getCommand("top")!!.setExecutor(TopCommand(helpers))
        getCommand("teambackpack")!!.setExecutor(TeamBackpackCommand(helpers, config, teams, settings))
        getCommand("bingo")!!.setExecutor(BingoCommand(helpers, teamInv))
        getCommand("start")!!.setExecutor(StartCommand(helpers, settings, countdown))
        getCommand("spawn")!!.setExecutor(SpawnCommand(helpers))
        getCommand("skip")!!.setExecutor(SkipCommand(helpers, teamInv, settings))
    }

    private fun listenerRegistration(helpers: IHelpers, scoreboards: IScoreboards, teams: ITeams, config: IConfig, teamSelectInv: ITeamselectInventory, settingsInv: ISettingsInventory, teamInv: ITeamInventories, settings: ISettings, timer: ITimer, manager: IBingoTaskManager) {
        val pluginManager = Bukkit.getPluginManager()
        pluginManager.registerEvents(DefaultListener(helpers, scoreboards, settingsInv, teamSelectInv, teams, teamInv, settings), this)
        pluginManager.registerEvents(TeamBackpackListener(config, teams, settings), this)
        pluginManager.registerEvents(InventoryListener(teams,helpers, settings, settingsInv, teamSelectInv), this)
        pluginManager.registerEvents(BingoListener(helpers, teamInv, teams, settings, timer, manager), this)
    }
}
