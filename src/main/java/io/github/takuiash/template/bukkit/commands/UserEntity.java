package io.github.takuiash.template.bukkit.commands;

import java.util.UUID;

import io.github.takuiash.template.core.utils.storage.Column;
import io.github.takuiash.template.core.utils.storage.ColumnType;
import io.github.takuiash.template.core.utils.storage.Entity;
import io.github.takuiash.template.core.utils.storage.StorageEntity;

@Entity(table = "users")
public class UserEntity extends StorageEntity<UUID> {

	@Column(type = ColumnType.UUID, primary = true)
	private UUID id;
	
	@Column(type = ColumnType.STRING, unique = true)
	private String username;
	
	public UserEntity() {
		this.id = UUID.randomUUID();
	}

	public UUID getId() {
		return id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
}
