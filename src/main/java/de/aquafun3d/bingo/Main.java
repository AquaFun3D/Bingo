package de.aquafun3d.bingo;

import de.aquafun3d.bingo.utils.Utils;
import de.aquafun3d.bingo.utils.config.BingoConfig;
import de.aquafun3d.bingo.utils.config.IConfig;
import de.aquafun3d.bingo.utils.spawncage.SpawnCage;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

	@Override
	public void onEnable() {
		var utils = new Utils();
		var config = new BingoConfig("BingoConfig");
		var cage = new SpawnCage();


	}

	@Override
	public void onDisable() {
		//Spawn wieder richtig setzten
	}
}
