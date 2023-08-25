package io.github.takuiash.template.core.utils.storage;

import java.util.List;

import com.google.common.collect.ImmutableList;

public enum StorageType {
	
	MARIADB("MariaDB", new String[] { "mariadb" }),
	MYSQL("MySQL", new String[] { "mysql" }),
	POSTGRESQL("PostgreSQL", new String[] { "postgresql" }),
	SQLITE("SQLite", new String[] { "sqlite" });
	  
	private final String name;
	private final List<String> identifiers;
	  
	StorageType(String name, String... identifiers) {
		this.name = name;
		this.identifiers = (List<String>)ImmutableList.copyOf((String[])identifiers);
	}
	  
	public static StorageType parse(String name, StorageType def) {
		for (StorageType t : values()) {
			for (String id : t.getIdentifiers()) {
				if (id.equalsIgnoreCase(name))
					return t; 
			} 
		} 
		return def;
	}
	  
	public String getName() {
		return this.name;
	}
	  
	public List<String> getIdentifiers() {
		return this.identifiers;
	}
}