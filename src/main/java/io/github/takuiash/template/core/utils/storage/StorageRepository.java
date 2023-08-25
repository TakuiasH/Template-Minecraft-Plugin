package io.github.takuiash.template.core.utils.storage;

import io.github.takuiash.template.core.utils.storage.sql.SqlAdapter;
import io.github.takuiash.template.core.utils.storage.sql.SqlConnector;
import io.github.takuiash.template.core.utils.storage.sql.SqlCredentials;
import io.github.takuiash.template.core.utils.storage.sql.SqlProvider;

public class StorageRepository<ID, ENTITY extends StorageEntity<ID>> implements StorageAdapter<ID, StorageEntity<ID>> {

	private StorageAdapter<ID, ENTITY> adapter;
	
	public StorageRepository(StorageType type, SqlCredentials credentials, Class<ENTITY> entityClass) {
		if(type == StorageType.SQLITE)
			throw new IllegalArgumentException("this storage type does not use credentials. Use file path");
		else {
			adapter = new SqlAdapter<ID, ENTITY>(new SqlConnector(SqlProvider.fromType(type), credentials), entityClass);
		}
	}
	
	public StorageRepository(StorageType type, String filePath, Class<ENTITY> entityClass) {
		if(type == StorageType.MARIADB || type == StorageType.MYSQL || type == StorageType.POSTGRESQL)
			throw new IllegalArgumentException("this storage type does not use file path. Use credentials");
		else {
			if(type == StorageType.SQLITE) 
				adapter = new SqlAdapter<ID, ENTITY>(new SqlConnector(filePath), entityClass);
		}
	}

	public boolean exists(ID primaryKey) {
		return adapter.exists(primaryKey);
	}

	public boolean exists(String fieldName, Object fieldValue) {
		return adapter.exists(fieldName, fieldValue);
	}

	public StorageResponse find(ID primaryKey) {
		return adapter.find(primaryKey);
	}

	public StorageResponse find(String fieldName, Object fieldValue) {
		return adapter.find(fieldName, fieldValue);
	}

	public void delete(ID primaryKey) {
		adapter.delete(primaryKey);
	}

	public void save(StorageEntity<ID> entity) {
		adapter.save(entity);
	}
	
}
