package de.aquafun3d.bingo.utils.tasks

import de.aquafun3d.bingo.utils.helpers.ISettings

class BingoTaskManager(private val _itemTaskManager: IItemTaskManager, private val _settings: ISettings, private val _mobTaskManager: IMobTaskManager, private val _biomeTaskManager: IBiomeTaskManager, private val _advancementTaskManager: IAdvancementTaskManager): IBingoTaskManager {

    private var _list = mutableListOf<IBingoTask>()

    override fun fillList(){
        val temp = mutableListOf<IBingoTask>()
        temp.addAll(_itemTaskManager.getItems(9 * _settings.getQuantity()))
        val e = (9 * _settings.getQuantity()) / (3 + _settings.getQuantity())
        if(_settings.getMobs()) temp.addAll(_mobTaskManager.getMobs(e))
        if(_settings.getAchievements()) temp.addAll(_advancementTaskManager.getAdvancements(e))
        if(_settings.getBioms()) temp.addAll(_biomeTaskManager.getBiomes(e))
        for(i in 1.. _settings.getQuantity() * 9){
            val rdm = (0 until temp.size).random()
            _list.add(temp[rdm])
            temp.removeAt(rdm)
        }
    }

    override fun getNewTask(type: TaskType): IBingoTask{
        return when(type){
            TaskType.ITEM -> _itemTaskManager.getItems(1) as IBingoTask
            TaskType.BIOME -> _biomeTaskManager.getBiomes(1) as IBingoTask
            TaskType.ACHIEVMENT -> _advancementTaskManager.getAdvancements(1) as IBingoTask
            TaskType.MOB -> _mobTaskManager.getMobs(1) as IBingoTask
            TaskType.ENCHANTMENT -> _itemTaskManager.getItems(1) as IBingoTask
        }
    }

    override fun replace(index: Int, task: IBingoTask){
        _list.removeAt(index)
        _list.add(index, task)
    }

    override fun getList(): List<IBingoTask>{
        return _list
    }
}