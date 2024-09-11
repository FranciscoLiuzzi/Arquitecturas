package tp1.personas.Factories;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import tp1.personas.DAOs.*;


public class MySqlJDBCDAOFactory extends DAOFactory {
	
	//Instancia de la Factory
	private static MySqlJDBCDAOFactory instance = null;
	
	public static final String driver = "com.mysql.cj.jdbc.Driver";
	public static final String uri = "jdbc:mysql://localhost:3306/Arquitecturas";
	public static Connection conn;
	
	private MySqlJDBCDAOFactory() {
	}
	
	//Abre la conección (Videos)
	public static Connection connect() {
		if (conn != null) {
			return conn;
		} else {
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
			return conn;
		}
	}
	
	
	//Retorna la instancia
	public static MySqlJDBCDAOFactory getInstance() {
		if (instance != null) {
			return instance;
		} else {
			instance = new MySqlJDBCDAOFactory();
			return instance;
		}
	}
	
	
	//Gets de DAO
	@Override
	public FacturaDAO getFacturaDAO() {
		return new MySqlFacturaDAO(connect());
	}

	@Override
	public ProductoDAO getProductoDAO() {
		return new MySqlProductoDAO(connect());
	}

	@Override
	public ClienteDAO getClienteDAO() {
		return new MySqlClienteDAO(connect());
	}

	@Override
	public FacProDAO getFacProDAO() {
		return new MySqlFacProDAO(connect());
	}
}
