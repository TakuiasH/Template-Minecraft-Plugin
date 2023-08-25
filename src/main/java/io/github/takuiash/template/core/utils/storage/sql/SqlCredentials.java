package io.github.takuiash.template.core.utils.storage.sql;

public class SqlCredentials {
	
	private String address;
 	private int port;
 	private String username;
 	private String password;
 	private String database;
  
 	SqlCredentials(String address, int port, String username, String password, String database) {
 		this.address = address;
 		this.port = port;
 		this.username = username;
 		this.password = password;
 		this.database = database;
 	}
 	
 	public String getAddress() {
	 	return this.address;
 	}
 	
 	public void setAddress(String address) {
 		this.address = address;
 	}
  
 	public int getPort() {
 		return this.port;
 	}
 	
 	public void setPort(int port) {
 		this.port = port;
 	}
  
 	public String getUsername() {
 		return this.username;
 	}
 	
 	public void setUsername(String username) {
 		this.username = username;
 	}
  
 	public String getPassword() {
 		return this.password;
 	}
 	
 	public void setPassword(String password) {
 		this.password = password;
 	}
  
 	public String getDatabase() {
 		return this.database;
 	}
 	
 	public void setDatabase(String database) {
 		this.database = database;
 	}
 	
 	public String toString() {
 		return "SqlCredentials(address=" + getAddress() + ", port=" + getPort() + ", username=" + getUsername() + ", password=" + getPassword() + ", database=" + getDatabase() +")";
 	}
  
 	public static SqlCredentialsBuilder create() {
 		return new SqlCredentialsBuilder();
 	}
 	  
 	public static SqlCredentialsBuilder builder() {
 		return new SqlCredentialsBuilder();
 	
 	}
 	public static class SqlCredentialsBuilder {
 		  
 		private String address;
 		private int port;
 		private String username;
 		private String password;
 		private String database;
    
 		public SqlCredentialsBuilder address(String address) {
 			this.address = address;
 			return this;
 		}
    
 		public SqlCredentialsBuilder port(int port) {
 			this.port = port;
 			return this;
 		}
    
 		public SqlCredentialsBuilder username(String username) {
 			this.username = username;
 			return this;
 		}
    
 		public SqlCredentialsBuilder password(String password) {
 			this.password = password;
 			return this;
 		}
    
 		public SqlCredentialsBuilder database(String database) {
 			this.database = database;
 			return this;
 		}
    
 		public SqlCredentials build() {
 			return new SqlCredentials(this.address, this.port, this.username, this.password, this.database);
 		}
    
 		public String toString() {
 			return "SqlCredentials.SqlCredentialsBuilder(address=" + this.address + ", port=" + this.port + ", username=" + this.username + ", password=" + this.password + ", database=" + this.database + ")";
 		}
 	}

}
