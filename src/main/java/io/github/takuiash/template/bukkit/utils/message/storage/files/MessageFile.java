package io.github.takuiash.template.bukkit.utils.message.storage.files;

import io.github.takuiash.template.bukkit.utils.message.MessageImpl;
import io.github.takuiash.template.bukkit.utils.message.api.Message;
import io.github.takuiash.template.bukkit.utils.message.lang.Language;
import io.github.takuiash.template.bukkit.utils.plugin.JavaPluginBase;
import io.github.takuiash.template.bukkit.utils.yaml.YamlFile;

public class MessageFile extends YamlFile {

	private final Language language;
	
	public MessageFile(JavaPluginBase plugin, Language language) {
		super(plugin.getDataFolder(), plugin.getConfig().getMessageFilePath(language));
		this.language = language;
	}
	
	public Message getMessage(String path) {
		return contains(path) ? new MessageImpl(language, this.getString(path)) : null;
	}
	
}
