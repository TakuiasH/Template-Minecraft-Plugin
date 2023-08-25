package io.github.takuiash.template.core.data.files;

import io.github.takuiash.template.bukkit.utils.plugin.JavaPluginBase;
import io.github.takuiash.template.bukkit.utils.yaml.YamlFile;

public class ConfigFile extends YamlFile {

	public ConfigFile(JavaPluginBase plugin) {
		super(plugin.getDataFolder(), "config.yml");
		
		saveDefaultConfig(plugin);
	}

}
