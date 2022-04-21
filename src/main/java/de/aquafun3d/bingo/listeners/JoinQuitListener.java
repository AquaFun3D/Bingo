package de.aquafun3d.bingo.listeners;

import de.aquafun3d.bingo.utils.Utils;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinQuitListener implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		Player player = e.getPlayer();
		e.joinMessage(Component.text(Utils.PREFIX + ChatColor.AQUA + player.getName() + ChatColor.LIGHT_PURPLE + " has joined"));
		//TODO Bingo settings laden
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent e){
		Player player = e.getPlayer();
		e.quitMessage(Component.text(Utils.PREFIX + ChatColor.AQUA + player.getName() + ChatColor.LIGHT_PURPLE + " has left"));
	}

	@EventHandler
	public void onItemDrop(PlayerDropItemEvent e){
		//TODO
	}

}
