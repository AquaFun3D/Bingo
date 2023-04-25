package de.aquafun3d.bingo.utils.config

import java.io.IOException

interface IConfig {
    fun createConfig()
    fun contains(path: String): Boolean
    fun set(path: String, value: Any?)
    fun getAny(path: String): Any?
    fun getInt(path: String): Int
    fun getString(path: String): String
    fun getDouble(path: String): Double
}
