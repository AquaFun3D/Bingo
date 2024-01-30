package de.aquafun3d.bingo.utils.tasks

import de.aquafun3d.bingo.utils.helpers.ISettings
import org.bukkit.block.Biome

class BiomeTaskManager(private val _settings: ISettings): IBiomeTaskManager {

    private var _list = mutableListOf<IBingoTask>()
    private var _listReturn = mutableListOf<IBingoTask>()

    override fun getBiomes(amount: Int): List<IBingoTask> {
        _listReturn.clear()
        if(_list.isEmpty()) {
            when (_settings.getBingoDifficulty()) {
                TaskDifficulty.EASY -> {
                    overworldEasy()
                    if (_settings.getNether()) nether()
                }
                TaskDifficulty.NORMAL -> {
                    overworldEasy()
                    overworldNormal()
                    if (_settings.getNether()) nether()
                }
                else -> {
                    overworldEasy()
                    overworldNormal()
                    overworldHard()
                    if (_settings.getNether()) nether()
                }
            }
        }
        _list.shuffle()
        var index = 0
        while (index < amount){
            if(_list.isEmpty()) break
            val rdm = (0 until _list.size).random()
            _listReturn.add(_list[rdm])
            _list.removeAt(rdm)
            index++
        }
        return _listReturn
    }

    private fun overworldEasy(){
        _list.add(BiomeTask("Ocean", Biome.OCEAN))
        _list.add(BiomeTask("Warm Ocean", Biome.WARM_OCEAN))
        _list.add(BiomeTask("Lukewarm Ocean", Biome.LUKEWARM_OCEAN))
        _list.add(BiomeTask("Cold Ocean", Biome.COLD_OCEAN))
        _list.add(BiomeTask("Frozen Ocean", Biome.FROZEN_OCEAN))
        _list.add(BiomeTask("Meadow", Biome.MEADOW))
        _list.add(BiomeTask("Grove", Biome.GROVE))
        _list.add(BiomeTask("Snowy Slopes", Biome.SNOWY_SLOPES))
        _list.add(BiomeTask("Windswept Hills", Biome.WINDSWEPT_HILLS))
        _list.add(BiomeTask("Windswept Forest", Biome.WINDSWEPT_FOREST))
        _list.add(BiomeTask("Forest", Biome.FOREST))
        _list.add(BiomeTask("Taiga", Biome.TAIGA))
        _list.add(BiomeTask("Snowy Taiga", Biome.SNOWY_TAIGA))
        _list.add(BiomeTask("Birch Forest", Biome.BIRCH_FOREST))
        _list.add(BiomeTask("Dark Forest", Biome.DARK_FOREST))
        _list.add(BiomeTask("Jungle", Biome.JUNGLE))
        _list.add(BiomeTask("River", Biome.RIVER))
        _list.add(BiomeTask("Swamp", Biome.SWAMP))
        _list.add(BiomeTask("Snowy Beach", Biome.SNOWY_BEACH))
        _list.add(BiomeTask("Stony Shore", Biome.STONY_SHORE))
        _list.add(BiomeTask("Plains", Biome.PLAINS))
        _list.add(BiomeTask("Snowy Plains", Biome.SNOWY_PLAINS))
        _list.add(BiomeTask("Desert", Biome.DESERT))
        _list.add(BiomeTask("Savanna", Biome.SAVANNA))
        _list.add(BiomeTask("Savanna Plateau", Biome.SAVANNA_PLATEAU))
        _list.add(BiomeTask("Badlands", Biome.BADLANDS))
    }

    private fun overworldNormal(){
        _list.add(BiomeTask("Deep Frozen Ocean", Biome.DEEP_FROZEN_OCEAN))
        _list.add(BiomeTask("Deep Lukewarm Ocean", Biome.DEEP_LUKEWARM_OCEAN))
        _list.add(BiomeTask("Deep Ocean", Biome.DEEP_OCEAN))
        _list.add(BiomeTask("Deep Cold Ocean", Biome.DEEP_COLD_OCEAN))
        _list.add(BiomeTask("Jagged Peaks", Biome.JAGGED_PEAKS))
        _list.add(BiomeTask("Frozen Peaks", Biome.FROZEN_PEAKS))
        _list.add(BiomeTask("Stony Peaks", Biome.STONY_PEAKS))
        _list.add(BiomeTask("Flower Forest", Biome.FLOWER_FOREST))
        _list.add(BiomeTask("Old Growth Pine Taiga", Biome.OLD_GROWTH_PINE_TAIGA))
        _list.add(BiomeTask("Old Growth Spruce Taiga", Biome.OLD_GROWTH_SPRUCE_TAIGA))
        _list.add(BiomeTask("Sparse Jungle", Biome.SPARSE_JUNGLE))
        _list.add(BiomeTask("Bamboo Jungle", Biome.BAMBOO_JUNGLE))
        _list.add(BiomeTask("Windswept Savanna", Biome.WINDSWEPT_SAVANNA))
        _list.add(BiomeTask("Wooded Badlands", Biome.WOODED_BADLANDS))
        _list.add(BiomeTask("Dripstone Cave", Biome.DRIPSTONE_CAVES))
        _list.add(BiomeTask("Lush Cave", Biome.DRIPSTONE_CAVES))
    }

    private fun overworldHard(){
        _list.add(BiomeTask("Mushroom Fields", Biome.MUSHROOM_FIELDS))
        _list.add(BiomeTask("Deep Dark", Biome.DEEP_DARK))
        _list.add(BiomeTask("Eroded Badlands", Biome.ERODED_BADLANDS))
        _list.add(BiomeTask("Ice Spikes", Biome.ICE_SPIKES))
        _list.add(BiomeTask("Sunflower Plains", Biome.SUNFLOWER_PLAINS))
        _list.add(BiomeTask("Mangrove Swamp", Biome.MANGROVE_SWAMP))
        _list.add(BiomeTask("Old Growth Birch Forest", Biome.OLD_GROWTH_BIRCH_FOREST))
        _list.add(BiomeTask("Windswept Gravelly Hills", Biome.WINDSWEPT_GRAVELLY_HILLS))
        _list.add(BiomeTask("Cherry Grove", Biome.CHERRY_GROVE))
        _list.add(BiomeTask("Frozen River", Biome.FROZEN_RIVER))
    }

    private fun nether(){
        _list.add(BiomeTask("Basalt Deltas", Biome.BASALT_DELTAS))
        _list.add(BiomeTask("Warped Forest", Biome.WARPED_FOREST))
        _list.add(BiomeTask("Crimson Forest", Biome.CRIMSON_FOREST))
        _list.add(BiomeTask("Soul Sand Valley", Biome.SOUL_SAND_VALLEY))
        _list.add(BiomeTask("Nether Wastes", Biome.NETHER_WASTES))
    }
}