package de.aquafun3d.bingo.Listeners;

import de.aquafun3d.bingo.utils.helpers.IHelpers;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

public class DefaultListener implements Listener {

	private IHelpers _helpers;

	public DefaultListener(IHelpers helpers){
		_helpers = helpers;
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		Player player = e.getPlayer();
		e.setJoinMessage(_helpers.getPrefix() + ChatColor.AQUA + player.getName() + ChatColor.LIGHT_PURPLE + " has joined");
		//TODO
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent event){
		Player player = event.getPlayer();
		event.setQuitMessage(_helpers.getPrefix() + ChatColor.AQUA + player.getName() + ChatColor.LIGHT_PURPLE + " has left");
	}

	//TODO onChat?

	@EventHandler
	public void onItemDrop(PlayerDropItemEvent e){
		//TODO
	}

	@EventHandler
	public void onHunger(FoodLevelChangeEvent e){
		Player player = (Player) e.getEntity();
		player.setSaturation(21);
		e.setCancelled(true);
	}
}
