package de.aquafun3d.bingo.commands;

import de.aquafun3d.bingo.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TopCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player){
			Player player = (Player) sender;
			if(Utils.isBingoRunning){
				String world = player.getWorld().getName();
				double x = player.getLocation().getX();
				double z = player.getLocation().getZ();
				float yaw = player.getLocation().getYaw();
				float pitch = player.getLocation().getPitch();
				double y = Bukkit.getWorld("world").getHighestBlockYAt((int) x, (int) z) + 1;
				if(world == "world_nether"){
					x *= 8;
					z *= 8;
				}
				Location loc = new Location(Bukkit.getWorld("world"),x,y,z,yaw,pitch);
				player.teleport(loc);
				Utils.send(player, ChatColor.GREEN + "Wooooosh!");
			}else{
				Utils.send(player,ChatColor.RED + "Bingo hasn't started yet!");
			}
		}
		return false;
	}
}
