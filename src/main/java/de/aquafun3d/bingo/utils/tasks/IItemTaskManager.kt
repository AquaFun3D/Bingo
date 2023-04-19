package de.aquafun3d.bingo.utils.tasks

interface IItemTaskManager {
    fun getItems(amount: Int): List<IBingoTask>
}