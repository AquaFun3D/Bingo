package de.aquafun3d.bingo.utils;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class BingoConfig {

	private File file;
	private YamlConfiguration config;

	public BingoConfig(){
		File dir = new File("./plugins/Configs/");
		if(!dir.exists()){
			dir.mkdirs();
		}

		file = new File(dir, "bingo_config.yml");
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		config = YamlConfiguration.loadConfiguration(file);
	}

	public boolean contains(String path){
		return config.contains(path);
	}

	public void set(String path, Object value) throws IOException {
		config.set(path,value);
		config.save(file);
	}

	public Object get(String path){
		if(!contains(path)){
			return null;
		}
		return config.get(path);
	}

	public ArrayList<String> getAll(String path){
		ArrayList<String> list = new ArrayList<>();
		if (!contains(path)) {
			return null;
		} else {
			for (String key : config.getConfigurationSection(path).getKeys(false)) {
				list.add(key);
			}
			return list;
		}
	}

	public int getInt(String path){
		if(!contains(path)){
			return 0;
		}else{
			return config.getInt(path);
		}
	}

	public double getDouble(String path){
		if(!contains(path)){
			return 0;
		}else{
			return config.getDouble(path);
		}
	}

	public String getString(String path){
		if(!contains(path)){
			return null;
		}else{
			return config.getString(path);
		}
	}
}
