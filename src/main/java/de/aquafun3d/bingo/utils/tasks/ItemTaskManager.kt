package de.aquafun3d.bingo.utils.tasks

import de.aquafun3d.bingo.utils.helpers.ISettings
import org.bukkit.Material

class ItemTaskManager(settings: ISettings): IItemTaskManager {

    private var _list = mutableListOf<IBingoTask>()
    private var _listReturn = mutableListOf<IBingoTask>()
    private val _settings: ISettings
    private val _difficulty: TaskDifficulty
    private val _nether: Boolean
    private val _end: Boolean

    init {
        _settings = settings
        _difficulty = settings.getDifficulty()
        _end = settings.getEnd()
        _nether = settings.getNether()
    }

    override fun getItems(amount : Int): List<IBingoTask>{
        when(_difficulty){
            TaskDifficulty.EASY -> {
                overworldEasy()
                if(_nether){
                    netherEasy()
                }
                if(_end){
                    endEasy()
                }
            }
            TaskDifficulty.NORMAL -> {

            }
            TaskDifficulty.HARD -> {

            }
            TaskDifficulty.EXTREME -> {

            }
        }
        var index = 0
        while (index < amount){
            val rdm = (0 until _list.size).random()
            _listReturn.add(_list[rdm])
            index++
        }
        return _listReturn
    }

    private fun overworldEasy(){
        _list.add(ItemTask(Material.DIRT))
    }

    private fun netherEasy(){
        _list.add(ItemTask(Material.DIRT))
    }

    private fun endEasy(){
        _list.add(ItemTask(Material.DIRT))
    }

}