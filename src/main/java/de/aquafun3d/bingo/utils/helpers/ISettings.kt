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
    fun isConfirmed(): Boolean
    fun setConfirmed(b: Boolean)
    fun setQuantity(multiyplyer: Int)
    fun getQuantity(): Int
    fun setTeamsize(size: Int)
    fun getTeamsize(): Int
}