package de.aquafun3d.bingo.utils.helpers

import de.aquafun3d.bingo.utils.tasks.TaskDifficulty

class Settings: ISettings { //Um die anderen TaskTypes erweitern

    private var _difficulty = TaskDifficulty.HARD
    private var _end: Boolean = false
    private var _nether: Boolean = false
    private var _silktouch: Boolean = false
    private var _confirmed: Boolean = false

    override fun getDifficulty(): TaskDifficulty {
        return _difficulty
    }

    override fun setDifficulty(difficulty: TaskDifficulty) {
        _difficulty = difficulty
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
}