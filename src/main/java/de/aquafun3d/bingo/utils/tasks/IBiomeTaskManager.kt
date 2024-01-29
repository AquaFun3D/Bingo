package de.aquafun3d.bingo.utils.tasks

interface IBiomeTaskManager {
    fun getBiomes(amount: Int): List<IBingoTask>
}