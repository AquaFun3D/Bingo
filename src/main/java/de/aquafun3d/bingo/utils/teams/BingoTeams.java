package de.aquafun3d.bingo.utils.teams;

import de.aquafun3d.bingo.utils.helpers.IHelpers;
import de.aquafun3d.bingo.utils.scoreboards.IScoreboards;
import de.aquafun3d.bingo.utils.scoreboards.Scoreboards;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.HashMap;
import java.util.UUID;

public class BingoTeams implements ITeams{

    private final IScoreboards _boards;
    private final IHelpers _helper;
    private final HashMap<UUID, Team> _teams = new HashMap<>();
    private final HashMap<Team, Inventory> _inventories = new HashMap<>();
    private int _teamsize = 1;
    //TODO add Scores

    public BingoTeams(Scoreboards boards, IHelpers helpers){
        _boards = boards;
        _helper = helpers;
    }

    private void addPlayerToTeam(Player player, String teamname){
        for(Scoreboard board : _boards.getPlayerBoards().values()){
            board.getTeam(teamname).addEntry(player.getName());
        }
        _teams.put(player.getUniqueId(), _boards.getPlayerBoards().get(player.getUniqueId()).getTeam(teamname));
    }

    public void joinTeam(Player player, String teamname) {
        UUID uuid = player.getUniqueId();
        Team team = _boards.getPlayerBoards().get(uuid).getTeam(teamname);
        if(teamname.equals("spec")){
            addPlayerToTeam(player,teamname);
        }
        if(team.getSize() >= _teamsize){
            _helper.send(player, ChatColor.RED + "Team is already full");
        }else{
            Team playerTeam = _boards.getPlayerBoards().get(uuid).getEntryTeam(player.getName());
            if(playerTeam != null && !playerTeam.getName().equals(teamname)){
                _teams.remove(uuid);
                _boards.getPlayerBoards().get(uuid).getTeam(teamname).removeEntry(playerTeam.getName());
            }
            addPlayerToTeam(player,teamname);
        }
    }

    public Team getPlayerTeam(Player player) {
        return null;
    }

    public String getPlayerTeamName(Player player) {
        return null;
    }
}
