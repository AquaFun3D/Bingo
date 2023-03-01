package de.aquafun3d.bingo.Commands;

import de.aquafun3d.bingo.utils.helpers.IHelpers;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class TopCommand implements CommandExecutor {

	private final IHelpers _helpers;

	public TopCommand(IHelpers helpers){
		_helpers = helpers;
	}

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
		if(sender instanceof Player player){
			if(_helpers.isBingoRunning()){
				var world = player.getWorld().getName();
				var x = player.getLocation().getX();
				var z = player.getLocation().getZ();
				var yaw = player.getLocation().getYaw();
				var pitch = player.getLocation().getPitch();
				var y = Objects.requireNonNull(Bukkit.getWorld(world)).getHighestBlockYAt((int) x, (int) z) + 1;
				if(world.equals("world_nether")){
					x*=8;
					z*=8;
				}
				var location = new Location(Bukkit.getWorld("world"),x,y,z,yaw,pitch);
				player.teleport(location);
				_helpers.send(player, ChatColor.GREEN + "Wooooosh!");
			}else{
				_helpers.send(player,ChatColor.RED + "Bingo hasn't started yet!");
			}
		}
		return false;
	}
}
