package de.aquafun3d.bingo.utils.tasks

interface IBingoTaskManager {
    fun getTasks(amount: Int, diff: BingoDifficulty?): List<IBingoTask>?
}
