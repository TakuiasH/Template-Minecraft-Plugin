package io.github.takuiash.template.bukkit.utils.message.lang;

import java.util.UUID;

import io.github.takuiash.template.bukkit.utils.message.storage.entities.PlayerLanguageEntity;

public class LanguageData {

	private UUID id;
	private Language language;
	private boolean autoUpdate;
	
	public LanguageData(PlayerLanguageEntity entity) {
		this.id = entity.getId();
		this.language = entity.getLanguage();
		this.autoUpdate = entity.isAutoUpdate();
	}
	
	public UUID getId() {
		return id;
	}
	
	public Language getLanguage() {
		return language;
	}
	
	public boolean isAutoUpdate() {
		return autoUpdate;
	}
}
