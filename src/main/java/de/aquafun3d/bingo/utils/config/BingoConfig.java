package de.aquafun3d.bingo.utils.config;

import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import java.io.IOException;

public class BingoConfig implements IConfig {

	private YamlConfiguration _config;
	private File _file;
	private final String _name;

	public BingoConfig(String name){
		_name = name;
		createConfig();
	}

	public void createConfig() {
		File dir = new File("./plugins/Configs/");
		if(!dir.exists()){
			dir.mkdirs();
		}

		_file = new File(dir, _name + ".yml");
		if(!_file.exists()){
			try {
				_file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		_config = YamlConfiguration.loadConfiguration(_file);
	}

	public Boolean contains(String path) {
		return _config.contains(path);
	}

	public void set(String path, Object value) throws IOException {
		_config.set(path,value);
		_config.save(_file);
	}

	public Object get(String path) {
		if(!contains(path)){
			throw new NullPointerException("couldn't find path");
		}
		return _config.get(path);
	}

	public int getInt(String path) {
		if(!contains(path)){
			throw new NullPointerException("couldn't find path");
		}
		return _config.getInt(path);
	}

	public String getString(String path) {
		if(!contains(path)){
			throw new NullPointerException("couldn't find path");
		}
		return _config.getString(path);
	}

	public double getDouble(String path) {
		if(!contains(path)){
			throw new NullPointerException("couldn't find path");
		}
		return _config.getDouble(path);
	}
}
