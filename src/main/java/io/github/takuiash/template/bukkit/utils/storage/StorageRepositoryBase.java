package io.github.takuiash.template.bukkit.utils.storage;

import io.github.takuiash.template.bukkit.utils.plugin.JavaPluginBase;
import io.github.takuiash.template.core.utils.storage.StorageAdapter;
import io.github.takuiash.template.core.utils.storage.StorageModule;
import io.github.takuiash.template.core.utils.storage.StorageResponse;

public abstract class StorageRepositoryBase implements StorageAdapter {

	private final String table;
	private final StorageModule module;
	
	public StorageRepositoryBase(JavaPluginBase plugin, String tableName) {
		this.table = tableName;
		
		if(plugin.getStorageConfig().useFile())
			this.module = new StorageModule(plugin.getStorageConfig().getType(), plugin.getStorageConfig().getFile());
		else
			this.module = new StorageModule(plugin.getStorageConfig().getType(), plugin.getStorageConfig().getCredentials());	
	}
	
	public String getTable() {
		return table;
	}
	
	public void execute(String query, Object... arguments) {
		module.execute(query, arguments);
	}

	public StorageResponse executeSelect(String query, Object... arguments) {
		return module.executeSelect(query, arguments);
	}

}
