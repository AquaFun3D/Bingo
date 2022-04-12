package de.aquafun3d.bingo;

import de.aquafun3d.bingo.commands.TopCommand;
import de.aquafun3d.bingo.utils.BingoConfig;
import de.aquafun3d.bingo.utils.SpawnCage;
import de.aquafun3d.bingo.utils.TimerService;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class Main extends JavaPlugin {

	public Main plugin;
	public static BingoConfig bingoConfig;
	public static TimerService timer;
	public static SpawnCage spawnCage;
	@Override
	public void onEnable() {
		Bukkit.getLogger().fine("Plugin activated");
		plugin = this;

		bingoConfig = new BingoConfig();
		timer = new TimerService(this);
		spawnCage = new SpawnCage();

		listenerRegistration();
		commandRegistraion();
	}

	@Override
	public void onDisable() {
		Bukkit.getLogger().fine("Plugin deactivated");
		Location spawn = Bukkit.getWorld("world").getSpawnLocation();
		Location newSpawn = new Location(Bukkit.getWorld("world"),spawn.getX(),spawn.getY() - 2,spawn.getZ());
		Bukkit.getWorld("world").setSpawnLocation(newSpawn);
		try {
			bingoConfig.set("time", timer.getSec());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void commandRegistraion(){
		getCommand("top").setExecutor(new TopCommand());

	}

	private void listenerRegistration(){
		PluginManager pluginManager = Bukkit.getPluginManager();
//		pluginManager.registerEvents(new JoinQuitListener(),this);
	}

}