package io.github.takuiash.template.core.data.files;

import io.github.takuiash.template.bukkit.utils.message.lang.Language;
import io.github.takuiash.template.bukkit.utils.plugin.JavaPluginBase;
import io.github.takuiash.template.bukkit.utils.yaml.YamlFile;

public class ConfigFile extends YamlFile {

	public ConfigFile(JavaPluginBase plugin) {
		super(plugin.getDataFolder(), "config.yml");
		
		saveDefaultConfig(plugin);
	}

	public Language getDefaultLanguage() {
		return Language.valueOf(getString("message.default-language"));
	}
	
	public String getMessageFilePath(Language language) {
		return getString("message.message-path").replaceAll("<language>", language.toString());
	}

}
