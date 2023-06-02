package br.com.checklist.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/db_checklist";
	private static final String USER_NAME = "root";
	private static final String PASSWORD = "mysql";

	public Connection recuperarConexao() {
		try {
			return DriverManager.getConnection(JDBC_URL, USER_NAME, PASSWORD);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
