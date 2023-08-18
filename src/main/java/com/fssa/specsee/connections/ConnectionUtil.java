package com.fssa.specsee.connections;

import java.sql.Connection;
import java.sql.DriverManager;

import io.github.cdimascio.dotenv.Dotenv;

public class ConnectionUtil {
	public static Connection getConnection() {
		Connection con = null;

		String url;
		String userName;
		String passWord;
		/*
		 * Check if the application is running in a CI environment
		 */
		if (System.getenv("CI") != null) {
			/*
			 * If CI environment, use environment variables for database connection
			 */
			url = System.getenv("DATABASE_HOST");
			userName = System.getenv("DATABASE_USERNAME");
			passWord = System.getenv("DATABASE_PASSWORD");
		} else {
			Dotenv env = Dotenv.load();
			url = env.get("DATABASE_HOST");
			userName = env.get("DATABASE_USERNAME");
			passWord = env.get("DATABASE_PASSWORD");
		}

		try {
			/*
			 * Load the MySQL JDBC driver class
			 */

			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, userName, passWord);
			System.out.println("connected");
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
