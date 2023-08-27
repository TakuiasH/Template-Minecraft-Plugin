package io.github.takuiash.template.bukkit.utils.message.api;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import io.github.takuiash.template.api.TemplatePluginProvider;
import io.github.takuiash.template.bukkit.utils.message.lang.Language;
import io.github.takuiash.template.bukkit.utils.message.storage.files.MessageFile;

public interface MessageManager {

	public static MessageManager get() {
		return TemplatePluginProvider.get().getMessageManager();
	}
		
	boolean containsMessageFile(Language language);
	MessageFile registerMessageFile(Language language, String defaultResourcePath);
	MessageFile getMessageFile(Language language);
	
	Message getMessage(String path, Language language);
	Message getMessage(String path, Player player);
	
	void sendMessage(String path, Player target);
	void sendMessage(String path, CommandSender target);
}
