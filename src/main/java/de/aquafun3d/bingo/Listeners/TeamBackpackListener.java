package de.aquafun3d.bingo.Listeners;

import de.aquafun3d.bingo.utils.config.IConfig;
import de.aquafun3d.bingo.utils.teams.ITeams;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;

public class TeamBackpackListener implements Listener {

    private IConfig _config;
    private ITeams _teams;

    public TeamBackpackListener(IConfig config, ITeams teams){
        _config = config;
        _teams = teams;
    }

    @EventHandler
    public void onInvClose(InventoryCloseEvent e) throws IOException {
        Component title = e.getView().title();
        Inventory inv = e.getInventory();
        Player player = (Player) e.getPlayer();
        if(title.equals(Component.text(ChatColor.GREEN + "Team-Backpack"))){
            _config.set("teambackpack." + _teams.getPlayerTeam(player).getName(), null);
            for(int i = 0; i < 9; i++){
                ItemStack item = inv.getItem(i);
                if(item == null) continue;
                if(item.getType() == Material.AIR) continue;
                _config.set("teambackpack." + _teams.getPlayerTeam(player).getName() + "." + i,item);
            }
        }
    }
}
