package de.aquafun3d.bingo;

import de.aquafun3d.bingo.Commands.TeamBackpackCommand;
import de.aquafun3d.bingo.Commands.TopCommand;
import de.aquafun3d.bingo.Listeners.DefaultListener;
import de.aquafun3d.bingo.Listeners.InventoryListener;
import de.aquafun3d.bingo.Listeners.TeamBackpackListener;
import de.aquafun3d.bingo.utils.config.IConfig;
import de.aquafun3d.bingo.utils.helpers.Helpers;
import de.aquafun3d.bingo.utils.config.BingoConfig;
import de.aquafun3d.bingo.utils.helpers.IHelpers;
import de.aquafun3d.bingo.utils.inventories.IInventory;
import de.aquafun3d.bingo.utils.inventories.TeamselectInventory;
import de.aquafun3d.bingo.utils.scoreboards.IScoreboards;
import de.aquafun3d.bingo.utils.scoreboards.Scoreboards;
import de.aquafun3d.bingo.utils.spawncage.SpawnCage;
import de.aquafun3d.bingo.utils.teams.BingoTeams;
import de.aquafun3d.bingo.utils.teams.ITeams;
import de.aquafun3d.bingo.utils.timer.Timer;
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
		var scoreboards = new Scoreboards();
		var teams = new BingoTeams(scoreboards,helpers);
		var teamSelectInv = new TeamselectInventory(helpers,scoreboards);
		var cage = new SpawnCage();
		var timer = new Timer(this,helpers);

		commandRegistration(helpers,config);
		listenerRegistration(helpers,scoreboards,teams,teamSelectInv);

	}

	@Override
	public void onDisable() {
		Bukkit.getLogger().fine("Plugin deactivated");
		var spawn = Bukkit.getWorld("world").getSpawnLocation();
		var location = new Location(Bukkit.getWorld("world"), spawn.getX(), spawn.getY() - 2.0, spawn.getZ());
		Bukkit.getWorld("world").setSpawnLocation(location);
	}

	private void commandRegistration(IHelpers helpers, IConfig config){
		getCommand("top").setExecutor(new TopCommand(helpers));
		getCommand("teambackpack").setExecutor(new TeamBackpackCommand(helpers,config));
	}

	private void listenerRegistration(IHelpers helpers, IScoreboards scoreboards, ITeams teams, IInventory teamSelectInv) {
		PluginManager pluginManager = Bukkit.getPluginManager();
		pluginManager.registerEvents(new DefaultListener(helpers, scoreboards),this);
		pluginManager.registerEvents(new TeamBackpackListener(),this);
		pluginManager.registerEvents(new InventoryListener(teams,teamSelectInv),this);
	}
}
