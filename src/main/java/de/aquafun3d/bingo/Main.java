package de.aquafun3d.bingo;

import de.aquafun3d.bingo.Commands.BingoCommand;
import de.aquafun3d.bingo.Commands.StartCommand;
import de.aquafun3d.bingo.Commands.TeamBackpackCommand;
import de.aquafun3d.bingo.Commands.TopCommand;
import de.aquafun3d.bingo.Listeners.DefaultListener;
import de.aquafun3d.bingo.Listeners.InventoryListener;
import de.aquafun3d.bingo.Listeners.TeamBackpackListener;
import de.aquafun3d.bingo.utils.config.IConfig;
import de.aquafun3d.bingo.utils.helpers.Helpers;
import de.aquafun3d.bingo.utils.config.BingoConfig;
import de.aquafun3d.bingo.utils.helpers.IHelpers;
import de.aquafun3d.bingo.utils.inventories.ISettingsInventory;
import de.aquafun3d.bingo.utils.inventories.ITeamselectInventory;
import de.aquafun3d.bingo.utils.inventories.SettingsTeamselectInventory;
import de.aquafun3d.bingo.utils.inventories.TeamselectTeamselectInventory;
import de.aquafun3d.bingo.utils.scoreboards.IScoreboards;
import de.aquafun3d.bingo.utils.scoreboards.Scoreboards;
import de.aquafun3d.bingo.utils.spawncage.SpawnCage;
import de.aquafun3d.bingo.utils.inventories.ITeamInventories;
import de.aquafun3d.bingo.utils.inventories.TeamInventories;
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
		var teamSelectInv = new TeamselectTeamselectInventory(helpers);
		var settingsInv = new SettingsTeamselectInventory(helpers);
		var teamInventories = new TeamInventories(teams,helpers);
		var cage = new SpawnCage();
		var timer = new Timer(this,helpers);

		commandRegistration(helpers,config,teams,teamInventories);
		listenerRegistration(helpers,scoreboards,teams,teamSelectInv,config,teamSelectInv,settingsInv,teamInventories);

	}

	@Override
	public void onDisable() {
		Bukkit.getLogger().fine("Plugin deactivated");
		var spawn = Bukkit.getWorld("world").getSpawnLocation();
		var location = new Location(Bukkit.getWorld("world"), spawn.getX(), spawn.getY() - 2.0, spawn.getZ());
		Bukkit.getWorld("world").setSpawnLocation(location);
	}

	private void commandRegistration(IHelpers helpers, IConfig config, ITeams teams, ITeamInventories teaminv){
		getCommand("top").setExecutor(new TopCommand(helpers));
		getCommand("teambackpack").setExecutor(new TeamBackpackCommand(helpers, config, teams));
		getCommand("start").setExecutor(new BingoCommand(helpers, teams, teaminv));
		getCommand("bingo").setExecutor(new StartCommand(helpers, teaminv, teams));
	}

	private void listenerRegistration(IHelpers helpers, IScoreboards scoreboards, ITeams teams, ITeamselectInventory teamSelectInv, IConfig config, ITeamselectInventory teamselect, ISettingsInventory settings, ITeamInventories teaminv) {
		PluginManager pluginManager = Bukkit.getPluginManager();
		pluginManager.registerEvents(new DefaultListener(helpers, scoreboards,settings,teamselect,teams,teaminv),this);
		pluginManager.registerEvents(new TeamBackpackListener(config,teams),this);
		pluginManager.registerEvents(new InventoryListener(teams,teamSelectInv),this);
	}
}
