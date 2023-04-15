package de.aquafun3d.bingo.Commands;

import de.aquafun3d.bingo.utils.helpers.IHelpers;
import de.aquafun3d.bingo.utils.inventories.ITeamInventories;
import de.aquafun3d.bingo.utils.teams.ITeams;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class BingoCommand implements CommandExecutor {

    private final IHelpers _helper;
    private final ITeams _teams;
    private final ITeamInventories _teaminv;

    public BingoCommand(IHelpers helper, ITeams teams, ITeamInventories teaminv){
        _helper = helper;
        _teams = teams;
        _teaminv = teaminv;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player player){
            if(_helper.isBingoRunning()){
                if(!_teams.getPlayerTeamName(player).equals("spec")) {
                    player.openInventory(_teaminv.getIventorybyPlayer(player));
                }else {
                    _helper.send(player,ChatColor.RED + "You are spectator");
                }
            }else{
                _helper.send(player,ChatColor.RED + "Bingo hasn't started yet");
            }
        }
        return false;
    }
}
