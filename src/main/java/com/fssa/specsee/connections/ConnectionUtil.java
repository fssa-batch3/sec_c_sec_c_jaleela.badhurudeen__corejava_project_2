package com.fssa.specsee.connections;

import java.sql.Connection;
import java.sql.DriverManager;


public class ConnectionUtil {
	public static Connection getConnection() {
		Connection con = null;

		String url;
		String userName;
		String passWord;
		/**
		 * Check if the application is running in a CI environment
		 */
		
			/**
			 * If CI environment, use environment variables for database connection
			 */
//			url = System.getenv("DATABASE_HOST");
//			userName = System.getenv("DATABASE_USERNAME");
//			passWord = System.getenv("DATABASE_PASSWORD");
			
			url = "jdbc:mysql://localhost:3306/specsee";
			userName = "root";
			passWord = "123456";
		
		try {
			/**
			 * Load the MySQL JDBC driver class
			 */

			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, userName, passWord);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Unable to connect to the database");
		}
		return con;

	}

	public static void main(String[] args) {
		getConnection();
	}

}
