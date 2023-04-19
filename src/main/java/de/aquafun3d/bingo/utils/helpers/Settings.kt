package de.aquafun3d.bingo.utils.helpers

import de.aquafun3d.bingo.utils.tasks.TaskDifficulty

class Settings: ISettings { //Um die anderen TaskTypes erweitern

    private var _difficulty = TaskDifficulty.EASY
    private var _end: Boolean = false
    private var _nether: Boolean = false

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


}