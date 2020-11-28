package com.rossettimonicadigiorgio.winestoremanagementv2.backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {
	private static final String DBURL = "jdbc:mysql://localhost:3306/winestoremanagement?";
	private static final String DBARGS = "createDatabaseIfNotExist=true&serverTimezone=UTC";
	private static final String DBUNAME = "user";
	private static final String DBPWD = "password.123";
	
	/**
	 * Establish a connection to mySQL server.
	 * 
	 * @return the created SQL Connection
	 */
	public static Connection establishConnection() {
		try {
			return DriverManager.getConnection(DBURL + DBARGS, DBUNAME, DBPWD);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
}
