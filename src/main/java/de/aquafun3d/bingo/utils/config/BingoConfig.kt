package de.aquafun3d.bingo.utils.config

import org.bukkit.configuration.file.YamlConfiguration
import java.io.File
import java.io.IOException

class BingoConfig(private val _name: String) : IConfig {
    private var _config: YamlConfiguration? = null
    private var _file: File? = null

    init {
        createConfig()
    }

    override fun createConfig() {
        val dir = File("./plugins/Configs/")
        if (!dir.exists()) {
            dir.mkdirs()
        }
        _file = File(dir, "$_name.yml")
        if (!_file!!.exists()) {
            try {
                _file!!.createNewFile()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        _config = YamlConfiguration.loadConfiguration(_file!!)
    }

    override operator fun contains(path: String?): Boolean {
        return _config!!.contains(path!!)
    }

    @Throws(IOException::class)
    override fun set(path: String?, value: Any?) {
        _config!![path!!] = value
        _config!!.save(_file!!)
    }

    override fun get(path: String?): Any? {
        if (!contains(path)) {
            throw NullPointerException("couldn't find path")
        }
        return _config!![path!!]
    }

    override fun getInt(path: String?): Int {
        if (!contains(path)) {
            throw NullPointerException("couldn't find path")
        }
        return _config!!.getInt(path!!)
    }

    override fun getString(path: String?): String? {
        if (!contains(path)) {
            throw NullPointerException("couldn't find path")
        }
        return _config!!.getString(path!!)
    }

    override fun getDouble(path: String?): Double {
        if (!contains(path)) {
            throw NullPointerException("couldn't find path")
        }
        return _config!!.getDouble(path!!)
    }
}
