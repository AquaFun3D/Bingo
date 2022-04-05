package de.aquafun3d.bingo.utils

import org.bukkit.*


class SpawnCage {

	init {
		for(world: World in Bukkit.getWorlds()){
			world.setGameRule(GameRule.SPAWN_RADIUS,1)
		}
		val spawn: Location = Bukkit.getWorld("world")!!.spawnLocation
		var x: Int = spawn.blockX
		var y: Int = spawn.blockY
		var z: Int = spawn.blockZ

		val new = Location(Bukkit.getWorld("world"),x.toDouble(),y.toDouble() + 2.0, z.toDouble())
		Bukkit.getWorld("world")?.spawnLocation = new

		x -= 5
		z -= 5

		//Floor
		for (xi in 0..10) {
			for (zi in 0..10) {
				val blockLoc = Location(Bukkit.getWorld("world"), (x + xi).toDouble(),	y.toDouble(), (z + zi).toDouble())
				blockLoc.block.type = Material.BEDROCK
			}
		}

		//Walls
		for (xj in 0..10) {
			for (yj in 0..3) {
				val blockLoc = Location(Bukkit.getWorld("world"), (x + xj).toDouble(), (y + yj).toDouble(), z.toDouble())
				blockLoc.block.type = Material.BEDROCK
			}
		}

		for (zj in 0..10) {
			for (yj in 0..3) {
				val blockLoc = Location(Bukkit.getWorld("world"), x.toDouble(), (y + yj).toDouble(), (z + zj).toDouble())
				blockLoc.block.type = Material.BEDROCK
			}
		}
		x += 10
		z += 10
		for (xk in 0..10) {
			for (yj in 0..3) {
				val blockLoc = Location(Bukkit.getWorld("world"),	(x - xk).toDouble(), (y + yj).toDouble(), z.toDouble())
				blockLoc.block.type = Material.BEDROCK
			}
		}
		for (zk in 0..10) {
			for (yj in 0..3) {
				val blockLoc = Location(Bukkit.getWorld("world"), x.toDouble(), (y + yj).toDouble(), (z - zk).toDouble())
				blockLoc.block.type = Material.BEDROCK
			}
		}

		//Clear
		x -= 1
		z -= 1
		y += 1
		for (xx in 0..8) {
			for (zz in 0..8) {
				for (yy in 0..9) {
					val blockLoc = Location(Bukkit.getWorld("world"),	(x - xx).toDouble(), (y + yy).toDouble(), (z - zz).toDouble())
					blockLoc.block.type = Material.AIR
				}
			}
		}
		x += 2
		z += 2
		y += 3
		for (xx in 0..12) {
			for (zz in 0..12) {
				for (yy in 0..9) {
					val blockLoc = Location(Bukkit.getWorld("world"),	(x - xx).toDouble(), (y + yy).toDouble(), (z - zz).toDouble())
					blockLoc.block.type = Material.AIR
				}
			}
		}
	}

	fun removeCage(){
		val spawn: Location = Bukkit.getWorld("world")!!.spawnLocation
		var x: Int = spawn.blockX
		var y: Int = spawn.blockY
		var z: Int = spawn.blockZ
		x -= 5
		z -= 5
		y -= 1

		//Walls
		for (xj in 0..10) {
			for (yj in 0..3) {
				val blockLoc = Location(
					Bukkit.getWorld("world"), (x + xj).toDouble(), (y + yj).toDouble(),
					z.toDouble()
				)
				blockLoc.block.type = Material.AIR
			}
		}

		for (zj in 0..10) {
			for (yj in 0..3) {
				val blockLoc = Location(Bukkit.getWorld("world"), x.toDouble(), (y + yj).toDouble(), (z + zj).toDouble())
				blockLoc.block.type = Material.AIR
			}
		}
		x += 10
		z += 10
		for (xk in 0..10) {
			for (yj in 0..3) {
				val blockLoc = Location(Bukkit.getWorld("world"), (x - xk).toDouble(), (y + yj).toDouble(), z.toDouble())
				blockLoc.block.type = Material.AIR
			}
		}
		for (zk in 0..10) {
			for (yj in 0..3) {
				val blockLoc = Location(Bukkit.getWorld("world"), x.toDouble(), (y + yj).toDouble(), (z - zk).toDouble())
				blockLoc.block.type = Material.AIR
			}
		}
	}
}