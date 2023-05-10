package de.aquafun3d.bingo.utils.helpers

import de.aquafun3d.bingo.utils.tasks.TaskDifficulty
import org.bukkit.Difficulty

class Settings: ISettings {

    private var _bingoDifficulty = TaskDifficulty.EASY
    private var _end: Boolean = false
    private var _nether: Boolean = false
    private var _silktouch: Boolean = false
    private var _confirmed: Boolean = false
    private var _keepInventory: Boolean = false
    private var _enchanting: Boolean = false
    private var _mobs: Boolean = false
    private var _bioms: Boolean = false
    private var _achievements: Boolean = false
    private var _pvp: Boolean = false
    private var _hunger: Boolean = false
    private var _difficulty = Difficulty.EASY
    private var _quantity: Int = 1
    private var _teamsize: Int = 1

    override fun getPvP(): Boolean {
        return _pvp
    }

    override fun setPvP(b: Boolean) {
        _pvp = b
    }

    override fun getHunger(): Boolean {
        return _hunger
    }

    override fun setHunger(b: Boolean) {
        _hunger = b
    }

    override fun getKeepInventory(): Boolean {
        return _keepInventory
    }

    override fun setKeepInventory(b: Boolean) {
        _keepInventory = b
    }

    override fun getEnchanting(): Boolean {
        return _enchanting
    }

    override fun setEnchanting(b: Boolean) {
        _enchanting = b
    }

    override fun getMobs(): Boolean {
        return  _mobs
    }

    override fun setMobs(b: Boolean) {
        _mobs = b
    }

    override fun getBioms(): Boolean {
        return _bioms
    }

    override fun setBioms(b: Boolean) {
        _bioms = b
    }

    override fun getAchievements(): Boolean {
        return _achievements
    }

    override fun setAchievements(b: Boolean) {
        _achievements = b
    }

    override fun getDifficulty(): Difficulty {
        return _difficulty
    }

    override fun setDifficulty(difficulty: Difficulty) {
        _difficulty = difficulty
    }

    override fun getBingoDifficulty(): TaskDifficulty {
        return _bingoDifficulty
    }

    override fun setBingoDifficulty(difficulty: TaskDifficulty) {
        _bingoDifficulty = difficulty
    }

    override fun getNether(): Boolean {
        return _nether
    }

    override fun setNether(b: Boolean) {
        _nether = b
    }

    override fun getEnd(): Boolean {
        return _end
    }

    override fun setEnd(b: Boolean) {
        _end = b
    }

    override fun getSilktouch(): Boolean {
        return _silktouch
    }

    override fun setSilktouch(b: Boolean) {
        _silktouch = b
    }

    override fun isConfirmed(): Boolean {
        return _confirmed
    }

    override fun setConfirmed(b: Boolean) {
        _confirmed = b
    }

    override fun setQuantity(multiplyer: Int){
        _quantity = if(multiplyer == 6){
            1
        }else{
            multiplyer
        }
    }

    override fun getQuantity(): Int {
        return _quantity
    }

    override fun setTeamsize(size: Int){
        _teamsize = if(size == 5){
            1
        }else{
            size
        }

    }

    override fun getTeamsize(): Int{
        return _teamsize
    }
}