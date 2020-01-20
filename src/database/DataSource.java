package database;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {

	static JdbcConnectionPool pool = new JdbcConnectionPool();
	private static Connection conn = null;

	private static Connection getConnection() throws ClassNotFoundException, SQLException {
		Connection connection = pool.getConnectionFromPool();
		return connection;
	}
	
	private DataSource() {
		super();
	}

	public static Connection Connection() {
		
		if(conn == null) {
			try {
				conn = DataSource.getConnection();
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			} 
		}
		
		return conn;
	}

	public static void returnConnection(Connection connection) {
		pool.returnConnectionToPool(connection);
	}
}
