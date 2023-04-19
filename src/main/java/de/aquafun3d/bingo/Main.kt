package de.aquafun3d.bingo

import de.aquafun3d.bingo.commands.BingoCommand
import de.aquafun3d.bingo.commands.StartCommand
import de.aquafun3d.bingo.commands.TeamBackpackCommand
import de.aquafun3d.bingo.commands.TopCommand
import de.aquafun3d.bingo.listeners.DefaultListener
import de.aquafun3d.bingo.listeners.InventoryListener
import de.aquafun3d.bingo.listeners.TeamBackpackListener
import de.aquafun3d.bingo.utils.config.BingoConfig
import de.aquafun3d.bingo.utils.config.IConfig
import de.aquafun3d.bingo.utils.countdown.Countdown
import de.aquafun3d.bingo.utils.countdown.ICountdown
import de.aquafun3d.bingo.utils.helpers.Helpers
import de.aquafun3d.bingo.utils.helpers.IHelpers
import de.aquafun3d.bingo.utils.inventories.*
import de.aquafun3d.bingo.utils.scoreboards.IScoreboards
import de.aquafun3d.bingo.utils.scoreboards.Scoreboards
import de.aquafun3d.bingo.utils.spawncage.ISpawnCage
import de.aquafun3d.bingo.utils.spawncage.SpawnCage
import de.aquafun3d.bingo.utils.teams.BingoTeams
import de.aquafun3d.bingo.utils.teams.ITeams
import de.aquafun3d.bingo.utils.timer.Timer
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {
    override fun onEnable() {
        Bukkit.getLogger().fine("Plugin activated")
        val helpers = Helpers()
        val config = BingoConfig("BingoConfig")
        val scoreboards = Scoreboards()
        val teams = BingoTeams(scoreboards, helpers)
        val teamSelectInv = TeamselectInventory(helpers)
        val settingsInv = SettingsInventory(helpers)
        val teamInventories = TeamInventories(teams, helpers)
        val countdown = Countdown(this)
        val cage = SpawnCage()
        val timer = Timer(this, helpers)
        commandRegistration(helpers, config, teams, teamInventories, countdown, cage)
        listenerRegistration(helpers, scoreboards, teams, teamSelectInv, config, teamSelectInv, settingsInv, teamInventories)
    }

    override fun onDisable() {
        Bukkit.getLogger().fine("Plugin deactivated")
        val spawn = Bukkit.getWorld("world")!!.spawnLocation
        val location = Location(Bukkit.getWorld("world"), spawn.x, spawn.y - 2.0, spawn.z)
        Bukkit.getWorld("world")!!.setSpawnLocation(location)
    }

    private fun commandRegistration(helpers: IHelpers, config: IConfig, teams: ITeams, teaminv: ITeamInventories, countdown: ICountdown, cage: ISpawnCage) {
        getCommand("top")!!.setExecutor(TopCommand(helpers))
        getCommand("teambackpack")!!.setExecutor(TeamBackpackCommand(helpers, config, teams))
        getCommand("bingo")!!.setExecutor(BingoCommand(helpers, teams, teaminv))
        getCommand("start")!!.setExecutor(StartCommand(helpers, teaminv, teams, countdown, cage))
    }

    private fun listenerRegistration(helpers: IHelpers, scoreboards: IScoreboards, teams: ITeams, teamSelectInv: ITeamselectInventory, config: IConfig, teamselect: ITeamselectInventory, settings: ISettingsInventory, teaminv: ITeamInventories) {
        val pluginManager = Bukkit.getPluginManager()
        pluginManager.registerEvents(DefaultListener(helpers, scoreboards, settings, teamselect, teams, teaminv), this)
        pluginManager.registerEvents(TeamBackpackListener(config, teams), this)
        pluginManager.registerEvents(InventoryListener(teams, teamSelectInv), this)
    }
}