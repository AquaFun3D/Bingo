package de.aquafun3d.bingo.utils.config

interface IConfig {
    fun createConfig()
    fun contains(path: String): Boolean
    fun set(path: String, value: Any?)
    fun getAny(path: String): Any?
}
