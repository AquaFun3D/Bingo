package de.aquafun3d.bingo.utils.helpers;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface IHelpers {

	void send(Player player, String message);

	void atAll(String message);

	boolean isBingoRunning();

	void changeBingoRunning(boolean bool);

	String getPrefix();

	ItemStack newItem(Material mat, String name);

	boolean isConfirmed();

	void changeConfirm(boolean bool);
}
