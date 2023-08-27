package io.github.takuiash.template.bukkit.utils.storage;

import java.io.File;

import io.github.takuiash.template.bukkit.utils.plugin.JavaPluginBase;
import io.github.takuiash.template.core.utils.storage.StorageType;
import io.github.takuiash.template.core.utils.storage.sql.SqlCredentials;

public class StorageConfig {

	private JavaPluginBase plugin;
	
	private StorageType type;
	
	private SqlCredentials credentials;
	private File file;
	
	private boolean useFile;
	
	public StorageConfig(JavaPluginBase plugin) {
		this.plugin = plugin;
		
		this.load();
	}
	
	private void load() {
		this.type = StorageType.parse(plugin.getConfig().getString("storage.type"));
		
		if(type == null) 
			throw new NullPointerException("Definition of 'storage.type' at config file is invalid.");
		
		if(type == StorageType.SQLITE) {
			if(!plugin.getConfig().contains("storage.file"))
				throw new NullPointerException("Definition of 'storage.file' can't be null with " + type.getName() + " database type");
			
			this.useFile = true;
			this.file = new File(plugin.getDataFolder(), plugin.getConfig().getString("storage.file"));
		} else {
			this.useFile = false;
			this.credentials = getCredentials();
		}
	}
	
	public StorageType getType() {
		return type;
	}
	
	public boolean useFile() {
		return useFile;
	}
	
	public File getFile() {
		return file;
	}
	
	public SqlCredentials getCredentials() {
		if(useFile = true)
			throw new IllegalArgumentException("Credentials isn't used by " + type.getName() + " database type.");
		
		if(this.credentials == null) {
			final String database = plugin.getConfig().getString("storage.database");
			final String address = plugin.getConfig().getString("storage.address");
			final int port = plugin.getConfig().getInt("storage.port");
			final String username = plugin.getConfig().getString("storage.username");
			final String password = plugin.getConfig().getString("storage.password");
			
			this.credentials = SqlCredentials.create().database(database).address(address).port(port).username(username).password(password).build();
		}
		return this.credentials;
	}
	
}
