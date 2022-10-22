package context;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBContext {

	/*
	 * change/update information of database connection, do not change name of
	 * instance variables in this class
	 */
	private final String serverName = "localhost";
	private final String dbName = "QLQuyengop";
	private final String portNumber = "1433";
	private final String instance = "";// leave this one empty if sql is a single instance
	private final String userID = "sa";
	private final String password = "123";

	public Connection getConnection() throws Exception {

		String url = "jdbc:sqlserver://" + serverName + ":" + portNumber + "\\" + instance + ";databaseName=" + dbName
				+ ";encrypt=true;trustServerCertificate=true;";

		if (instance == null || instance.trim().isEmpty())
			url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";databaseName=" + dbName
					+ ";encrypt=true;trustServerCertificate=true;";
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		return DriverManager.getConnection(url, userID, password);
	}
}
