package io.github.takuiash.template.core.utils.storage;

import java.io.File;

import io.github.takuiash.template.core.utils.storage.sql.SqlConnector;
import io.github.takuiash.template.core.utils.storage.sql.SqlCredentials;
import io.github.takuiash.template.core.utils.storage.sql.SqlProvider;

public class StorageModule implements StorageAdapter {

	private final StorageAdapter adapter;
	
	public StorageModule(StorageType type, SqlCredentials credentials) {		
		if(type == StorageType.SQLITE)
			throw new IllegalArgumentException("This storage type does not use credentials. Use file path");
		else {
			adapter = new SqlConnector(SqlProvider.fromType(type), credentials);
		}
	}
	
	public StorageModule(StorageType type, File file) {
		if(type == StorageType.MARIADB || type == StorageType.MYSQL || type == StorageType.POSTGRESQL)
			throw new IllegalArgumentException("This storage type does not use file path. Use credentials");
		else {
			adapter = new SqlConnector(file);
		}
	}

	public void execute(String query, Object... arguments) {
		this.adapter.execute(query, arguments);
	}
	
	public StorageResponse executeSelect(String query, Object... arguments) {
		return this.adapter.executeSelect(query, arguments);
	}
	
}
