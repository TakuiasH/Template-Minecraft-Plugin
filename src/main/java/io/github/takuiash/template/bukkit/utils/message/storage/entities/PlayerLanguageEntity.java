package io.github.takuiash.template.bukkit.utils.message.storage.entities;

import java.util.UUID;

import io.github.takuiash.template.bukkit.utils.message.lang.Language;
import io.github.takuiash.template.core.utils.storage.Row;

public class PlayerLanguageEntity {

	private UUID id;
	private Language language;
	private boolean autoUpdate;
		
	public PlayerLanguageEntity(UUID id, Language language) {
		this(id, language, true);
	}
	
	public PlayerLanguageEntity(UUID id, Language language, boolean autoUpdate) {
		this.id = id;
		this.language = language;
		this.autoUpdate = autoUpdate;
	}
	
	public PlayerLanguageEntity(Row row) {
		this.id = row.getUUID("id");
		this.language = Language.valueOf(row.getString("language"));
		this.autoUpdate = row.getInt("auto_update") == 0 ? false : true;
	}
	
	public UUID getId() {
		return id;
	}
	
	public void setId(UUID id) {
		this.id = id;
	}
	
	public Language getLanguage() {
		return language;
	}
	
	public void setLanguage(Language language) {
		this.language = language;
	}
	
	public boolean isAutoUpdate() {
		return autoUpdate;
	}
	
	public void setAutoUpdate(boolean autoUpdate) {
		this.autoUpdate = autoUpdate;
	}
	
}
