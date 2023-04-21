package de.aquafun3d.bingo.utils.helpers

import de.aquafun3d.bingo.utils.tasks.TaskDifficulty

interface ISettings {
    fun getDifficulty(): TaskDifficulty
    fun setDifficulty(difficulty: TaskDifficulty)
    fun getNether(): Boolean
    fun setNether(b: Boolean)
    fun getEnd(): Boolean
    fun setEnd(b: Boolean)
    fun getSilktouch(): Boolean
    fun setSilktouch(b: Boolean)
}