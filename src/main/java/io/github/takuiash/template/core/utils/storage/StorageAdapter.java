package io.github.takuiash.template.core.utils.storage;

public interface StorageAdapter {
	
	void execute(String query, Object... arguments);
	StorageResponse executeSelect(String query, Object... arguments);
	
}
