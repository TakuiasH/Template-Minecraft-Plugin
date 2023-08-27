package io.github.takuiash.template.bukkit.utils.message.listeners;

import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLocaleChangeEvent;

import io.github.takuiash.template.bukkit.utils.message.lang.Language;
import io.github.takuiash.template.bukkit.utils.plugin.JavaPluginBase;

public class LanguageListener implements Listener {

	private JavaPluginBase plugin;
	
	public LanguageListener(JavaPluginBase plugin) {
		this.plugin = plugin;
	}
	
	public void onPlayerJoin(PlayerLocaleChangeEvent event) {
		if(!plugin.getLanguageManager().isAutoUpdate(event.getPlayer())) 
			return;
		
		Language language = Language.valueOf(event.getLocale());
		
		if(language == null || !plugin.getMessageManager().containsMessageFile(language))
			language = plugin.getLanguageManager().getDefaultLanguage();
				
		plugin.getLanguageManager().setLanguage(event.getPlayer(), language);
	}
	
}
