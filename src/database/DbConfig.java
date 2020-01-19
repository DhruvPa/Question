package database;

public class DbConfig {

	public String DB_USER_NAME ;
	  
	 public String DB_PASSWORD ;
	  
	 public String DB_URL;
	  
	 public String DB_DRIVER;
	  
	 public Integer DB_MAX_CONNECTIONS;
	  
	 public DbConfig(){
	  init();
	 }
	  
	 private static DbConfig configuration = new DbConfig();
	  
	 public static DbConfig getInstance(){ 
	  return configuration;
	 }
	  
	 private void init() {
	  DB_USER_NAME = "dbuser_root";
	  DB_PASSWORD = "password";
	  DB_URL = "jdbc:mariadb://localhost:3306/data";
	  DB_DRIVER = "org.mariadb.jdbc.Driver";
	  DB_MAX_CONNECTIONS = 40;
	 }
	}

