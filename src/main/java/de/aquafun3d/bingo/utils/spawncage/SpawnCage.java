package de.aquafun3d.bingo.utils.spawncage;

import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.Objects;

public class SpawnCage implements ISpawnCage {

	public SpawnCage(){
		createCage();
	}

	private void createCage(){
		for(var world : Bukkit.getWorlds()){
			world.setGameRule(GameRule.SPAWN_RADIUS,1);
		}

		var spawn = Objects.requireNonNull(Bukkit.getWorld("world")).getSpawnLocation();
		int x = spawn.getBlockX();
		int y = spawn.getBlockY();
		int z = spawn.getBlockZ();

		Objects.requireNonNull(Bukkit.getWorld("world")).setSpawnLocation(x,y+2,z);

		x-=5;
		z-=5;

		//Floor
		for (int xi = 0; xi < 11; xi++){
			for (int zi = 0; zi < 11; zi++){
				Location blockLoc = new Location(Bukkit.getWorld("world"),x + xi , y , z + zi);
				blockLoc.getBlock().setType(Material.BEDROCK);
			}
		}

		//Walls
		for (int xj = 0; xj < 11; xj++){
			for (int yj = 0; yj < 4; yj++) {
				Location blockLoc = new Location(Bukkit.getWorld("world"), x + xj, y + yj, z);
				blockLoc.getBlock().setType(Material.BEDROCK);
			}
		}

		for (int zj = 0; zj < 11; zj++){
			for (int yj = 0; yj < 4; yj++) {
				Location blockLoc = new Location(Bukkit.getWorld("world"), x, y + yj, z + zj);
				blockLoc.getBlock().setType(Material.BEDROCK);
			}
		}
		x += 10;
		z += 10;
		for (int xk = 0; xk < 11; xk++){
			for (int yj = 0; yj < 4; yj++) {
				Location blockLoc = new Location(Bukkit.getWorld("world"), x - xk, y + yj, z);
				blockLoc.getBlock().setType(Material.BEDROCK);
			}
		}
		for (int zk = 0; zk < 11; zk++){
			for (int yj = 0; yj < 4; yj++) {
				Location blockLoc = new Location(Bukkit.getWorld("world"), x , y + yj, z - zk);
				blockLoc.getBlock().setType(Material.BEDROCK);
			}
		}

		//Clear
		x -= 1;
		z -= 1;
		y += 1;
		for (int xx = 0; xx < 9; xx++){
			for (int zz = 0; zz < 9; zz++){
				for (int yy = 0; yy < 10; yy++) {
					Location blockLoc = new Location(Bukkit.getWorld("world"), x - xx, y + yy, z - zz);
					blockLoc.getBlock().setType(Material.AIR);
				}
			}
		}
		x += 2;
		z += 2;
		y += 3;
		for (int xx = 0; xx < 13; xx++){
			for (int zz = 0; zz < 13; zz++){
				for (int yy = 0; yy < 10; yy++) {
					Location blockLoc = new Location(Bukkit.getWorld("world"), x - xx, y + yy, z - zz);
					blockLoc.getBlock().setType(Material.AIR);
				}
			}
		}
	}

	public void removeCage(){
		Location spawn = Objects.requireNonNull(Bukkit.getWorld("world")).getSpawnLocation();
		int x = (int) spawn.getX();
		int y = (int) spawn.getY();
		int z = (int) spawn.getZ();
		x -= 5;
		z -= 5;
		y -= 1;

		//Walls
		for (int xj = 0; xj < 11; xj++){
			for (int yj = 0; yj < 4; yj++) {
				Location blockLoc = new Location(Bukkit.getWorld("world"), x + xj, y + yj, z);
				blockLoc.getBlock().setType(Material.AIR);
			}
		}

		for (int zj = 0; zj < 11; zj++){
			for (int yj = 0; yj < 4; yj++) {
				Location blockLoc = new Location(Bukkit.getWorld("world"), x, y + yj, z + zj);
				blockLoc.getBlock().setType(Material.AIR);
			}
		}
		x += 10;
		z += 10;
		for (int xk = 0; xk < 11; xk++){
			for (int yj = 0; yj < 4; yj++) {
				Location blockLoc = new Location(Bukkit.getWorld("world"), x - xk, y + yj, z);
				blockLoc.getBlock().setType(Material.AIR);
			}
		}
		for (int zk = 0; zk < 11; zk++){
			for (int yj = 0; yj < 4; yj++) {
				Location blockLoc = new Location(Bukkit.getWorld("world"), x , y + yj, z - zk);
				blockLoc.getBlock().setType(Material.AIR);
			}
		}
	}
}
