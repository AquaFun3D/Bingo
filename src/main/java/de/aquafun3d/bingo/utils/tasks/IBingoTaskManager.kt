package de.aquafun3d.bingo.utils.tasks

interface IBingoTaskManager {
    fun fillList()
    fun getList(): List<IBingoTask>
}