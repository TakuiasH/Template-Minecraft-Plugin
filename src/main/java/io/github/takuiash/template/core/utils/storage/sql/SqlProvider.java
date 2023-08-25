package io.github.takuiash.template.core.utils.storage.sql;

import io.github.takuiash.template.core.utils.storage.StorageType;

public enum SqlProvider {
	
	MYSQL(StorageType.MYSQL, "jdbc:mysql://"),
	MARIADB(StorageType.MARIADB, "jdbc:mariadb://"),
	POSTGRESQL(StorageType.POSTGRESQL, "jdbc:postgresql://"),
	SQLITE(StorageType.SQLITE, "jdbc:sqlite:");
    
	private StorageType type;
	private String jdbcString;
  
	SqlProvider(StorageType type, String jdbcString) {
		this.type = type;
		this.jdbcString = jdbcString;
	}
  
	public static SqlProvider fromType(StorageType type) {
		for(SqlProvider t : values()) {
			if(t.type == type)
				return t;
		}
		return null;
	}
	
	public String formatAddress(String fileName) {
		return this.jdbcString + fileName;
	}
	
	public String formatAddress(SqlCredentials credentials) {
		return this.jdbcString + credentials.getAddress() + ":" + credentials.getPort() + "/" + credentials.getDatabase() + "?useSSL=false";
	}
}
