package de.aquafun3d.bingo.utils.tasks

import de.aquafun3d.bingo.utils.helpers.ISettings
import org.bukkit.Material
import org.bukkit.entity.EntityType

class AdvancementTaskManager(private val _settings: ISettings): IAdvancementTaskManager {

    private var _list = mutableListOf<IBingoTask>()
    private var _listReturn = mutableListOf<IBingoTask>()

    override fun getAdvancements(amount: Int): List<IBingoTask> {
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
                    if (_settings.getEnd()) end()
                }

                TaskDifficulty.HARD -> {
                    overworldEasy()
                    overworldNormal()
                    overworldHard()
                    if (_settings.getNether()) nether()
                    if (_settings.getEnd()) end()
                }

                TaskDifficulty.EXTREME -> {
                    overworldEasy()
                    overworldNormal()
                    overworldHard()
                    if (_settings.getNether()) nether()
                    if (_settings.getEnd()) end()
                    extreme()
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
        _list.add(MobTask(Material.COW_SPAWN_EGG, "Cow", EntityType.COW))
    }

    private fun overworldNormal(){

    }

    private fun overworldHard(){

    }

    private fun extreme(){

    }

    private fun nether(){

    }

    private fun end(){

    }
}