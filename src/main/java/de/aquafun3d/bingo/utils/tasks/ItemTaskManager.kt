package de.aquafun3d.bingo.utils.tasks

class ItemTaskManager : IBingoTaskManager {
    private val _list: List<IBingoTask>? = null
    private val _listReturn: List<IBingoTask>? = null
    override fun getTasks(amount: Int, diff: BingoDifficulty?): List<IBingoTask>? {
        when (diff) {
            BingoDifficulty.ONE -> overworldEasy
            else -> {}
        }
        //RANDOM aus list in returnlist
        return _list
    }

    val overworldEasy: Unit
        get() {}
}
