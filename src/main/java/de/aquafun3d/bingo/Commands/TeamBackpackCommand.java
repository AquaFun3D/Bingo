package de.aquafun3d.bingo.Commands;

import de.aquafun3d.bingo.utils.config.IConfig;
import de.aquafun3d.bingo.utils.helpers.IHelpers;
import de.aquafun3d.bingo.utils.teams.ITeams;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class TeamBackpackCommand implements CommandExecutor {

    private final IHelpers _helpers;
    private final IConfig _config;
    private final ITeams _teams;

    public TeamBackpackCommand(IHelpers helpers, IConfig config, ITeams teams){
        _helpers = helpers;
        _config = config;
        _teams = teams;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player player){
            Inventory inv = Bukkit.createInventory(null,9, Component.text(ChatColor.GREEN + "Team-Backpack"));
            if(_helpers.isBingoRunning()){
                if(_config.contains("teambackpack")){
                    for (int i = 0; i < 9; i++) {
                        ItemStack item;
                        if(_config.get("teambackpack." + _teams.getPlayerTeam(player).getName() + "." + i) == null){
                            item = new ItemStack(Material.AIR);
                            inv.setItem(i,item);
                        }else{
                            item = (ItemStack) _config.get("teambackpack." + _teams.getPlayerTeam(player).getName() + "." + i);
                            inv.setItem(i,item);
                        }
                    }
                }
                player.openInventory(inv);
            }else{
                _helpers.send(player, ChatColor.RED + "Bingo hasn't started yet");
            }
        }
        return false;
    }
}
