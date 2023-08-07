package com.fssa.specsee.connections;

import java.sql.Connection;
import java.sql.DriverManager;

import io.github.cdimascio.dotenv.Dotenv;

public class ConnectionUtil {
	public static Connection getConnection() {
        Connection con = null;

        String url, userName, passWord;

        if (System.getenv("CI") != null) {
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
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, userName, passWord);
            System.out.println("connected");
        } catch (Exception e) {
            throw new RuntimeException("Unable to connect to the database");
        }
        return con;
        
    }
	
}
