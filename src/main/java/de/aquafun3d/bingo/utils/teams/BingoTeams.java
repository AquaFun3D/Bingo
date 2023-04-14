package de.aquafun3d.bingo.utils.teams;

import de.aquafun3d.bingo.utils.helpers.IHelpers;
import de.aquafun3d.bingo.utils.scoreboards.IScoreboards;
import de.aquafun3d.bingo.utils.scoreboards.Scoreboards;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.HashMap;
import java.util.UUID;

public class BingoTeams implements ITeams{

    private final IScoreboards _boards;
    private final IHelpers _helper;
    private final HashMap<UUID, Team> _teams = new HashMap<>();
    private final HashMap<String, String> _teamnames = new HashMap<>();
    private int _teamsize = 1;
    //TODO add Scores

    public BingoTeams(Scoreboards boards, IHelpers helpers){
        _boards = boards;
        _helper = helpers;
        initTeamnames();
    }

    private void addPlayerToTeam(Player player, String teamname){
        for(Scoreboard board : _boards.getPlayerBoards().values()){
            board.getTeam(teamname).addEntry(player.getName());
            //TODO muss ich das bei allen Spielern auch machen
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

    private void initTeamnames(){
        _teamnames.put("spec", ChatColor.GRAY + "[Spec] ");
        _teamnames.put("white", ChatColor.WHITE + "[#1] ");
        _teamnames.put("orange", ChatColor.GOLD + "[#2] ");
        _teamnames.put("magenta", ChatColor.LIGHT_PURPLE + "[#3] ");
        _teamnames.put("lightblue", ChatColor.AQUA + "[#4] ");
        _teamnames.put("yellow", ChatColor.YELLOW + "[#5] ");
        _teamnames.put("lime", ChatColor.GREEN + "[#6] ");
        _teamnames.put("pink", ChatColor.RED + "[#7] ");
        _teamnames.put("gray", ChatColor.DARK_GRAY + "[#8] ");
        _teamnames.put("lightgray", ChatColor.GRAY + "[#9] ");
        _teamnames.put("cyan", ChatColor.DARK_AQUA + "[#10] ");
        _teamnames.put("purple", ChatColor.DARK_PURPLE + "[#11] ");
        _teamnames.put("blue", ChatColor.BLUE + "[#12] ");
        _teamnames.put("brown", ChatColor.GOLD + "[#13] ");
        _teamnames.put("green", ChatColor.DARK_GREEN + "[#14] ");
        _teamnames.put("red", ChatColor.DARK_RED + "[#15] ");
        _teamnames.put("black", ChatColor.DARK_GRAY + "[#16] ");
    }

    public String getTeamPrefix(String name){
        return _teamnames.get(name);
    }

    public Team getPlayerTeam(Player player) {
        return _teams.get(player.getUniqueId());
    }

    public String getPlayerTeamName(Player player) {
        return getPlayerTeam(player).getName();
    }

    public String getPlayerTeamPrefix(Player player) {
        return _teams.get(player.getUniqueId()).prefix().toString();
    }


    public final HashMap<UUID,Team> getTeams(){
        return this._teams;
    }
}
