package io.github.takuiash.template.bukkit.utils.message.api;

import org.bukkit.entity.Player;

import io.github.takuiash.template.api.TemplatePluginProvider;
import io.github.takuiash.template.bukkit.utils.message.lang.Language;

public interface LanguageManager {
	
	public static LanguageManager get() {
		return TemplatePluginProvider.get().getLanguageManager();
	}
	
	boolean isAutoUpdate(Player player);
	void setLanguage(Player player, Language language);
	Language getLanguage(Player player);
	Language getDefaultLanguage();

}
