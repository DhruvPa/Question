package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExecuteStatements {

	private List<String> columns;

	private static ExecuteStatements exec = null;

	private ExecuteStatements() {
		super();
		this.columns = (new ArrayList<String>());
	}

	public static ExecuteStatements ExecuteStatements() {

		if (exec == null) {
			exec = new ExecuteStatements();
		}
		return exec;
	}

	public synchronized void createTable(Connection connection, String tableName, String var) {
		try {
			var = var.replaceAll("\\((.*?)\\)", " ");
			this.columns = (Stream.of(var.split(",")).collect(Collectors.toList()));
			Statement s = connection.createStatement();
			StringBuilder create = new StringBuilder();
			create.append("CREATE TABLE " + tableName+"(");
			String col_string = String.join(" Varchar(250),", columns);
			create.append(col_string);
			create.append(" Varchar(250));");
			s.executeQuery(create.toString());

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void insertInTable(Connection connection, String tableName, String val) {
		try {
			Statement s = connection.createStatement();
			String Query = "Insert into " + tableName +" values ('" + val.toString() +"');";
			
			Query = Query.replaceAll("(?<=,)(?=,)", "null");
			Query = Query.replaceAll(",", "','");
			s.executeQuery(Query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<String> getColumns() {
		return columns;
	}

	public void setColumns(List<String> columns) {
		this.columns = columns;
	}

	public synchronized Boolean checkTableExists(Connection connection, String tableName) {

		boolean tExists = false;
		try (ResultSet rs = connection.getMetaData().getTables(null, null, tableName, null)) {
			while (rs.next()) {
				String tName = rs.getString("TABLE_NAME");
				if (tName != null && tName.equals(tableName)) {
					tExists = true;
					break;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tExists;
	}

}
