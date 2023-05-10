package de.aquafun3d.bingo.utils.config

import org.bukkit.configuration.file.YamlConfiguration
import java.io.File
import java.io.IOException

class BingoConfig : IConfig {
    private lateinit var _config: YamlConfiguration
    private lateinit var _file: File

    init {
        createConfig()
    }

    override fun createConfig() {
        val dir = File("./plugins/Configs/")
        if (!dir.exists()) {
            dir.mkdirs()
        }
        _file = File(dir, "bingo.yml")
        if (!_file.exists()) {
            try {
                _file.createNewFile()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        _config = YamlConfiguration.loadConfiguration(_file)
        set("teambackpack", null)
    }

    override fun contains(path: String): Boolean {
        return _config.contains(path)
    }

    @Throws(IOException::class)
    override fun set(path: String, value: Any?) {
        _config[path] = value
        _config.save(_file)
    }

    override fun getAny(path: String): Any? {
        if (!contains(path)) {
            throw NullPointerException("couldn't find path")
        }
        return _config[path]
    }
}
