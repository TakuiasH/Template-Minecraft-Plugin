package io.github.takuiash.template.bukkit.utils.message;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import io.github.takuiash.template.bukkit.utils.message.api.Message;
import io.github.takuiash.template.bukkit.utils.message.api.MessageManager;
import io.github.takuiash.template.bukkit.utils.message.lang.Language;
import io.github.takuiash.template.bukkit.utils.message.storage.files.MessageFile;
import io.github.takuiash.template.bukkit.utils.plugin.JavaPluginBase;

public class MessageExecutor implements MessageManager {
	
	private final JavaPluginBase plugin;
	private final Map<Language, MessageFile> fileMap = new HashMap<>();
	
	public MessageExecutor(JavaPluginBase plugin) {
		this.plugin = plugin;
	}
	
	public boolean containsMessageFile(Language language) {
		return fileMap.containsKey(language);
	}
	
	public MessageFile registerMessageFile(Language language, String defaultResourcePath) {
		MessageFile file = new MessageFile(plugin, language);
		fileMap.put(language, file);
		if(defaultResourcePath != null) file.saveResource(plugin, defaultResourcePath, false);
		return file;
	}
	
	public MessageFile getMessageFile(Language language) {		
		return containsMessageFile(language) ? fileMap.get(language) : registerMessageFile(language, null);
	}
	
	public Message getMessage(String path, Language language) {
		Message message = this.getMessageFile(language).getMessage(path);
		
		if(message == null && language != plugin.getLanguageManager().getDefaultLanguage())
			return getMessage(path, plugin.getLanguageManager().getDefaultLanguage());
		
		return message;
	}
	
	public Message getMessage(String path, Player player) {
		return this.getMessage(path, plugin.getLanguageManager().getLanguage(player));
	}
	
	public void sendMessage(String path, Player target) {
		target.sendMessage(this.getMessage(path, plugin.getLanguageManager().getLanguage(target)).getString());
	}
	
	public void sendMessage(String path, CommandSender target) {
		Language language = target instanceof Player ? plugin.getLanguageManager().getLanguage((Player) target) : plugin.getLanguageManager().getDefaultLanguage();
		target.sendMessage(this.getMessage(path, language).getString());
	}
}
