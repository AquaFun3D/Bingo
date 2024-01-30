package de.aquafun3d.bingo.utils.tasks

import de.aquafun3d.bingo.utils.helpers.ISettings

class BingoTaskManager(private val _itemTaskManager: IItemTaskManager, private val _settings: ISettings, private val _mobTaskManager: IMobTaskManager, private val _biomeTaskManager: IBiomeTaskManager, private val _advancementTaskManager: IAdvancementTaskManager): IBingoTaskManager {

    private var _list = mutableListOf<IBingoTask>()

    override fun fillList(){
        val temp = mutableListOf<IBingoTask>()
        temp.addAll(_itemTaskManager.getItems(9 * _settings.getQuantity()))
        if(_settings.getMobs()) temp.addAll(_mobTaskManager.getMobs(9 * _settings.getQuantity() / 3))
        for(i in 1.. _settings.getQuantity() * 9){
            val rdm = (0 until temp.size).random()
            _list.add(temp[rdm])
            temp.removeAt(rdm)
        }
    }

    override fun getList(): List<IBingoTask>{
        return _list
    }
}