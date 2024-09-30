package tp1.personas.Factories;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import tp1.personas.DAOs.PersonaDAO;
import tp1.personas.abstracts.DAOFactory;

public class MySqlJDBCDAOFactory extends DAOFactory {
	
	private static MySqlJDBCDAOFactory instance = null;
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String uri = "jdbc:mysql://localhost:3306/Arquitecturas";
	public static Connection conn;
	
	private MySqlJDBCDAOFactory() {
	}
	
	try {
		Class.forName(driver).getDeclaredConstructor().newInstance();
	} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
			| NoSuchMethodException | SecurityException | ClassNotFoundException e) {
		e.printStackTrace();
			System.exit(1);
	}
	
	try {
		Connection conn = DriverManager.getConnection(uri, "root", "naranja");
		conn.setAutoCommit(false);
		conn.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	@Override
	public PersonaDAO getPersonaDAO() {
		// TODO Auto-generated method stub
		return null;
	}

}