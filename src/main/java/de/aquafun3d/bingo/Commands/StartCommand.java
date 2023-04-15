package de.aquafun3d.bingo.Commands;

import de.aquafun3d.bingo.utils.helpers.IHelpers;
import de.aquafun3d.bingo.utils.inventories.ITeamInventories;
import de.aquafun3d.bingo.utils.teams.ITeams;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class StartCommand implements CommandExecutor {

    private final IHelpers _helper;
    private final ITeamInventories _teamInventories;
    private final ITeams _teams;

    public StartCommand(IHelpers helper, ITeamInventories teamInventories, ITeams teams){
        _helper = helper;
        _teamInventories = teamInventories;
        _teams = teams;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = (Player) sender;
        if(!player.isOp()){
            _helper.send(player, ChatColor.RED + "You don't have permissions to do that!");
            return false;
        }
        if(_helper.isBingoRunning()){
            _helper.send(player, ChatColor.RED + "Bingo is already running");
        }
        if(_helper.isConfirmed()){
            //TODO Countdown
            _helper.changeBingoRunning(true);
            _teamInventories.fillInventories();
            for(World world : Bukkit.getWorlds()){
                world.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);
                world.setGameRule(GameRule.KEEP_INVENTORY,true);
            }
            for (Player p : Bukkit.getOnlinePlayers()){
                p.getInventory().clear();
                p.setSaturation(20);
                p.setHealth(20);
                if(_teams.getPlayerTeamName(player).equals("spec")) {
                    p.setGameMode(GameMode.SPECTATOR);
                }else {
                    p.setGameMode(GameMode.SURVIVAL);
                }
            }
            player.getInventory().setItem(8, _teamInventories.getItem());
        }
        return false;
    }
}
