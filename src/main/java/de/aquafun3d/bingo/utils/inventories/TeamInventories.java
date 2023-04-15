package de.aquafun3d.bingo.utils.inventories;

import de.aquafun3d.bingo.utils.helpers.IHelpers;
import de.aquafun3d.bingo.utils.teams.ITeams;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.Team;

import java.util.HashMap;

public class TeamInventories implements ITeamInventories{

    private final HashMap<Team, Inventory> _inventories = new HashMap<>();
    private final IHelpers _helper;
    private final ITeams _teams;
    private final ItemStack _item;

    public TeamInventories(ITeams teams, IHelpers helper){
        _teams = teams;
        _helper = helper;
        _item = _helper.newItem(Material.BUNDLE,ChatColor.DARK_AQUA + "Bingo");
    }

    public void fillInventories(){
        for(Team t : _teams.getTeams().values()){
            Inventory inv = Bukkit.createInventory(null,9 * 1, ChatColor.DARK_PURPLE + "Bingo"); //TODO * Quatity
            //TODO generate Bingo Items
            //inv.setContents(null); TODO
            if(!_inventories.containsKey(t)){
                _inventories.put(t,inv);
            }
        }
    }

    public void removeItem(Player player, ItemStack item){
        _inventories.get(_teams.getPlayerTeam(player)).removeItem(item);
    }

    public Inventory getIventorybyPlayer(Player player){
        return null;
    }

    public Inventory getInventorybyTeam(Team team){
        return null;
    }

    public  ItemStack getItem(){
        return _item;
    }
}
