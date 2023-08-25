package io.github.takuiash.template.core.utils.storage;

public interface StorageAdapter<ID, ENTITY extends StorageEntity<ID>> {

	boolean exists(ID primaryKey);
	boolean exists(String fieldName, Object fieldValue);
	
	StorageResponse find(ID primaryKey);
	StorageResponse find(String fieldName, Object fieldValue);
	
	void delete(ID primaryKey);
	
	void save(StorageEntity<ID> entity);
}
