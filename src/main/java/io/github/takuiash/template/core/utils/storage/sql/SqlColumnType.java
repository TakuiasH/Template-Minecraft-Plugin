package io.github.takuiash.template.core.utils.storage.sql;

import io.github.takuiash.template.core.utils.storage.ColumnType;

public enum SqlColumnType {

	UUID(ColumnType.UUID, "VARCHAR(36)", "UUID", "UUID", "VARCHAR(36)", false, false),
	STRING(ColumnType.STRING, "VARCHAR", "VARCHAR", "VARCHAR", "VARCHAR", true, false),
	TEXT(ColumnType.TEXT, "TEXT", "TEXT", "TEXT", "TEXT", false, false),
	INT(ColumnType.INT, "INT", "INT", "INT", "INT", false, false),
	DOUBLE(ColumnType.DOUBLE, "DECIMAL", "DECIMAL", "DECIMAL", "DECIMAL", true, true),
	BOOLEAN(ColumnType.BOOLEAN, "BOOLEAN", "BOOLEAN", "BOOLEAN", "BOOLEAN", false, false),
	LONG(ColumnType.LONG, "BIGINT", "BIGINT", "BIGINT", "BIGINT", false, false),
	ENUM(ColumnType.ENUM, "VARCHAR", "VARCHAR", "VARCHAR", "VARCHAR", true, false),
	LIST(ColumnType.LIST, "TEXT", "TEXT", "TEXT", "TEXT", false, false);
	
	private ColumnType identifier;
	private String mysqlPattern;
	private String postgresqlPattern;
	private String mariadbPattern;
	private String sqlitePattern;
	private boolean sizeable;
	private boolean decimal;
	
	SqlColumnType(ColumnType identifier, String mysqlPattern, String postgresqlPattern, String mariadbPattern, String sqlitePattern, boolean sizeable, boolean decimal) {
		this.identifier = identifier;
		this.mysqlPattern = mysqlPattern;
		this.postgresqlPattern = postgresqlPattern;
		this.mariadbPattern = mariadbPattern;
		this.sqlitePattern = sqlitePattern;
		this.sizeable = sizeable;
		this.decimal = decimal;
	}
	
	public ColumnType getIdentifier() {
		return identifier;
	}
	
	public boolean isSizeable() {
		return sizeable;
	}
	
	public boolean isDecimal() {
		return decimal;
	}
	
	public String getPattern(SqlProvider provider) {
		switch (provider) {
			case MYSQL: 
				return mysqlPattern;
			case POSTGRESQL:
				return postgresqlPattern;
			case MARIADB:
				return mariadbPattern;
			case SQLITE:
				return sqlitePattern;
		default:
			throw new IllegalArgumentException("Unexpected value: " + provider);
		}
	}
	
	public static SqlColumnType fromIdentifier(ColumnType type) {
		for(SqlColumnType t : values()) {
			if(t.getIdentifier() == type)
				return t;
		}
		return null;
	}
}
