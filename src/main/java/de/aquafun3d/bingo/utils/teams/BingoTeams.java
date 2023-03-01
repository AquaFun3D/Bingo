package de.aquafun3d.bingo.utils.teams;

import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

import java.util.HashMap;
import java.util.UUID;

public class BingoTeams implements ITeams{

    private final HashMap<UUID, Team> _teams = new HashMap<>();

    public void joinTeam(Player player, String teamname) {

    }

    public Team getPlayerTeam(Player player) {
        return null;
    }

    public String getPlayerTeamName(Player player) {
        return null;
    }
}
