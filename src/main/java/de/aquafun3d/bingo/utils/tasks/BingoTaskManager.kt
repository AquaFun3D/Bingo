package de.aquafun3d.bingo.utils.tasks

import de.aquafun3d.bingo.utils.helpers.ISettings

class BingoTaskManager(private val _itemTaskManager: IItemTaskManager, private val _settings: ISettings): IBingoTaskManager {

    private var _list = mutableListOf<IBingoTask>()

    override fun fillList(){
        _list.addAll(_itemTaskManager.getItems(9 * _settings.getQuantity()))
    }

    override fun getList(): List<IBingoTask>{
        return _list
    }
}