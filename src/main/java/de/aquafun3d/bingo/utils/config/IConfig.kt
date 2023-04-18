package de.aquafun3d.bingo.utils.config

import java.io.IOException

interface IConfig {
    fun createConfig()
    operator fun contains(path: String?): Boolean

    @Throws(IOException::class)
    operator fun set(path: String?, value: Any?)
    operator fun get(path: String?): Any?
    fun getInt(path: String?): Int
    fun getString(path: String?): String?
    fun getDouble(path: String?): Double
}
