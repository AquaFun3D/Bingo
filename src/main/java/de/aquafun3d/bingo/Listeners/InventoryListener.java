package de.aquafun3d.bingo.Listeners;

import de.aquafun3d.bingo.utils.inventories.ITeamselectInventory;
import de.aquafun3d.bingo.utils.teams.ITeams;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class InventoryListener implements Listener {

    private final ITeams _teams;
    private final ITeamselectInventory _teamInv;

    public InventoryListener(ITeams teams, ITeamselectInventory teamInv){
        _teams = teams;
        _teamInv = teamInv;
    }

    @EventHandler
    public void onInvClick(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();
        ItemStack item = e.getCurrentItem();

        if(e.getView().title().equals(Component.text(ChatColor.DARK_PURPLE + "Bingo"))){
            e.setCancelled(true);
        }
        if(e.getView().title().equals(Component.text(ChatColor.DARK_PURPLE + "Settings"))) {
            e.setCancelled(true);
        }
        if(e.getView().title().equals(Component.text(ChatColor.DARK_PURPLE + "Teamselect"))){
            e.setCancelled(true);
        }
        if (item == null){
            return;
        }
        if(item.getItemMeta() == null){
            return;
        }

        //SETTINGS



        //TEAMSELCT

        if (Objects.equals(item.getItemMeta().displayName(), Component.text(ChatColor.WHITE + "Team #1"))) {
            _teams.joinTeam(player,"white");
            _teamInv.updateInventory("white",player);
        }

        if (Objects.equals(item.getItemMeta().displayName(), Component.text(ChatColor.GOLD + "Team #2"))) {
            _teams.joinTeam(player,"orange");
            _teamInv.updateInventory("orange",player);
        }

        if (Objects.equals(item.getItemMeta().displayName(), Component.text(ChatColor.LIGHT_PURPLE + "Team #3"))) {
            _teams.joinTeam(player,"magenta");
            _teamInv.updateInventory("magenta",player);
        }

        if (Objects.equals(item.getItemMeta().displayName(), Component.text(ChatColor.AQUA + "Team #4"))) {
            _teams.joinTeam(player,"lightblue");
            _teamInv.updateInventory("lightblue",player);
        }

        if (Objects.equals(item.getItemMeta().displayName(), Component.text(ChatColor.YELLOW + "Team #5"))) {
            _teams.joinTeam(player,"yellow");
            _teamInv.updateInventory("yellow",player);
        }

        if (Objects.equals(item.getItemMeta().displayName(), Component.text(ChatColor.GREEN + "Team #6"))) {
            _teams.joinTeam(player,"lime");
            _teamInv.updateInventory("lime",player);
        }

        if (Objects.equals(item.getItemMeta().displayName(), Component.text(ChatColor.RED + "Team #7"))) {
            _teams.joinTeam(player,"pink");
            _teamInv.updateInventory("pink",player);
        }

        if (Objects.equals(item.getItemMeta().displayName(), Component.text(ChatColor.DARK_GRAY + "Team #8"))) {
            _teams.joinTeam(player,"gray");
            _teamInv.updateInventory("gray",player);
        }

        if (Objects.equals(item.getItemMeta().displayName(), Component.text(ChatColor.GRAY + "Team #9"))) {
            _teams.joinTeam(player,"lightgray");
            _teamInv.updateInventory("lightgray",player);
        }

        if (Objects.equals(item.getItemMeta().displayName(), Component.text(ChatColor.DARK_AQUA + "Team #10"))) {
            _teams.joinTeam(player,"cyan");
            _teamInv.updateInventory("cyan",player);
        }

        if (Objects.equals(item.getItemMeta().displayName(), Component.text(ChatColor.DARK_PURPLE + "Team #11"))) {
            _teams.joinTeam(player,"purple");
            _teamInv.updateInventory("purple",player);
        }

        if (Objects.equals(item.getItemMeta().displayName(), Component.text(ChatColor.BLUE + "Team #12"))) {
            _teams.joinTeam(player,"blue");
            _teamInv.updateInventory("blue",player);
        }

        if (Objects.equals(item.getItemMeta().displayName(), Component.text(ChatColor.GOLD + "Team #13"))) {
            _teams.joinTeam(player,"brown");
            _teamInv.updateInventory("brown",player);
        }

        if (Objects.equals(item.getItemMeta().displayName(), Component.text(ChatColor.DARK_GREEN + "Team #14"))) {
            _teams.joinTeam(player,"green");
            _teamInv.updateInventory("green",player);
        }

        if (Objects.equals(item.getItemMeta().displayName(), Component.text(ChatColor.DARK_RED + "Team #15"))) {
            _teams.joinTeam(player,"red");
            _teamInv.updateInventory("red",player);
        }

        if (Objects.equals(item.getItemMeta().displayName(), Component.text(ChatColor.DARK_GRAY + "Team #16"))) {
            _teams.joinTeam(player,"black");
            _teamInv.updateInventory("black",player);
        }
        if(Objects.equals(item.getItemMeta().displayName(), Component.text(ChatColor.GRAY + "Spectator"))){
            _teams.joinTeam(player,"spec");
            _teamInv.updateInventory("spec",player);
        }
    }
}
