package io.github.takuiash.template.core.utils.storage.sql;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.github.takuiash.template.core.utils.storage.Row;
import io.github.takuiash.template.core.utils.storage.StorageAdapter;
import io.github.takuiash.template.core.utils.storage.StorageResponse;

public class SqlConnector implements StorageAdapter {
	
	private SqlProvider provider;

	private File file;
	private SqlCredentials credentials;
	
	private Connection connection;

	public SqlConnector(SqlProvider provider, SqlCredentials credentials) {
		this.provider = provider;
		this.credentials = credentials;
	}
	
	public SqlConnector(File file) {
		this.provider = SqlProvider.SQLITE;
		this.file = file;
						
		try { 
			if(!file.exists()) {
				file.getParentFile().mkdirs();
				file.createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public SqlProvider getProvider() {
		return provider;
	}
	
	public Connection getConnection() {
		try {
			if(connection == null || connection.isClosed()) {
				if(provider == SqlProvider.SQLITE)
					connection = DriverManager.getConnection(provider.formatAddress(file.getAbsolutePath()));
				else
					connection = DriverManager.getConnection(
							provider.formatAddress(credentials),
							credentials.getUsername(),
							credentials.getPassword()
						);
			}
			return connection;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

	public void execute(String query, Object... arguments) {
		System.out.println(query);
		try {
			PreparedStatement st = getConnection().prepareStatement(query);

		    for( int i = 0; i < arguments.length;i++){
		    	//if(arguments[i] instanceof UUID)
			    ///    st.setObject(i+1, arguments[i].toString());
		    	//else
		    	st.setObject(i+1, arguments[i]);
		    }
			
			st.executeUpdate();
			st.close();		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}
	public StorageResponse executeSelect(String query, Object... arguments) {	
		System.out.println(query);
		try {
			List<Row> results = new ArrayList<Row>();
			PreparedStatement st = getConnection().prepareStatement(query);

		    for( int i = 0; i < arguments.length;i++){
		    	//if(arguments[i] instanceof UUID)
			    //    st.setObject(i+1,arguments[i].toString());
		    	//else
		    	st.setObject(i+1,arguments[i]);
		    }
			
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				 Map<String, Object> values = new HashMap<String, Object>();
				for (int y = 1; y <= rs.getMetaData().getColumnCount(); y++) 
					values.put(rs.getMetaData().getColumnLabel(y), rs.getObject(y));
				results.add(new Row(values));
			}
			
			st.close();
			return new StorageResponse(results);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
