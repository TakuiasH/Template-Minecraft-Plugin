package io.github.takuiash.template.bukkit.utils.message;

import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;

import io.github.takuiash.template.bukkit.utils.Utils;
import io.github.takuiash.template.bukkit.utils.message.api.Message;
import io.github.takuiash.template.bukkit.utils.message.lang.Language;

public  class MessageImpl implements Message {

	private Language language;
	private final Map<String, String> replaces = Collections.emptyMap();
	private String message;
	
	public MessageImpl(Language language, String message) {
		this.language = language;
		this.message = message;
	}
	
	public Language getLanguage() {
		return language;
	}

	public Message replaceAll(String regex, String replacement) {
		replaces.put(regex, replacement);
		return this;
	}

	public String getString() {
		String formatedMessage = Utils.translateHexColors(message);
		
		for(Entry<String, String> replace : replaces.entrySet()) 
			formatedMessage = formatedMessage.replaceAll(replace.getKey(), replace.getValue());
		
		return formatedMessage;
	}
	
	public String toString() {
		return this.getString();
	}

}
