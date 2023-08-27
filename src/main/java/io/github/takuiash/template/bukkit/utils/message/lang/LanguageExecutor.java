package io.github.takuiash.template.bukkit.utils.message.lang;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import io.github.takuiash.template.bukkit.utils.message.api.LanguageManager;
import io.github.takuiash.template.bukkit.utils.message.events.PlayerLanguageChangeEvent;
import io.github.takuiash.template.bukkit.utils.message.storage.entities.PlayerLanguageEntity;
import io.github.takuiash.template.bukkit.utils.message.storage.repositories.PlayerLanguageRepository;
import io.github.takuiash.template.bukkit.utils.plugin.JavaPluginBase;

public class LanguageExecutor implements LanguageManager {
	
	private final JavaPluginBase plugin;
	private final PlayerLanguageRepository langRepo;
	
	public LanguageExecutor(JavaPluginBase plugin) {
		this.plugin = plugin;
		this.langRepo = new PlayerLanguageRepository(plugin);
	}

	public boolean isAutoUpdate(Player player) {
		return langRepo.isAutoUpdate(player.getUniqueId());
	}
	
	public void setLanguage(Player player, Language language) {
		PlayerLanguageEntity entity = langRepo.find(player.getUniqueId());
		Language oldLanguage = null;

		if(entity == null) {			
			langRepo.insert(new PlayerLanguageEntity(player.getUniqueId(), language));
		} else {
			oldLanguage = entity.getLanguage();
			entity.setLanguage(language);
			
			langRepo.update(entity);
		}
		
		Bukkit.getPluginManager().callEvent(new PlayerLanguageChangeEvent(player, language, oldLanguage));

	}
	
	public Language getLanguage(Player player) {
		final PlayerLanguageEntity entity = langRepo.find(player.getUniqueId());
		
		if(entity == null) {
			Language language = Language.valueOf(player.getLocale());
			
			if(language == null)
				language = this.getDefaultLanguage();
			
			setLanguage(player, language);
			return language;
		}
		
		return entity.getLanguage();
	}
	
	public Language getDefaultLanguage() {
		return plugin.getConfig().getDefaultLanguage();
	}
}
