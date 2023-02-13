package de.aquafun3d.bingo.utils.config;

import java.io.IOException;

public interface IConfig {

	void createConfig();

	Boolean contains(String path);

	void set(String path, Object value) throws IOException;

	Object get(String path);

	int getInt(String path);

	String getString(String path);

	double getDouble(String path);

}
