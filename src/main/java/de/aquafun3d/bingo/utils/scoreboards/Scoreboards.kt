package de.aquafun3d.bingo.utils.scoreboards

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.scoreboard.Criteria
import org.bukkit.scoreboard.DisplaySlot
import org.bukkit.scoreboard.Objective
import org.bukkit.scoreboard.Scoreboard
import java.util.*

class Scoreboards : IScoreboards {
    private val _playerBoards = mutableMapOf<UUID, Scoreboard>()
    private val _playerObjectives = mutableMapOf<UUID, Objective>()
    private val _defaultBoard = Bukkit.getScoreboardManager().newScoreboard

    init {
        initDefaultBoard()
    }

    override fun initPlayerScorebaord(player: Player) {
        val uuid = player.uniqueId
        if (!_playerBoards.containsKey(uuid)) {
            _playerBoards[uuid] = _defaultBoard
        }
        player.scoreboard = _playerBoards[uuid]!!
        _playerObjectives[uuid] = player.scoreboard.getObjective("bingo")!!
    }

    private fun addTeam(board: Scoreboard, teamname: String, prefix: String) {
        board.registerNewTeam(teamname)
        board.getTeam(teamname)!!.prefix(Component.text(prefix))
    }

    private fun initScore(teamname: String, value: Component, score: Int) {
        val obj = _defaultBoard.getObjective("bingo")
        val team = _defaultBoard.getTeam(teamname)
        team!!.prefix(value)
        obj!!.getScore(value.toString()).score = score
    }

    override fun updateScore(player: Player, teamname: String, value: String, score: Int, remove: Boolean) {
        val uuid = player.uniqueId
        val board = _playerBoards[uuid]
        val obj = _playerObjectives[uuid]
        board!!.resetScores(board.getTeam(teamname)!!.prefix().toString())
        if (!remove) {
            board.getTeam(teamname)!!.prefix(Component.text(value))
            obj!!.getScore(value).score = score
        }
    }

    private fun initDefaultBoard() {
        addTeam(_defaultBoard, "spec", Component.text("[Spec] ", NamedTextColor.GRAY).toString())
        addTeam(_defaultBoard, "white", Component.text("[#1] ",NamedTextColor.WHITE).toString())
        addTeam(_defaultBoard, "orange", Component.text("[#2] ", NamedTextColor.GOLD).toString())
        addTeam(_defaultBoard, "magenta", Component.text("[#3] ", NamedTextColor.LIGHT_PURPLE).toString())
        addTeam(_defaultBoard, "lightblue", Component.text("[#4] ", NamedTextColor.AQUA).toString())
        addTeam(_defaultBoard, "yellow", Component.text("[#5] ", NamedTextColor.YELLOW).toString())
        addTeam(_defaultBoard, "lime", Component.text("[#6] ",NamedTextColor.GREEN).toString())
        addTeam(_defaultBoard, "pink", Component.text("[#7] ", NamedTextColor.RED).toString())
        addTeam(_defaultBoard, "gray", Component.text("[#8] ", NamedTextColor.DARK_GRAY).toString())
        addTeam(_defaultBoard, "lightgray", Component.text("[#9] ", NamedTextColor.GRAY).toString())
        addTeam(_defaultBoard, "cyan", Component.text("[#10] ", NamedTextColor.DARK_AQUA).toString())
        addTeam(_defaultBoard, "purple", Component.text("[#11] ", NamedTextColor.DARK_PURPLE).toString())
        addTeam(_defaultBoard, "blue", Component.text("[#12] ", NamedTextColor.BLUE).toString())
        addTeam(_defaultBoard, "brown", Component.text("[#13] ", NamedTextColor.GOLD).toString())
        addTeam(_defaultBoard, "green", Component.text("[#14] ", NamedTextColor.DARK_GREEN).toString())
        addTeam(_defaultBoard, "red", Component.text("[#16] ", NamedTextColor.DARK_GRAY).toString())
        addTeam(_defaultBoard, "firstplace", Component.text("1. Place: ",NamedTextColor.GOLD).toString())
        addTeam(_defaultBoard, "place", Component.text("Your Team: ",NamedTextColor.GOLD).toString())
        addTeam(_defaultBoard, "blank", " ")
        addTeam(_defaultBoard, "itemcount", "X items left")
        _defaultBoard.registerNewObjective("bingo", Criteria.DUMMY, Component.text("bingo"))
        _defaultBoard.getObjective("bingo")!!.displayName(Component.text("Bingo",NamedTextColor.BLUE))
        if (_defaultBoard.getObjective("bingo")!!.displaySlot != DisplaySlot.SIDEBAR) {
            _defaultBoard.getObjective("bingo")!!.displaySlot = DisplaySlot.SIDEBAR
        }
        initScore("firstplace", Component.text("1. Place: ",NamedTextColor.GOLD), 13)
        initScore("blank", Component.text(" "), 11)
        initScore("blank", Component.text(" "), 14)
        initScore("place", Component.text("Your Team: ",NamedTextColor.GOLD), 12)
        initScore("itemcount", Component.text("X items left"), 10)
    }

    override fun getPlayerboards(): Map<UUID, Scoreboard>{
        return _playerBoards
    }
}