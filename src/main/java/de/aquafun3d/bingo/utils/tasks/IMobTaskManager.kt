package de.aquafun3d.bingo.utils.tasks

interface IMobTaskManager {
    fun getMobs(amount: Int): List<IBingoTask>
}