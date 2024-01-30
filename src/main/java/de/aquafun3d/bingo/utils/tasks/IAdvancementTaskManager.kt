package de.aquafun3d.bingo.utils.tasks

interface IAdvancementTaskManager {
    fun getAdvancements(amount: Int): List<IBingoTask>
}