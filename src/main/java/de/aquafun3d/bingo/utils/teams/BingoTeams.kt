package de.aquafun3d.bingo.utils.teams

import de.aquafun3d.bingo.utils.helpers.IHelpers
import de.aquafun3d.bingo.utils.helpers.ISettings
import de.aquafun3d.bingo.utils.scoreboards.IScoreboards
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.entity.Player
import org.bukkit.scoreboard.Team
import java.util.*

class BingoTeams(private val _boards: IScoreboards, private val _helper: IHelpers, private val _settings: ISettings) : ITeams {
    private val _teams = mutableMapOf<UUID, Team>()
    private val _teamnames = mutableMapOf<String, Component>()

    init {
        initTeamnames()
    }

    private fun addPlayerToTeam(player: Player, teamname: String) {
        for (board in _boards.getPlayerboards().values) {
            board.getTeam(teamname)!!.addEntry(player.name)
        }
        _teams[player.uniqueId] = _boards.getPlayerboards()[player.uniqueId]!!.getTeam(teamname)!!
    }

    override fun joinTeam(player: Player, teamname: String) {
        val uuid = player.uniqueId
        val team = _boards.getPlayerboards()[uuid]!!.getTeam(teamname)
        if (teamname == "spec") {
            addPlayerToTeam(player, teamname)
            return
        }
        if (team!!.size >= _settings.getTeamsize()) {
            _helper.send(player, Component.text("Team is already full", NamedTextColor.RED))
        } else {
            val playerTeam = _boards.getPlayerboards()[uuid]!!.getEntryTeam(player.name)
            if (playerTeam != null && playerTeam.name != teamname) {
                _teams.remove(uuid)
                _boards.getPlayerboards()[uuid]!!.getTeam(teamname)!!.removeEntry(playerTeam.name)
            }
            addPlayerToTeam(player, teamname)
        }
    }

    private fun initTeamnames() {
        _teamnames["spec"] = Component.text("[Spec] ",NamedTextColor.GRAY)
        _teamnames["white"] = Component.text("[#1] ",NamedTextColor.WHITE)
        _teamnames["orange"] = Component.text("[#2] ", NamedTextColor.GOLD)
        _teamnames["magenta"] = Component.text("[#3] ", NamedTextColor.LIGHT_PURPLE)
        _teamnames["lightblue"] = Component.text("[#4] ", NamedTextColor.AQUA)
        _teamnames["yellow"] = Component.text("[#5] ", NamedTextColor.YELLOW)
        _teamnames["lime"] = Component.text("[#6] ",NamedTextColor.GREEN)
        _teamnames["pink"] = Component.text("[#7] ", NamedTextColor.RED)
        _teamnames["gray"] = Component.text("[#8] ", NamedTextColor.DARK_GRAY)
        _teamnames["lightgray"] = Component.text("[#9] ", NamedTextColor.GRAY)
        _teamnames["cyan"] = Component.text("[#10] ", NamedTextColor.DARK_AQUA)
        _teamnames["purple"] = Component.text("[#11] ", NamedTextColor.DARK_PURPLE)
        _teamnames["blue"] = Component.text("[#12] ", NamedTextColor.BLUE)
        _teamnames["brown"] = Component.text("[#13] ", NamedTextColor.GOLD)
        _teamnames["green"] = Component.text("[#14] ", NamedTextColor.DARK_GREEN)
        _teamnames["red"] = Component.text("[#15] ", NamedTextColor.DARK_RED)
        _teamnames["black"] = Component.text("[#16] ", NamedTextColor.DARK_GRAY)
    }

    override fun getTeamPrefix(name: String): Component {
        return _teamnames[name]!!
    }

    override fun getPlayerTeam(player: Player): Team? {
        return _teams[player.uniqueId]
    }

    override fun getPlayerTeamName(player: Player): String {
        return getPlayerTeam(player)!!.name
    }

    override fun getPlayerTeamPrefix(player: Player): Component {
        return _teams[player.uniqueId]!!.prefix()
    }

    override fun getTeams(): Map<UUID, Team>{
        return _teams
    }

    override fun updateTeamSuffix(player: Player, itemCount: Int){
        if(_teams[player.uniqueId]!!.name == "spec") return
        _teams[player.uniqueId]!!.suffix(Component.text(" (" + ((_settings.getQuantity() * 9) - itemCount) + "/" + _settings.getQuantity() * 9 + ")", NamedTextColor.YELLOW))
    }
}
