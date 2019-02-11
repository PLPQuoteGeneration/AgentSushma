package com.cg.qgs.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.cg.qgs.exception.QGSException;

public class JdbcUtility {
	private static Connection connection = null;

	public static Connection getConnection() throws QGSException {

		File file = null;
		file = new File("resources/jdbc.properties");
		FileInputStream fileInputStream = null;

		try {
			fileInputStream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			System.err.println(e.getStackTrace());
		}

		Properties properties = new Properties();
		try {
			properties.load(fileInputStream);
			
			String driver = properties.getProperty("db.driver");
			String url = properties.getProperty("db.url");
			String username = properties.getProperty("db.user");
			String password = properties.getProperty("db.pass");
			
			Class.forName(driver);
			
			connection = DriverManager.getConnection(url, username, password);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return connection;
	}

}
