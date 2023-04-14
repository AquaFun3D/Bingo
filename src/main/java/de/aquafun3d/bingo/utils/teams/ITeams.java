package de.aquafun3d.bingo.utils.teams;

import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

import java.util.HashMap;
import java.util.UUID;

public interface ITeams {
    void joinTeam(Player player, String teamname);
    Team getPlayerTeam(Player player);
    String getPlayerTeamName(Player player);
    String getPlayerTeamPrefix(Player player);
    HashMap<UUID,Team> getTeams();
    String getTeamPrefix(String name);
}
