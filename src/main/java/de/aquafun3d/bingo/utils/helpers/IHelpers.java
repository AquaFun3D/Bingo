package de.aquafun3d.bingo.utils.helpers;

import org.bukkit.entity.Player;

public interface IHelpers {

	void send(Player player, String message);

	void atAll(String message);

	boolean isBingoRunning();

	void changeBingoRunning(boolean bool);
}
