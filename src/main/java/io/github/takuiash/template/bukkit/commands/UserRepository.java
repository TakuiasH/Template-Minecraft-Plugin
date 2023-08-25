package io.github.takuiash.template.bukkit.commands;

import java.util.UUID;

import io.github.takuiash.template.core.utils.storage.StorageRepository;
import io.github.takuiash.template.core.utils.storage.StorageType;

public class UserRepository extends StorageRepository<UUID, UserEntity> {

	public UserRepository() {
		super(StorageType.SQLITE, "test.db", UserEntity.class);
	}

}
