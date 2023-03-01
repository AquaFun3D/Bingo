package de.aquafun3d.bingo.utils.scoreboards;

import de.aquafun3d.bingo.utils.helpers.IHelpers;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Criteria;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.util.HashMap;
import java.util.UUID;

public class Scoreboards implements IScoreboards{

    private final IHelpers _helper;
    private final HashMap<UUID, Scoreboard> _playerScoreboards = new HashMap<>();
    private final HashMap<UUID, Objective> _playerObjectives = new HashMap<>();
    private final Scoreboard _defaultBoard = Bukkit.getScoreboardManager().getNewScoreboard();

    public Scoreboards(IHelpers helper){
        _helper = helper;
        initDefaultBoard();
    }

    public void initPlayerScorebaord(Player player){
        UUID uuid = player.getUniqueId();
        if(!_playerScoreboards.containsKey(uuid)){
            _playerScoreboards.put(uuid, _defaultBoard);
        }
        player.setScoreboard(_playerScoreboards.get(uuid));
        _playerObjectives.put(uuid,player.getScoreboard().getObjective("bingo"));
    }

    private void addTeam(Scoreboard board, String teamname, String prefix){
        board.registerNewTeam(teamname);
        board.getTeam(teamname).prefix(Component.text(prefix));
    }

    //TODO Items auf Scoreboard (fillScoreboards)
    //TODO update Scores

    private void initDefaultBoard() {
        addTeam(_defaultBoard, "spec", ChatColor.GRAY + "[Spec] ");
        addTeam(_defaultBoard, "white", ChatColor.WHITE + "[#1] ");
        addTeam(_defaultBoard, "orange", ChatColor.GOLD + "[#2] ");
        addTeam(_defaultBoard, "magenta", ChatColor.LIGHT_PURPLE + "[#3] ");
        addTeam(_defaultBoard, "lblue", ChatColor.AQUA + "[#4] ");
        addTeam(_defaultBoard, "yellow", ChatColor.YELLOW + "[#5] ");
        addTeam(_defaultBoard, "lime", ChatColor.GREEN + "[#6] ");
        addTeam(_defaultBoard, "pink", ChatColor.RED + "[#7] ");
        addTeam(_defaultBoard, "gray", ChatColor.DARK_GRAY + "[#8] ");
        addTeam(_defaultBoard, "lgray", ChatColor.GRAY + "[#9] ");
        addTeam(_defaultBoard, "cyan", ChatColor.DARK_AQUA + "[#10] ");
        addTeam(_defaultBoard, "purple", ChatColor.DARK_PURPLE + "[#11] ");
        addTeam(_defaultBoard, "blue", ChatColor.BLUE + "[#12] ");
        addTeam(_defaultBoard, "brown", ChatColor.GOLD + "[#13] ");
        addTeam(_defaultBoard, "green", ChatColor.DARK_GREEN + "[#14] ");
        addTeam(_defaultBoard, "red", ChatColor.DARK_RED + "[#15] ");
        addTeam(_defaultBoard, "black", ChatColor.DARK_GRAY + "[#16] ");
        addTeam(_defaultBoard,"firstplace", ChatColor.GOLD + "1. Place: ");
        addTeam(_defaultBoard,"place", ChatColor.GOLD + "Your Team: ");
        addTeam(_defaultBoard,"blank", " ");
        addTeam(_defaultBoard,"itemcount", "X items left");

        _defaultBoard.registerNewObjective("bingo", Criteria.DUMMY, Component.text("bingo"));
    }
}
