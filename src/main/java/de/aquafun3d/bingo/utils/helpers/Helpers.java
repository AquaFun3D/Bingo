package de.aquafun3d.bingo.utils.helpers;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scoreboard.Scoreboard;

public class Helpers implements IHelpers{

	private boolean _isBingoRunning = false;
	private boolean _confirm = false;
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

	public boolean isConfirmed(){
		return _confirm;
	}

	public void changeConfirm(boolean bool){
		_confirm = bool;
	}

	public String getPrefix() {
		return _prefix;
	}

	public ItemStack newItem(Material mat, String name){
		ItemStack item = new ItemStack(mat);
		ItemMeta meta = item.getItemMeta();
		meta.displayName(Component.text(name));
		item.setItemMeta(meta);
		return item;
	}

}
