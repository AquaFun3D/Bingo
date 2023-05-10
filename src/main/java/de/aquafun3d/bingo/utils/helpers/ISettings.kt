package de.aquafun3d.bingo.utils.helpers

import de.aquafun3d.bingo.utils.tasks.TaskDifficulty
import org.bukkit.Difficulty

interface ISettings {
    fun getBingoDifficulty(): TaskDifficulty
    fun setBingoDifficulty(difficulty: TaskDifficulty)
    fun getNether(): Boolean
    fun setNether(b: Boolean)
    fun getEnd(): Boolean
    fun setEnd(b: Boolean)
    fun getSilktouch(): Boolean
    fun setSilktouch(b: Boolean)
    fun isConfirmed(): Boolean
    fun setConfirmed(b: Boolean)
    fun setQuantity(multiplyer: Int)
    fun getQuantity(): Int
    fun setTeamsize(size: Int)
    fun getTeamsize(): Int
    fun getPvP(): Boolean
    fun setPvP(b: Boolean)
    fun getKeepInventory(): Boolean
    fun setKeepInventory(b: Boolean)
    fun getEnchanting(): Boolean
    fun setEnchanting(b: Boolean)
    fun getMobs(): Boolean
    fun setMobs(b: Boolean)
    fun getBioms(): Boolean
    fun setBioms(b: Boolean)
    fun getAchievements(): Boolean
    fun setAchievements(b: Boolean)
    fun getDifficulty(): Difficulty
    fun setDifficulty(difficulty: Difficulty)
    fun getHunger(): Boolean
    fun setHunger(b: Boolean)
}