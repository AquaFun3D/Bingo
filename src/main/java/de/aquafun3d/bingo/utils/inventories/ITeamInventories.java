package de.aquafun3d.bingo.utils.inventories;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.Team;

public interface ITeamInventories {

    void fillInventories();
    void removeItem(Player player, ItemStack item);
    Inventory getIventorybyPlayer(Player player);
    Inventory getInventorybyTeam(Team team);
    ItemStack getItem();
}
