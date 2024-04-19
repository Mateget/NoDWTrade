package nodwtrade;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ConfigHandler {

	public static Config config;

	public static void readAllConfigs() {
		loadConfigFile(new File(NoDWTrade.configDir, "config.json"));
	}

	public static void creationCheckConfig() {
		if (config == null) {
			config = new Config();
		}
	}

	public static void writeConfig() {
		writeConfigFile(new File(NoDWTrade.configDir, "config.json"));
	}

	public static void loadConfigFile(File file) {
		try {
			if (!file.exists())
				file.createNewFile();
			Gson gson = new Gson();
			BufferedReader br = new BufferedReader(
					new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
			config = gson.fromJson(br, Config.class);
			br.close();
		} catch (Exception e) {
			NoDWTrade.LOGGER.error("Failed to read config:\r\n" + e.getMessage());
		}
	}

	public static void writeConfigFile(File file) {
		try {
			if (!file.exists())
				file.createNewFile();
			Gson gson = (new GsonBuilder()).setPrettyPrinting().disableHtmlEscaping().create();
			String json = gson.toJson(config);
			OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);
			writer.write(json);
			writer.close();
		} catch (Exception e) {
			NoDWTrade.LOGGER.error("Failed to save config:\r\n" + e.getMessage());
		}
	}

}
