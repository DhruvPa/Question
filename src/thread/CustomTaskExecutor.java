package thread;

import java.sql.Connection;
import java.sql.SQLException;

import database.DataSource;
import database.ExecuteStatements;
import exception.CustomException;
import file.FileOperations;

public class CustomTaskExecutor implements Runnable {

	private Long position;
	private FileOperations fo;
	private Connection connection;
	private ExecuteStatements exec;

	public CustomTaskExecutor(Long position) {
		this.position = position;
	}

	@Override
	public void run() {
		try {
			//System.out.println("CustomTaskExecutor Started by position :" + position);
			executeTask();
			//System.out.println("CustomTaskExecutor Started by position :" + position);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (CustomException e) {
			e.printStackTrace();
		}
	}

	private synchronized void executeTask() throws ClassNotFoundException, SQLException, CustomException {
		
		fo = FileOperations.FileOperations(
				"C:\\\\Users\\\\e01612\\\\Desktop\\\\interview\\\\us-accidents\\\\US_Accidents_May19.csv");
		connection = DataSource.Connection();
		exec = ExecuteStatements.ExecuteStatements();

		try {

			exec.insertInTable(connection, "records", fo.getContentAtLine(this.position));
			exec.insertInTable(connection, "lastread",
					Thread.currentThread().getName() + "," + this.position.toString());
			DataSource.returnConnection(connection);
		} finally {
			DataSource.returnConnection(connection);
		}
	}
}