package com.fssa.specsee.connections;

import java.sql.Connection;
import java.sql.DriverManager;

public class conectionUtil {
	public static Connection getConnection() {

		Connection con = null;

		String url = "jdbc:mysql://localhost:3306/specsee";
		String userName = "root";
		String passWord = "123456";
		try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, userName, passWord);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Unable to connect to the database");
		}
		return con;
	}

}
