package de.aquafun3d.bingo.utils;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Utils {

	public static String PREFIX = ChatColor.DARK_GRAY + "[" + ChatColor.DARK_AQUA + "Bingo" + ChatColor.DARK_GRAY + "] " + ChatColor.DARK_PURPLE;

	public static void send(Player player, String message){
		player.sendMessage(PREFIX + message);
	}

	public static void atAll(String message){
		Bukkit.broadcast(Component.text(PREFIX + message));
	}

	public static boolean isBingoRunning = false;
}
