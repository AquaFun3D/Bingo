package de.aquafun3d.bingo.Listeners;

import de.aquafun3d.bingo.utils.helpers.IHelpers;
import de.aquafun3d.bingo.utils.scoreboards.IScoreboards;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class DefaultListener implements Listener {

	private final IHelpers _helpers;
	private final IScoreboards _scoreboard;

	public DefaultListener(IHelpers helpers, IScoreboards scoreboards){
		_helpers = helpers;
		_scoreboard = scoreboards;
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		Player player = e.getPlayer();
		e.joinMessage(Component.text(_helpers.getPrefix() + ChatColor.AQUA + player.getName() + ChatColor.LIGHT_PURPLE + " has joined"));
		_scoreboard.initPlayerScorebaord(player);
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent e){
		Player player = e.getPlayer();
		e.quitMessage(Component.text(_helpers.getPrefix() + ChatColor.AQUA + player.getName() + ChatColor.LIGHT_PURPLE + " has left"));
	}

	@EventHandler
	public void onChat(AsyncChatEvent e){
		//TODO
	}

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
