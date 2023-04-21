package de.aquafun3d.bingo.utils.tasks

class BingoTaskManager(itemTaskManager: IItemTaskManager): IBingoTaskManager {

    private var _list = mutableListOf<IBingoTask>()
    private val _itemTaskManager: IItemTaskManager

    init {
        _itemTaskManager = itemTaskManager
    }

    override fun fillList(){
        _list.addAll(_itemTaskManager.getItems(9))
    }

    override fun getList(): List<IBingoTask>{
        return _list
    }
}