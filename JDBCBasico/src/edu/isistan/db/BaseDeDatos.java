package edu.isistan.db;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class BaseDeDatos {
	
	public static void main(String[] args) {
		String driver = "org.apache.derby.jdbc.EmbeddedDriver";
		
		try {
			Class.forName(driver).getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			e.printStackTrace();
				System.exit(1);
		}
		
		String uri = "jdbc:derby:MyDerbyDb;create=true";
		
		try {
			Connection conn = DriverManager.getConnection(uri);
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
