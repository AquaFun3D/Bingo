package de.aquafun3d.bingo.utils.scoreboards;

import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;

import java.util.HashMap;
import java.util.UUID;

public interface IScoreboards {
    void initPlayerScorebaord(Player player);
    void updateScore(Player player, String teamname, String value, int score, boolean remove);
    HashMap<UUID, Scoreboard> getPlayerBoards();
}
