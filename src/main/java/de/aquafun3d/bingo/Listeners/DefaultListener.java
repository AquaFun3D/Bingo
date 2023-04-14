package de.aquafun3d.bingo.Listeners;

import de.aquafun3d.bingo.utils.helpers.IHelpers;
import de.aquafun3d.bingo.utils.inventories.IInventory;
import de.aquafun3d.bingo.utils.scoreboards.IScoreboards;
import de.aquafun3d.bingo.utils.teams.ITeams;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

public class DefaultListener implements Listener {

	private final IHelpers _helpers;
	private final IScoreboards _scoreboard;
	private final IInventory _teamselect;
	private final IInventory _settings;
	private final ITeams _teams;

	public DefaultListener(IHelpers helpers, IScoreboards scoreboards, IInventory settings, IInventory teamselect, ITeams teams){
		_helpers = helpers;
		_scoreboard = scoreboards;
		_teamselect = teamselect;
		_settings = settings;
		_teams = teams;
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		Player player = e.getPlayer();
		e.joinMessage(Component.text(_helpers.getPrefix() + ChatColor.AQUA + player.getName() + ChatColor.LIGHT_PURPLE + " has joined"));
		_scoreboard.initPlayerScorebaord(player);
		//TODO
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent e){
		Player player = e.getPlayer();
		e.quitMessage(Component.text(_helpers.getPrefix() + ChatColor.AQUA + player.getName() + ChatColor.LIGHT_PURPLE + " has left"));
	}

	@EventHandler
	public void onChat(AsyncChatEvent e){
		Player player = e.getPlayer();
		String prefix;
		if(player.isOp()){
			prefix = ChatColor.RED + player.displayName().toString() + ChatColor.DARK_GRAY + " | " + ChatColor.WHITE + e.message();
		}else{
			prefix = ChatColor.GRAY + player.displayName().toString() + ChatColor.DARK_GRAY + " | " + ChatColor.WHITE + e.message();
		}
		e.message(Component.text(_teams.getPlayerTeamPrefix(player) + prefix));
	}

	@EventHandler
	public void onItemDrop(PlayerDropItemEvent e){
		ItemStack item = e.getItemDrop().getItemStack();
		if(item.equals(_settings.getItem()) || item.equals(_teamselect.getItem())){
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onHunger(FoodLevelChangeEvent e){
		Player player = (Player) e.getEntity();
		player.setSaturation(21);
		e.setCancelled(true);
	}

	@EventHandler
	public void onRightClick(PlayerInteractEvent e){
		Player player = e.getPlayer();
		if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if (e.hasItem()) {
				if (e.getItem().equals(_teamselect.getItem())) {
					_teamselect.updateInventory(_teams.getPlayerTeamName(player),player);
				}
			}
		}
		if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if (e.hasItem()) {
				if (e.getItem().equals(_settings.getItem())) {
					_settings.updateInventory("",player);
				}
			}
		}
	}
}
