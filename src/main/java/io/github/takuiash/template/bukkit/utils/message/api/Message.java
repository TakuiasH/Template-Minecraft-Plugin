package io.github.takuiash.template.bukkit.utils.message.api;

import io.github.takuiash.template.bukkit.utils.message.lang.Language;

public interface Message {
	
	Language getLanguage();
	Message replaceAll(String regex, String replacement);
	String getString();
	
}
