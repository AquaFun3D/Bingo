package de.aquafun3d.bingo;

import de.aquafun3d.bingo.Commands.TopCommand;
import de.aquafun3d.bingo.Listeners.DefaultListener;
import de.aquafun3d.bingo.utils.helpers.Helpers;
import de.aquafun3d.bingo.utils.config.BingoConfig;
import de.aquafun3d.bingo.utils.helpers.IHelpers;
import de.aquafun3d.bingo.utils.spawncage.SpawnCage;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

	@Override
	public void onEnable() {
		Bukkit.getLogger().fine("Plugin activated");
		var helpers = new Helpers();
		var config = new BingoConfig("BingoConfig");
		var cage = new SpawnCage();

		commandRegistration(helpers);
		listenerRegistration(helpers);

	}

	@Override
	public void onDisable() {
		Bukkit.getLogger().fine("Plugin deactivated");
		var spawn = Bukkit.getWorld("world").getSpawnLocation();
		var location = new Location(Bukkit.getWorld("world"), spawn.getX(), spawn.getY() - 2.0, spawn.getZ());
		Bukkit.getWorld("world").setSpawnLocation(location);
	}

	private void commandRegistration(IHelpers helpers){
		getCommand("top").setExecutor(new TopCommand(helpers));
	}

	private void listenerRegistration(IHelpers helpers) {
		PluginManager pluginManager = Bukkit.getPluginManager();
		pluginManager.registerEvents(new DefaultListener(helpers),this);
	}
}
