import java.sql.Connection;
import java.sql.SQLException;

import database.DataSource;
import database.ExecuteStatements;
import exception.CustomException;
import file.FileOperations;
import thread.ThreadMain;

public class CallerClass {

	public static void main(String[] args) {
		try {
			Connection connection = DataSource.getConnection();
			FileOperations fo = FileOperations.FileOperations(
					"C:\\\\Users\\\\e01612\\\\Desktop\\\\interview\\\\us-accidents\\\\US_Accidents_May19.csv");
			ExecuteStatements exec = ExecuteStatements.ExecuteStatements();
			ThreadMain tm = new ThreadMain();
			tm.setPool(fo.getNoOfLinesInFile());
			Boolean result = false;
			if (!exec.checkTableExists(connection, "records")) {
				String val = fo.getContentAtLine(0L);
				exec.createTable(connection, "records",val);
				DataSource.returnConnection(connection);
			}
			if (!exec.checkTableExists(connection, "lastread")) {
				String val = "thread,record";
				exec.createTable(connection,"lastread", val);
				DataSource.returnConnection(connection);
			}

			tm.Execute();
		} catch (CustomException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
