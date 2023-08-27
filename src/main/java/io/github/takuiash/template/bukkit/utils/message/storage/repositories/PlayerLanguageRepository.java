package io.github.takuiash.template.bukkit.utils.message.storage.repositories;

import java.util.UUID;

import io.github.takuiash.template.bukkit.utils.message.storage.entities.PlayerLanguageEntity;
import io.github.takuiash.template.bukkit.utils.plugin.JavaPluginBase;
import io.github.takuiash.template.bukkit.utils.storage.StorageRepositoryBase;
import io.github.takuiash.template.core.utils.storage.StorageResponse;

public class PlayerLanguageRepository extends StorageRepositoryBase {

	public PlayerLanguageRepository(JavaPluginBase plugin) {
		super(plugin, "tk_player_languages");
		
		execute("CREATE TABLE IF NOT EXISTS " + getTable() + "("
				
			+ "id VARCHAR(36), "
			+ "language VARCHAR(12) NOT NULL, "
			+ "auto_update BOOLEAN, "

			+ "CONSTRAINT PK_" + getTable() + " PRIMARY KEY (id)"
			
		+ ");");
	}
		
	public boolean exists(UUID id) {
		return !executeSelect("SELECT id FROM " + getTable() + " WHERE id=?;", id).isEmpty();
	}
	
	public void delete(UUID id) {
		execute("DELETE FROM " + getTable() + " WHERE id=?;", id);
	}
	
	public PlayerLanguageEntity find(UUID id) {
		final StorageResponse response = this.executeSelect("SELECT * FROM " + getTable() + " WHERE id=?;", id);
		
		if(response == null || response.isEmpty())
			return null;		
		
		return new PlayerLanguageEntity(response.first());
	}
	
	public void insert(PlayerLanguageEntity entity) {
		execute("INSERT INTO " + getTable() + " (id, language, auto_update) VALUES (?, ?, ?);", 
			entity.getId(), 
			entity.getLanguage(), 
			entity.isAutoUpdate()
		);
		
	}
	
	public void update(PlayerLanguageEntity entity) {
		execute("UPDATE " + getTable() + " SET language=? auto_update=? WHERE id=?;", 
			entity.getLanguage(), 
			entity.isAutoUpdate(), 
			entity.getId()
		);
	}
	
	public boolean isAutoUpdate(UUID id) {
		final StorageResponse response = this.executeSelect("SELECT auto_update FROM " + getTable() + " WHERE id=?;", id);
		
		if(response == null || response.isEmpty())
			return true;		
		
		return response.first().getInt("auto_update") == 0 ? false : true;
	}
	
}
