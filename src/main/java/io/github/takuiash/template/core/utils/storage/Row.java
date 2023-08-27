package io.github.takuiash.template.core.utils.storage;

import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

public class Row {
	
	private final Map<String, Object> values;
	
	public Row() {
		this.values = Collections.emptyMap();
	}
	
	public Row(Map<String, Object> values) {
		this.values = values;
	}
	
	public Set<String> getColumns() {
		return values.keySet();
	}
	
	public Object get(String column) {
		return values.get(column);
	}
	
	public Row set(String column, Object value) {
		this.values.put(column, value);
		return this;
	}
	
	public String getString(String column) {
		return get(column).toString();
	}
	
	public Integer getInt(String column) {
		try {
			return Integer.parseInt(getString(column));
		} catch (NumberFormatException e) {
			return null;
		}
	}
	
	public UUID getUUID(String column) {
		try {
			return UUID.fromString(getString(column));
		} catch (NumberFormatException e) {
			return null;
		}
	}
	
	public Long getLong(String column) {
		try {
			return Long.parseLong(getString(column));
		} catch (NumberFormatException e) {
			return null;
		}
	}
	
	public Double getDouble(String column) {
		try {
			return Double.parseDouble(getString(column));
		} catch (NumberFormatException e) {
			return null;
		}
	}
	
	public Boolean getBoolean(String column) {
		return Boolean.parseBoolean(getString(column));
	}
	
	public Map<String, Object> all() {
		return values;
	}
	
	@Override
	public String toString() {
		String row = "Row = [";
		
		for(Entry<String, Object> value : values.entrySet()) {
			row += value.getKey() + ": " + value.getValue() + ", ";
		}
				
		return row.substring(0, row.length() - 2) + "]";
	}
	
}

