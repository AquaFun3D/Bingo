package de.aquafun3d.bingo.utils.scoreboards;

import org.bukkit.entity.Player;

public interface IScoreboards {
    void initPlayerScorebaord(Player player);
    void updateScore(Player player, String teamname, String value, int score, boolean remove);
}
