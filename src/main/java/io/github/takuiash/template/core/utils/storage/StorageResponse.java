package io.github.takuiash.template.core.utils.storage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class StorageResponse {
	
	private List<Row> result = new ArrayList<Row>();
	
	public StorageResponse(List<Row> result) {
		this.result = result;
	}
	
	public Row first() {
		return result.get(0);
	}
	
	public Row last() {
		return result.get(result.size() - 1);
	}
	
	public List<Row> all() {
		return Collections.unmodifiableList(result);
	}
	
	public boolean isEmpty() {
		return result.isEmpty();
	}
	
	public Row get(int index) {
		return result.get(index);
	}
	
	public Stream<Row> stream() {
		return result.stream();
	}
	
	@Override
	public String toString() {
		String response = "DataResponse = [";
		
		for(Row value : result) {
			response += value.toString() + ", ";
		}
				
		return response.substring(0, response.length() - 2) + "]";
	}
}
