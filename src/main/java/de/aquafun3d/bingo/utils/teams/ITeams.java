package de.aquafun3d.bingo.utils.teams;

import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

public interface ITeams {
    void joinTeam(Player player, String teamname);
    Team getPlayerTeam(Player player);
    String getPlayerTeamName(Player player);
}
