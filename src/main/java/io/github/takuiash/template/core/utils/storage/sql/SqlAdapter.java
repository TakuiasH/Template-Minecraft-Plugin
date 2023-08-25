package io.github.takuiash.template.core.utils.storage.sql;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang.reflect.FieldUtils;

import io.github.takuiash.template.core.utils.storage.Column;
import io.github.takuiash.template.core.utils.storage.Entity;
import io.github.takuiash.template.core.utils.storage.StorageAdapter;
import io.github.takuiash.template.core.utils.storage.StorageEntity;
import io.github.takuiash.template.core.utils.storage.StorageException;
import io.github.takuiash.template.core.utils.storage.StorageResponse;

public class SqlAdapter<ID, ENTITY extends StorageEntity<ID>> implements StorageAdapter<ID, ENTITY> {

	private final SqlConnector connector;
	
	private String tableName;
		
	public SqlAdapter(SqlConnector connector, Class<ENTITY> entityClass) {
		if(!entityClass.isAnnotationPresent(Entity.class))
			throw new StorageException("entity class needs to be annotated with @Entity annotation");
		
		this.connector = connector;
		this.tableName = entityClass.getAnnotation(Entity.class).table();
		
		connector.executeQuery("DROP TABLE " + tableName);
		
		try {
			StorageEntity<ID> entity = entityClass.getConstructor().newInstance();
			
			String columnsString = "";
			String primaryKeyString = "";

			for(Field field : entity.getClass().getDeclaredFields()) {
				if(!field.isAnnotationPresent(Column.class))
					continue;
				
				Column options = field.getAnnotation(Column.class);
				SqlColumnType type = SqlColumnType.fromIdentifier(options.type());
				String columnName = options.name().isEmpty() ? field.getName() : options.name();
				String columnOptions = type.isSizeable() ? "(" + options.size() + (type.isDecimal() ? ", " + options.decimal() : "") + ")" : "";
				columnOptions += (options.nullable() ? "" : " NOT NULL") + (options.unique() ? " UNIQUE" : "");
				columnsString += columnName + " " + type.getPattern(connector.getProvider()) + columnOptions + ", ";
				
				if(options.primary())
					primaryKeyString += columnName + ", ";
			}

			columnsString += "CONSTRAINT PK_" + tableName + " PRIMARY KEY (" + primaryKeyString.substring(0, primaryKeyString.length() - 2) + ")";
			connector.executeQuery("CREATE TABLE IF NOT EXISTS " + tableName + " (" + columnsString + ");");
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}

	}
	
	public boolean exists(ID primaryKey) {
		return !connector.executeSelect("SELECT id FROM " + tableName + " WHERE id=?;", primaryKey).isEmpty();
	}
	
	public boolean exists(String fieldName, Object fieldValue) {
		return !connector.executeSelect("SELECT id FROM " + tableName + " WHERE " + fieldName + "=?;", fieldValue).isEmpty();
	}

	public StorageResponse find(ID primaryKey) {
		return connector.executeSelect("SELECT * FROM " + tableName + " WHERE id=?;", primaryKey);
	}
	
	public StorageResponse find(String fieldName, Object fieldValue) {
		return connector.executeSelect("SELECT * FROM " + tableName + " WHERE " + fieldName + "=?;", fieldValue);
	}

	public void delete(ID primaryKey) {
		connector.executeQuery("DELETE FROM " + tableName + " WHERE id=?;", primaryKey);
	}
	
	public void save(StorageEntity<ID> entity) {
		List<Object> arguments = new ArrayList<>();

		if(exists(entity.getId())) {
			String set = "";
			for(Field field : entity.getClass().getDeclaredFields()) {
				if(!field.isAnnotationPresent(Column.class) || field.getName().equals("id")) continue;
				
				try {
					String fieldName = field.getAnnotation(Column.class).name();
					Object fieldValue = FieldUtils.readField(entity, field.getName(), true);
					
					set += (fieldName.isEmpty() ? field.getName() : fieldName) + "=?, ";
					
					if(fieldValue != null && fieldValue.getClass().isEnum())
						fieldValue = fieldValue.toString();
					
					if(fieldValue instanceof List) {
						if(((List<?>) fieldValue).contains("")) ((List<?>) fieldValue).remove("");
						fieldValue = ((List<?>) fieldValue).stream().map(Object::toString).collect(Collectors.joining("</n>"));
					}

					arguments.add(fieldValue);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
			arguments.add(entity.getId());
			connector.executeQuery("UPDATE " + tableName + " SET " + set.substring(0, set.length() - 2) + " WHERE id=?;", arguments.toArray());
		}else {
			String keys = "";
			String values = "";
			for(Field field : entity.getClass().getDeclaredFields()) {
				if(!field.isAnnotationPresent(Column.class)) continue;
				
				try {
					String fieldName = field.getAnnotation(Column.class).name();
					Object fieldValue = FieldUtils.readField(entity, field.getName(), true);
				
					keys += (fieldName.isEmpty() ? field.getName() : fieldName) + ", ";
					values += "?, "; 
					
					if(fieldValue != null && fieldValue.getClass().isEnum())
						fieldValue = fieldValue.toString();
					
					if(fieldValue instanceof List) 
						fieldValue = ((List<?>) fieldValue).stream().map(Object::toString).collect(Collectors.joining("</n>"));

					arguments.add(fieldValue);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
			connector.executeQuery("INSERT INTO " + tableName + "(" + keys.substring(0, keys.length() - 2) + ") VALUES (" + values.substring(0, values.length() - 2) + ");", arguments.toArray());
		}

	}


}
