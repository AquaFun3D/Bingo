package de.aquafun3d.bingo.utils;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Utils {

	private boolean _isBingoRunning = false;
	private final String _prefix = ChatColor.DARK_GRAY + "[" + ChatColor.DARK_AQUA + "Bingo" + ChatColor.DARK_GRAY + "] " + ChatColor.DARK_PURPLE;

	public void send(Player player, String message){
		player.sendMessage(Component.text(_prefix + message));
	}

	public void atAll(String message){
		Bukkit.broadcast(Component.text(_prefix + message));
	}

	public boolean isBingoRunning() {
		return _isBingoRunning;
	}

	public void changeBingoRunning(boolean bool) {
		this._isBingoRunning = bool;
	}

}
